package BillsPaymentSystem;

import BillsPaymentSystem.entities.BankAccount;
import BillsPaymentSystem.entities.CreditCard;
import BillsPaymentSystem.entities.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("bills_payment_system");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

//        User user = new User("Georgi", "Petrov", "abv@abv.bg", "1234");

//        CreditCard creditCard = new CreditCard("Mastercard", "December", "2022");
//
//        BankAccount bankAccount = new BankAccount("ECB", "88888888");

//        entityManager.persist(user);
//        entityManager.persist(creditCard);
//        entityManager.persist(bankAccount);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
