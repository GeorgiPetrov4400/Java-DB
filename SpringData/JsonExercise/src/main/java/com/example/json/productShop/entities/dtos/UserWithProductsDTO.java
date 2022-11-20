package com.example.json.productShop.entities.dtos;

import java.util.List;

public class UserWithProductsDTO {

    private String firstName;

    private String lastName;

    private Integer age;

    private ProductsSoldWithCountDTO products;

    public UserWithProductsDTO() {
    }

    public UserWithProductsDTO(String firstName, String lastName, Integer age, ProductsSoldWithCountDTO products) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.products = products;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public ProductsSoldWithCountDTO getProducts() {
        return products;
    }

    public void setProducts(ProductsSoldWithCountDTO products) {
        this.products = products;
    }
}
