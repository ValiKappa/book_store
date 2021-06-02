package com.example.bookstore.service;

import com.example.bookstore.dto.UserDto;
import com.example.bookstore.exception.RecordNotFoundException;
import com.example.bookstore.model.Books;
import com.example.bookstore.model.User;
import com.example.bookstore.repository.BooksRepository;
import com.example.bookstore.repository.UserRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {

    private UserRepository userRepository;
    private BooksService booksService;
    private BooksRepository booksRepository;

    @Autowired
    public UserService(UserRepository userRepository, @Lazy BooksService  booksService, BooksRepository booksRepository) {
        this.booksService = booksService;
        this.userRepository = userRepository;
        this.booksRepository = booksRepository;

    }

    public User save(@NonNull User user) {
       return userRepository.save(user);
    }

    public Set<UserDto> returnAllUsers () {
      List<User> users = userRepository.findAll();
      Set<UserDto> collection = new HashSet<>();
      for (User user : users) {
          UserDto userDto = new UserDto();
          userDto.setId(user.getId());
          userDto.setCreatedAt(user.getCreatedAt());
          userDto.setEmail(user.getEmail());

          collection.add(userDto);
      }
      return collection;
    }

    public User findByUsername (@NonNull String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RecordNotFoundException(String.format("User not found with username : %s", username)));
    }

    public Books saveBook(@NonNull Books book, @NonNull User user) {
        book.setUser(user);
        return booksRepository.save(book);
    }

    public void deleteByUsername(@NonNull String username) {
        userRepository.deleteByUsername(username);
    }
}
