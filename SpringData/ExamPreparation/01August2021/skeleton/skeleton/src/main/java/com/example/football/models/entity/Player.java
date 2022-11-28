package com.example.football.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "players")
public class Player extends BaseEntity {
    
    //•	email – accepts valid email addresses (must contains '@' and '.' – a dot).
    // The values are unique in the database.
    //•	birth date – a date in the "dd/MM/yyyy" format.
    //•	position – one of the following – ATT, MID, DEF.
    //o	Note: The players table has relations with the towns, teams and stats tables.

    @Column(name = "first_name", nullable = false)
    @Size(min = 3)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @Size(min = 3)
    private String lastName;

    private String email;

    private Date birthDate;

    private PlayerPosition playerPosition;

    public Player() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public PlayerPosition getPlayerPosition() {
        return playerPosition;
    }

    public void setPlayerPosition(PlayerPosition playerPosition) {
        this.playerPosition = playerPosition;
    }
}
