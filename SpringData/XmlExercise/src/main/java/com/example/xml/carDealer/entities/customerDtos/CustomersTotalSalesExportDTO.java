package com.example.xml.carDealer.entities.customerDtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomersTotalSalesExportDTO {

    @XmlElement(name = "customer")
    private List<CustomerTotalSalesDTO> customers;

    public CustomersTotalSalesExportDTO() {
    }

    public CustomersTotalSalesExportDTO(List<CustomerTotalSalesDTO> customers) {
        this.customers = customers;
    }

    public List<CustomerTotalSalesDTO> getCustomers() {
        return customers;
    }

    public void setCustomers(List<CustomerTotalSalesDTO> customers) {
        this.customers = customers;
    }
}
