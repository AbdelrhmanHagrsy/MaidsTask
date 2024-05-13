package com.example.Sales.Management.System.service;

import com.example.Sales.Management.System.dto.BookDto;
import com.example.Sales.Management.System.entity.Book;
import com.example.Sales.Management.System.exception.RecordAlreadyExistException;
import com.example.Sales.Management.System.exception.RecordNotFoundException;
import com.example.Sales.Management.System.mapper.BookMapper;
import com.example.Sales.Management.System.repository.BookRepository;
import com.example.Sales.Management.System.repository.BorrowingRecordRepository;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {
    
    private final BookRepository bookRepository;
    private final BorrowingRecordRepository borrowingRecordRepository;
    private final BookMapper bookMapper;
    private final MessageSource messageSource;

    public BookService(BookRepository bookRepository, BorrowingRecordRepository borrowingRecordRepository, BookMapper bookMapper, MessageSource messageSource) {
        this.bookRepository = bookRepository;
        this.borrowingRecordRepository = borrowingRecordRepository;
        this.bookMapper = bookMapper;
        this.messageSource = messageSource;
    }

    public Book getBook(Long id){
        Optional<Book> book = bookRepository.findById(id);
        // throw exception if book not found
        if(book.isEmpty())
            throw new RecordNotFoundException(messageSource.getMessage("record.notfound", new Object[]{id}, LocaleContextHolder.getLocale()));
        return book.get();
    }
    
    public List<BookDto> getAllBooks() {
        List<Book> books =  bookRepository.findAll();
        return books.stream().map(x->bookMapper.toDto(x)).collect(Collectors.toList());
    }
    
    public BookDto getBookById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
    // throw exception if book not found
        if(book.isEmpty())
            throw new RecordNotFoundException(messageSource.getMessage("record.notfound", new Object[]{id}, LocaleContextHolder.getLocale()));
        return bookMapper.toDto(book.get());
    }

    public BookDto addBook(BookDto bookDto) {
            // check if name already exist
            Optional<Book> book = bookRepository.findByTitle(bookDto.getTitle());
            if(book.isPresent())
                throw new RecordAlreadyExistException(messageSource.getMessage("record.exist", new Object[]{bookDto.getTitle()}, LocaleContextHolder.getLocale()));
            Book newBook =  bookRepository.save(bookMapper.toEntity(bookDto));
            return  bookMapper.toDto(newBook);
    }
    
    
    public BookDto updateBook(Long id, BookDto bookDto) {

        Optional<Book> book = bookRepository.findById(id);
        // throw exception if book not found
        if(book.isEmpty())
            throw new RecordNotFoundException(messageSource.getMessage("record.notfound", new Object[]{id}, LocaleContextHolder.getLocale()));
        bookDto.setId(id);
        Book newBook =  bookRepository.save(bookMapper.toEntity(bookDto));
        return  bookMapper.toDto(newBook);
                
    }


    @Transactional
    public Book deleteBook(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        // throw exception if book not found
        if(book.isEmpty())
            throw new RecordNotFoundException(messageSource.getMessage("record.notfound", new Object[]{id}, LocaleContextHolder.getLocale()));
        // delete all borrowing record related to that book
        borrowingRecordRepository.deleteAllByBook(book.get());
        //
        bookRepository.deleteById(id);
        return book.get();
    }

}
