package com.example.json.productShop.services.impl;

import com.example.json.productShop.entities.Category;
import com.example.json.productShop.entities.Product;
import com.example.json.productShop.entities.User;
import com.example.json.productShop.entities.dtos.CategoryInputDTO;
import com.example.json.productShop.entities.dtos.ProductInputDTO;
import com.example.json.productShop.entities.dtos.UserInputDTO;
import com.example.json.productShop.repositories.CategoryRepository;
import com.example.json.productShop.repositories.ProductRepository;
import com.example.json.productShop.repositories.UserRepository;
import com.example.json.productShop.services.SeedService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SeedServiceImpl implements SeedService {

    private static final String JSON_USERS_PATH = "src/main/resources/productShopJsons/users.json";
    private static final String JSON_PRODUCTS_PATH = "src/main/resources/productShopJsons/products.json";
    private static final String JSON_CATEGORIES_PATH = "src/main/resources/productShopJsons/categories.json";

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;

    @Autowired
    public SeedServiceImpl(UserRepository userRepository, ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.modelMapper = new ModelMapper();
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    @Override
    public void seedUsers() throws IOException {
        FileReader fileReader = new FileReader(JSON_USERS_PATH);
        UserInputDTO[] userInputDTOS = this.gson.fromJson(fileReader, UserInputDTO[].class);

        List<User> users = Arrays.stream(userInputDTOS)
                .map(input -> this.modelMapper.map(input, User.class))
                .collect(Collectors.toList());

        fileReader.close();
        this.userRepository.saveAll(users);
    }

    @Override
    public void seedProducts() throws IOException {
        FileReader fileReader = new FileReader(JSON_PRODUCTS_PATH);
        ProductInputDTO[] productInputDTOS = this.gson.fromJson(fileReader, ProductInputDTO[].class);

        List<Product> products = Arrays.stream(productInputDTOS)
                .map(input -> this.modelMapper.map(input, Product.class))
                .map(this::setRandomSeller)
                .map(this::setRandomBuyer)
                .map(this::setRandomCategory)
                .collect(Collectors.toList());

        fileReader.close();
        this.productRepository.saveAll(products);
    }

    @Override
    public void seedCategories() throws IOException {
        FileReader fileReader = new FileReader(JSON_CATEGORIES_PATH);
        CategoryInputDTO[] categoryInputDTOS = this.gson.fromJson(fileReader, CategoryInputDTO[].class);

        List<Category> categories = Arrays.stream(categoryInputDTOS)
                .map(input -> this.modelMapper.map(input, Category.class))
                .collect(Collectors.toList());

        fileReader.close();
        this.categoryRepository.saveAll(categories);
    }

    private Product setRandomCategory(Product product) {
        Random random = new Random();

        long categoriesCount = this.categoryRepository.count();
        long count = random.nextLong(categoriesCount);

        Set<Category> categories = new HashSet<>();
        for (int i = 0; i < count; i++) {
            long randomId = random.nextLong(count) + 1;

            Optional<Category> randomCategory = this.categoryRepository.findById(randomId);

            categories.add(randomCategory.get());
        }

        product.setCategories(categories);

        return product;
    }

    private Product setRandomSeller(Product product) {
        Optional<User> seller = getRandomUser();
        product.setSeller(seller.get());

        return product;
    }

    private Product setRandomBuyer(Product product) {
        if (product.getPrice().compareTo(BigDecimal.valueOf(1000)) > 0) {
            return product;
        }

        Optional<User> buyer = getRandomUser();
        product.setBuyer(buyer.get());

        return product;
    }

    private Optional<User> getRandomUser() {
        long usersCount = this.userRepository.count();
        long randomUserId = new Random().nextLong(usersCount) + 1;

        Optional<User> seller = this.userRepository.findById(randomUserId);
        return seller;
    }
}
