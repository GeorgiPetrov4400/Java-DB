package exam.service.impl;

import com.google.gson.Gson;
import exam.model.dto.customerDto.CustomerImportDTO;
import exam.model.entity.Customer;
import exam.model.entity.Town;
import exam.repository.CustomerRepository;
import exam.service.CustomerService;
import exam.service.TownService;
import exam.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private static final Path IMPORT_CUSTOMERS_JSON =
            Path.of("src", "main", "resources", "files", "json", "customers.json");

    private final CustomerRepository customerRepository;
    private final TownService townService;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final Gson gson;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, TownService townService, ModelMapper modelMapper, ValidationUtil validationUtil, Gson gson) {
        this.customerRepository = customerRepository;
        this.townService = townService;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.gson = gson;
    }

    @Override
    public boolean areImported() {
        return this.customerRepository.count() > 0;
    }

    @Override
    public String readCustomersFileContent() throws IOException {
        return Files.readString(IMPORT_CUSTOMERS_JSON);
    }

    @Override
    public String importCustomers() throws IOException {
        CustomerImportDTO[] customerImportDTOS =
                gson.fromJson(readCustomersFileContent(), CustomerImportDTO[].class);

        return Arrays.stream(customerImportDTOS)
                .map(this::importCustomer).collect(Collectors.joining(System.lineSeparator()));
    }

    private String importCustomer(CustomerImportDTO customerImportDTO) {
        Set<ConstraintViolation<CustomerImportDTO>> violations = validationUtil.violation(customerImportDTO);

        if (!violations.isEmpty()) {
            return "Invalid Customer";
        }

        Optional<Customer> findByEmail = this.customerRepository.findByEmail(customerImportDTO.getEmail());

        if (findByEmail.isPresent()) {
            return "Invalid Customer";
        }

        Customer customer = modelMapper.map(customerImportDTO, Customer.class);

        Town town = this.townService.findByName(customerImportDTO.getTown().getName());
        customer.setTown(town);

        this.customerRepository.save(customer);
        return String.format("Successfully imported Customer %s %s - %s",
                customer.getFirstName(), customer.getLastName(), customer.getEmail());
    }
}
