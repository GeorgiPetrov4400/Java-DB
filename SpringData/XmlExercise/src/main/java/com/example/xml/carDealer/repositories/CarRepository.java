package com.example.xml.carDealer.repositories;

import com.example.xml.carDealer.entities.Car;
import com.example.xml.carDealer.entities.carDtos.CarWithPartsDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    @Query(value = "SELECT * FROM `car_dealer_xml`.cars ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Optional<Car> getRandomEntity();

    List<Car> findAllByMakeOrderByModelAscTravelledDistanceDesc(String maker);

}
