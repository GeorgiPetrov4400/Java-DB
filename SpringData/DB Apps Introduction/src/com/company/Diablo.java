package com.company;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class Diablo {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter password default (empty):");
        String password = scanner.nextLine().trim();
        System.out.println();

        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", password);

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/diablo", properties);

        PreparedStatement statement = connection.prepareStatement(
                "SELECT u.`user_name`, u.`first_name`, u.`last_name`, \n" +
                        "(SELECT COUNT(ug.`id`) FROM `users_games` AS ug WHERE ug.`user_id` = u.`id`) AS 'games_count' \n" +
                        "FROM `users` AS u\n" +
                        "WHERE u.`user_name` = ?");

        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        statement.setString(1, username);

        ResultSet result = statement.executeQuery();

        if (result.next()) {
            System.out.println("User: " + result.getString("user_name"));
            System.out.println(result.getString("first_name") + " " +
                    result.getString("last_name") + " " +
                    "has played " + result.getInt("games_count") + " games");
        } else {
            System.out.println("No such user exists");
        }

        connection.close();
    }
}
