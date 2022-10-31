import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class EmployeesFromDepartment {

    private static final String GET_EMPLOYEES_FROM_DEPARTMENT =
            "SELECT e FROM Employee e" +
                    " WHERE e.department.name = :departmentName" +
                    " ORDER BY e.salary, e.id";

    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("soft_uni");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        Query employeeQuery = entityManager.createQuery(GET_EMPLOYEES_FROM_DEPARTMENT, Employee.class);
        employeeQuery.setParameter("departmentName", "Research and Development");
        List<Employee> result = employeeQuery.getResultList();

        for (Employee employee : result) {
            System.out.printf("%s %s from %s - $%.2f%n",
                    employee.getFirstName(), employee.getLastName(),
                    employee.getDepartment().getName(), employee.getSalary());
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
