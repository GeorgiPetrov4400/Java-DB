package com.example.springdataautomappinggamestoreexercise.entities.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

public class UserLoginDTO {

    @Email(message = "Incorrect username")
    private String email;

    @Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,})", message = "Incorrect username / password")
    private String password;

    private boolean isAdmin;

    public UserLoginDTO() {
    }

    public UserLoginDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
