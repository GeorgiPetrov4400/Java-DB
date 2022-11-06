package bg.softuni.springdataintroexercise.services;

import bg.softuni.springdataintroexercise.entities.*;
import bg.softuni.springdataintroexercise.repositories.AuthorRepository;
import bg.softuni.springdataintroexercise.repositories.BookRepository;
import bg.softuni.springdataintroexercise.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SeedServiceImpl implements SeedService {

    private static final String RESOURCE_PATH = "src\\main\\resources\\files\\";
    private static final String AUTHORS_FILE_PATH = RESOURCE_PATH + "authors.txt";
    private static final String CATEGORIES_FILE_PATH = RESOURCE_PATH + "categories.txt";
    private static final String BOOKS_FILE_PATH = RESOURCE_PATH + "books.txt";

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private CategoryService categoryService;

    @Override
    public void seedAuthors() throws IOException {
        Files.readAllLines(Path.of(AUTHORS_FILE_PATH))
                .stream()
                .filter(s -> !s.isBlank())
                .map(s -> s.split(" "))
                .map(name -> new Author(name[0], name[1]))
                .forEach(authorRepository::save);
    }

    @Override
    public void seedCategories() throws IOException {
        Files.readAllLines(Path.of(CATEGORIES_FILE_PATH))
                .stream()
                .filter(s -> !s.isBlank())
                .map(Category::new)
                .forEach(categoryRepository::save);
    }

    @Override
    public void seedBooks() throws IOException {
        Files.readAllLines(Path.of(BOOKS_FILE_PATH))
                .stream()
                .filter(s -> !s.isBlank())
                .map(this::getBookInfo)
                .forEach(bookRepository::save);
    }

    private Book getBookInfo(String line) {
        String[] data = line.split(" ");

        int editionTypeIndex = Integer.parseInt(data[0]);
        EditionType editionType = EditionType.values()[editionTypeIndex];

        LocalDate releaseDate = LocalDate.parse(data[1], DateTimeFormatter.ofPattern("d/M/yyyy"));

        int copies = Integer.parseInt(data[2]);

        BigDecimal price = new BigDecimal(data[3]);

        int ageRestrictionIndex = Integer.parseInt(data[4]);
        AgeRestriction ageRestriction = AgeRestriction.values()[ageRestrictionIndex];

        String bookTitle = Arrays.stream(data).skip(5).collect(Collectors.joining(" "));

        Author author = authorService.getRandomAuthor();
        Set<Category> categories = categoryService.getRandomCategories();

        return new Book(bookTitle, editionType, price, copies, releaseDate, ageRestriction, author, categories);
    }
}
