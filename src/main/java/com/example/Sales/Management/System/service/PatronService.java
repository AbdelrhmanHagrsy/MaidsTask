package com.example.Sales.Management.System.service;


import com.example.Sales.Management.System.dto.PatronDto;
import com.example.Sales.Management.System.entity.Patron;
import com.example.Sales.Management.System.exception.RecordAlreadyExistException;
import com.example.Sales.Management.System.exception.RecordNotFoundException;
import com.example.Sales.Management.System.mapper.PatronMapper;
import com.example.Sales.Management.System.repository.BorrowingRecordRepository;
import com.example.Sales.Management.System.repository.PatronRepository;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PatronService {
    
        private final PatronRepository  patronRepository;

        private final BorrowingRecordRepository borrowingRecordRepository;

        private final PatronMapper patronMapper ;
        private final MessageSource messageSource;


    public PatronService(PatronRepository patronRepository, BorrowingRecordRepository borrowingRecordRepository, PatronMapper patronMapper, MessageSource messageSource) {
        this.patronRepository = patronRepository;
        this.borrowingRecordRepository = borrowingRecordRepository;
        this.patronMapper = patronMapper;
        this.messageSource = messageSource;
    }

    public Patron getPatron(Long id){
            Optional<Patron> patron = patronRepository.findById(id);
            // throw exception if patron not found
            if(patron.isEmpty())
                throw new RecordNotFoundException(messageSource.getMessage("record.notfound", new Object[]{id}, LocaleContextHolder.getLocale()));
            return patron.get();
        }

        public List<PatronDto> getAllPatrons() {
                List<Patron> patrons =  patronRepository.findAll();
                return patrons.stream().map(x->patronMapper.toDto(x)).collect(Collectors.toList());
            }
                
        public PatronDto getPatronById(Long id) {
            Optional<Patron> patron = patronRepository.findById(id);
            // throw exception if patron not found
            if(patron.isEmpty())
                throw new RecordNotFoundException(messageSource.getMessage("record.notfound", new Object[]{id}, LocaleContextHolder.getLocale()));
            return patronMapper.toDto(patron.get());
        }
    
        public PatronDto addPatron(PatronDto patronDto) {
                // check if name already exist
                Optional<Patron> patron = patronRepository.findByName(patronDto.getName());
                if(patron.isPresent())
                    throw new RecordAlreadyExistException(messageSource.getMessage("record.exist", new Object[]{patronDto.getName()}, LocaleContextHolder.getLocale()));
                Patron newPatron =  patronRepository.save(patronMapper.toEntity(patronDto));
                return  patronMapper.toDto(newPatron);
        }
        
        
        public PatronDto updatePatron(Long id, PatronDto patronDto) {
            Optional<Patron> patron = patronRepository.findById(id);
            // throw exception if patron not found
            if(patron.isEmpty())
                throw new RecordNotFoundException(messageSource.getMessage("record.notfound", new Object[]{id}, LocaleContextHolder.getLocale()));
            patronDto.setId(id);
            Patron newPatron =  patronRepository.save(patronMapper.toEntity(patronDto));
            return  patronMapper.toDto(newPatron);
                    
        }
    

        @Transactional
        public Patron deletePatron(Long id) {
            Optional<Patron> patron = patronRepository.findById(id);
            // throw exception if patron not found
            if(patron.isEmpty())
                throw new RecordNotFoundException(messageSource.getMessage("record.notfound", new Object[]{id}, LocaleContextHolder.getLocale()));
            // delete all borrowing record related to patron
            borrowingRecordRepository.deleteAllByPatron(patron.get());
            //
            patronRepository.deleteById(id);
            return patron.get();
        }
}
