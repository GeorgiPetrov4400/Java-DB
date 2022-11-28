package com.example.football.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Entity
@Table(name = "teams")
public class Team extends BaseEntity {

    //o	Note: The teams table has relation with the towns table.

    @Column(nullable = false, unique = true)
    @Size(min = 3)
    private String name;

    @Column(name = "stadium_name", nullable = false)
    @Size(min = 3)
    private String stadiumName;

    @Column(name = "fan_base", nullable = false)
    @Min(1000)
    private Long fanBase;

    @Column(nullable = false)
    @Size(min = 10)
    private String history;

    public Team() {
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
}
