package modelMapper;

import com.google.gson.Gson;
import modelMapper.entities.Employee;
import modelMapper.entities.dtos.addresses.AddressDTO;
import modelMapper.entities.dtos.addresses.CreateAddressDTO;
import modelMapper.entities.dtos.CreateEmployeeDTO;
import modelMapper.services.AddressService;
import modelMapper.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;

@Component
public class DatabaseMain implements CommandLineRunner {

    private final AddressService addressService;
    private final EmployeeService employeeService;
    private final Scanner scanner;
    private final Gson gson;

    @Autowired
    public DatabaseMain(AddressService addressService, EmployeeService employeeService, Scanner scanner, Gson gson) {
        this.addressService = addressService;
        this.employeeService = employeeService;
        this.scanner = scanner;
        this.gson = gson;
    }

    @Override
    public void run(String... args) throws Exception {

        createAddress();

        // createEmployee();

        // printAllEmployees();

        // printEmployeeNames();

        //  printEmployeeNameAndSalary();
    }

    private void printEmployeeNameAndSalary() {
        String firstName = this.employeeService.findFirstNameAndSalaryById(1L).getFirstName();
        BigDecimal salary = this.employeeService.findFirstNameAndSalaryById(1L).getSalary();

        System.out.println(firstName + " " + salary);
    }

    private void printEmployeeNames() {
        System.out.println(this.employeeService.findNamesById(1L));
    }

    private void printAllEmployees() {
        this.employeeService.findAll().forEach(System.out::println);
    }

    private void createEmployee() {
        String firstName = scanner.nextLine();
        BigDecimal salary = new BigDecimal(scanner.nextLine());
        LocalDate birthday = LocalDate.parse(scanner.nextLine());

        String country = scanner.nextLine();
        String city = scanner.nextLine();

        CreateAddressDTO address = new CreateAddressDTO(country, city);

        CreateEmployeeDTO employeeDTO = new CreateEmployeeDTO(firstName, null, salary, birthday, address);

        Employee employee = this.employeeService.create(employeeDTO);

        System.out.println(employee);
    }

    private void createAddress() {
        String input = this.scanner.nextLine();

        CreateAddressDTO data = this.gson.fromJson(input, CreateAddressDTO.class);

        AddressDTO created = addressService.create(data);

        System.out.println(this.gson.toJson(created));
    }
}
