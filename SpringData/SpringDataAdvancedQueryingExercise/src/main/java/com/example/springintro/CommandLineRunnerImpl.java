package com.example.springintro;

import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Book;
import com.example.springintro.model.entity.EditionType;
import com.example.springintro.service.AuthorService;
import com.example.springintro.service.BookService;
import com.example.springintro.service.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;

    public CommandLineRunnerImpl(CategoryService categoryService, AuthorService authorService, BookService bookService) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception {
//        seedData();
//
//        printAllBooksAfterYear(2000);
//        printAllAuthorsNamesWithBooksWithReleaseDateBeforeYear(1990);
//        printAllAuthorsAndNumberOfTheirBooks();
//        printALlBooksByAuthorNameOrderByReleaseDate("George", "Powell");

        // SPRING DATA ADVANCED QUERIYNG HOMEWORK

        Scanner scanner = new Scanner(System.in);

        //1. Book Titles by Age Restriction  // Разкоментирай следващия ред и натисни run за да стартира задачата
        // BookTitlesByAgeRestriction_01(scanner);


        //2. Golden Books  // Разкоментирай следващия ред и натисни run за да стартира задачата
        // GoldenBooks_02(scanner);


        //3. Books by Price  // Разкоментирай следващия ред и натисни run за да стартира задачата
        // BookByPrice_03();


        //4. Not Released Books  // Разкоментирай следващия ред и натисни run за да стартира задачата
        // NotReleasedBooks_04(scanner);


        //5. Books Released Before Date  // Разкоментирай следващия ред и натисни run за да стартира задачата
        // BooksReleasedBeforeDate_05(scanner);


        //6. Authors Search  // Разкоментирай следващия ред и натисни run за да стартира задачата

    }

    private void BooksReleasedBeforeDate_05(Scanner scanner) {
        String[] dateInput = scanner.nextLine().split("-");

        int day = Integer.parseInt(dateInput[0]);
        int month = Integer.parseInt(dateInput[1]);
        int year = Integer.parseInt(dateInput[2]);

        LocalDate localDate = LocalDate.of(year, month, day);

        this.bookService.findBooksByReleaseDateBefore(localDate)
                .forEach(book -> System.out.printf("%s %s %.2f%n", book.getTitle(), book.getEditionType(), book.getPrice()));
    }

    private void NotReleasedBooks_04(Scanner scanner) {
        int year = Integer.parseInt(scanner.nextLine());

        this.bookService.findByReleaseDateBeforeOrReleaseDateAfter(year)
                .forEach(book -> System.out.println(book.getTitle()));
    }

    private void BookByPrice_03() {
        float priceLowerThan = 5;
        float priceHigherThan = 40;

        this.bookService.findBooksByPriceLessThanAndPriceGreaterThan(priceLowerThan, priceHigherThan)
                .forEach(book -> System.out.printf("%s - $%.2f%n", book.getTitle(), book.getPrice()));
    }

    private void GoldenBooks_02(Scanner scanner) {
        int inputCopies = Integer.parseInt(scanner.nextLine());

        this.bookService.findBooksByEditionTypeAndCopiesLessThan(EditionType.GOLD, inputCopies)
                .forEach(book -> System.out.println(book.getTitle()));
    }

    private void BookTitlesByAgeRestriction_01(Scanner scanner) {
        String ageRestrictionInput = scanner.nextLine();

        AgeRestriction ageRestriction = AgeRestriction.valueOf(ageRestrictionInput.toUpperCase());
        this.bookService.findBooksByAgeRestriction(ageRestriction)
                .forEach(book -> System.out.println(book.getTitle()));
    }

    private void printALlBooksByAuthorNameOrderByReleaseDate(String firstName, String lastName) {
        bookService
                .findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(firstName, lastName)
                .forEach(System.out::println);
    }

    private void printAllAuthorsAndNumberOfTheirBooks() {
        authorService
                .getAllAuthorsOrderByCountOfTheirBooks()
                .forEach(System.out::println);
    }

    private void printAllAuthorsNamesWithBooksWithReleaseDateBeforeYear(int year) {
        bookService
                .findAllAuthorsWithBooksWithReleaseDateBeforeYear(year)
                .forEach(System.out::println);
    }

    private void printAllBooksAfterYear(int year) {
        bookService
                .findAllBooksAfterYear(year)
                .stream()
                .map(Book::getTitle)
                .forEach(System.out::println);
    }

    private void seedData() throws IOException {
        categoryService.seedCategories();
        authorService.seedAuthors();
        bookService.seedBooks();
    }
}
