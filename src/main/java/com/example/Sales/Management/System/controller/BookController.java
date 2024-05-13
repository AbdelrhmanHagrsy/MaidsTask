package com.example.Sales.Management.System.controller;

import com.example.Sales.Management.System.dto.BookDto;
import com.example.Sales.Management.System.entity.Book;
import com.example.Sales.Management.System.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
        @Autowired
        private BookService bookService;
        
        @GetMapping
        public ResponseEntity<List<BookDto>> getAllBooks() {
            List<BookDto> books = bookService.getAllBooks();
            return ResponseEntity.ok(books);
        }
        
        @GetMapping("/{id}")
        public ResponseEntity<BookDto> getBookById(@PathVariable Long id) {
            BookDto book = bookService.getBookById(id);
            return ResponseEntity.ok(book);
        }
        
        @PostMapping("/add")
        public ResponseEntity<BookDto> addBook(@RequestBody BookDto bookDto) {
            BookDto newBook = bookService.addBook(bookDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(newBook);
        }
        
        @PutMapping("/{id}")
        public ResponseEntity<BookDto> updateBook(@PathVariable Long id, @RequestBody BookDto bookDto) {
            BookDto updatedBook = bookService.updateBook(id, bookDto);
            return ResponseEntity.ok(updatedBook);
            
        }
        
        @DeleteMapping("/{id}")
        public ResponseEntity<Book> deleteBook(@PathVariable Long id) {
            Book book = bookService.deleteBook(id);
            return ResponseEntity.ok(book);

        }
}
