package com.example.xml.carDealer.services;

import com.example.xml.carDealer.entities.carDtos.CarsToyotaExportDTO;
import com.example.xml.carDealer.entities.carDtos.CarsWithPartsExportDTO;

public interface CarService {

    CarsToyotaExportDTO findAllCarsByMake(String maker);

    CarsWithPartsExportDTO getAllCarWithParts();
}
