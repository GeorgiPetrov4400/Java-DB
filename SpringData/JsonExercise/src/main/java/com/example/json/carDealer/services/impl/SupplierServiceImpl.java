package com.example.json.carDealer.services.impl;

import com.example.json.carDealer.repositories.SupplierRepository;
import com.example.json.carDealer.services.SupplierService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;
    private final ModelMapper modelMapper;

    public SupplierServiceImpl(SupplierRepository supplierRepository, ModelMapper modelMapper) {
        this.supplierRepository = supplierRepository;
        this.modelMapper = new ModelMapper();
    }


}
