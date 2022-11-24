package com.example.xml.carDealer.services.impl;

import com.example.xml.carDealer.entities.Supplier;
import com.example.xml.carDealer.entities.supplierDtos.SupplierNotAbroadDTO;
import com.example.xml.carDealer.entities.supplierDtos.SupplierNotAbroadExportDTO;
import com.example.xml.carDealer.repositories.SupplierRepository;
import com.example.xml.carDealer.services.SupplierService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;
    private final ModelMapper modelMapper;

    public SupplierServiceImpl(SupplierRepository supplierRepository, ModelMapper modelMapper) {
        this.supplierRepository = supplierRepository;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public SupplierNotAbroadExportDTO findAllSupplierNotAbroad() {
        List<SupplierNotAbroadDTO> suppliers =
                this.supplierRepository.findAllByImporterIsFalseOrderById();

        return new SupplierNotAbroadExportDTO(suppliers);
    }
}
