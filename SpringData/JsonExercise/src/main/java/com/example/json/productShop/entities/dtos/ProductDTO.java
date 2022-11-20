package com.example.json.productShop.entities.dtos;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ProductDTO {

    private String name;

    private BigDecimal price;

    private String buyer;

    private String seller;

    private Set<CategoryInputDTO> categories;

    public ProductDTO() {
    }

    public ProductDTO(String name, BigDecimal price, String buyer, String seller, Set<CategoryInputDTO> categories) {
        this.name = name;
        this.price = price;
        this.buyer = buyer;
        this.seller = seller;
        this.categories = new HashSet<>();
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

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public Set<CategoryInputDTO> getCategories() {
        return categories;
    }

    public void setCategories(Set<CategoryInputDTO> categories) {
        this.categories = categories;
    }

//    public ProductsSoldWithCountDTO toProductsSoldWithCountDTO(List<ProductDTO> sellingProducts) {
//        return new ProductsSoldWithCountDTO(sellingProducts
//                .stream()
//                .map(this::toProductInputDTO).collect(Collectors.toList()));
//    }
//
//    public ProductsSoldWithCountDTO toProductInputDTO() {
//        return new ProductsSoldWithCountDTO();
//    }
}
