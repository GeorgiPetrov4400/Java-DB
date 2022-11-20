package com.example.json.productShop.services;

import java.io.FileNotFoundException;

public interface SeedService {

    void seedUsers() throws FileNotFoundException;

    void seedProducts() throws FileNotFoundException;

    void seedCategories() throws FileNotFoundException;

    default void seedAll() throws FileNotFoundException {
        seedUsers();
        seedCategories();
        seedProducts();
    }
}
