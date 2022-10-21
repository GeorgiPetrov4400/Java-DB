package exercise;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class GetVillainsNames {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "12345");

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", properties);

        PreparedStatement statement = connection.prepareStatement
                ("SELECT v.`name`, COUNT(DISTINCT mv.`minion_id`) AS 'count_minions' FROM `villains` AS v" +
                        " JOIN `minions_villains` AS mv ON v.`id` = mv.`villain_id`" +
                        " GROUP BY v.`id`" +
                        " HAVING `count_minions` > 15" +
                        " ORDER BY `count_minions` DESC");

        ResultSet result = statement.executeQuery();

        while (result.next()) {
            System.out.println(result.getString("v.name") + " " + result.getInt("count_minions"));
        }

        connection.close();
    }
}
