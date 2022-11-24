package com.example.xml.carDealer.services.impl;

import com.example.xml.carDealer.config.Discount;
import com.example.xml.carDealer.entities.*;
import com.example.xml.carDealer.entities.carDtos.CarImportDTO;
import com.example.xml.carDealer.entities.customerDtos.CustomerImportDTO;
import com.example.xml.carDealer.entities.partDto.PartImportDTO;
import com.example.xml.carDealer.entities.supplierDtos.SupplierImportDTO;
import com.example.xml.carDealer.repositories.*;
import com.example.xml.carDealer.services.SeedService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

@Service
public class SeedServiceImpl implements SeedService {

    private static final Path SUPPLIER_XML_PATH =
            Path.of("src", "main", "resources", "xmlFilesCarDealer", "suppliers.xml");
    private static final Path PART_XML_PATH =
            Path.of("src", "main", "resources", "xmlFilesCarDealer", "parts.xml");
    private static final Path CAR_XML_PATH =
            Path.of("src", "main", "resources", "xmlFilesCarDealer", "cars.xml");
    private static final Path CUSTOMER_XML_PATH =
            Path.of("src", "main", "resources", "xmlFilesCarDealer", "customers.xml");


    private final SupplierRepository supplierRepository;
    private final PartRepository partRepository;
    private final CarRepository carRepository;
    private final CustomerRepository customerRepository;
    private final SaleRepository saleRepository;
    private final ModelMapper modelMapper;

    public SeedServiceImpl(SupplierRepository supplierRepository, PartRepository partRepository, CarRepository carRepository, CustomerRepository customerRepository, SaleRepository saleRepository) {
        this.supplierRepository = supplierRepository;
        this.partRepository = partRepository;
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
        this.saleRepository = saleRepository;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public void seedSuppliers() throws IOException, JAXBException {
        JAXBContext context = JAXBContext.newInstance(SupplierImportDTO.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        BufferedReader bufferedReader = Files.newBufferedReader(SUPPLIER_XML_PATH);
        SupplierImportDTO supplierImportDTO = (SupplierImportDTO) unmarshaller.unmarshal(bufferedReader);

        List<Supplier> suppliers = supplierImportDTO.getSuppliers().stream()
                .map(supplier -> modelMapper.map(supplier, Supplier.class))
                .collect(Collectors.toList());

        bufferedReader.close();
        this.supplierRepository.saveAll(suppliers);
    }

    @Override
    public void seedParts() throws IOException, JAXBException {
        JAXBContext context = JAXBContext.newInstance(PartImportDTO.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        BufferedReader bufferedReader = Files.newBufferedReader(PART_XML_PATH);
        PartImportDTO partImportDTO = (PartImportDTO) unmarshaller.unmarshal(bufferedReader);

        List<Part> parts = partImportDTO.getParts().stream()
                .map(part -> modelMapper.map(part, Part.class))
                .map(this::setRandomSupplier)
                .collect(Collectors.toList());

        bufferedReader.close();
        this.partRepository.saveAll(parts);
    }

    @Override
    public void seedCars() throws IOException, JAXBException {
        JAXBContext context = JAXBContext.newInstance(CarImportDTO.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        BufferedReader bufferedReader = Files.newBufferedReader(CAR_XML_PATH);
        CarImportDTO carImportDTO = (CarImportDTO) unmarshaller.unmarshal(bufferedReader);

        List<Car> cars = carImportDTO.getCars().stream()
                .map(car -> modelMapper.map(car, Car.class))
                .map(this::setRandomParts)
                .collect(Collectors.toList());

        bufferedReader.close();
        this.carRepository.saveAll(cars);
    }

    @Override
    public void seedCustomers() throws IOException, JAXBException {
        JAXBContext context = JAXBContext.newInstance(CustomerImportDTO.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        BufferedReader bufferedReader = Files.newBufferedReader(CUSTOMER_XML_PATH);
        CustomerImportDTO customerImportDTO = (CustomerImportDTO) unmarshaller.unmarshal(bufferedReader);

        List<Customer> customers = customerImportDTO.getCustomers().stream()
                .map(customer -> modelMapper.map(customer, Customer.class))
                .collect(Collectors.toList());

        bufferedReader.close();
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

                    while (saleCars.contains(car)) {
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
        final long numberOfParts = random.nextLong(10, 20);

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
