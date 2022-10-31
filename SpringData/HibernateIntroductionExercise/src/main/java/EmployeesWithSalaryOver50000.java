import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class EmployeesWithSalaryOver50000 {

    private static final String GET_EMPLOYEES_WITH_SALARY_BIGGER_THAN_50000 =
            "SELECT e.firstName FROM Employee e WHERE e.salary > 50000";

    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("soft_uni");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        Query employeeQuery = entityManager.createQuery(GET_EMPLOYEES_WITH_SALARY_BIGGER_THAN_50000, String.class);
        List<String> resultList = employeeQuery.getResultList();

        for (String name : resultList) {
            System.out.println(name);
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
