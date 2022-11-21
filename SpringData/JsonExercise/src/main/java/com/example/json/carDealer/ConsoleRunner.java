package com.example.json.carDealer;

import com.example.json.carDealer.entities.dtos.CarWithPartsDTO;
import com.example.json.carDealer.entities.dtos.CarsToyotaDTO;
import com.example.json.carDealer.entities.dtos.CustomerNameAndBirthdayDTO;
import com.example.json.carDealer.entities.dtos.SupplierNotAbroadDTO;
import com.example.json.carDealer.repositories.SupplierRepository;
import com.example.json.carDealer.services.CarService;
import com.example.json.carDealer.services.CustomService;
import com.example.json.carDealer.services.SeedService;
import com.example.json.carDealer.utils.JsonOutput;
import com.google.gson.Gson;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final SeedService seedService;
    private final CustomService customService;
    private final CarService carService;
    private final SupplierRepository supplierRepository;
    private final Gson gson;


    public ConsoleRunner(SeedService seedService, CustomService customService, CarService carService, SupplierRepository supplierRepository, Gson gson) {
        this.seedService = seedService;
        this.customService = customService;
        this.carService = carService;
        this.supplierRepository = supplierRepository;
        this.gson = gson;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        // seedService.seedSuppliers();
        // seedService.seedParts();
        // seedService.seedCars();
        // seedService.seedCustomers();
        // seedService.seedSales();

        //  seedService.seedAll();


        // Query 1 – Ordered Customers
        // orderedCustomers_01();

        // Query 2 – Cars from Make Toyota
        // carsFromMaker_02();

        // Query 3 – Local Suppliers
        // localSuppliers_03();

        // Query 4 – Cars with Their List of Parts
        // carsWithTheirParts_04();

        // Query 5 – Total Sales by Customer


        // Query 6 – Sales with Applied Discount


    }

    private void carsWithTheirParts_04() throws IOException {
        List<CarWithPartsDTO> carWithPartsDTOS = this.carService.getAllCarWithParts();

        JsonOutput.writeJsonToFile(carWithPartsDTOS,
                Path.of("src/main/resources/carDealerJsons/output/cars-and-parts.json"));
    }

    private void localSuppliers_03() throws IOException {
        List<SupplierNotAbroadDTO> allSupplierNotAbroad =
                this.supplierRepository.findAllByImporterIsFalseOrderById();

        JsonOutput.writeJsonToFile(allSupplierNotAbroad,
                Path.of("src/main/resources/carDealerJsons/output/local-suppliers.json"));
    }

    private void carsFromMaker_02() throws IOException {
        List<CarsToyotaDTO> allCarsByMake = this.carService.findAllCarsByMake("Toyota");

        JsonOutput.writeJsonToFile(allCarsByMake,
                Path.of("src/main/resources/carDealerJsons/output/toyota-cars.json"));
    }

    private void orderedCustomers_01() throws IOException {
        List<CustomerNameAndBirthdayDTO> allCustomers = this.customService.getAllCustomers();

        JsonOutput.writeJsonToFile(allCustomers,
                Path.of("src/main/resources/carDealerJsons/output/ordered-customers.json"));
    }
}
