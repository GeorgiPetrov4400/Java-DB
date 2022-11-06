package bg.softuni.springdataintroexercise.repositories;

import bg.softuni.springdataintroexercise.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByReleaseDateAfter(LocalDate releaseDate);

    List<Book> findAllByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescTitle(String firstName, String lastName);

  //  List<Book> findAllByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescTitle();

}
