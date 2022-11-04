package HospitalDatabase;

import HospitalDatabase.entities.Diagnose;
import HospitalDatabase.entities.Medicament;
import HospitalDatabase.entities.Patient;
import HospitalDatabase.entities.Visitation;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("hospital");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        Patient patient = new Patient("Georgi", "Petrov", "Pazardzhik",
                "abv@abv.bg", LocalDate.now(),
                "https://en.wikipedia.org/wiki/Al_Bundy#/media/File:Al_Bundy_(Ed_O'Neill).jpg",
                false);

        Visitation visitation = new Visitation(LocalDate.now(), "After work");

        Diagnose diagnose = new Diagnose("Sick", "Not good");

        Medicament medicament = new Medicament("Beer", patient);

        entityManager.persist(patient);
        entityManager.persist(visitation);
        entityManager.persist(diagnose);
        entityManager.persist(medicament);


        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
