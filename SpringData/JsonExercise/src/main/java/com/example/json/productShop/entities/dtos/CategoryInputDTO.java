package com.example.json.productShop.entities.dtos;

public class CategoryInputDTO {

    private String name;

    public CategoryInputDTO() {
    }

    public CategoryInputDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
