package com.example.xml.productShop.entities.userDtos;

import com.example.xml.productShop.entities.productDtos.ProductBasicInfo;
import com.example.xml.productShop.entities.productDtos.ProductDTO;
import com.example.xml.productShop.entities.productDtos.ProductsSoldWithCountDTO;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserDTO {

    private String firstName;
    private String lastName;
    private Integer age;
    private Set<UserDTO> friend;
    private Set<ProductDTO> soldProducts;
    private Set<ProductDTO> boughtProducts;

    public String getFullName(){
        return firstName + " " + lastName;
    }

    public UserDTO() {
        this.friend = new HashSet<>();
        this.soldProducts = new HashSet<>();
        this.boughtProducts = new HashSet<>();
    }

    public UserDTO(String firstName, String lastName, Integer age, Set<UserDTO> friend, Set<ProductDTO> soldProducts, Set<ProductDTO> bought) {
        this();
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

    public Set<ProductDTO> getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(Set<ProductDTO> soldProducts) {
        this.soldProducts = soldProducts;
    }

    public Set<ProductDTO> getBoughtProducts() {
        return boughtProducts;
    }

    public void setBoughtProducts(Set<ProductDTO> boughtProducts) {
        this.boughtProducts = boughtProducts;
    }

    public UserWithProductsWrapperDTO usersCountWrapperDto(List<UserDTO> users){
        List<UserWithProductsDTO> usersWrapper =
                users
                        .stream()
                        .map(UserDTO::userWithProductsDTO)
                        .toList();

        return new UserWithProductsWrapperDTO(usersWrapper);
    }

    public UserWithProductsDTO userWithProductsDTO(){
        List<ProductBasicInfo> products = soldProducts
                .stream()
                .map(ProductDTO::productBasicInfo)
                .toList();

        ProductsSoldWithCountDTO productsSoldWithCountDTO = new ProductsSoldWithCountDTO(products);

        return new UserWithProductsDTO(firstName, lastName, age, productsSoldWithCountDTO);
    }
}
