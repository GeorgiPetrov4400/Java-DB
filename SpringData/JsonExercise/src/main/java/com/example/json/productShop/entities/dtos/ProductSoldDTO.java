package com.example.json.productShop.entities.dtos;

import java.math.BigDecimal;

public class ProductSoldDTO {

    private String firstName;

    private BigDecimal price;

    private String buyerFirstName;

    private String buyerLastName;

    public ProductSoldDTO() {
    }

    public ProductSoldDTO(String firstName, BigDecimal price, String buyerFirstName, String buyerLastName) {
        this.firstName = firstName;
        this.price = price;
        this.buyerFirstName = buyerFirstName;
        this.buyerLastName = buyerLastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getBuyerFirstName() {
        return buyerFirstName;
    }

    public void setBuyerFirstName(String buyerFirstName) {
        this.buyerFirstName = buyerFirstName;
    }

    public String getBuyerLastName() {
        return buyerLastName;
    }

    public void setBuyerLastName(String buyerLastName) {
        this.buyerLastName = buyerLastName;
    }
}
