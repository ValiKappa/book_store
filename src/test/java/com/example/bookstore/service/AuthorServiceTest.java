package com.example.bookstore.service;

import com.example.bookstore.model.Author;
import com.example.bookstore.repository.AuthorRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.Optional;
import static org.mockito.Mockito.*;

@ExtendWith(value = SpringExtension.class)
@WebMvcTest(AuthorService.class)
public class AuthorServiceTest {

    @Mock
    private AuthorRepository authorRepository;
    private AuthorService authorService;

    @BeforeEach
    public void init() {
        authorService = new AuthorService(authorRepository, authorService);
    }

    @Test
    public void expectNullPointerException() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            authorService.findById(null);
            authorService.findByAuthorName(null);
        });
    }
    @Test
    public void shouldDeleteById() {
        Author author = new Author();
        author.setId(3L);
        when(authorRepository.findById(author.getId())).thenReturn(Optional.ofNullable(null));
        authorService.deleteById(author.getId());
        verify(authorRepository).deleteById(author.getId());
    }

    @Test
    public void shouldDeleteByAuthorName() {
        Author author = new Author();
        author.setAuthorName("Nora Roberts");
        when(authorRepository.findByAuthorName(author.getAuthorName())).thenReturn(Optional.ofNullable(null));
        authorService.deleteByAuthorName(author.getAuthorName());
        verify(authorRepository).deleteByAuthorName(author.getAuthorName());
    }
}
