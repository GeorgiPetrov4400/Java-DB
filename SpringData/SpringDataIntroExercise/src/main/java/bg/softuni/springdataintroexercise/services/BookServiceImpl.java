package bg.softuni.springdataintroexercise.services;

import bg.softuni.springdataintroexercise.entities.Book;
import bg.softuni.springdataintroexercise.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> findAllBooksByAuthorFirstNameAndLastNameOrderByReleaseDate() {
        return this.bookRepository.
                findAllByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescTitle("George", "Powell");
    }
}
