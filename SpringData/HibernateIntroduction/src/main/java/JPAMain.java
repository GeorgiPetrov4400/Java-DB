import entities.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAMain {
    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("school-db");

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Student student = new Student("Joro");
        entityManager.persist(student);

        Student foundStudent = entityManager.find(Student.class, 2);
        System.out.println(foundStudent.getId() + " " + foundStudent.getName());

        entityManager.remove(foundStudent);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
