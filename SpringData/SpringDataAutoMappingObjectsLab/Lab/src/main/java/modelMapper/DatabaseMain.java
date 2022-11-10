package modelMapper;

import modelMapper.entities.Address;
import modelMapper.entities.Employee;
import modelMapper.entities.dtos.AddressDTO;
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

    @Autowired
    public DatabaseMain(AddressService addressService, EmployeeService employeeService, Scanner scanner) {
        this.addressService = addressService;
        this.employeeService = employeeService;
        this.scanner = scanner;
    }

    @Override
    public void run(String... args) throws Exception {

        // createAddress(scanner);

        // createEmployee(scanner);

        // printAllEmployees();

        // printEmployeeNames();

        printEmployeeNameAndSalary();
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

        AddressDTO address = new AddressDTO(country, city);

        CreateEmployeeDTO employeeDTO = new CreateEmployeeDTO(firstName, null, salary, birthday, address);

        Employee employee = this.employeeService.create(employeeDTO);

        System.out.println(employee);
    }

    private void createAddress() {
        String country = scanner.nextLine();
        String city = scanner.nextLine();

        AddressDTO data = new AddressDTO(country, city);

        Address address = addressService.create(data);

        System.out.println(address);
    }
}
