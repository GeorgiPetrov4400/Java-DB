package com.example.xml.productShop.services;

import com.example.xml.productShop.entities.categoryDtos.CategoryStatisticsDTO;

import java.util.List;

public interface CategoryService {
    List<CategoryStatisticsDTO> getCategoryStatistics();

}
