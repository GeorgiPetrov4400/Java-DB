package com.example.xml.carDealer.services.impl;

import com.example.xml.carDealer.entities.Customer;
import com.example.xml.carDealer.entities.customerDtos.CustomerNameAndBirthdayDTO;
import com.example.xml.carDealer.entities.customerDtos.CustomerTotalSalesDTO;
import com.example.xml.carDealer.entities.customerDtos.CustomersExportDTO;
import com.example.xml.carDealer.entities.customerDtos.CustomersTotalSalesExportDTO;
import com.example.xml.carDealer.repositories.CustomerRepository;
import com.example.xml.carDealer.services.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public CustomersExportDTO getAllCustomers() {
        List<Customer> customers =
                this.customerRepository.findAllByOrderByBirthDateAscYoungDriverDesc();

        List<CustomerNameAndBirthdayDTO> collect = customers.stream()
                .map(customer -> modelMapper.map(customer, CustomerNameAndBirthdayDTO.class))
                .collect(Collectors.toList());

        return new CustomersExportDTO(collect);
    }

    @Override
    public CustomersTotalSalesExportDTO getAllWithTotalSales() {
        List<CustomerTotalSalesDTO> customers =
                this.customerRepository.getAllCustomersWithTotalSale();

        return new CustomersTotalSalesExportDTO(customers);
    }
}
