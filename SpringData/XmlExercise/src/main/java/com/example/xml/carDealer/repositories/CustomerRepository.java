package com.example.xml.carDealer.repositories;

import com.example.xml.carDealer.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query(value = "SELECT * FROM `car_dealer_xml`.customers ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Optional<Customer> getRandomEntity();

    List<Customer> findAllByOrderByBirthDateAscYoungDriverDesc();
}
