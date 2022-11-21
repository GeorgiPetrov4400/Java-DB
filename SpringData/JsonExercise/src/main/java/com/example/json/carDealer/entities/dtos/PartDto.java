package com.example.json.carDealer.entities.dtos;

import java.math.BigDecimal;

public class PartDto {

    private String name;
    private BigDecimal price;
    private Integer quantity;
    private SupplierDTO supplier;

    public PartDto() {
    }

    public PartDto(String name, BigDecimal price, Integer quantity, SupplierDTO supplier) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.supplier = supplier;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public SupplierDTO getSupplier() {
        return supplier;
    }

    public void setSupplier(SupplierDTO supplier) {
        this.supplier = supplier;
    }
}
