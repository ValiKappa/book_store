package com.example.bookstore.controller;
import com.example.bookstore.dto.UserDto;
import com.example.bookstore.model.Books;
import com.example.bookstore.model.User;
import com.example.bookstore.service.BooksService;
import com.example.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    private UserService userService;
    private BooksService booksService;

    @Autowired
    public UserController (UserService userService, BooksService booksService) {
        this.userService = userService;
        this.booksService = booksService;
    }

    @GetMapping
    public ResponseEntity<Set<UserDto>> findAll () {
        return ResponseEntity.ok(userService.returnAllUsers());
    }

    @PostMapping()
    public ResponseEntity<HttpStatus> save(@RequestBody User user) {
        userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping(value = "/{book}{user}")
    public ResponseEntity<HttpStatus> saveBookByUser(@RequestBody Books book, User user ) {
        userService.saveBook(book, user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping(value = "/{username}")
    public ResponseEntity<HttpStatus> deleteByUsername(@PathVariable String username) {
        userService.deleteByUsername(username);
        return ResponseEntity.ok().build();
    }
}
