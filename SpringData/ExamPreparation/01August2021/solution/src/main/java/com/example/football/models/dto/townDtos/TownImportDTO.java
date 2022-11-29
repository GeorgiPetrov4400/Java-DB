package com.example.football.models.dto.townDtos;

import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class TownImportDTO {
    @Size(min = 2)
    private String name;
    @Positive
    private Integer population;
    @Size(min = 10)
    private String travelGuide;

    public TownImportDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public String getTravelGuide() {
        return travelGuide;
    }

    public void setTravelGuide(String travelGuide) {
        this.travelGuide = travelGuide;
    }
}
