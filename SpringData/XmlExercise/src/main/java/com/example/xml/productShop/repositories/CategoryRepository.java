package com.example.xml.productShop.repositories;

import com.example.xml.productShop.entities.Category;
import com.example.xml.productShop.entities.categoryDtos.CategoryStatisticsDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT new com.example.xml.productShop.entities.categoryDtos.CategoryStatisticsDTO" +
            " (c.name, COUNT(p), AVG(p.price), SUM(p.price))" +
            " FROM Product p JOIN p.categories AS c GROUP BY c ORDER BY COUNT(p) DESC")
    List<CategoryStatisticsDTO> getCategoryStatistics();
}
