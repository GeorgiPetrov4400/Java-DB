package com.example.json.productShop.services.impl;

import com.example.json.productShop.entities.dtos.CategoryStatistics;
import com.example.json.productShop.entities.dtos.ProductWithoutBuyerDTO;
import com.example.json.productShop.repositories.ProductRepository;
import com.example.json.productShop.services.ProductService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductWithoutBuyerDTO> getProductsInPriceRange(BigDecimal lower, BigDecimal upper) {
        return this.productRepository.findAllByPriceBetweenAndBuyerIsNullOrderByPriceAsc(lower, upper);
    }

    @Override
    public List<CategoryStatistics> getCategoryStatistics() {
        return this.productRepository.getCategoryStatistics();
    }


}

