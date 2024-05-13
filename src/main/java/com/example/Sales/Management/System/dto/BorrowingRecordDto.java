package com.example.Sales.Management.System.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;


public class BorrowingRecordDto {
    
        @JsonProperty("borrowing_record_id")
        public Long id;
        @JsonProperty("book")
        public BookDto book;
    
        @JsonProperty("patron")
        public PatronDto patron;
    
        @JsonProperty("borrowing_date")
        private Date borrowingDate;
    
        @JsonProperty("return_date")
        private Date returnDate;

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public BookDto getBook() {
                return book;
        }

        public void setBook(BookDto book) {
                this.book = book;
        }

        public PatronDto getPatron() {
                return patron;
        }

        public void setPatron(PatronDto patron) {
                this.patron = patron;
        }

        public Date getBorrowingDate() {
                return borrowingDate;
        }

        public void setBorrowingDate(Date borrowingDate) {
                this.borrowingDate = borrowingDate;
        }

        public Date getReturnDate() {
                return returnDate;
        }

        public void setReturnDate(Date returnDate) {
                this.returnDate = returnDate;
        }
}
