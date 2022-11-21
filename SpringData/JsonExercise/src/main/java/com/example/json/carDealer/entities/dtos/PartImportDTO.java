package com.example.json.carDealer.entities.dtos;

import java.math.BigDecimal;

public class PartImportDTO {

    private String name;

    private BigDecimal price;

    private Long quantity;

    public PartImportDTO() {
    }

    public PartImportDTO(String name, BigDecimal price, Long quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
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

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
