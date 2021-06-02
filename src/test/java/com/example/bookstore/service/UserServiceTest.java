package com.example.bookstore.service;

import com.example.bookstore.dto.UserDto;
import com.example.bookstore.model.Books;
import com.example.bookstore.model.User;
import com.example.bookstore.repository.BooksRepository;
import com.example.bookstore.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.net.http.HttpHeaders;
import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.registerFormatterForType;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(value = SpringExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    private UserService userService;
    private BooksService booksService;
    private BooksRepository booksRepository;
    private ModelMapper modelMapper;

    @BeforeEach
    public void init() {
        userService = new UserService(userRepository, booksService, booksRepository);
    }

    @Test
    public void shouldSave() {
        User user = new User();
        user.setUsername("Vlado");
        when(userRepository.save(ArgumentMatchers.any(User.class))).thenReturn(user);
        User created = userService.save(user);
        assertThat(created.getUsername()).isSameAs(user.getUsername());
        Mockito.verify(userRepository).save(user);
    }

    @Test
    public void shouldReturnAllUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User());
        Set<UserDto> collection = new HashSet<>();
        for (User user : users) {
            UserDto userDto = new UserDto();
            userDto.setId(user.getId());
            userDto.setCreatedAt(user.getCreatedAt());
            userDto.setEmail(user.getEmail());

            collection.add(userDto);
        }
        when(userRepository.findAll()).thenReturn(users);
        Set<UserDto> expected = userService.returnAllUsers();
        assertEquals(expected, collection);
        verify(userRepository).findAll();
    }

    @Test
    public void shouldFindByUsername() {
            Assertions.assertThrows(NullPointerException.class, () -> {
                userService.findByUsername(null);
            });
    }

    @Test
    public void shouldDeleteByUsername() {
        User user = new User();
        user.setUsername("");
        when(userRepository.findByUsername((""))).thenReturn(Optional.ofNullable(null));
        userService.deleteByUsername(user.getUsername());
    }
}




