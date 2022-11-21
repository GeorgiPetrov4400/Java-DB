package com.example.json.carDealer.entities.dtos;

import java.util.Set;

public class CarWithPartsDTO {

    private CarWithoutPartsDTO car;

    public Set<PartWithNameDTO> parts;

    public CarWithPartsDTO() {
    }

    public CarWithPartsDTO(CarWithoutPartsDTO car, Set<PartWithNameDTO> parts) {
        this.car = car;
        this.parts = parts;
    }

    public CarWithoutPartsDTO getCar() {
        return car;
    }

    public void setCar(CarWithoutPartsDTO car) {
        this.car = car;
    }

    public Set<PartWithNameDTO> getParts() {
        return parts;
    }

    public void setParts(Set<PartWithNameDTO> parts) {
        this.parts = parts;
    }
}
