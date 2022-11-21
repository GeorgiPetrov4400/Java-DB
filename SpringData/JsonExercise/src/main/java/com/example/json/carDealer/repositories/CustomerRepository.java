package com.example.json.carDealer.repositories;

import com.example.json.carDealer.entities.Car;
import com.example.json.carDealer.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query(value = "select * from `cars_dealer`.customers order by RAND() LIMIT 1", nativeQuery = true)
    Optional<Customer> getRandomEntity();
}
