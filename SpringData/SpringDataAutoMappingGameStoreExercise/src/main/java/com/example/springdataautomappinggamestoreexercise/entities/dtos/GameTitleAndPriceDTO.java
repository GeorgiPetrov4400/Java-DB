package com.example.springdataautomappinggamestoreexercise.entities.dtos;

import javax.persistence.Column;
import java.math.BigDecimal;

public class GameTitleAndPriceDTO {

    @Column
    private String title;

    @Column
    private BigDecimal price;

    public GameTitleAndPriceDTO() {
    }

    public GameTitleAndPriceDTO(String title, BigDecimal price) {
        this.title = title;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
