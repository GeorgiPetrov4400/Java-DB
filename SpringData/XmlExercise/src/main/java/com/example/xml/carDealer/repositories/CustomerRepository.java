package com.example.xml.carDealer.repositories;

import com.example.xml.carDealer.entities.Customer;
import com.example.xml.carDealer.entities.customerDtos.CustomerTotalSalesDTO;
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

    @Query("SELECT new com.example.xml.carDealer.entities.customerDtos.CustomerTotalSalesDTO" +
            " (c.name, COUNT(s), SUM(p.price*(1.0-(s.discount/100.0))))" +
            " FROM Customer c JOIN c.sales s JOIN s.car car JOIN car.parts p GROUP BY c" +
            " ORDER BY COUNT(s) DESC, SUM(p.price*(1-s.discount/100)) desc")

    List<CustomerTotalSalesDTO> getAllCustomersWithTotalSale();
}
