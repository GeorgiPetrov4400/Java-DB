package com.example.xml.productShop.services;

import com.example.xml.productShop.entities.userDtos.ExportSellerDTO;
import com.example.xml.productShop.entities.userDtos.UserWithProductsWrapperDTO;
import com.example.xml.productShop.entities.userDtos.UserWithSoldProductsDTO;

import java.util.List;

public interface UserService {

    ExportSellerDTO getUserWithSoldProducts();

    UserWithProductsWrapperDTO getUserWithSoldProductsOrderByCount();
}
