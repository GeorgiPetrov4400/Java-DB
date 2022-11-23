package com.example.xml.productShop.entities.userDtos;

import com.example.xml.productShop.entities.productDtos.ProductDTO;

import java.util.HashSet;
import java.util.Set;

import static com.example.xml.productShop.entities.productDtos.ProductDTO.toProductsSoldWithCountDTO;

public class UserDTO {

    private String firstName;
    private String lastName;
    private Integer age;
    private Set<ProductDTO> sellingProducts;
    private Set<ProductDTO> boughtProducts;
    private Set<UserDTO> friend;

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public UserDTO() {
        this.sellingProducts = new HashSet<>();
        this.boughtProducts = new HashSet<>();
        this.friend = new HashSet<>();
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

    public Set<UserDTO> getFriend() {
        return friend;
    }

    public void setFriend(Set<UserDTO> friend) {
        this.friend = friend;
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

    public UserWithProductsDTO toUserWithProductsDTO() {
        return new UserWithProductsDTO(firstName, lastName, age,
                toProductsSoldWithCountDTO(sellingProducts));
    }
}
