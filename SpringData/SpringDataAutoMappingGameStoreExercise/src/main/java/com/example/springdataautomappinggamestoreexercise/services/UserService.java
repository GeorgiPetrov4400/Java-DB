package com.example.springdataautomappinggamestoreexercise.services;

import com.example.springdataautomappinggamestoreexercise.entities.dtos.UserRegisterDTO;

public interface UserService {
    void registerUser(UserRegisterDTO userRegisterDTO);
}
