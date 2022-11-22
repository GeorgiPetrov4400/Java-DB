package com.example.json.productShop.services.impl;

import com.example.json.productShop.entities.User;
import com.example.json.productShop.entities.dtos.UserDTO;
import com.example.json.productShop.entities.dtos.UserWithProductsDTO;
import com.example.json.productShop.entities.dtos.UserWithProductsWrapperDTO;
import com.example.json.productShop.entities.dtos.UserWithSoldProductsDTO;
import com.example.json.productShop.repositories.UserRepository;
import com.example.json.productShop.services.UserService;
import com.example.json.productShop.utils.JsonOutput;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Path;
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
    public List<UserWithSoldProductsDTO> getUserWithSoldProducts() throws IOException {
        List<User> allUsersWithSoldProducts = this.userRepository.findAllBySellingProductsGreaterThanEqual();

        List<UserWithSoldProductsDTO> userWithSoldProductsDTOList = allUsersWithSoldProducts.stream()
                .map(user -> this.modelMapper.map(user, UserWithSoldProductsDTO.class))
                .collect(Collectors.toList());

        JsonOutput.writeJsonToFile(userWithSoldProductsDTOList,
                Path.of("src/main/resources/productShopJsons/output/users-sold-products.json"));

        return userWithSoldProductsDTOList;
    }

    @Override
    public UserWithProductsWrapperDTO getUserWithSoldProductsOrderByCount() throws IOException {

//        List<UserWithProductsDTO> userWithProductsDTOList =
//                this.userRepository.findAllWithSoldProductsOrderByCount()
//                        .stream().map(user -> this.modelMapper.map(user, UserDTO.class))
//                        .map(UserDTO::toUserWithProductsDTO).collect(Collectors.toList());
//
//        UserWithProductsWrapperDTO userWithProductsWrapperDTO =
//                new UserWithProductsWrapperDTO(userWithProductsDTOList);

        List<UserWithProductsDTO> usersAndProducts =
                this.userRepository.findAllBySellingProductsBuyerIsNotNullOrderBySellingProductsBuyerFirstName().stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .map(UserDTO::toUserWithProductsDTO).collect(Collectors.toList());

        UserWithProductsWrapperDTO userWithProductsWrapperDTO =
                new UserWithProductsWrapperDTO(usersAndProducts);

        JsonOutput.writeToJson(userWithProductsWrapperDTO,
                Path.of("src/main/resources/productShopJsons/output/users-and-products.json"));

        return userWithProductsWrapperDTO;
    }
}
