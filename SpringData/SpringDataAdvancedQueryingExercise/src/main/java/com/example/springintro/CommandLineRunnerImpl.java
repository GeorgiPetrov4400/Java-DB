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
import java.time.LocalDate;
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
        // bookTitlesByAgeRestriction_01(scanner);


        //2. Golden Books  // Разкоментирай следващия ред и натисни run за да стартира задачата
        // goldenBooks_02(scanner);


        //3. Books by Price  // Разкоментирай следващия ред и натисни run за да стартира задачата
        // bookByPrice_03();


        //4. Not Released Books  // Разкоментирай следващия ред и натисни run за да стартира задачата
        // notReleasedBooks_04(scanner);


        //5. Books Released Before Date  // Разкоментирай следващия ред и натисни run за да стартира задачата
        // booksReleasedBeforeDate_05(scanner);


        //6. Authors Search  // Разкоментирай следващия ред и натисни run за да стартира задачата
        // authorsSearch_06(scanner);


        //7. Books Search  // Разкоментирай следващия ред и натисни run за да стартира задачата
        // booksSearch_07(scanner);


        //8. Book Titles Search  // Разкоментирай следващия ред и натисни run за да стартира задачата
        // bookTitlesSearch_08(scanner);


        //9. Count Books  // Разкоментирай следващия ред и натисни run за да стартира задачата
        // countBooks_09(scanner);


        //10. Total Book Copies  // Разкоментирай следващия ред и натисни run за да стартира задачата
        // totalBookCopies_10();


        //11. Reduced Book  // Разкоментирай следващия ред и натисни run за да стартира задачата
        // reducedBook_11(scanner);


        //12. Increase Book Copies  // Разкоментирай следващия ред и натисни run за да стартира задачата
        // increaseBookCopies_12(scanner);


        //13. Remove Books  // Разкоментирай следващия ред и натисни run за да стартира задачата
        // removeBooks_13(scanner);


        //14. Stored Procedure  // Разкоментирай следващия ред и натисни run за да стартира задачата
        String nameInput = scanner.nextLine();

        String[] name = nameInput.split("\\s+");

        int countBooksByAuthor = this.bookService.countBooksByAuthorNameStoredProcedure(nameInput);

        System.out.printf("%s has written %d books%n", nameInput, countBooksByAuthor);
    }

    private void removeBooks_13(Scanner scanner) {
        int deleteCopiesLessThan = Integer.parseInt(scanner.nextLine());

        int deletedBooksCount = this.bookService.deleteBooksByCopiesLessThan(deleteCopiesLessThan);
        System.out.println(deletedBooksCount + " books deleted");
    }

    private void increaseBookCopies_12(Scanner scanner) {
        String date = scanner.nextLine();
        int increaseCopies = Integer.parseInt(scanner.nextLine());

        int updatedBooks = this.bookService.addCopiesToBook(date, increaseCopies);
        System.out.println(increaseCopies * updatedBooks);
    }

    private void reducedBook_11(Scanner scanner) {
        String input = scanner.nextLine();

        this.bookService.findBooksByTitleContaining(input)
                .forEach(book -> System.out.printf("%s %s %s %.2f%n",
                        book.getTitle(), book.getEditionType(), book.getAgeRestriction(), book.getPrice()));
    }

    private void totalBookCopies_10() {
        this.authorService.findTotalCopiesByAuthor()
                .forEach(author ->
                        System.out.println(author.getFirstName() + " " + author.getLastName() + " " + author.getAllCopies()));
    }

    private void countBooks_09(Scanner scanner) {
        int length = Integer.parseInt(scanner.nextLine());

        List<Book> allBooksByTitle = this.bookService.findAllByTitleGreaterThanCount(length);

        System.out.println(allBooksByTitle.size());
    }

    private void bookTitlesSearch_08(Scanner scanner) {
        String startWith = scanner.nextLine();

        this.bookService.findByAuthorLastNameStartWith(startWith)
                .forEach(book ->
                        System.out.printf("%s (%s %s)%n", book.getTitle(), book.getAuthor().getFirstName(), book.getAuthor().getLastName()));
    }

    private void booksSearch_07(Scanner scanner) {
        String input = scanner.nextLine();

        this.bookService.findByTitleContaining(input)
                .forEach(book -> System.out.println(book.getTitle()));
    }

    private void authorsSearch_06(Scanner scanner) {
        String input = scanner.nextLine();

        this.authorService.findByFirstNameEndingWith(input).
                forEach(author -> System.out.println(author.getFirstName() + " " + author.getLastName()));
    }

    private void booksReleasedBeforeDate_05(Scanner scanner) {
        String[] dateInput = scanner.nextLine().split("-");

        int day = Integer.parseInt(dateInput[0]);
        int month = Integer.parseInt(dateInput[1]);
        int year = Integer.parseInt(dateInput[2]);

        LocalDate localDate = LocalDate.of(year, month, day);

        this.bookService.findBooksByReleaseDateBefore(localDate)
                .forEach(book -> System.out.printf("%s %s %.2f%n", book.getTitle(), book.getEditionType(), book.getPrice()));
    }

    private void notReleasedBooks_04(Scanner scanner) {
        int year = Integer.parseInt(scanner.nextLine());

        this.bookService.findByReleaseDateBeforeOrReleaseDateAfter(year)
                .forEach(book -> System.out.println(book.getTitle()));
    }

    private void bookByPrice_03() {
        float priceLowerThan = 5;
        float priceHigherThan = 40;

        this.bookService.findBooksByPriceLessThanAndPriceGreaterThan(priceLowerThan, priceHigherThan)
                .forEach(book -> System.out.printf("%s - $%.2f%n", book.getTitle(), book.getPrice()));
    }

    private void goldenBooks_02(Scanner scanner) {
        int inputCopies = Integer.parseInt(scanner.nextLine());

        this.bookService.findBooksByEditionTypeAndCopiesLessThan(EditionType.GOLD, inputCopies)
                .forEach(book -> System.out.println(book.getTitle()));
    }

    private void bookTitlesByAgeRestriction_01(Scanner scanner) {
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
