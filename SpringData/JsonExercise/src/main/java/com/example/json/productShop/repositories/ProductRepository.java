package com.example.json.productShop.repositories;

import com.example.json.productShop.entities.Product;
import com.example.json.productShop.entities.dtos.CategoryStatistics;
import com.example.json.productShop.entities.dtos.ProductWithoutBuyerDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT new com.example.json.productShop.entities.dtos.ProductWithoutBuyerDTO(p.name, p.price, p.seller.firstName, p.seller.lastName)" +
            " FROM Product p WHERE p.price>:lower AND p.price<:upper AND p.buyer IS NULL ORDER BY p.price ASC")
    List<ProductWithoutBuyerDTO> findAllByPriceBetweenAndBuyerIsNullOrderByPriceAsc(BigDecimal lower, BigDecimal upper);

    @Query("SELECT new com.example.json.productShop.entities.dtos.CategoryStatistics(" +
            "c.name, COUNT(p), AVG(p.price), SUM(p.price))" +
            " FROM Product p JOIN p.categories AS c GROUP BY c")
    List<CategoryStatistics> getCategoryStatistics();
}
