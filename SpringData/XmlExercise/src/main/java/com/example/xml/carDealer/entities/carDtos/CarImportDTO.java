package com.example.xml.carDealer.entities.carDtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarImportDTO {

    @XmlElement(name = "car")
    private List<CarMakeModelDistanceDTO> cars;

    public CarImportDTO() {
    }

    public List<CarMakeModelDistanceDTO> getCars() {
        return cars;
    }

    public void setCars(List<CarMakeModelDistanceDTO> cars) {
        this.cars = cars;
    }
}
