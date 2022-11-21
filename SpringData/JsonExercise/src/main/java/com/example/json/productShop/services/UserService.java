package com.example.json.productShop.services;

import com.example.json.productShop.entities.User;
import com.example.json.productShop.entities.dtos.UserWithProductsWrapperDTO;
import com.example.json.productShop.entities.dtos.UserWithSoldProductsDTO;

import java.io.IOException;
import java.util.List;

public interface UserService {

    List<UserWithSoldProductsDTO> getUserWithSoldProducts() throws IOException;

    UserWithProductsWrapperDTO getUserWithSoldProductsOrderByCount() throws IOException;

  //  UserWithProductsWrapperDTO usersAndProducts();
}
