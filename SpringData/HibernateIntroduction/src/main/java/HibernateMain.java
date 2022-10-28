import entities.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class HibernateMain {
    public static void main(String[] args) {

        Configuration configuration = new Configuration();
        configuration.configure();

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();

        session.beginTransaction();

//        Student example = new Student();
//        example.setName("Gosho");
//        session.persist(example);
//
//        Student studentFromDB = session.get(Student.class, 1);
//        System.out.println(studentFromDB.getId() + " " + studentFromDB.getName());

        List<Student> studentList = session.createQuery("FROM Student", Student.class).list();

        for (Student student : studentList) {
            System.out.println(student.getId() + " " + student.getName());
        }

        session.getTransaction().commit();
        session.close();
    }
}
