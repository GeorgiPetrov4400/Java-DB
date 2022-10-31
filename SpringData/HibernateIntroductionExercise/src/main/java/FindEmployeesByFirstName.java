import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Scanner;

public class FindEmployeesByFirstName {

    private static final String GET_EMPLOYEES_BY_FIRST_NAME_PATTERN =
            "SELECT е FROM Employee е WHERE е.firstName LIKE :pattern";

    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("soft_uni");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        Scanner scanner = new Scanner(System.in);
        String inputPattern = scanner.nextLine();

        entityManager.createQuery(GET_EMPLOYEES_BY_FIRST_NAME_PATTERN, Employee.class)
                .setParameter("pattern", inputPattern + "%")
                .getResultList()
                .forEach(e -> System.out.printf("%s %s - %s - ($%.2f)%n",
                        e.getFirstName(), e.getLastName(), e.getJobTitle(), e.getSalary()));

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
