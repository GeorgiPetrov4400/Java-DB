package com.example.xml.carDealer.entities.supplierDtos;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "suppliers")
@XmlAccessorType(XmlAccessType.FIELD)
public class SupplierImportDTO {

    @XmlElement(name = "supplier")
    private List<SupplierNameAndImportDTO> suppliers;

    public SupplierImportDTO() {
    }

    public List<SupplierNameAndImportDTO> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(List<SupplierNameAndImportDTO> suppliers) {
        this.suppliers = suppliers;
    }
}
