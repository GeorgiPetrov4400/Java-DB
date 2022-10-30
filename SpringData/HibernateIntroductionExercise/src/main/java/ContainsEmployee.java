import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.Scanner;

public class ContainsEmployee {

    private static final String GET_EMPLOYEE_BY_NAME =
            "SELECT COUNT(e) FROM Employee e WHERE e.firstName = :first_name AND e.lastName = :last_name";

    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("soft_uni");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        Scanner scanner = new Scanner(System.in);

        String[] inputEmployeeName = scanner.nextLine().split(" ");
        String employeeFirstName = inputEmployeeName[0];
        String employeeLastName = inputEmployeeName[1];

        Long foundEmployeeName = entityManager.createQuery(GET_EMPLOYEE_BY_NAME, Long.class)
                .setParameter("first_name", employeeFirstName)
                .setParameter("last_name", employeeLastName)
                .getSingleResult();

        if (foundEmployeeName > 0) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
