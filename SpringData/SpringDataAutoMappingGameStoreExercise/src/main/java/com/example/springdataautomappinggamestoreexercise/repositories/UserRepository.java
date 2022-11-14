package com.example.springdataautomappinggamestoreexercise.repositories;

import com.example.springdataautomappinggamestoreexercise.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
