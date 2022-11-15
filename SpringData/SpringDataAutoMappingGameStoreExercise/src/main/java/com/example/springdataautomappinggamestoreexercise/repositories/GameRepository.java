package com.example.springdataautomappinggamestoreexercise.repositories;

import com.example.springdataautomappinggamestoreexercise.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    void deleteById(Long id);
}
