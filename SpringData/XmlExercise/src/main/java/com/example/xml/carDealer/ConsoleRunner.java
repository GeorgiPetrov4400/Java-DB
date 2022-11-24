package com.example.xml.carDealer;

import com.example.xml.carDealer.entities.carDtos.CarsToyotaExportDTO;
import com.example.xml.carDealer.entities.carDtos.CarsWithPartsExportDTO;
import com.example.xml.carDealer.entities.customerDtos.CustomersExportDTO;
import com.example.xml.carDealer.entities.supplierDtos.SupplierNotAbroadExportDTO;
import com.example.xml.carDealer.services.CarService;
import com.example.xml.carDealer.services.CustomerService;
import com.example.xml.carDealer.services.SeedService;
import com.example.xml.carDealer.services.SupplierService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.nio.file.Path;
import java.util.List;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final SeedService seedService;
    private final CustomerService customerService;
    private final CarService carService;
    private final SupplierService supplierService;

    public ConsoleRunner(SeedService seedService, CustomerService customerService, CarService carService, SupplierService supplierService) {
        this.seedService = seedService;
        this.customerService = customerService;
        this.carService = carService;
        this.supplierService = supplierService;
    }

    @Override
    public void run(String... args) throws Exception {
        //   seedService.seedSuppliers();
        //   seedService.seedParts();
        //   seedService.seedCars();
        //   seedService.seedCustomers();
        //   seedService.seedSales();

        //   Разкоментирай следващия ред за да създадеш и напълниш базата
        //   seedService.seedAll();


        // Query 1 – Ordered Customers  //Разкоментирай следващия ред за да стартираш задачата
        // orderedCustomers_01();

        // Query 2 – Cars from Make Toyota  //Разкоментирай следващия ред за да стартираш задачата
        // carsFromToyota_02();

        // Query 3 – Local Suppliers  //Разкоментирай следващия ред за да стартираш задачата
        // localSuppliers_03();

        // Query 4 – Cars with Their List of Parts  //Разкоментирай следващия ред за да стартираш задачата
        // carsWithParts_04();
    }

    private void carsWithParts_04() throws JAXBException {
        CarsWithPartsExportDTO carsWithPartsExportDTO = this.carService.getAllCarWithParts();

        JAXBContext context = JAXBContext.newInstance(CarsWithPartsExportDTO.class);
        Marshaller marshaller = context.createMarshaller();

        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(carsWithPartsExportDTO,
                Path.of("src", "main", "resources", "outputCarDealer", "cars-and-parts.xml").toFile());
    }

    private void localSuppliers_03() throws JAXBException {
        SupplierNotAbroadExportDTO supplierNotAbroadExportDTO =
                this.supplierService.findAllSupplierNotAbroad();

        JAXBContext context = JAXBContext.newInstance(SupplierNotAbroadExportDTO.class);
        Marshaller marshaller = context.createMarshaller();

        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(supplierNotAbroadExportDTO,
                Path.of("src", "main", "resources", "outputCarDealer", "local-suppliers.xml").toFile());
    }

    private void carsFromToyota_02() throws JAXBException {
        CarsToyotaExportDTO carsToyotaExportDTO = this.carService.findAllCarsByMake("Toyota");

        JAXBContext context = JAXBContext.newInstance(CarsToyotaExportDTO.class);
        Marshaller marshaller = context.createMarshaller();

        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(carsToyotaExportDTO,
                Path.of("src", "main", "resources", "outputCarDealer", "toyota-cars.xml").toFile());
    }

    private void orderedCustomers_01() throws JAXBException {
        CustomersExportDTO customersExportDTO = this.customerService.getAllCustomers();

        JAXBContext context = JAXBContext.newInstance(CustomersExportDTO.class);
        Marshaller marshaller = context.createMarshaller();

        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(customersExportDTO,
                Path.of("src", "main", "resources", "outputCarDealer", "ordered-customers.xml").toFile());
    }
}
