package com.example.xml.productShop.services;

import com.example.xml.productShop.entities.categoryDtos.CategoryStatisticsDTO;
import com.example.xml.productShop.entities.productDtos.ProductInRangeExportDTO;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

    ProductInRangeExportDTO getProductsInPriceRange(BigDecimal lower, BigDecimal upper);

}
