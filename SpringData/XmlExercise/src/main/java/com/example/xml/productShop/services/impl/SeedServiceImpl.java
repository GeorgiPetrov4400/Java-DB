package com.example.xml.productShop.services.impl;

import com.example.xml.productShop.entities.Category;
import com.example.xml.productShop.entities.Product;
import com.example.xml.productShop.entities.User;
import com.example.xml.productShop.entities.categoryDtos.CategoryImportDTO;
import com.example.xml.productShop.entities.productDtos.ProductImportDTO;
import com.example.xml.productShop.entities.userDtos.UserImportDTO;
import com.example.xml.productShop.repositories.CategoryRepository;
import com.example.xml.productShop.repositories.ProductRepository;
import com.example.xml.productShop.repositories.UserRepository;
import com.example.xml.productShop.services.SeedService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SeedServiceImpl implements SeedService {

    private static final Path XML_USERS_PATH =
            Path.of("src", "main", "resources", "xmlFilesProductShop", "users.xml");
    private static final Path XML_PRODUCTS_PATH =
            Path.of("src", "main", "resources", "xmlFilesProductShop", "products.xml");
    private static final Path XML_CATEGORIES_PATH =
            Path.of("src", "main", "resources", "xmlFilesProductShop", "categories.xml");

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public SeedServiceImpl(UserRepository userRepository, ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public void seedUsers() throws IOException, JAXBException {
        JAXBContext context = JAXBContext.newInstance(UserImportDTO.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        BufferedReader bufferedReader = Files.newBufferedReader(XML_USERS_PATH);
        UserImportDTO userImportDTO = (UserImportDTO) unmarshaller.unmarshal(bufferedReader);

        List<User> users = userImportDTO.getUsers().stream()
                .map(user -> this.modelMapper.map(user, User.class))
                .collect(Collectors.toList());

        bufferedReader.close();
        this.userRepository.saveAll(users);
    }

    @Override
    public void seedProducts() throws IOException, JAXBException {
        JAXBContext context = JAXBContext.newInstance(ProductImportDTO.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        BufferedReader bufferedReader = Files.newBufferedReader(XML_PRODUCTS_PATH);
        ProductImportDTO productImportDTO = (ProductImportDTO) unmarshaller.unmarshal(bufferedReader);

        List<Product> products = productImportDTO.getProducts().stream()
                .map(product -> this.modelMapper.map(product, Product.class))
                .map(this::setRandomBuyer)
                .map(this::setRandomSeller)
                .map(this::setRandomCategory)
                .collect(Collectors.toList());

        bufferedReader.close();
        this.productRepository.saveAll(products);
    }

    @Override
    public void seedCategories() throws IOException, JAXBException {
        JAXBContext context = JAXBContext.newInstance(CategoryImportDTO.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        FileReader fileReader = new FileReader(XML_CATEGORIES_PATH.toAbsolutePath().toString());
        CategoryImportDTO categoryImportDTO = (CategoryImportDTO) unmarshaller.unmarshal(fileReader);

        List<Category> categories = categoryImportDTO.getCategories().stream()
                .map(categoryNameDTO -> new Category(categoryNameDTO.getName()))
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
        if (product.getPrice().compareTo(BigDecimal.valueOf(1400)) > 0) {
            return product;
        }

        Optional<User> buyer = getRandomUser();
        product.setBuyer(buyer.get());

        return product;
    }

    private Optional<User> getRandomUser() {
        long usersCount = this.userRepository.count();
        long randomUserId = new Random().nextLong(usersCount) + 1;

        return this.userRepository.findById(randomUserId);
    }
}
