package com.example.xml.productShop;

import com.example.xml.productShop.entities.categoryDtos.CategoryDataDTO;
import com.example.xml.productShop.entities.categoryDtos.CategoryStatisticsDTO;
import com.example.xml.productShop.entities.productDtos.ProductInRangeExportDTO;
import com.example.xml.productShop.entities.userDtos.ExportSellerDTO;
import com.example.xml.productShop.entities.userDtos.UserWithProductsWrapperDTO;
import com.example.xml.productShop.services.CategoryService;
import com.example.xml.productShop.services.ProductService;
import com.example.xml.productShop.services.SeedService;
import com.example.xml.productShop.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.List;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final SeedService seedService;
    private final CategoryService categoryService;
    private final ProductService productService;
    private final UserService userService;

    public ConsoleRunner(SeedService seedService, CategoryService categoryService, ProductService productService, UserService userService) {
        this.seedService = seedService;
        this.categoryService = categoryService;
        this.productService = productService;
        this.userService = userService;
    }


    @Override
    public void run(String... args) throws Exception {
        // seedService.seedUsers();
        //  seedService.seedCategories();
        //  seedService.seedProducts();
        //  seedService.seedAll();


        // Query 1 – Products in Range
        // productsInRange_01();

        // Query 2 – Successfully Sold Products
        // successfullySoldProducts_02();

        // Query 3 – Categories by Products Count
        // categoriesByProductsCount_03();

        // Query 4 – Users and Products
        // usersAndProducts_04();


    }

    private void usersAndProducts_04() throws JAXBException {
        UserWithProductsWrapperDTO usersAndProducts = this.userService.getUserWithSoldProductsOrderByCount();

        JAXBContext context = JAXBContext.newInstance(UserWithProductsWrapperDTO.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(usersAndProducts,
                Path.of("src/main/resources/outputProductShop/users-and-products.xml").toFile());
    }

    private void categoriesByProductsCount_03() throws JAXBException {
        List<CategoryStatisticsDTO> categoryStatistics = this.categoryService.getCategoryStatistics();

        CategoryDataDTO categoryDataDTO = new CategoryDataDTO(categoryStatistics);

        JAXBContext context = JAXBContext.newInstance(CategoryDataDTO.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(categoryDataDTO,
                Path.of("src/main/resources/outputProductShop/categories-by-products.xml").toFile());
    }

    private void successfullySoldProducts_02() throws JAXBException {
        ExportSellerDTO userWithSoldProducts = this.userService.getUserWithSoldProducts();

        JAXBContext context = JAXBContext.newInstance(ExportSellerDTO.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(userWithSoldProducts,
                Path.of("src/main/resources/outputProductShop/users-sold-products.xml").toFile());
    }

    private void productsInRange_01() throws JAXBException {
        ProductInRangeExportDTO productsInPriceRange =
                this.productService.getProductsInPriceRange(BigDecimal.valueOf(1400), BigDecimal.valueOf(1500));

        JAXBContext context = JAXBContext.newInstance(ProductInRangeExportDTO.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(productsInPriceRange,
                Path.of("src/main/resources/outputProductShop/products-in-range.xml").toFile());
    }
}
