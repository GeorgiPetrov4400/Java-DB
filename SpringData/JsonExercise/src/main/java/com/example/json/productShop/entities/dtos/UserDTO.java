package com.example.json.productShop.entities.dtos;

import java.util.Set;

import static com.example.json.productShop.entities.dtos.ProductDTO.toProductsSoldWithCountDTO;

public class UserDTO {

    private String firstName;

    private String lastName;

    private Integer age;

    private Set<ProductDTO> sellingProducts;

    private Set<ProductDTO> boughtProducts;

    private Set<UserDTO> friends;

    public UserDTO() {
    }

    public UserDTO(String firstName, String lastName, Integer age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
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

    public Set<ProductDTO> getSellingProducts() {
        return sellingProducts;
    }

    public void setSellingProducts(Set<ProductDTO> sellingProducts) {
        this.sellingProducts = sellingProducts;
    }

    public Set<ProductDTO> getBoughtProducts() {
        return boughtProducts;
    }

    public void setBoughtProducts(Set<ProductDTO> boughtProducts) {
        this.boughtProducts = boughtProducts;
    }

    public Set<UserDTO> getFriends() {
        return friends;
    }

    public void setFriends(Set<UserDTO> friends) {
        this.friends = friends;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public UserWithProductsDTO toUserWithProductsDTO() {
        return new UserWithProductsDTO(firstName, lastName, age, toProductsSoldWithCountDTO(sellingProducts));
    }
}
