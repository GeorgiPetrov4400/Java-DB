import entities.Address;
import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;

public class RemoveTowns {

    private static final String GET_ADDRESSES_BY_TOWN_NAME =
            "SELECT a FROM Address a WHERE a.town.name = :town_name";
    private static final String GET_TOWN_BY_NAME =
            "SELECT t FROM Town t WHERE t.name = :town_name";

    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("soft_uni");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        Scanner scanner = new Scanner(System.in);
        String deleteTown = scanner.nextLine();

        List<Address> addressList = entityManager.createQuery(GET_ADDRESSES_BY_TOWN_NAME, Address.class)
                .setParameter("town_name", deleteTown)
                .getResultList();

        int deletedAddresses = addressList.size();

        for (Address address : addressList) {
            address.getEmployees().forEach(employee -> employee.setAddress(null));
            entityManager.remove(address);
        }

        Town townForDelete = entityManager.createQuery(GET_TOWN_BY_NAME, Town.class)
                .setParameter("town_name", deleteTown)
                .getSingleResult();

        entityManager.remove(townForDelete);

        String printAddressType = deletedAddresses == 1 ? "address" : "addresses";
        System.out.printf("%d %s in %s deleted%n", deletedAddresses, printAddressType, deleteTown);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
