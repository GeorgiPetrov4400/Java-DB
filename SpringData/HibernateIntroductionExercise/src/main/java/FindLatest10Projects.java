import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Comparator;

public class FindLatest10Projects {

    private static final String GET_10_LAST_STARTED_PROJECTS =
            "SELECT p FROM Project p ORDER BY p.startDate DESC";

    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("soft_uni");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        entityManager.createQuery(GET_10_LAST_STARTED_PROJECTS, Project.class)
                .setMaxResults(10)
                .getResultList()
                .stream()
                .sorted(Comparator.comparing(Project::getName))
                .forEach(System.out::println);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
