package com.example.springdataautomappinggamestoreexercise.services;

import com.example.springdataautomappinggamestoreexercise.entities.User;
import com.example.springdataautomappinggamestoreexercise.entities.dtos.UserRegisterDTO;
import com.example.springdataautomappinggamestoreexercise.repositories.UserRepository;
import com.example.springdataautomappinggamestoreexercise.utils.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
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
        userRepository.save(user);
    }
}
