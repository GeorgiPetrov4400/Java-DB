package com.example.xml.carDealer.entities.carDtos;

import com.example.xml.carDealer.entities.partDto.PartDTO;
import com.example.xml.carDealer.entities.partDto.PartNamePriceDTO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CarDTO {

    private String make;

    private String model;

    private Long travelledDistance;

    private List<PartDTO> parts;

    public CarDTO() {
    }

    public CarDTO(String make, String model, Long travelledDistance, List<PartDTO> parts) {
        this.make = make;
        this.model = model;
        this.travelledDistance = travelledDistance;
        this.parts = parts;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Long getTravelledDistance() {
        return travelledDistance;
    }

    public void setTravelledDistance(Long travelledDistance) {
        this.travelledDistance = travelledDistance;
    }

    public List<PartDTO> getParts() {
        return parts;
    }

    public void setParts(List<PartDTO> parts) {
        this.parts = parts;
    }

    public CarWithPartsDTO carWithPartsDTO() {
        CarAttributesDTO car = new CarAttributesDTO(make, model, travelledDistance);

        Set<PartNamePriceDTO> parts =
                this.parts
                        .stream()
                        .map(CarDTO::partWithNameDto)
                        .collect(Collectors.toSet());

        return new CarWithPartsDTO(car, parts);
    }

    public static PartNamePriceDTO partWithNameDto(PartDTO partDTO) {
        return new PartNamePriceDTO(partDTO.getName(), partDTO.getPrice());
    }

    public BigDecimal getCarPrice() {
        return parts
                .stream()
                .map(PartDTO::getPrice)
                .reduce(BigDecimal.ONE, BigDecimal::add);
    }
}
