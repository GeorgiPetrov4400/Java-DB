package com.example.xml.carDealer.services;

import com.example.xml.carDealer.entities.customerDtos.CustomerNameAndBirthdayDTO;
import com.example.xml.carDealer.entities.customerDtos.CustomersExportDTO;
import com.example.xml.carDealer.entities.customerDtos.CustomersTotalSalesExportDTO;

import java.util.List;

public interface CustomerService {

    CustomersExportDTO getAllCustomers();

    CustomersTotalSalesExportDTO getAllWithTotalSales();
}
