import hasEntities.Article;
import hasEntities.Author;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("relations");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

//        Vehicle car = new Car("Mazda 6", "Petrol", 5);
//        Vehicle bike = new Bike();
//        Vehicle plane = new Plane("Boeing", "Кerosene", 200);
//        Vehicle truck = new Truck("Mercedes Actros", "Diesel", 50000.00);
//
//        entityManager.persist(car);
//        entityManager.persist(bike);
//        entityManager.persist(plane);
//        entityManager.persist(truck);
//
////        Car findFromDB = entityManager.find(Car.class, 1L);
////
////        System.out.println(findFromDB.getModel() + " " + findFromDB.getSeats());
//
//        PlateNumber plateNumber = new PlateNumber("12345");
//        SportCar sportCar1 = new SportCar(plateNumber);
//        SportCar sportCar2 = new SportCar(plateNumber);
//
//        entityManager.persist(plateNumber);
//        entityManager.persist(sportCar1);
//        entityManager.persist(sportCar2);

        Article article = new Article("firstArticle");
        Author author = new Author("Gosho");

        author.addArticle(article);
        article.setAuthor(author);

        entityManager.persist(author);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
