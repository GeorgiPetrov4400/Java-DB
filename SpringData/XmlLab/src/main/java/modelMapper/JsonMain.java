package modelMapper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import modelMapper.entities.dtos.addresses.CreateAddressDTO;
import modelMapper.entities.dtos.CompanyDTO;
import modelMapper.entities.dtos.CreateEmployeeDTO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

// @Component
public class JsonMain implements CommandLineRunner {

    private final Scanner scanner;
    private final Gson gson;

    public JsonMain(Scanner scanner) {
        this.scanner = scanner;

        this.gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
              //  .setDateFormat("yyyy-MM-dd")
                .setPrettyPrinting()
                .create();
    }

    @Override
    public void run(String... args) throws Exception {

        CreateAddressDTO address1 = new CreateAddressDTO("Bulgaria", "Pazardzhik");
        CreateEmployeeDTO firstEmployee = new CreateEmployeeDTO("Gosho", "Goshov",
                new BigDecimal(1000), address1);

        CreateAddressDTO address2 = new CreateAddressDTO("Bulgaria", "Plovdiv");
        CreateEmployeeDTO secondEmployee = new CreateEmployeeDTO("Pesho", "Peshov",
                new BigDecimal(2000), address2);

        CreateAddressDTO address3 = new CreateAddressDTO("Bulgaria", "Sofia");
        CreateEmployeeDTO thirdEmployee = new CreateEmployeeDTO("Tosho", "Toshov",
                new BigDecimal(3000), address3);

        CompanyDTO company = new CompanyDTO("Company", List.of(firstEmployee, secondEmployee, thirdEmployee));

        System.out.println(this.gson.toJson(company));

        String input = this.scanner.nextLine();

        // {"name":"Company","employees":[{"firstName":"Gosho","lastName":"Goshov","salary":1000,"address":{"country":"Bulgaria","city":"Pazardzhik"}},{"firstName":"Pesho","lastName":"Peshov","salary":2000,"address":{"country":"Bulgaria","city":"Plovdiv"}},{"firstName":"Tosho","lastName":"Toshov","salary":3000,"address":{"country":"Bulgaria","city":"Sofia"}}]}

        CompanyDTO parsed = this.gson.fromJson(input, CompanyDTO.class);

        parsed.getEmployees().forEach(createEmployeeDTO ->
                System.out.println(createEmployeeDTO.getAddress().getCity()));
    }

    private void jsonTest() {
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .create();

        //   AddressDTO addressDTO = new AddressDTO("Bulgaria", "Pazardzhik");
        CreateAddressDTO address1 = new CreateAddressDTO("Bulgaria", "Pazardzhik");
        CreateAddressDTO address2 = new CreateAddressDTO("Bulgaria", "Plovdiv");
        CreateAddressDTO address3 = new CreateAddressDTO("Bulgaria", "Sofia");

//        CreateEmployeeDTO createEmployeeDTO = new CreateEmployeeDTO("Dimo", "Padalski",
//                new BigDecimal(1000), LocalDate.of(2000, 07, 30), addressDTO);
        CreateEmployeeDTO createEmployeeDTO = new CreateEmployeeDTO("Dimo", "Padalski",
                new BigDecimal(1000), LocalDate.of(2000, 07, 30), address1);

//        String json = gson.toJson(addressDTO);
//        System.out.println(json);

        String jsonEmployee = gson.toJson(createEmployeeDTO);
        System.out.println(jsonEmployee);

        List<CreateAddressDTO> addressList = List.of(address1, address2, address3);
        System.out.println(gson.toJson(addressList));

        String input = this.scanner.nextLine();

        CreateEmployeeDTO parsedDTO = gson.fromJson(input, CreateEmployeeDTO.class);
//        AddressDTO[] array = gson.fromJson(input, AddressDTO[].class);

        System.out.println();
    }
}
