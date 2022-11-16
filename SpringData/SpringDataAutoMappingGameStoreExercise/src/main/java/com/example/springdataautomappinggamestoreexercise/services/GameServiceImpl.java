package com.example.springdataautomappinggamestoreexercise.services;

import com.example.springdataautomappinggamestoreexercise.entities.Game;
import com.example.springdataautomappinggamestoreexercise.entities.User;
import com.example.springdataautomappinggamestoreexercise.entities.dtos.GameAddDTO;
import com.example.springdataautomappinggamestoreexercise.repositories.GameRepository;
import com.example.springdataautomappinggamestoreexercise.utils.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.Set;

@Service
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final UserService userService;

    public GameServiceImpl(GameRepository gameRepository, ModelMapper modelMapper, ValidationUtil validationUtil, UserService userService) {
        this.gameRepository = gameRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.userService = userService;
    }


    @Override
    public void addGame(GameAddDTO gameAddDTO) {
        User loggedInUser = this.userService.getLoggedInUser();

        if (loggedInUser != null && loggedInUser.isAdmin()) {
            Set<ConstraintViolation<GameAddDTO>> violations = validationUtil.violation(gameAddDTO);
            if (!violations.isEmpty()) {
                violations.stream().map(ConstraintViolation::getMessage).forEach(System.out::println);
                return;
            }

            Game game = modelMapper.map(gameAddDTO, Game.class);
            gameRepository.save(game);
            System.out.println("Successfully added " + game.getTitle());
        } else {
            System.out.println("Only login Admin user can add game");
        }

    }

    @Override
    public void editGame(Long gameId, BigDecimal price, Double size) {
        User loggedInUser = this.userService.getLoggedInUser();

        if (loggedInUser != null && loggedInUser.isAdmin()) {
            Game game = gameRepository.findById(gameId).orElse(null);

            if (game == null) {
                System.out.println("Invalid game id");
                return;
            }

            game.setPrice(price);
            game.setSize(size);

            gameRepository.save(game);
            System.out.println("Successfully edited " + game.getTitle());
        } else {
            System.out.println("Only login Admin user can edit game");
        }
    }

    @Override
    public void deleteGame(Long id) {
        User loggedInUser = this.userService.getLoggedInUser();

        if (loggedInUser != null && loggedInUser.isAdmin()) {
            Game game = gameRepository.findById(id).orElse(null);

            if (game == null) {
                System.out.println("Cannot delete non existing game");
            } else {
                System.out.println("Delete " + game.getTitle());
                gameRepository.deleteById(id);
            }
        } else {
            System.out.println("Only login Admin user can delete game");
        }
    }

    @Override
    public void getAllGames() {
        this.gameRepository.findAll()
                .forEach(game -> System.out.println(game.getTitle() + " " + game.getPrice()));
    }

    @Override
    public void findGameByTitle(String title) {
        Game gameByTitle = this.gameRepository.findGameByTitle(title);

        if (gameByTitle == null) {
            System.out.println("Game does not exist in database");
            return;
        }

        System.out.printf("Title: %s%nPrice: %.2f%nDescription: %s%nRelease date: %s%n",
                gameByTitle.getTitle(), gameByTitle.getPrice(), gameByTitle.getDescription(),
                gameByTitle.getReleaseDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
    }

}

