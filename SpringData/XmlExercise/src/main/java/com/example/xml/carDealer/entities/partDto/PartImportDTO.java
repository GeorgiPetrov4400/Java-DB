package com.example.xml.carDealer.entities.partDto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "parts")
@XmlAccessorType(XmlAccessType.FIELD)
public class PartImportDTO {

    @XmlElement(name = "part")
    private List<PartNamePriceQuantityDTO> parts;

    public PartImportDTO() {
    }

    public List<PartNamePriceQuantityDTO> getParts() {
        return parts;
    }

    public void setParts(List<PartNamePriceQuantityDTO> parts) {
        this.parts = parts;
    }
}
