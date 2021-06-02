package com.example.bookstore.service;

import com.example.bookstore.exception.RecordNotFoundException;
import com.example.bookstore.model.Author;
import com.example.bookstore.repository.AuthorRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    private AuthorRepository authorRepository;
    private AuthorService authorService;

    @Autowired
    public AuthorService(AuthorRepository authorRepository, @Lazy AuthorService authorService) {
        this.authorRepository = authorRepository;
        this.authorService = authorService;
    }

    public Set<Author> findAll() {
       return authorRepository.findAll()
               .stream()
               .collect(Collectors.toSet());
    }

    public void save(@NonNull Author author) {
        authorRepository.save(author);
    }

    public Author findById(@NonNull Long id){
       return authorRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
    }

    public Author findByAuthorName(@NonNull String authorName) {
        return authorRepository.findByAuthorName(authorName)
                .orElseThrow(IllegalArgumentException::new);
    }

    public void deleteById(@NonNull Long id) {
        authorRepository.deleteById(id);
    }
    @Transactional
    public void deleteByAuthorName(@NonNull String authorName) {
        authorRepository.deleteByAuthorName(authorName);
    }

    public Author update(@NonNull Long id, @NonNull Author author) {
       Author foundAuthor =  authorRepository.findById(id)
               .orElseThrow(() ->
                       new RecordNotFoundException(String.format("Author with id: %s", id)));

       foundAuthor.setAuthorName(author.getAuthorName());
       return authorRepository.save(foundAuthor);
    }

}
