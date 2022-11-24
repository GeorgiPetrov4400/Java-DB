package com.example.xml.carDealer.entities.supplierDtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "suppliers")
@XmlAccessorType(XmlAccessType.FIELD)
public class SupplierNotAbroadExportDTO {

    @XmlElement(name = "supplier")
    private List<SupplierNotAbroadDTO> suppliers;

    public SupplierNotAbroadExportDTO() {
    }

    public SupplierNotAbroadExportDTO(List<SupplierNotAbroadDTO> suppliers) {
        this.suppliers = suppliers;
    }

    public List<SupplierNotAbroadDTO> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(List<SupplierNotAbroadDTO> suppliers) {
        this.suppliers = suppliers;
    }
}
