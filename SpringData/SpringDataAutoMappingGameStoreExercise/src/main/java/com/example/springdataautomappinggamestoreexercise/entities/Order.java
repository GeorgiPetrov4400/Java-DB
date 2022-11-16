package com.example.springdataautomappinggamestoreexercise.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order extends BaseEntity {

    @ManyToOne
    private User buyer;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Game> games;

    public Order() {
        this.games = new HashSet<>();
    }

    public Order(User buyer, Set<Game> games) {
        this();
        this.buyer = buyer;
        this.games = games;
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public Set<Game> getGames() {
        return games;
    }

    public void setGames(Set<Game> games) {
        this.games = games;
    }
}
