package com.example.bookstore.repository;

import com.example.bookstore.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    Optional<Author> findByAuthorName(String authorName);
    Optional<Author> findById(Long id);
    void deleteByAuthorName(String authorName);
}
