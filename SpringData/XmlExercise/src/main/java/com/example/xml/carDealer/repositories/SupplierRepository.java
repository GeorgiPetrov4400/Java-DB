package com.example.xml.carDealer.repositories;

import com.example.xml.carDealer.entities.Supplier;
import com.example.xml.carDealer.entities.supplierDtos.SupplierNotAbroadDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    @Query("SELECT new com.example.xml.carDealer.entities.supplierDtos.SupplierNotAbroadDTO" +
            " (s.id, s.name, COUNT(p.id)) FROM Supplier s JOIN s.parts p" +
            " WHERE s.isImporter = false GROUP BY s.id ORDER BY COUNT(p.id) DESC")
    List<SupplierNotAbroadDTO> findAllByImporterIsFalseOrderById();

}
