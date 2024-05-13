package com.example.Sales.Management.System.controller;

import com.example.Sales.Management.System.dto.PatronDto;
import com.example.Sales.Management.System.entity.Patron;
import com.example.Sales.Management.System.service.PatronService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/api/patron")
public class PatronController {
    
        @Autowired
        private PatronService patronService;
        
        @GetMapping
        public ResponseEntity<List<PatronDto>> getAllBooks() {
            List<PatronDto> patrons = patronService.getAllPatrons();
            return ResponseEntity.ok(patrons);
        }
        
        @GetMapping("/{id}")
        public ResponseEntity<PatronDto> getBookById(@PathVariable Long id) {
            PatronDto patronDto = patronService.getPatronById(id);
            return ResponseEntity.ok(patronDto);
        }
        
        @PostMapping("/add")
        public ResponseEntity<PatronDto> addBook(@RequestBody PatronDto patronDto) {
            PatronDto patron = patronService.addPatron(patronDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(patron);
        }
        
        @PutMapping("/{id}")
        public ResponseEntity<PatronDto> updateBook(@PathVariable Long id, @RequestBody PatronDto patronDto) {
            PatronDto updatedPatron = patronService.updatePatron(id,patronDto );
            return ResponseEntity.ok(updatedPatron);
            
        }
        
        @DeleteMapping("/{id}")
        public ResponseEntity<Patron> deleteBook(@PathVariable Long id) {
            Patron patron = patronService.deletePatron(id);
                return ResponseEntity.ok(patron);
    
        }
}
