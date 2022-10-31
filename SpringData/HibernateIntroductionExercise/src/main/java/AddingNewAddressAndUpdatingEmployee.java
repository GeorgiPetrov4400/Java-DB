import entities.Address;
import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class AddingNewAddressAndUpdatingEmployee {

    private static final String GET_EMPLOYEE = "SELECT e FROM Employee e WHERE e.lastName = :last_name";

    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("soft_uni");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        Scanner scanner = new Scanner(System.in);

        String lastNameInput = scanner.nextLine();

        String addressToAdd = "Vitoshka 15";
        Address address = new Address();
        address.setText(addressToAdd);
        entityManager.persist(address);

        Employee employee = entityManager.createQuery(GET_EMPLOYEE, Employee.class)
                .setParameter("last_name", lastNameInput)
                .getSingleResult();

        employee.setAddress(address);
        entityManager.persist(employee);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
