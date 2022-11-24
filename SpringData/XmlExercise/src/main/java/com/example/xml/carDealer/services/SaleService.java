package com.example.xml.carDealer.services;

import com.example.xml.carDealer.entities.saleDtos.SaleWithDiscountDTO;
import com.example.xml.carDealer.entities.saleDtos.SaleWithDiscountExportDTO;

import java.util.List;

public interface SaleService {
    SaleWithDiscountExportDTO getAllSalesWithDiscount();
}
