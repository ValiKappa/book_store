package com.example.bookstore.controller;

import com.example.bookstore.model.Author;
import com.example.bookstore.service.AuthorService;
import com.example.bookstore.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(value = "/authors")
public class AuthorController {

    private AuthorService authorService;
    private BooksService booksService;

    @Autowired
    public AuthorController(AuthorService authorService, BooksService booksService) {
        this.authorService = authorService;
        this.booksService = booksService;
    }

    @GetMapping
    public ResponseEntity<Set<Author>> findAll () {
        return ResponseEntity.ok(authorService.findAll());
    }

    @PostMapping
    public ResponseEntity<HttpStatus> save(@RequestBody Author author) {
        authorService.save(author);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Author> update(@PathVariable Long id, @RequestBody Author author) {
       return ResponseEntity.ok(authorService.update(id, author));
    }

    @DeleteMapping(value = "/{authorname}")
    public ResponseEntity<HttpStatus> deleteByAuthorName(@PathVariable String authorName) {
        authorService.deleteByAuthorName(authorName);
        return ResponseEntity.ok().build();
    }
}
