package com.example.xml.productShop.entities.productDtos;

import com.example.xml.productShop.entities.categoryDtos.CategoryDTO;
import com.example.xml.productShop.entities.userDtos.UserDTO;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class ProductDTO {
    private String name;
    private BigDecimal price;
    private UserDTO buyer;
    private UserDTO seller;
    Set<CategoryDTO> categories;

    public ProductDTO() {
        this.categories = new HashSet<>();
    }

    public ProductDTO(String name, BigDecimal price, UserDTO buyer, UserDTO seller, Set<CategoryDTO> categories) {
        this.name = name;
        this.price = price;
        this.buyer = buyer;
        this.seller = seller;
       // this.categories = categories;
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

    public ProductBasicInfo productBasicInfo() {
        return new ProductBasicInfo(name, price);
    }
}
