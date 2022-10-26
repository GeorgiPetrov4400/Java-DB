import entities.Student;
import entities.User;
import orm.Connector;
import orm.EntityManager;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws SQLException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException {
        Connector.createConnection("root", "12345", "soft_uni");

        Connection connection = Connector.getConnection();

        EntityManager<User> userManager = new EntityManager<>(connection);
//        User user = new User("Gosho", 40, LocalDate.now());
//        userManager.persist(user);
//
        EntityManager<Student> studentManager = new EntityManager<>(connection);
//        Student student = new Student("Joro");
//        studentManager.persist(student);

        User first = userManager.findFirst(User.class);
        System.out.println(first.getId() + " " + first.getUsername());

        Student firstStudent = studentManager.findFirst(Student.class, "name = 'Joro'");
        System.out.println(firstStudent.getId() + " " + firstStudent.getName());

        userManager.find(User.class, "age > 18 AND registration_date > '2022-08-08'")
                .forEach(user -> System.out.println(user.toString()));
    }
}
