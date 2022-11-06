package bg.softuni.springdataintroexercise.repositories;

import bg.softuni.springdataintroexercise.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    Set<Author> findByBooksReleaseDateBefore(LocalDate releaseDate);

    @Query("SELECT a FROM Author a ORDER BY a.books.size DESC")
    List<Author> findAllGroupByBooksOrderByBooksSize();

}
