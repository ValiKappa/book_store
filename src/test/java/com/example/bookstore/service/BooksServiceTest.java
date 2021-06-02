package com.example.bookstore.service;

import com.example.bookstore.model.Author;
import com.example.bookstore.model.Books;
import com.example.bookstore.repository.BooksRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.awt.print.Book;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(value = SpringExtension.class)
public class BooksServiceTest {

    @Mock
    private BooksRepository booksRepository;
    private BooksService booksService;
    private AuthorService authorService;

    @BeforeEach
    public void init(){
        booksService = new BooksService(booksRepository, authorService);
    }

    @Test
    public void shouldSaveBook (){
       Books book = Books.builder()
                .name("Winter Moon")
                .genre("Horror")
                .year(1975)
                .build();
        Author author = new Author();
        author.setAuthorName("Dean Kunz");

        when(booksRepository.save(ArgumentMatchers.any(Books.class))).thenReturn(book);
        Books createdBook = booksService.save(book, author);
        assertThat(createdBook.getName()).isSameAs(book.getName());
        Mockito.verify(booksRepository).save(book);
    }
    @Test
    public void shouldFindName() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            booksService.findByName(null);
        });
    }
    @Test
    public void shouldReturnAllBooks() {
        List<Books> booksList = new ArrayList();
        booksList.add(new Books());
        when(booksRepository.findAll()).thenReturn(booksList);
        Set<Books> books = new HashSet<>(booksList);
        Set<Books> expected = booksService.returnAllBooks();
        assertEquals(expected, books);
       Mockito.verify(booksRepository).findAll();
    }

    @Test
    public void shouldDeleteById() {
        Books book = new Books();
        book.setName("Nora Roberts");
        when(booksRepository.findByName(book.getName())).thenReturn(Optional.ofNullable(null));
        booksService.deleteBook(book.getName());
        verify(booksRepository).deleteByName(book.getName());
    }
}

