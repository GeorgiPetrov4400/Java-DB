package com.example.springdataautomappinggamestoreexercise;

import com.example.springdataautomappinggamestoreexercise.entities.dtos.GameAddDTO;
import com.example.springdataautomappinggamestoreexercise.entities.dtos.UserLoginDTO;
import com.example.springdataautomappinggamestoreexercise.entities.dtos.UserRegisterDTO;
import com.example.springdataautomappinggamestoreexercise.services.GameService;
import com.example.springdataautomappinggamestoreexercise.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final BufferedReader bufferedReader;
    private final UserService userService;
    private final GameService gameService;

    public ConsoleRunner(UserService userService, GameService gameService) {
        this.userService = userService;
        this.gameService = gameService;
        this.bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void run(String... args) throws Exception {

        while (true) {
            System.out.println("Enter your commands: ");
            String[] input = bufferedReader.readLine().split("\\|");
            String command = input[0];

            switch (command) {
                case "RegisterUser":
                    userService.registerUser(new UserRegisterDTO(input[1], input[2], input[3], input[4]));
                    break;

                case "LoginUser":
                    userService.loginUser(new UserLoginDTO(input[1], input[2]));
                    break;

                case "Logout":
                    userService.logout();
                    break;

                case "AddGame":
                    gameService.addGame(new GameAddDTO(input[1], new BigDecimal(input[2]),
                            Double.parseDouble(input[3]), input[4], input[5], input[6],
                            LocalDate.parse(input[7], DateTimeFormatter.ofPattern("dd-MM-yyyy"))));
                    break;

                case "EditGame":
                    Long gameId = Long.parseLong(input[1]);
                    String[] priceInput = input[2].split("=");
                    BigDecimal price = new BigDecimal(priceInput[1]);
                    String[] sizeInput = input[3].split("=");
                    double size = Double.parseDouble(sizeInput[1]);
                    gameService.editGame(gameId, price, size);
                    break;

                case "DeleteGame":
                    gameService.deleteGame(Long.parseLong(input[1]));
                    break;

                case "AllGames":
                    gameService.getAllGames();
                    break;

                case "DetailGame":
                    gameService.findGameByTitle(input[1]);
                    break;

                case "OwnedGames":
                    userService.getOwnedGames();
                    break;
            }
        }
    }
}
