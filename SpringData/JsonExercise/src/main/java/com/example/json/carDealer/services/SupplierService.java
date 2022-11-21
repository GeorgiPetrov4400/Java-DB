package com.example.json.carDealer.services;

import com.example.json.carDealer.entities.Supplier;
import com.example.json.carDealer.entities.dtos.SupplierNotAbroadDTO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface SupplierService {

    List<SupplierNotAbroadDTO> findAllSupplierNotAbroad();
}
