package com.example.json.carDealer.entities.dtos;

import java.math.BigDecimal;

public class PartWithNameDTO {

    private String name;

    private BigDecimal price;

    public PartWithNameDTO() {
    }

    public PartWithNameDTO(String name, BigDecimal price) {
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
