import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class ChangeCasing {

    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("soft_uni");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        Query townQuery = entityManager.createQuery("SELECT t FROM Town t", Town.class);
        List<Town> resultList = townQuery.getResultList();

        for (Town town : resultList) {
            String townName = town.getName();
            if (townName.length() <= 5) {
                String townNameToUpper = townName.toUpperCase();
                town.setName(townNameToUpper);
                entityManager.persist(town);
            }
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
