package com.example.football.models.dto.teamDtos;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public class TeamImportDTO {

    @Size(min = 3)
    private String name;

    @Size(min = 3)
    private String stadiumName;

    @Min(1000)
    private Long fanBase;

    @Size(min = 10)
    private String history;

    private String townName;

    public TeamImportDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStadiumName() {
        return stadiumName;
    }

    public void setStadiumName(String stadiumName) {
        this.stadiumName = stadiumName;
    }

    public Long getFanBase() {
        return fanBase;
    }

    public void setFanBase(Long fanBase) {
        this.fanBase = fanBase;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }
}
