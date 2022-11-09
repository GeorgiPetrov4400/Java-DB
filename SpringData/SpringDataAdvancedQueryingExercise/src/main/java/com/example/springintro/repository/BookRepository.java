package com.example.springintro.repository;

import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Book;
import com.example.springintro.model.entity.EditionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByReleaseDateAfter(LocalDate releaseDateAfter);

    List<Book> findAllByReleaseDateBefore(LocalDate releaseDateBefore);

    List<Book> findAllByAuthor_FirstNameAndAuthor_LastNameOrderByReleaseDateDescTitle(String author_firstName, String author_lastName);

    List<Book> findBooksByAgeRestriction(AgeRestriction ageRestriction);

    List<Book> findBooksByEditionTypeAndCopiesLessThan(EditionType editionType, int copies);

    List<Book> findAllByPriceIsLessThanOrPriceIsGreaterThan(BigDecimal low, BigDecimal high);

    List<Book> findByReleaseDateBeforeOrReleaseDateAfter(LocalDate before, LocalDate after);

    List<Book> findBooksByReleaseDateBefore(LocalDate localDate);

    List<Book> findByTitleContaining(String string);

    List<Book> findBooksByAuthorLastNameStartingWith(String startsWith);

    @Query(value = "SELECT b FROM Book AS b WHERE LENGTH(b.title) > :input")
    List<Book> findAllByTitleGreaterThanCount(int input);

    List<Book> findBooksByTitleContaining(String string);

    @Query(value = "UPDATE Book b SET b.copies = b.copies + :increaseCopies WHERE b.releaseDate > :after")
    @Modifying
    @Transactional
    int addCopiesToBook(LocalDate after, int increaseCopies);

    @Transactional
    int deleteBooksByCopiesLessThan(int copies);

    @Procedure("udp_books_written")
    int countBooksByAuthorStoredProcedure(String firstName, String lastName);

}
