package com.example.bookstore.controller;

import com.example.bookstore.model.Author;
import com.example.bookstore.model.Books;
import com.example.bookstore.service.AuthorService;
import com.example.bookstore.service.BooksService;
import com.example.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(value = "/books")
public class BooksController {

        private AuthorService authorService;
        private UserService userService;
        private BooksService booksService;

    @Autowired
    public BooksController(AuthorService authorService, UserService userService, BooksService booksService) {
        this.authorService = authorService;
        this.booksService = booksService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<Set<Books>> findAll () {
        return ResponseEntity.ok(booksService.returnAllBooks());
    }

    @PostMapping
    public ResponseEntity<HttpStatus> save(@RequestBody Books books, Author author) {
        booksService.save(books, author);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteBook(@PathVariable String name) {
        booksService.deleteBook(name);
        return ResponseEntity.ok().build();
    }
}
