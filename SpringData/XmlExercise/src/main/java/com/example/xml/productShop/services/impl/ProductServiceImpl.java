package com.example.xml.productShop.services.impl;

import com.example.xml.productShop.entities.Product;
import com.example.xml.productShop.entities.User;
import com.example.xml.productShop.entities.productDtos.ProductInRangeExportDTO;
import com.example.xml.productShop.entities.productDtos.ProductWithNamePriceSellerDTO;
import com.example.xml.productShop.repositories.ProductRepository;
import com.example.xml.productShop.services.ProductService;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final TypeMap<Product, ProductWithNamePriceSellerDTO> productToDtoMapping;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
        this.modelMapper = new ModelMapper();

        Converter<User, String> userToFullNameConverter =
                context -> context.getSource() == null ? null : context.getSource().getFullName();

        TypeMap<Product, ProductWithNamePriceSellerDTO> typeMap =
                this.modelMapper.createTypeMap(Product.class, ProductWithNamePriceSellerDTO.class);

        this.productToDtoMapping = typeMap.addMappings(map ->
                map.using(userToFullNameConverter)
                        .map(Product::getSeller, ProductWithNamePriceSellerDTO::setSeller));
    }

    @Override
    public ProductInRangeExportDTO getProductsInPriceRange(BigDecimal lower, BigDecimal upper) {
        List<Product> productsInRange =
                this.productRepository.findAllByPriceBetweenAndBuyerIsNullOrderByPriceAsc(lower, upper);

        List<ProductWithNamePriceSellerDTO> collect = productsInRange.stream()
                .map(this.productToDtoMapping::map)
                .collect(Collectors.toList());


        return new ProductInRangeExportDTO(collect);
    }

//    @Override
//    public List<CategoryStatisticsDTO> getCategoryStatistics() {
//        List<Category> categories = this.productRepository.getCategoryStatistics();
//
//        List<CategoryDataDTO> collect = categories.stream()
//                .map(category -> this.modelMapper.map(category, CategoryDataDTO.class))
//                .collect(Collectors.toList());
//
//        return new CategoryStatisticsDTO(collect);
//    }
}

