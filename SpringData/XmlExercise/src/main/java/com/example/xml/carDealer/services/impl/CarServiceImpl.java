package com.example.xml.carDealer.services.impl;

import com.example.xml.carDealer.entities.Car;
import com.example.xml.carDealer.entities.carDtos.*;
import com.example.xml.carDealer.repositories.CarRepository;
import com.example.xml.carDealer.services.CarService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final ModelMapper modelMapper;

    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public CarsToyotaExportDTO findAllCarsByMake(String maker) {
        List<Car> cars = this.carRepository.findAllByMakeOrderByModelAscTravelledDistanceDesc(maker);

        List<CarToyotaAttributesDTO> collect = cars.stream()
                .map(car -> modelMapper.map(car, CarToyotaAttributesDTO.class)).collect(Collectors.toList());

        return new CarsToyotaExportDTO(collect);
    }

    @Override
    @Transactional
    public CarsWithPartsExportDTO getAllCarWithParts() {
        List<CarWithPartsDTO> carsWithParts =
                this.carRepository.findAll().stream()
                        .map(car -> modelMapper.map(car, CarDTO.class))
                        .map(CarDTO::carWithPartsDTO).collect(Collectors.toList());
        //        return this.carRepository
        //                .findAll()
        //                .stream()
        //                .map(car -> modelMapper.map(car, CarDTO.class))
        //                .map(CarDTO::carWithPartsDto)
        //                .toList();
        return new CarsWithPartsExportDTO(carsWithParts);
    }
}
