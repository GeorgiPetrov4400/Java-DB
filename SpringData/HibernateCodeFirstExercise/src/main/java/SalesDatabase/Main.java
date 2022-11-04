package SalesDatabase;

import SalesDatabase.entities.Customer;
import SalesDatabase.entities.Product;
import SalesDatabase.entities.Sale;
import SalesDatabase.entities.StoreLocation;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("sales");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        Product product = new Product("Bob", 100.00, BigDecimal.TEN);
        Customer customer = new Customer("Jorgos", "abv@abv.bg", "123456789");
        StoreLocation storeLocation = new StoreLocation("Pazardzhik");
        Sale sale = new Sale(product, customer, storeLocation);

        entityManager.persist(product);
        entityManager.persist(customer);
        entityManager.persist(storeLocation);
        entityManager.persist(sale);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
