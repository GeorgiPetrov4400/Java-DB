package com.example.json.carDealer.services;

import com.example.json.carDealer.entities.dtos.CarWithPartsDTO;
import com.example.json.carDealer.entities.dtos.CarsToyotaDTO;

import java.util.List;

public interface CarService {

    List<CarsToyotaDTO> findAllCarsByMake(String manufacturer);

    List<CarWithPartsDTO> getAllCarWithParts();
}
