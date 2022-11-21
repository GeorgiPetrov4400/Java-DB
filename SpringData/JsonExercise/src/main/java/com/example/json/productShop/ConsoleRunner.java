package com.example.json.productShop;

import com.example.json.productShop.entities.dtos.CategoryStatistics;
import com.example.json.productShop.services.ProductService;
import com.example.json.productShop.services.SeedService;
import com.example.json.productShop.services.UserService;
import com.example.json.productShop.utils.JsonOutput;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final SeedService seedService;
    private final ProductService productService;
    private final UserService userService;
    private final Gson gson;

    @Autowired
    public ConsoleRunner(SeedService seedService, ProductService productService, Gson gson, UserService userService) {
        this.seedService = seedService;
        this.productService = productService;
        this.userService = userService;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        //  this.seedService.seedUsers();
        // this.seedService.seedCategories();
        // this.seedService.seedProducts();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter query number from homework: ");

        String command = scanner.nextLine();

        switch (command) {
            case "1":
                productsInRange_01();
                break;
            case "2":
                successfullySoldProducts_02();
                break;
            case "3":
                categoriesByProductsCount_03();
                break;
            case "4":
                usersAndProducts_04();
                break;
            default:
                System.out.println("Exit");
                break;
        }
    }

    private void usersAndProducts_04() throws IOException {
        this.userService.getUserWithSoldProductsOrderByCount();
    }

    private void categoriesByProductsCount_03() throws IOException {
        List<CategoryStatistics> categoryStatistics = this.productService.getCategoryStatistics();

        JsonOutput.writeJsonToFile(categoryStatistics,
                Path.of("src/main/resources/productShopJsons/output/categories-by-products.json"));
    }

    private void successfullySoldProducts_02() throws IOException {
        this.userService.getUserWithSoldProducts();
    }

    private void productsInRange_01() throws IOException {
        this.productService.getProductsInPriceRange(BigDecimal.valueOf(800), BigDecimal.valueOf(1200));
    }
}
