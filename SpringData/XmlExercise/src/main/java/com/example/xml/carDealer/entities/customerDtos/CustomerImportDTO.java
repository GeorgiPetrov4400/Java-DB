package com.example.xml.carDealer.entities.customerDtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerImportDTO {

    @XmlElement(name = "customer")
    private List<CustomerInfoDTO> customers;

    public CustomerImportDTO() {
    }

    public List<CustomerInfoDTO> getCustomers() {
        return customers;
    }

    public void setCustomers(List<CustomerInfoDTO> customers) {
        this.customers = customers;
    }
}
