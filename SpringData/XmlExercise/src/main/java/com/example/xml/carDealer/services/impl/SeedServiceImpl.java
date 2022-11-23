package com.example.xml.carDealer.services.impl;

import com.example.xml.carDealer.entities.Car;
import com.example.xml.carDealer.entities.Part;
import com.example.xml.carDealer.entities.Supplier;
import com.example.xml.carDealer.repositories.*;
import com.example.xml.productShop.services.SeedService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.LongStream;

@Service
public class SeedServiceImpl implements SeedService {

    private static final String SUPPLIER_JSON_PATH = "src/main/resources/carDealerJsons/suppliers.json";
    private static final String PART_JSON_PATH = "src/main/resources/carDealerJsons/parts.json";
    private static final String CAR_JSON_PATH = "src/main/resources/carDealerJsons/cars.json";
    private static final String CUSTOMER_JSON_PATH = "src/main/resources/carDealerJsons/customers.json";


    private final SupplierRepository supplierRepository;
    private final PartRepository partRepository;
    private final CarRepository carRepository;
    private final CustomerRepository customerRepository;
    private final SaleRepository saleRepository;

    public SeedServiceImpl(SupplierRepository supplierRepository, PartRepository partRepository, CarRepository carRepository, CustomerRepository customerRepository, SaleRepository saleRepository) {
        this.supplierRepository = supplierRepository;
        this.partRepository = partRepository;
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
        this.saleRepository = saleRepository;
    }

    @Override
    public void seedUsers() throws IOException {

    }

    @Override
    public void seedProducts() throws IOException {

    }

    @Override
    public void seedCategories() throws IOException {

    }

    private Optional<Supplier> getRandomSupplier() {
        long suppliersCount = this.supplierRepository.count();
        long randomSupplierId = new Random().nextLong(suppliersCount) + 1;

        Optional<Supplier> supplier = this.supplierRepository.findById(randomSupplierId);
        return supplier;
    }

    private Part setRandomSupplier(Part part) {
        Optional<Supplier> supplier = getRandomSupplier();
        part.setSupplier(supplier.get());

        return part;
    }

    private Car setRandomParts(Car car) {
        final Random random = new Random();
        final long numberOfParts = random.nextLong(3, 6);

        final List<Part> parts = new ArrayList<>();

        LongStream.range(0, numberOfParts)
                .forEach(number -> {
//                    Part part = this.partRepository.getRandomEntity().orElseThrow(NoSuchElementException::new);
//
//                    while (parts.contains(part)) {
//                        part = this.partRepository.getRandomEntity().orElseThrow(NoSuchElementException::new);
//                    }

//                    parts.add(part);
                });

        car.setParts(parts);
        return car;

    }


}
