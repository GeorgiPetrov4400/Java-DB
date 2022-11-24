package com.example.xml.carDealer.entities.customerDtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomersExportDTO {

    @XmlElement(name = "customer")
    private List<CustomerNameAndBirthdayDTO> customers;

    public CustomersExportDTO() {
    }

    public CustomersExportDTO(List<CustomerNameAndBirthdayDTO> customers) {
        this.customers = customers;
    }

    public List<CustomerNameAndBirthdayDTO> getCustomers() {
        return customers;
    }

    public void setCustomers(List<CustomerNameAndBirthdayDTO> customers) {
        this.customers = customers;
    }
}
