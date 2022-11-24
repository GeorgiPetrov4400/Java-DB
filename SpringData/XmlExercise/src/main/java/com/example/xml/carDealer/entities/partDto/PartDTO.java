package com.example.xml.carDealer.entities.partDto;

import com.example.xml.carDealer.entities.Supplier;
import com.example.xml.carDealer.entities.supplierDtos.SupplierDTO;

import java.math.BigDecimal;

public class PartDTO {

    private String name;

    private BigDecimal price;

    private Long quantity;

    private SupplierDTO supplier;

    public PartDTO() {
    }

    public PartDTO(String name, BigDecimal price, Long quantity, SupplierDTO supplier) {
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

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public SupplierDTO getSupplier() {
        return supplier;
    }

    public void setSupplier(SupplierDTO supplier) {
        this.supplier = supplier;
    }
}
