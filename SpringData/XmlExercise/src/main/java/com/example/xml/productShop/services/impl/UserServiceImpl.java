package com.example.xml.productShop.services.impl;

import com.example.xml.productShop.entities.User;
import com.example.xml.productShop.entities.userDtos.*;
import com.example.xml.productShop.repositories.UserRepository;
import com.example.xml.productShop.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.modelMapper = new ModelMapper();
    }


    @Override
    @Transactional
    public ExportSellerDTO getUserWithSoldProducts() {
        List<User> users = this.userRepository.findAllWithSoldProducts();

        List<UserWithSoldProductsDTO> collect = users.stream()
                .map(user -> modelMapper.map(user, UserWithSoldProductsDTO.class))
                .collect(Collectors.toList());

        return new ExportSellerDTO(collect);
    }

    @Override
    @Transactional
    public UserWithProductsWrapperDTO getUserWithSoldProductsOrderByCount() {

        final List<UserWithProductsDTO> usersAndProducts = this.userRepository
                .findAllBySellingProductsBuyerIsNotNullOrderBySellingProductsBuyerFirstName()
                .stream()
                .map(user -> this.modelMapper.map(user, UserDTO.class))
                .map(UserDTO::toUserWithProductsDTO)
                .collect(Collectors.toList());

        final UserWithProductsWrapperDTO usersWithProductsWrapperDTO =
                new UserWithProductsWrapperDTO(usersAndProducts);

        return usersWithProductsWrapperDTO;
    }
}

