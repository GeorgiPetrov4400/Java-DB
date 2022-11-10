package com.example.springintro.service;

import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Book;
import com.example.springintro.model.entity.EditionType;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface BookService {
    void seedBooks() throws IOException;

    List<Book> findAllBooksAfterYear(int year);

    List<String> findAllAuthorsWithBooksWithReleaseDateBeforeYear(int year);

    List<String> findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(String firstName, String lastName);

    List<Book> findBooksByAgeRestriction(AgeRestriction ageRestriction);

    List<Book> findBooksByEditionTypeAndCopiesLessThan(EditionType editionType, int copies);

    List<Book> findBooksByPriceLessThanAndPriceGreaterThan(float low, float high);

    List<Book> findByReleaseDateBeforeOrReleaseDateAfter(int year);

    List<Book> findBooksByReleaseDateBefore(LocalDate localDate);

    List<Book> findByTitleContaining(String string);

    List<Book> findByAuthorLastNameStartWith(String startWith);

    List<Book> findAllByTitleGreaterThanCount(int length);

    List<Book> findBooksByTitleContaining(String string);

    int addCopiesToBook(String date, int increaseCopies);

    int deleteBooksByCopiesLessThan(int copies);

    Integer countBooksByAuthorNameStoredProcedure(String name);
}
