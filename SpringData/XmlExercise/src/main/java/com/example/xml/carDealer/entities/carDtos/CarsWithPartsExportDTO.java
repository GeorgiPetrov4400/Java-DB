package com.example.xml.carDealer.entities.carDtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarsWithPartsExportDTO {

    @XmlElement(name = "car")
    private List<CarWithPartsDTO> cars;

    public CarsWithPartsExportDTO() {
    }

    public CarsWithPartsExportDTO(List<CarWithPartsDTO> cars) {
        this.cars = cars;
    }

    public List<CarWithPartsDTO> getCars() {
        return cars;
    }

    public void setCars(List<CarWithPartsDTO> cars) {
        this.cars = cars;
    }
}
