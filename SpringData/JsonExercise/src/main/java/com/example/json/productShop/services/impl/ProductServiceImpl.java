package com.example.json.productShop.services.impl;

import com.example.json.productShop.entities.dtos.CategoryStatistics;
import com.example.json.productShop.entities.dtos.ProductWithoutBuyerDTO;
import com.example.json.productShop.repositories.ProductRepository;
import com.example.json.productShop.services.ProductService;
import com.example.json.productShop.utils.JsonOutput;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductWithoutBuyerDTO> getProductsInPriceRange(BigDecimal lower, BigDecimal upper) throws IOException {
        List<ProductWithoutBuyerDTO> productsInRange =
                this.productRepository.findAllByPriceBetweenAndBuyerIsNullOrderByPriceAsc(lower, upper);

        JsonOutput.writeJsonToFile(productsInRange, Path.of("src/main/resources/productShopJsons/output/products-in-range.json"));

        return productsInRange;
    }

    @Override
    public List<CategoryStatistics> getCategoryStatistics() {
        return this.productRepository.getCategoryStatistics();
    }

}

