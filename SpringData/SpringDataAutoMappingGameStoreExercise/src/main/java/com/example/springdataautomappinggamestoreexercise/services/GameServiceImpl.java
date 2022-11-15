package com.example.springdataautomappinggamestoreexercise.services;

import com.example.springdataautomappinggamestoreexercise.entities.Game;
import com.example.springdataautomappinggamestoreexercise.entities.dtos.GameAddDTO;
import com.example.springdataautomappinggamestoreexercise.repositories.GameRepository;
import com.example.springdataautomappinggamestoreexercise.utils.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;

@Service
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public GameServiceImpl(GameRepository gameRepository, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.gameRepository = gameRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }


    @Override
    public void addGame(GameAddDTO gameAddDTO) {

        Set<ConstraintViolation<GameAddDTO>> violations = validationUtil.violation(gameAddDTO);
        if (!violations.isEmpty()) {
            violations.stream().map(ConstraintViolation::getMessage).forEach(System.out::println);
            return;
        }

        Game game = modelMapper.map(gameAddDTO, Game.class);
        gameRepository.save(game);
        System.out.println("Successfully added " + game.getTitle());
    }

    @Override
    public void editGame(Long gameId, BigDecimal price, Double size) {
        Game game = gameRepository.findById(gameId).orElse(null);

        if (game == null) {
            System.out.println("Invalid game id");
            return;
        }

        game.setPrice(price);
        game.setSize(size);

        gameRepository.save(game);
        System.out.println("Successfully edited " + game.getTitle());
    }

    @Override
    public void deleteGame(Long id) {
        Optional<Game> gameById = gameRepository.findById(id);
        if (gameById.isEmpty()) {
            System.out.println("Cannot delete non existing game");
            return;
        }

        gameRepository.deleteById(id);
    }
}
