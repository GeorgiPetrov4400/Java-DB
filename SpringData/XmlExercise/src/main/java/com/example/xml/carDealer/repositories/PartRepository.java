package com.example.xml.carDealer.repositories;

import com.example.xml.carDealer.entities.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PartRepository extends JpaRepository<Part, Long> {

    @Query(value = "SELECT * FROM `car_dealer_xml`.parts ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Optional<Part> getRandomEntity();
}
