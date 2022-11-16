package com.example.springdataautomappinggamestoreexercise.services;

import com.example.springdataautomappinggamestoreexercise.entities.User;
import com.example.springdataautomappinggamestoreexercise.entities.dtos.UserLoginDTO;
import com.example.springdataautomappinggamestoreexercise.entities.dtos.UserRegisterDTO;

import java.util.Optional;

public interface UserService {
    void registerUser(UserRegisterDTO userRegisterDTO);

    void loginUser(UserLoginDTO userLoginDTO);

    void logout();

    User getLoggedInUser();
}
