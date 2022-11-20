package com.example.json.productShop.services;

import com.example.json.productShop.entities.dtos.CategoryStatistics;
import com.example.json.productShop.entities.dtos.ProductWithoutBuyerDTO;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

    List<ProductWithoutBuyerDTO> getProductsInPriceRange(BigDecimal lower, BigDecimal upper);

    List<CategoryStatistics> getCategoryStatistics();
}
