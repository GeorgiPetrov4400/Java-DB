package modelMapper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import modelMapper.entities.dtos.AddressDTO;
import modelMapper.entities.dtos.CompanyDTO;
import modelMapper.entities.dtos.CreateEmployeeDTO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

@Component
public class JsonMain implements CommandLineRunner {

    private final Scanner scanner;
    private final Gson gson;

    public JsonMain(Scanner scanner) {
        this.scanner = scanner;

        this.gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .create();
    }

    @Override
    public void run(String... args) throws Exception {
        AddressDTO address1 = new AddressDTO("Bulgaria", "Pazardzhik");
        CreateEmployeeDTO firstEmployee = new CreateEmployeeDTO("Gosho", "Goshov",
                new BigDecimal(1000), address1);

        AddressDTO address2 = new AddressDTO("Bulgaria", "Plovdiv");
        CreateEmployeeDTO secondEmployee = new CreateEmployeeDTO("Pesho", "Peshov",
                new BigDecimal(2000), address2);

        AddressDTO address3 = new AddressDTO("Bulgaria", "Sofia");
        CreateEmployeeDTO thirdEmployee = new CreateEmployeeDTO("Tosho", "Toshov",
                new BigDecimal(3000), address3);

        CompanyDTO company = new CompanyDTO("Company", List.of(firstEmployee, secondEmployee, thirdEmployee));

        System.out.println(this.gson.toJson(company));

        String input = this.scanner.nextLine();

        CompanyDTO parsed = this.gson.fromJson(input, CompanyDTO.class);

        parsed.getEmployees().forEach(createEmployeeDTO -> System.out.println(createEmployeeDTO.getAddress().getCity()));

//        for (CreateEmployeeDTO employee : parsed.getEmployees()) {
//            System.out.println(employee.getAddress().getCity());
//        }
    }

    private void jsonTest() {
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .create();

        //   AddressDTO addressDTO = new AddressDTO("Bulgaria", "Pazardzhik");
        AddressDTO address1 = new AddressDTO("Bulgaria", "Pazardzhik");
        AddressDTO address2 = new AddressDTO("Bulgaria", "Plovdiv");
        AddressDTO address3 = new AddressDTO("Bulgaria", "Sofia");

//        CreateEmployeeDTO createEmployeeDTO = new CreateEmployeeDTO("Dimo", "Padalski",
//                new BigDecimal(1000), LocalDate.of(2000, 07, 30), addressDTO);
        CreateEmployeeDTO createEmployeeDTO = new CreateEmployeeDTO("Dimo", "Padalski",
                new BigDecimal(1000), LocalDate.of(2000, 07, 30), address1);

//        String json = gson.toJson(addressDTO);
//        System.out.println(json);

        String jsonEmployee = gson.toJson(createEmployeeDTO);
        System.out.println(jsonEmployee);

        List<AddressDTO> addressList = List.of(address1, address2, address3);
        System.out.println(gson.toJson(addressList));

        String input = this.scanner.nextLine();

        CreateEmployeeDTO parsedDTO = gson.fromJson(input, CreateEmployeeDTO.class);
//        AddressDTO[] array = gson.fromJson(input, AddressDTO[].class);

        System.out.println();
    }
}
