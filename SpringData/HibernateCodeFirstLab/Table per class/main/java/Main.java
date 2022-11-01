import entities.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("relations");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        Vehicle car = new Car("Mazda 6", "Petrol", 5);
        Vehicle bike = new Bike();
        Vehicle plane = new Plane("Boeing", "Кerosene", 200);
        Vehicle truck = new Truck("Mercedes Actros", "Diesel", 50000.00);

        entityManager.persist(car);
        entityManager.persist(bike);
        entityManager.persist(plane);
        entityManager.persist(truck);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
