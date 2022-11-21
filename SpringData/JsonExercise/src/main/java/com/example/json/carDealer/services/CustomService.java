package com.example.json.carDealer.services;

import com.example.json.carDealer.entities.dtos.CustomerNameAndBirthdayDTO;

import java.util.List;

public interface CustomService {

    List<CustomerNameAndBirthdayDTO> getAllCustomers();
}
