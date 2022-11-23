package com.example.xml.productShop.services.impl;

import com.example.xml.productShop.entities.categoryDtos.CategoryDataDTO;
import com.example.xml.productShop.entities.categoryDtos.CategoryStatisticsDTO;
import com.example.xml.productShop.repositories.CategoryRepository;
import com.example.xml.productShop.services.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryStatisticsDTO> getCategoryStatistics() {
        List<CategoryStatisticsDTO> categories = this.categoryRepository.getCategoryStatistics();

        CategoryDataDTO categoryDataDTO = new CategoryDataDTO(categories);

        return categories;
    }
}
