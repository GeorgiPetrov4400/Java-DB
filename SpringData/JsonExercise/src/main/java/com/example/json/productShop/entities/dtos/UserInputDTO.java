package com.example.json.productShop.entities.dtos;

public class UserInputDTO {

    private String firstName;

    private String lastName;

    private int age;

    public UserInputDTO() {
    }

    public UserInputDTO(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }
}
