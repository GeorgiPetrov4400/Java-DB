package com.example.springintro;

import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Author;
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
import java.util.List;
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

        // SPRING DATA ADVANCED QUERYING HOMEWORK

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
        // AuthorsSearch_06(scanner);


        //7. Books Search  // Разкоментирай следващия ред и натисни run за да стартира задачата
        // BooksSearch_07(scanner);


        //8. Book Titles Search  // Разкоментирай следващия ред и натисни run за да стартира задачата
        // BookTitlesSearch_08(scanner);


        //9. Count Books  // Разкоментирай следващия ред и натисни run за да стартира задачата
        // CountBooks_09(scanner);


        //10. Total Book Copies  // Разкоментирай следващия ред и натисни run за да стартира задачата
        // TotalBookCopies_10();


        //11. Reduced Book  // Разкоментирай следващия ред и натисни run за да стартира задачата
        // ReducedBook_11(scanner);

    }

    private void ReducedBook_11(Scanner scanner) {
        String input = scanner.nextLine();

        this.bookService.findBooksByTitleContaining(input)
                .forEach(book -> System.out.printf("%s %s %s %.2f%n",
                        book.getTitle(), book.getEditionType(), book.getAgeRestriction(), book.getPrice()));
    }

    private void TotalBookCopies_10() {
        this.authorService.findTotalCopiesByAuthor()
                .forEach(author ->
                        System.out.println(author.getFirstName() + " " + author.getLastName() + " " + author.getAllCopies()));
    }

    private void CountBooks_09(Scanner scanner) {
        int length = Integer.parseInt(scanner.nextLine());

        List<Book> allBooksByTitle = this.bookService.findAllByTitleGreaterThanCount(length);

        System.out.println(allBooksByTitle.size());
    }

    private void BookTitlesSearch_08(Scanner scanner) {
        String startWith = scanner.nextLine();

        this.bookService.findByAuthorLastNameStartWith(startWith)
                .forEach(book ->
                        System.out.printf("%s (%s %s)%n", book.getTitle(), book.getAuthor().getFirstName(), book.getAuthor().getLastName()));
    }

    private void BooksSearch_07(Scanner scanner) {
        String input = scanner.nextLine();

        this.bookService.findByTitleContaining(input)
                .forEach(book -> System.out.println(book.getTitle()));
    }

    private void AuthorsSearch_06(Scanner scanner) {
        String input = scanner.nextLine();

        this.authorService.findByFirstNameEndingWith(input).
                forEach(author -> System.out.println(author.getFirstName() + " " + author.getLastName()));
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
