package com.example.football.models.entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "teams")
public class Team extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String name;

    @Column(name = "stadium_name", nullable = false)
    private String stadiumName;

    @Column(name = "fan_base", nullable = false)
  //  @Min(1000)
    private Long fanBase;

    @Column(nullable = false, columnDefinition = "TEXT")
  //  @Size(min = 10)
    private String history;

    @ManyToOne(optional = false)
    private Town town;

    @OneToMany(targetEntity = Player.class, mappedBy = "team")
    private Set<Player> players; // Щом има Set трябва да имаме в Player hashcode и equals

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

    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
    }

    public Set<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }

    @Override
    public String toString() {
        return name + " - " + fanBase;
    }

    //    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Team team = (Team) o;
//        return Objects.equals(getId(), team.getId()) && Objects.equals(name, team.name)
//                && Objects.equals(stadiumName, team.stadiumName)
//                && Objects.equals(history, team.history)
//                && Objects.equals(town, team.town);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(getId(), name, stadiumName, history, town);
//    }
}
