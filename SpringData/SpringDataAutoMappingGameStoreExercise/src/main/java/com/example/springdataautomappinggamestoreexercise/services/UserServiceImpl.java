package com.example.springdataautomappinggamestoreexercise.services;

import com.example.springdataautomappinggamestoreexercise.entities.Game;
import com.example.springdataautomappinggamestoreexercise.entities.User;
import com.example.springdataautomappinggamestoreexercise.entities.dtos.UserLoginDTO;
import com.example.springdataautomappinggamestoreexercise.entities.dtos.UserRegisterDTO;
import com.example.springdataautomappinggamestoreexercise.repositories.GameRepository;
import com.example.springdataautomappinggamestoreexercise.repositories.UserRepository;
import com.example.springdataautomappinggamestoreexercise.utils.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private User loggedInUser;
    private final GameRepository gameRepository;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, ValidationUtil validationUtil, GameRepository gameRepository) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.gameRepository = gameRepository;
    }

    @Override
    public void registerUser(UserRegisterDTO userRegisterDTO) {
        if (!userRegisterDTO.getPassword().equals(userRegisterDTO.getConfirmPassword())) {
            System.out.println("Confirm password must be equal to password!");
            return;
        }

        Set<ConstraintViolation<UserRegisterDTO>> violations = validationUtil.violation(userRegisterDTO);
        if (!violations.isEmpty()) {
            violations.stream().map(ConstraintViolation::getMessage).forEach(System.out::println);
            return;
        }

        User user = modelMapper.map(userRegisterDTO, User.class);

        if (userRepository.count() == 0) {
            user.setAdmin(true);
        }

        userRepository.save(user);
        System.out.println(user.getFullName() + " was registered");
    }

    @Override
    public void loginUser(UserLoginDTO userLoginDTO) {
        Set<ConstraintViolation<UserLoginDTO>> violations = validationUtil.violation(userLoginDTO);
        if (!violations.isEmpty()) {
            violations.stream().map(ConstraintViolation::getMessage).forEach(System.out::println);
            return;
        }

        User user = userRepository.findByEmailAndPassword(userLoginDTO.getEmail(), userLoginDTO.getPassword())
                .orElse(null);

        if (user == null) {
            System.out.println("Incorrect username / password");
            return;
        }

        loggedInUser = user;
        System.out.println("Successfully logged in " + loggedInUser.getFullName());

    }

    @Override
    public void logout() {
        if (loggedInUser == null) {
            System.out.println("Cannot log out. No user was logged in.");
        } else {
            loggedInUser = null;
        }
    }

    @Override
    public User getLoggedInUser() {
        if (loggedInUser == null) {
            try {
                throw new IOException("User not logged in");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return this.loggedInUser;
    }

    @Override
    @Transactional
    public void getOwnedGames() {
        if (loggedInUser != null && !loggedInUser.isAdmin()) {
            Game firstGame = this.gameRepository.findGameById(1L);
            Game secondGame = this.gameRepository.findGameById(3L);

            Set<Game> purchasedGames = new HashSet<>();

            purchasedGames.add(firstGame);
            purchasedGames.add(secondGame);

            loggedInUser.setGames(purchasedGames);

            this.loggedInUser.getGames().stream().map(Game::getTitle).collect(Collectors.toList())
                    .forEach(System.out::println);
        } else {
            System.out.println("Login user first");
        }
    }

}
