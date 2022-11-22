package modelMapper;

import modelMapper.entities.Address;
import modelMapper.entities.Employee;
import modelMapper.entities.dtos.EmployeeDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.boot.CommandLineRunner;

import java.math.BigDecimal;

// @Component
public class ConsoleRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        ModelMapper mapper = new ModelMapper();

//        PropertyMap<Employee, EmployeeDTO> propertyMap = new PropertyMap<>() {
//            @Override
//            protected void configure() {
//                map().setCity(source.getAddress().getCity());
//            }
//        };
//        mapper.addMappings(propertyMap);
//        mapper.validate();


        TypeMap<Employee, EmployeeDTO> typeMap = mapper.createTypeMap(Employee.class, EmployeeDTO.class);
        typeMap.addMappings(mapping -> mapping.map(source -> source.getAddress().getCity(),
                EmployeeDTO::setAddressCity));  // (destination, value) -> destination.setCity(value.toString()) == EmployeeDTO::setCity
        typeMap.validate();

        Address address = new Address("Bulgaria", "Pazardzhik");
        Employee employee = new Employee("Joro", BigDecimal.valueOf(8888), address);

      //  EmployeeDTO employeeDTO = mapper.map(employee, EmployeeDTO.class);
        EmployeeDTO employeeDTO = typeMap.map(employee);

        System.out.println(employeeDTO);
    }
}
