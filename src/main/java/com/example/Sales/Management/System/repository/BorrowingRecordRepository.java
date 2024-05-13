package com.example.Sales.Management.System.repository;

import com.example.Sales.Management.System.entity.Book;
import com.example.Sales.Management.System.entity.BorrowingRecord;
import com.example.Sales.Management.System.entity.Patron;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecord,Long> {

    Optional<BorrowingRecord> findByBookAndPatron(Book book, Patron patron);
    void deleteAllByBook(Book book);
    void deleteAllByPatron(Patron patron);
}
