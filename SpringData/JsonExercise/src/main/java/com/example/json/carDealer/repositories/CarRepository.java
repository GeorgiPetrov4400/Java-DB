package com.example.json.carDealer.repositories;

import com.example.json.carDealer.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    @Query(value = "select * from `cars_dealer`.cars order by RAND() LIMIT 1", nativeQuery = true)
    Optional<Car> getRandomEntity();

    List<Car> findCarsByMakeOrderByModelAscTravelledDistanceDesc(String manufacturer);
}
