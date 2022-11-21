package com.example.json.carDealer.entities.dtos;

import java.time.LocalDateTime;

public class CustomerImportDTO {

    private String name;

    private String birthDate;

    private boolean youngDriver;

    public CustomerImportDTO() {
    }

    public CustomerImportDTO(String name, String birthDate, boolean youngDriver) {
        this.name = name;
        this.birthDate = birthDate;
        this.youngDriver = youngDriver;
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
}
