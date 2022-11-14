package com.example.springdataautomappinggamestoreexercise;

import com.example.springdataautomappinggamestoreexercise.entities.dtos.UserRegisterDTO;
import com.example.springdataautomappinggamestoreexercise.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final BufferedReader bufferedReader;
    private final UserService userService;

    public ConsoleRunner(UserService userService) {
        this.userService = userService;
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
                    break;
                case "Logout":
                    break;
            }
        }
    }
}
