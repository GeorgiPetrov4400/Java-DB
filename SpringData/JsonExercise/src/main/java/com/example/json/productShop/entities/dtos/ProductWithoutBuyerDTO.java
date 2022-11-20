package com.example.json.productShop.entities.dtos;

import java.math.BigDecimal;

public class ProductWithoutBuyerDTO {

    private String name;

    private BigDecimal price;

    private String seller;

    public ProductWithoutBuyerDTO() {
    }

    public ProductWithoutBuyerDTO(String name, BigDecimal price, String firstName, String lastName) {
        this.name = name;
        this.price = price;

        if (firstName == null) {
            this.seller = lastName;
        } else {
            this.seller = firstName + ' ' + lastName;
        }
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getSeller() {
        return seller;
    }

    //    interface
//    String getName();
//
//    BigDecimal getPrice();
//
//    @Value("#{target.seller.firstName + ' ' + target.seller.lastName}")
//    String getSeller();
}
