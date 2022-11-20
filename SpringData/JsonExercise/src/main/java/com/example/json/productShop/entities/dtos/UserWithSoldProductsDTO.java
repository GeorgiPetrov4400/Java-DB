package com.example.json.productShop.entities.dtos;

import java.util.ArrayList;
import java.util.List;

public class UserWithSoldProductsDTO {

    private String firstName;

    private String lastName;

    private List<SoldProductsDTO> sellingProducts;

    public UserWithSoldProductsDTO() {
    }

    public UserWithSoldProductsDTO(String firstName, String lastName, List<SoldProductsDTO> soldProducts) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.sellingProducts = new ArrayList<>();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<SoldProductsDTO> getSellingProducts() {
        return sellingProducts;
    }

    public void setSellingProducts(List<SoldProductsDTO> sellingProducts) {
        this.sellingProducts = sellingProducts;
    }
}
