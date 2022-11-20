package com.example.json.productShop;

import com.example.json.productShop.entities.User;
import com.example.json.productShop.entities.dtos.CategoryStatistics;
import com.example.json.productShop.entities.dtos.ProductWithoutBuyerDTO;
import com.example.json.productShop.entities.dtos.UserWithProductsWrapperDTO;
import com.example.json.productShop.entities.dtos.UserWithSoldProductsDTO;
import com.example.json.productShop.services.ProductService;
import com.example.json.productShop.services.SeedService;
import com.example.json.productShop.services.UserService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

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

        // Query 1 – Products in Range
        // productsInRange_01();

        // Query 2 – Successfully Sold Products
        // successfullySoldProducts_02();

        // Query 3 – Categories by Products Count
        // categoriesByProductsCount_03();

        // Query 4 – Users and Products
        UserWithProductsWrapperDTO users = this.userService.getUserWithSoldProductsOrderByCount();
      //  UserWithProductsWrapperDTO user = this.userService.usersAndProducts();

        String toJson = gson.toJson(users);
        System.out.println(toJson);
    }

    private void categoriesByProductsCount_03() throws IOException {
        List<CategoryStatistics> categoryStatistics = this.productService.getCategoryStatistics();

        FileWriter fileWriter =
                new FileWriter("src/main/resources/productShopJsons/output/categories-by-products.json");

        gson.toJson(categoryStatistics, fileWriter);

        fileWriter.flush();
        fileWriter.close();
    }

    private void successfullySoldProducts_02() throws IOException {
        List<UserWithSoldProductsDTO> userWithSoldProducts = this.userService.getUserWithSoldProducts();

        FileWriter fileWriter =
                new FileWriter("src/main/resources/productShopJsons/output/users-sold-products.json");

        gson.toJson(userWithSoldProducts, fileWriter);

        fileWriter.flush();
        fileWriter.close();
    }

    private void productsInRange_01() throws IOException {
        List<ProductWithoutBuyerDTO> productsInPriceRange =
                this.productService.getProductsInPriceRange(BigDecimal.valueOf(800), BigDecimal.valueOf(1200));

        FileWriter fileWriter =
                new FileWriter("src/main/resources/productShopJsons/output/products-in-range.json");

        gson.toJson(productsInPriceRange, fileWriter);

        fileWriter.flush();
        fileWriter.close();
    }
}
