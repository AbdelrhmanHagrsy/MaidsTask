package com.example.Sales.Management.System.repository;

import com.example.Sales.Management.System.entity.Patron;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface PatronRepository extends JpaRepository<Patron,Long> {
    
    Optional<Patron> findByName(String name);
}
