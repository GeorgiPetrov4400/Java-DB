import entities.Employee;
import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class GetEmployeeWithProject {

    private static final String GET_EMPLOYEE_WITH_PROJECTS =
            "SELECT e FROM Employee e WHERE e.id =: employeeId";

    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("soft_uni");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        Scanner scanner = new Scanner(System.in);

        int inputId = Integer.parseInt(scanner.nextLine());

        Employee employee = entityManager.createQuery(GET_EMPLOYEE_WITH_PROJECTS, Employee.class)
                .setParameter("employeeId", inputId)
                .getSingleResult();

        System.out.printf("%s %s - %s%n", employee.getFirstName(), employee.getLastName(), employee.getJobTitle());
        employee.getProjects()
                .stream()
                .sorted(Comparator.comparing(Project::getName))
                .forEach(project -> System.out.println("\t" + project.getName()));

        entityManager.getTransaction().commit();
        entityManager.close();
    }
} 
