package modelMapper.services;

import modelMapper.entities.Employee;
import modelMapper.entities.dtos.CreateEmployeeDTO;
import modelMapper.entities.dtos.EmployeeDTO;
import modelMapper.entities.dtos.EmployeeNameAndSalaryDTO;
import modelMapper.entities.dtos.EmployeeNamesDTO;

import java.util.List;

public interface EmployeeService {
    Employee create(CreateEmployeeDTO employeeDTO);

    List<EmployeeDTO> findAll();

    EmployeeNamesDTO findNamesById(long id);

    EmployeeNameAndSalaryDTO findFirstNameAndSalaryById(long id);
}
