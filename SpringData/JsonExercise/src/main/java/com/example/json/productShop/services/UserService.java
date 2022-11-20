package com.example.json.productShop.services;

import com.example.json.productShop.entities.User;
import com.example.json.productShop.entities.dtos.UserWithProductsWrapperDTO;
import com.example.json.productShop.entities.dtos.UserWithSoldProductsDTO;

import java.util.List;

public interface UserService {

    List<UserWithSoldProductsDTO> getUserWithSoldProducts();

    UserWithProductsWrapperDTO getUserWithSoldProductsOrderByCount();

  //  UserWithProductsWrapperDTO usersAndProducts();
}
