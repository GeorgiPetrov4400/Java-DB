package com.example.json.productShop.entities.dtos;

import java.math.BigDecimal;

public class ProductInputDTO {

    private String name;

    private BigDecimal price;

    public ProductInputDTO() {
    }

    public ProductInputDTO(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
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
}
