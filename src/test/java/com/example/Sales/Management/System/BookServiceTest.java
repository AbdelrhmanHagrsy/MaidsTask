package com.example.Sales.Management.System;

import com.example.Sales.Management.System.dto.BookDto;
import com.example.Sales.Management.System.entity.Book;
import com.example.Sales.Management.System.exception.RecordAlreadyExistException;
import com.example.Sales.Management.System.mapper.BookMapper;
import com.example.Sales.Management.System.repository.BookRepository;
import com.example.Sales.Management.System.repository.BorrowingRecordRepository;
import com.example.Sales.Management.System.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.context.MessageSource;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Optional;

import static org.mockito.Mockito.when;

@AutoConfigureMockMvc
class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private BorrowingRecordRepository borrowingRecordRepository;

    @Mock
    private BookMapper bookMapper;

    @Mock
    private MessageSource messageSource;

    @InjectMocks
    private BookService bookService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
    }



    @Test
    void testAddBook() {
        // Mock data
        BookDto bookDto = new BookDto();
        bookDto.setTitle("Test Book");
        Book book = new Book();
        when(bookRepository.findByTitle(bookDto.getTitle())).thenReturn(Optional.empty());
        when(bookMapper.toEntity(bookDto)).thenReturn(book);
        when(bookRepository.save(book)).thenReturn(book);
        when(bookMapper.toDto(book)).thenReturn(bookDto);

        // Test
        BookDto result = bookService.addBook(bookDto);

        // Verify
        assertEquals(bookDto.getTitle(), result.getTitle());
    }

    @Test
    void testGetBookById() {
        // Mock data
        Long bookId = 1L;
        Book book = new Book();
        book.setId(bookId);
        BookDto bookDto = new BookDto();
        bookDto.setId(1L);

        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));
        when(bookMapper.toDto(book)).thenReturn(bookDto);
        // Test
        BookDto result = bookService.getBookById(bookId);

        // Verify book not exist
        assertEquals(book.getId(), result.getId());
    }


    @Test
    void testAddBook_RecordAlreadyExists() {
        BookDto bookDto = new BookDto();
        bookDto.setTitle("Test Book");
        when(bookRepository.findByTitle(bookDto.getTitle())).thenReturn(Optional.of(new Book()));

        // Test & Verify
        assertThrows(RecordAlreadyExistException.class, () -> bookService.addBook(bookDto));
    }

}