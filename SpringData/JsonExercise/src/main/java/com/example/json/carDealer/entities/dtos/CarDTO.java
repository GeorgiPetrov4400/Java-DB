package com.example.json.carDealer.entities.dtos;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CarDTO {

    private String make;
    private String model;
    private Long travelledDistance;
    List<PartDto> parts;

    public CarDTO() {
    }

    public CarDTO(String make, String model, Long travelledDistance, List<PartDto> parts) {
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

    public List<PartDto> getParts() {
        return parts;
    }

    public void setParts(List<PartDto> parts) {
        this.parts = parts;
    }

    public BigDecimal getCarPrice(){
        return parts
                .stream()
                .map(PartDto::getPrice)
                .reduce(BigDecimal.ONE, BigDecimal::add );
    }

    public CarWithPartsDTO carWithPartsDto(){
        CarWithoutPartsDTO car = new CarWithoutPartsDTO(make, model, travelledDistance);

        Set<PartWithNameDTO> parts =
                this.parts
                        .stream()
                        .map(CarDTO::partWithNameDto)
                        .collect(Collectors.toSet());

        return new CarWithPartsDTO(car, parts);
    }

    public static PartWithNameDTO partWithNameDto(PartDto partDto){
        return new PartWithNameDTO(partDto.getName(), partDto.getPrice());
    }
}
