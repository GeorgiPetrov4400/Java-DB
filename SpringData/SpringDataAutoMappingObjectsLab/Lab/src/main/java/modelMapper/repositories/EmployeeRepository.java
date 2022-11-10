package modelMapper.repositories;

import modelMapper.entities.Employee;
import modelMapper.entities.dtos.EmployeeNameAndSalaryDTO;
import modelMapper.entities.dtos.EmployeeNamesDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query(value = "SELECT new modelMapper.entities.dtos.EmployeeNamesDTO(e.firstName, e.lastName) FROM Employee AS e WHERE e.id =:id")
    EmployeeNamesDTO findNamesById(long id);

    EmployeeNameAndSalaryDTO findFirstNameAndSalaryById(long id);
}
