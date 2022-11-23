package com.example.xml.productShop.entities.productDtos;

import com.example.xml.productShop.entities.categoryDtos.CategoryDTO;
import com.example.xml.productShop.entities.userDtos.UserDTO;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

public class ProductDTO {
    private String name;
    private BigDecimal price;
    private UserDTO buyer;
    private UserDTO seller;
    Set<CategoryDTO> categories;

    public ProductDTO() {
    }

    public ProductDTO(String name, BigDecimal price, UserDTO buyer, UserDTO seller, Set<CategoryDTO> categories) {
        this.name = name;
        this.price = price;
        this.buyer = buyer;
        this.seller = seller;
        this.categories = categories;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public UserDTO getBuyer() {
        return buyer;
    }

    public void setBuyer(UserDTO buyer) {
        this.buyer = buyer;
    }

    public UserDTO getSeller() {
        return seller;
    }

    public void setSeller(UserDTO seller) {
        this.seller = seller;
    }

    public Set<CategoryDTO> getCategories() {
        return categories;
    }

    public void setCategories(Set<CategoryDTO> categories) {
        this.categories = categories;
    }

    public static ProductsSoldWithCountDTO toProductsSoldWithCountDTO(Set<ProductDTO> sellingProducts) {
        return new ProductsSoldWithCountDTO(sellingProducts
                .stream()
                .map(ProductDTO::toProductBasicInfo)
                .collect(Collectors.toList()));
    }

    public static ProductBasicInfo toProductBasicInfo(ProductDTO productDTO) {
        return new ProductBasicInfo(productDTO.getName(), productDTO.getPrice());
    }
}
