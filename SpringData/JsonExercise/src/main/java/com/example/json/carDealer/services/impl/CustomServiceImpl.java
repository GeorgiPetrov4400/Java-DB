package com.example.json.carDealer.services.impl;

import com.example.json.carDealer.entities.dtos.CustomerNameAndBirthdayDTO;
import com.example.json.carDealer.repositories.CustomerRepository;
import com.example.json.carDealer.services.CustomService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomServiceImpl implements CustomService {

    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;

    @Autowired
    public CustomServiceImpl(CustomerRepository customerRepository, ModelMapper modelMapper, Gson gson) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
    }


    @Override
    public List<CustomerNameAndBirthdayDTO> getAllCustomers() {
        return this.customerRepository.findAllByOrderByBirthDateAscYoungDriverDesc()
                .stream().map(customer -> modelMapper.map(customer, CustomerNameAndBirthdayDTO.class))
                .collect(Collectors.toList());
    }
}
