package com.example.json.carDealer.entities.dtos;

import com.example.json.carDealer.entities.Sale;

import java.util.ArrayList;
import java.util.List;

public class CustomerNameAndBirthdayDTO {

    private Long id;

    private String name;

    private String birthDate;

    private boolean youngDriver;

    private Sale[] sales;

    public CustomerNameAndBirthdayDTO() {
        this.sales = new Sale[0];
    }

    public CustomerNameAndBirthdayDTO(Long id, String name, String birthDate, boolean youngDriver) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.youngDriver = youngDriver;
        this.sales = new Sale[0];
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

    public Sale[] getSales() {
        return sales;
    }

    public void setSales(Sale[] sales) {
        this.sales = new Sale[0];
    }
}
