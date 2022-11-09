package com.example.springintro.service;

import com.example.springintro.model.entity.Author;
import com.example.springintro.model.entity.AuthorNameAndAllCopies;

import java.io.IOException;
import java.util.List;

public interface AuthorService {
    void seedAuthors() throws IOException;

    Author getRandomAuthor();

    List<String> getAllAuthorsOrderByCountOfTheirBooks();

    List<Author> findByFirstNameEndingWith(String string);

    List<AuthorNameAndAllCopies> findTotalCopiesByAuthor();
}
