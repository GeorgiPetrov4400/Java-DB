package com.example.json.productShop.entities.dtos;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UserDTO {

    private String firstName;

    private String lastName;

    private Integer age;

    private Set<ProductDTO> sellingProducts;

    private Set<ProductDTO> boughtProducts;

    private Set<UserDTO> friends;

    public UserDTO() {
        this.sellingProducts = new HashSet<>();
        this.boughtProducts = new HashSet<>();
        this.friends = new HashSet<>();
    }

    public UserDTO(String firstName, String lastName, Integer age) {
        this();
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    //    public UserWithProductsDTO toUserWithProductsDTO() {
//        return new UserWithProductsDTO(firstName, lastName, age, toProductsSoldWithCountDTO(sellingProducts));
//    }
    public UserWithProductsDTO toUserWithProductsDTO() {
        return new UserWithProductsDTO(firstName, lastName, age, toProductsSoldWithCountDTO());
    }

    public ProductsSoldWithCountDTO toProductsSoldWithCountDTO() {
        return new ProductsSoldWithCountDTO(sellingProducts.stream()
                .map(this::toProductBasicInfo).collect(Collectors.toList()));
    }

    public ProductBasicInfo toProductBasicInfo(ProductDTO productDTO) {
        return new ProductBasicInfo(productDTO.getName(), productDTO.getPrice());
    }
}
