package com.example.json.carDealer;

import com.example.json.carDealer.services.SeedService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final SeedService seedService;

    public ConsoleRunner(SeedService seedService) {
        this.seedService = seedService;
    }

    @Override
    public void run(String... args) throws Exception {
        // seedService.seedSuppliers();
        // seedService.seedParts();
        // seedService.seedCars();
        // seedService.seedCustomers();
        // seedService.seedSales();


    }
}
