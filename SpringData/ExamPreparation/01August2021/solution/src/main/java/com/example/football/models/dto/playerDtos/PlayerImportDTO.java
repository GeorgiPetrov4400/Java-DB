package com.example.football.models.dto.playerDtos;

import com.example.football.models.dto.statDtos.StatIdDTO;
import com.example.football.models.dto.teamDtos.TeamNameDTO;
import com.example.football.models.dto.townDtos.TownNameDTO;
import com.example.football.models.entity.PlayerPosition;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement(name = "player")
@XmlAccessorType(XmlAccessType.FIELD)
public class PlayerImportDTO {

    @XmlElement(name = "first-name")
    @Size(min = 3)
    private String firstName;

    @XmlElement(name = "last-name")
    @Size(min = 3)
    private String lastName;

    @XmlElement
    @Email
    private String email;

    @XmlElement(name = "birth-date")
    private String birthDate;

    @XmlElement(name = "position")
    private PlayerPosition playerPosition;

    @XmlElement(name = "town")
    private TownNameDTO town;

    @XmlElement(name = "team")
    private TeamNameDTO team;

    @XmlElement(name = "stat")
    private StatIdDTO stat;

    public PlayerImportDTO() {
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public PlayerPosition getPlayerPosition() {
        return playerPosition;
    }

    public TownNameDTO getTown() {
        return town;
    }

    public TeamNameDTO getTeam() {
        return team;
    }

    public StatIdDTO getStat() {
        return stat;
    }
}
