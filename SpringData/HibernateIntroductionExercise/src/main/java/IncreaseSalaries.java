import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class IncreaseSalaries {

    private static final String GET_EMPLOYEES_FROM_DEPARTMENTS =
        "SELECT e FROM Employee e WHERE e.department.name" +
                " IN (:EngineeringDpt, :ToolDesignDpt, :MarketingDpt, :InformationServicesDpt)";

    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("soft_uni");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        entityManager.createQuery(GET_EMPLOYEES_FROM_DEPARTMENTS, Employee.class)
                .setParameter("EngineeringDpt", "Engineering")
                .setParameter("ToolDesignDpt", "Tool Design")
                .setParameter("MarketingDpt", "Marketing")
                .setParameter("InformationServicesDpt", "Information Services")
                .getResultList()
                .forEach(employee -> {
                    employee.setSalary(employee.getSalary().multiply(BigDecimal.valueOf(1.12)));
                    System.out.printf("%s %s ($%.2f)%n",
                            employee.getFirstName(), employee.getLastName(), employee.getSalary());
                });

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
