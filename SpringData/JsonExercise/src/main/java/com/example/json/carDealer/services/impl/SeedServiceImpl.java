package com.example.json.carDealer.services.impl;

import com.example.json.carDealer.constant.Discount;
import com.example.json.carDealer.entities.*;
import com.example.json.carDealer.entities.dtos.CarImportDTO;
import com.example.json.carDealer.entities.dtos.CustomerImportDTO;
import com.example.json.carDealer.entities.dtos.PartImportDTO;
import com.example.json.carDealer.entities.dtos.SupplierInputDTO;
import com.example.json.carDealer.repositories.*;
import com.example.json.carDealer.services.SeedService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
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
    private final ModelMapper modelMapper;
    private final Gson gson;

    @Autowired
    public SeedServiceImpl(SupplierRepository supplierRepository, PartRepository partRepository, CarRepository carRepository, CustomerRepository customerRepository, SaleRepository saleRepository, Gson gson) {
        this.supplierRepository = supplierRepository;
        this.partRepository = partRepository;
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
        this.saleRepository = saleRepository;
        this.modelMapper = new ModelMapper();
        this.gson = gson;
    }

    @Override
    public void seedSuppliers() throws IOException {
        FileReader fileReader = new FileReader(SUPPLIER_JSON_PATH);
        SupplierInputDTO[] supplierInputDTOS = this.gson.fromJson(fileReader, SupplierInputDTO[].class);

        List<Supplier> suppliers = Arrays.stream(supplierInputDTOS)
                .map(supplier -> this.modelMapper.map(supplier, Supplier.class))
                .collect(Collectors.toList());

        fileReader.close();
        this.supplierRepository.saveAll(suppliers);
    }

    @Override
    public void seedParts() throws IOException {
        FileReader fileReader = new FileReader(PART_JSON_PATH);
        PartImportDTO[] partImportDTOS = this.gson.fromJson(fileReader, PartImportDTO[].class);

        List<Part> parts = Arrays.stream(partImportDTOS).map(part -> this.modelMapper.map(part, Part.class))
                .map(this::setRandomSupplier)
                .collect(Collectors.toList());

        fileReader.close();
        this.partRepository.saveAll(parts);
    }

    @Override
    public void seedCars() throws IOException {
        FileReader fileReader = new FileReader(CAR_JSON_PATH);
        CarImportDTO[] carImportDTOS = this.gson.fromJson(fileReader, CarImportDTO[].class);

        List<Car> cars = Arrays.stream(carImportDTOS).map(car -> this.modelMapper.map(car, Car.class))
                .map(this::setRandomParts)
                .collect(Collectors.toList());

        fileReader.close();
        this.carRepository.saveAll(cars);
    }

    @Override
    public void seedCustomers() throws IOException {
        FileReader fileReader = new FileReader(CUSTOMER_JSON_PATH);
        CustomerImportDTO[] customerImportDTOS = this.gson.fromJson(fileReader, CustomerImportDTO[].class);

        List<Customer> customers = Arrays.stream(customerImportDTOS)
                .map(customer -> this.modelMapper.map(customer, Customer.class))
                .collect(Collectors.toList());

        fileReader.close();
        this.customerRepository.saveAll(customers);
    }

    @Override
    public void seedSales() {
            final Random random = new Random();

            long high = this.carRepository.count();
            final long numberOfSales = random.nextLong(high);

            final List<Sale> sales = new ArrayList<>();
            Set<Car> saleCars = new HashSet<>();

            LongStream.range(0, numberOfSales)
                    .forEach(number -> {
                        Car car = this.carRepository.getRandomEntity().orElseThrow(NoSuchElementException::new);

                        while (saleCars.contains(car)){
                            car = this.carRepository.getRandomEntity().orElseThrow(NoSuchElementException::new);
                        }

                        saleCars.add(car);

                        Customer customer = this.customerRepository.getRandomEntity().orElseThrow(NoSuchElementException::new);

                        Integer discount = Discount.getDiscount();

                        Sale newSale = new Sale(discount, car, customer);
                        sales.add(newSale);
                    });

            this.saleRepository.saveAllAndFlush(sales);
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
                    Part part = this.partRepository.getRandomEntity().orElseThrow(NoSuchElementException::new);

                    while (parts.contains(part)) {
                        part = this.partRepository.getRandomEntity().orElseThrow(NoSuchElementException::new);
                    }

                    parts.add(part);
                });

        car.setParts(parts);
        return car;

    }
}
