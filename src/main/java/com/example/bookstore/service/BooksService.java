package com.example.bookstore.service;
import com.example.bookstore.model.Author;
import com.example.bookstore.model.Books;
import com.example.bookstore.repository.BooksRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Set;

@Service
public class BooksService {

    private BooksRepository booksRepository;
    private AuthorService authorService;

    @Autowired
    public BooksService(BooksRepository booksRepository, AuthorService authorService) {
        this.booksRepository = booksRepository;
        this.authorService = authorService;
    }

    public Books save(@NonNull Books book, @NonNull Author author) {
        book.setAuthor(author);
        return booksRepository.save(book);
    }

    public Books findByName(@NonNull String name) {
        return booksRepository.findByName(name)
                .orElseThrow(IllegalArgumentException::new);
    }

    public Set<Books> returnAllBooks () {
        return new HashSet<>(booksRepository.findAll());
    }

    public void  deleteBook (String name) {
        booksRepository.deleteByName(name);
    }
}

