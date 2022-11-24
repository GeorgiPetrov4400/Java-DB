package com.example.xml.carDealer.services.impl;

import com.example.xml.carDealer.entities.saleDtos.SaleDTO;
import com.example.xml.carDealer.entities.saleDtos.SaleWithDiscountDTO;
import com.example.xml.carDealer.entities.saleDtos.SaleWithDiscountExportDTO;
import com.example.xml.carDealer.repositories.SaleRepository;
import com.example.xml.carDealer.services.SaleService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;
    private final ModelMapper modelMapper;

    public SaleServiceImpl(SaleRepository saleRepository, ModelMapper modelMapper) {
        this.saleRepository = saleRepository;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public SaleWithDiscountExportDTO getAllSalesWithDiscount() {
        List<SaleWithDiscountDTO> sales = this.saleRepository.findAll().stream()
                .map(sale -> modelMapper.map(sale, SaleDTO.class))
                .map(SaleDTO::saleWithDiscountDTO)
                .collect(Collectors.toList());

        return new SaleWithDiscountExportDTO(sales);
    }
}
