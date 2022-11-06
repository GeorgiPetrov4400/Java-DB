package bg.softuni.springdataintroexercise.services;

import bg.softuni.springdataintroexercise.entities.Author;

import java.util.List;

public interface AuthorService {
    Author getRandomAuthor();

    List<Author> findAllOrderByBooks();
}
