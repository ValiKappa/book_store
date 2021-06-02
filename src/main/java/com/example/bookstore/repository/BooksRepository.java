package com.example.bookstore.repository;

import com.example.bookstore.model.Books;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BooksRepository extends JpaRepository<Books, Long> {

    Optional<Books> findByName(String name);
    void deleteByName(String name);

}
