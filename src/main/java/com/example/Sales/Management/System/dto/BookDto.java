package com.example.Sales.Management.System.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;


public class BookDto {
        @JsonProperty("book_id")
        public Long id;
        @JsonProperty("book_title")
        public String title;
        @JsonProperty("author_name")
        public String author;
        @JsonProperty("publication_year")
        public int publicationYear;

        @JsonProperty("createdBy")
        public String createdBy;

        @JsonProperty("createdDate")
        public String createdDate;

        @JsonProperty("lastModifiedBy")
        public String lastModifiedBy;

        @JsonProperty("lastModifiedDate")
        public String  lastModifiedDate;

        @JsonProperty("isbn")
        public String isbn;

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getTitle() {
                return title;
        }

        public void setTitle(String title) {
                this.title = title;
        }

        public String getAuthor() {
                return author;
        }

        public void setAuthor(String author) {
                this.author = author;
        }

        public int getPublicationYear() {
                return publicationYear;
        }

        public void setPublicationYear(int publicationYear) {
                this.publicationYear = publicationYear;
        }

        public String getIsbn() {
                return isbn;
        }

        public void setIsbn(String isbn) {
                this.isbn = isbn;
        }

        public String getCreatedBy() {
                return createdBy;
        }

        public void setCreatedBy(String createdBy) {
                this.createdBy = createdBy;
        }

        public String getCreatedDate() {
                return createdDate;
        }

        public void setCreatedDate(String createdDate) {
                this.createdDate = createdDate;
        }

        public String getLastModifiedBy() {
                return lastModifiedBy;
        }

        public void setLastModifiedBy(String lastModifiedBy) {
                this.lastModifiedBy = lastModifiedBy;
        }

        public String getLastModifiedDate() {
                return lastModifiedDate;
        }

        public void setLastModifiedDate(String lastModifiedDate) {
                this.lastModifiedDate = lastModifiedDate;
        }
}
