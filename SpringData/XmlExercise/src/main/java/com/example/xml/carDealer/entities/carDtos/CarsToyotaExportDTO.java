package com.example.xml.carDealer.entities.carDtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarsToyotaExportDTO {

    @XmlElement(name = "car")
    private List<CarToyotaAttributesDTO> cars;

    public CarsToyotaExportDTO() {
    }

    public CarsToyotaExportDTO(List<CarToyotaAttributesDTO> cars) {
        this.cars = cars;
    }

    public List<CarToyotaAttributesDTO> getCars() {
        return cars;
    }

    public void setCars(List<CarToyotaAttributesDTO> cars) {
        this.cars = cars;
    }
}
