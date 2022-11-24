package com.example.xml.carDealer.services;

import com.example.xml.carDealer.entities.customerDtos.CustomerNameAndBirthdayDTO;
import com.example.xml.carDealer.entities.customerDtos.CustomersExportDTO;

import java.util.List;

public interface CustomerService {

    CustomersExportDTO getAllCustomers();
}
