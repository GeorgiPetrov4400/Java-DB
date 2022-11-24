package com.example.xml.carDealer.entities.customerDtos;

import com.example.xml.carDealer.entities.saleDtos.SaleDTO;

import java.util.List;

public class CustomerDTO {

    private Long id;

    private String name;

    private String birthDate;

    private boolean youngDriver;

    private List<SaleDTO> sales;

    public CustomerDTO() {
    }

    public CustomerDTO(Long id, String name, String birthDate, boolean youngDriver, List<SaleDTO> sales) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.youngDriver = youngDriver;
        this.sales = sales;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isYoungDriver() {
        return youngDriver;
    }

    public void setYoungDriver(boolean youngDriver) {
        this.youngDriver = youngDriver;
    }

    public List<SaleDTO> getSales() {
        return sales;
    }

    public void setSales(List<SaleDTO> sales) {
        this.sales = sales;
    }
}
