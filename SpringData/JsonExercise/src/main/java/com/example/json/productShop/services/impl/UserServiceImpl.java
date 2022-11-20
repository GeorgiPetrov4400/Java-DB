package com.example.json.productShop.services.impl;

import com.example.json.productShop.entities.User;
import com.example.json.productShop.entities.dtos.UserDTO;
import com.example.json.productShop.entities.dtos.UserWithProductsDTO;
import com.example.json.productShop.entities.dtos.UserWithProductsWrapperDTO;
import com.example.json.productShop.entities.dtos.UserWithSoldProductsDTO;
import com.example.json.productShop.repositories.UserRepository;
import com.example.json.productShop.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = new ModelMapper();
    }

    @Override
    @Transactional
    public List<UserWithSoldProductsDTO> getUserWithSoldProducts() {
        List<User> allUsersWithSoldProducts = this.userRepository.findAllBySellingProductsGreaterThanEqual();

        return allUsersWithSoldProducts.stream()
                .map(user -> this.modelMapper.map(user, UserWithSoldProductsDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserWithProductsWrapperDTO getUserWithSoldProductsOrderByCount() {

        List<UserWithProductsDTO> userWithProductsDTOList =
                this.userRepository.findAllWithSoldProductsOrderByCount()
                .stream().map(user -> this.modelMapper.map(user, UserDTO.class))
                .map(UserDTO::toUserWithProductsDTO).collect(Collectors.toList());

        return new UserWithProductsWrapperDTO(userWithProductsDTOList);
    }

//    @Override
//    public UserWithProductsWrapperDTO usersAndProducts() {
//
//        List<UserWithProductsDTO> collect =
//                this.userRepository.findAllBySellingProductsBuyerIsNotNullOrderBySellingProductsBuyerFirstName()
//                        .stream().map(user -> this.modelMapper.map(user, UserDTO.class))
//                        .map(UserDTO::toUserWithProductsDTO).collect(Collectors.toList());
//
//        UserWithProductsWrapperDTO userWithProductsWrapperDTO =
//                new UserWithProductsWrapperDTO(collect);
//
//        return userWithProductsWrapperDTO;
//    }

}
