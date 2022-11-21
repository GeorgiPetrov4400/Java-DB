package com.example.json.carDealer.services.impl;

import com.example.json.carDealer.repositories.SaleRepository;
import com.example.json.carDealer.services.SaleService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;

    @Autowired
    public SaleServiceImpl(SaleRepository saleRepository, ModelMapper modelMapper, Gson gson) {
        this.saleRepository = saleRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
    }
}
