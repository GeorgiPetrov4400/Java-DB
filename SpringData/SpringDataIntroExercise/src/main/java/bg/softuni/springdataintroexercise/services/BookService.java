package bg.softuni.springdataintroexercise.services;

import bg.softuni.springdataintroexercise.entities.Book;

import java.util.List;

public interface BookService {

    List<Book> findAllBooksByAuthorFirstNameAndLastNameOrderByReleaseDate();
}
