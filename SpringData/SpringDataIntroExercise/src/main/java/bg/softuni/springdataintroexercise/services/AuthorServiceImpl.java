package bg.softuni.springdataintroexercise.services;

import bg.softuni.springdataintroexercise.entities.Author;
import bg.softuni.springdataintroexercise.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author getRandomAuthor() {
        long size = this.authorRepository.count();

        long authorId = new Random().nextLong(size) + 1;

        return this.authorRepository.findById(authorId).get();
    }

    @Override
    public List<Author> findAllOrderByBooks() {
        return this.authorRepository.findAllGroupByBooksOrderByBooksSize();
    }
}
