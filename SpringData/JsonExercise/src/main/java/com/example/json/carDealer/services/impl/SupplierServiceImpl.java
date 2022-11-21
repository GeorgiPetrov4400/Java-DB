package com.example.json.carDealer.services.impl;

import com.example.json.carDealer.entities.dtos.SupplierNotAbroadDTO;
import com.example.json.carDealer.repositories.SupplierRepository;
import com.example.json.carDealer.services.SupplierService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public SupplierServiceImpl(SupplierRepository supplierRepository, ModelMapper modelMapper) {
        this.supplierRepository = supplierRepository;
        this.modelMapper = new ModelMapper();
    }


    @Override
    public List<SupplierNotAbroadDTO> findAllSupplierNotAbroad() {
        return this.supplierRepository.findAllByImporterIsFalseOrderById();
    }
}
