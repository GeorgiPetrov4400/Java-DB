package com.example.xml.carDealer.entities.carDtos;

import com.example.xml.carDealer.entities.partDto.PartNamePriceDTO;

import javax.xml.bind.annotation.*;
import java.util.List;
import java.util.Set;

@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarWithPartsDTO {

    private CarAttributesDTO car;
//    @XmlAttribute
//    private String make;
//
//    @XmlAttribute
//    private String model;
//
//    @XmlAttribute(name = "travelled-distance")
//    private Long travelledDistance;

    @XmlElement
    private Set<PartNamePriceDTO> parts;

    public CarWithPartsDTO() {
    }

    public CarWithPartsDTO(CarAttributesDTO car, Set<PartNamePriceDTO> parts) {
        this.car = car;
        this.parts = parts;
    }

    public CarAttributesDTO getCar() {
        return car;
    }

    public void setCar(CarAttributesDTO car) {
        this.car = car;
    }

    public Set<PartNamePriceDTO> getParts() {
        return parts;
    }

    public void setParts(Set<PartNamePriceDTO> parts) {
        this.parts = parts;
    }
}
