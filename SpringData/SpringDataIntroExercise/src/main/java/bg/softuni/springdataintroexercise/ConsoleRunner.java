package bg.softuni.springdataintroexercise;

import bg.softuni.springdataintroexercise.entities.Author;
import bg.softuni.springdataintroexercise.entities.Book;
import bg.softuni.springdataintroexercise.repositories.AuthorRepository;
import bg.softuni.springdataintroexercise.repositories.BookRepository;
import bg.softuni.springdataintroexercise.services.SeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final SeedService seedService;
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public ConsoleRunner(SeedService seedService, BookRepository bookRepository, AuthorRepository authorRepository) {
        this.seedService = seedService;
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        //   this.seedService.seedAuthors();

        //   this.seedService.seedCategories();

        //  this.seedService.seedAll();

        // QUERIES
        //1. Get all books after the year 2000.
//        LocalDate year2000 = LocalDate.of(2000, 1, 1);
//
//        List<Book> bookList = this.bookRepository.findByReleaseDateAfter(year2000);
//
//        bookList.forEach(b -> System.out.println(b.getTitle()));


        //2. Get all authors with at least one book with release date before 1990.
//        LocalDate year1990 = LocalDate.of(1990, 1, 1);
//
//        Set<Author> authors = this.authorRepository.findByBooksReleaseDateBefore(year1990);
//
//        authors.forEach(a -> System.out.println(a.getFirstName() + " " + a.getLastName()));


        //3. Get all authors, ordered by the number of their books (descending).

//        List<Author> authorList = this.authorRepository.findAll();
//
//        authorList.stream()
//                .sorted((l, r) -> r.getBooks().size() - l.getBooks().size())
//                .forEach(a -> System.out.printf("%s %s - %d%n",
//                        a.getFirstName(), a.getLastName(), a.getBooks().size()));

        //4. Get all books from author George Powell

        this.bookRepository.
                findAllByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescTitle("George", "Powell")
                .forEach(book ->
                        System.out.println(book.getTitle() + " " + book.getReleaseDate() + " " + book.getCopies()));

    }
}
