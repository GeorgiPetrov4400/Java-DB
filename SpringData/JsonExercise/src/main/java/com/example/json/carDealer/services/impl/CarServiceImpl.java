package com.example.json.carDealer.services.impl;

import com.example.json.carDealer.entities.dtos.CarDTO;
import com.example.json.carDealer.entities.dtos.CarWithPartsDTO;
import com.example.json.carDealer.entities.dtos.CarsToyotaDTO;
import com.example.json.carDealer.repositories.CarRepository;
import com.example.json.carDealer.services.CarService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;

    @Autowired
    public CarServiceImpl(CarRepository carRepository, ModelMapper modelMapper, Gson gson) {
        this.carRepository = carRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
    }

    @Override
    public List<CarsToyotaDTO> findAllCarsByMake(String manufacturer) {
        return this.carRepository.findCarsByMakeOrderByModelAscTravelledDistanceDesc(manufacturer)
                .stream().map(car -> modelMapper.map(car, CarsToyotaDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<CarWithPartsDTO> getAllCarWithParts() {
        return this.carRepository
                .findAll()
                .stream()
                .map(car -> modelMapper.map(car, CarDTO.class))
                .map(CarDTO::carWithPartsDto)
                .toList();
    }
}
