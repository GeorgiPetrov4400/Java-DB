import entities.Department;
import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EmployeesMaximumSalaries {

    private static final String GET_MAX_SALARY_BY_DEPARTMENT =
            "SELECT e.department.name, MAX(e.salary) FROM Employee e GROUP BY e.department.name" +
                    " HAVING MAX(e.salary) NOT BETWEEN 30000 AND 70000";

    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("soft_uni");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        entityManager.createQuery(GET_MAX_SALARY_BY_DEPARTMENT, Object[].class)
                .getResultList()
                .forEach(d -> System.out.printf("%s\t %s%n", d[0], d[1]));

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
