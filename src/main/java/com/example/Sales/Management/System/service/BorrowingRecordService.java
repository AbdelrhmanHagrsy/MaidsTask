package com.example.Sales.Management.System.service;

import com.example.Sales.Management.System.dto.BorrowingRecordDto;
import com.example.Sales.Management.System.entity.Book;
import com.example.Sales.Management.System.entity.BorrowingRecord;
import com.example.Sales.Management.System.entity.Patron;
import com.example.Sales.Management.System.exception.RecordNotFoundException;
import com.example.Sales.Management.System.mapper.BorrowingRecordMapper;
import com.example.Sales.Management.System.repository.BorrowingRecordRepository;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;


@Service
public class BorrowingRecordService {
    
    private final BookService bookService;
    private final PatronService patronService;
    private final BorrowingRecordRepository borrowingRecordRepository;
    private final BorrowingRecordMapper borrowingRecordMapper;
    private final MessageSource messageSource;
    
    public BorrowingRecordService(BookService bookService, PatronService patronService, BorrowingRecordRepository borrowingRecordRepository, BorrowingRecordMapper borrowingRecordMapper, MessageSource messageSource) {
    this.bookService = bookService;
    this.patronService = patronService;
    this.borrowingRecordRepository = borrowingRecordRepository;
    this.borrowingRecordMapper = borrowingRecordMapper;
    this.messageSource = messageSource;
    }
    
    public BorrowingRecordDto borrowBook(Long bookId, Long patronId) {
        try{
            
            Book book  = bookService.getBook(bookId);
            Patron patron = patronService.getPatron(patronId);
            BorrowingRecord borrowingRecord = new BorrowingRecord();
            borrowingRecord.setBook(book);
            borrowingRecord.setPatron(patron);
            borrowingRecord.setBorrowingDate(new Date()); 
            borrowingRecord.setReturnDate(null);
            borrowingRecord = borrowingRecordRepository.save(borrowingRecord);
            return borrowingRecordMapper.toDto(borrowingRecord);
        }catch (Exception e){
            throw  new RuntimeException(e.getMessage());
        }
       
    }

    public BorrowingRecordDto returnBook(Long bookId, Long patronId) {
        Book book  = bookService.getBook(bookId);
        Patron patron = patronService.getPatron(patronId);
        Optional<BorrowingRecord> borrowingRecord =  borrowingRecordRepository.findByBookAndPatron(book,patron);
        if(borrowingRecord.isEmpty())
            throw new RecordNotFoundException(messageSource.getMessage("record.notfoundborrow", new Object[]{bookId,patronId}, LocaleContextHolder.getLocale()));
        // update return date with current date
        borrowingRecord.get().setReturnDate(new Date());
        BorrowingRecord updatedBorrowingRecord = borrowingRecordRepository.save(borrowingRecord.get());
        return borrowingRecordMapper.toDto(updatedBorrowingRecord);
    }

}
