package com.example.json.carDealer.repositories;

import com.example.json.carDealer.entities.Supplier;
import com.example.json.carDealer.entities.dtos.SupplierNotAbroadDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    @Query("select new com.example.json.carDealer.entities.dtos.SupplierNotAbroadDTO(" +
            "s.id, s.name, count(p.id)) " +
            "FROM Supplier s " +
            "JOIN s.parts p " +
            "WHERE s.isImporter = false " +
            "GROUP BY s.id " +
            "ORDER BY count(p.id) DESC")
    List<SupplierNotAbroadDTO> findAllByImporterIsFalseOrderById();
}
