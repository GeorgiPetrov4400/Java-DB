package com.example.springdataautomappinggamestoreexercise.services;

import com.example.springdataautomappinggamestoreexercise.entities.dtos.GameAddDTO;

import java.math.BigDecimal;

public interface GameService {
    void addGame(GameAddDTO gameAddDTO);

    void editGame(Long gameId, BigDecimal price, Double size);

    void deleteGame(Long id);

    void getAllGames();

    void findGameByTitle(String title);

}
