package DBAppsIntroductionExercise;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class IncreaseMinionsAge {

    private static final String UPDATE_MINIONS_NAME_AND_AGE_BY_ID =
            "UPDATE minions SET name = LOWER(name), age = age + 1 WHERE id IN (?,?,?)";

    private static final String GET_ALL_MINIONS = "SELECT name, age FROM minions";

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        Connection connection = Utils.getSQLConnection();

        String[] inputId = scanner.nextLine().split(" ");

        int firstId = Integer.parseInt(inputId[0]);
        int secondId = Integer.parseInt(inputId[1]);
        int thirdId = Integer.parseInt(inputId[2]);

        PreparedStatement updateMinionStatement = connection.prepareStatement(UPDATE_MINIONS_NAME_AND_AGE_BY_ID);
        updateMinionStatement.setInt(1, firstId);
        updateMinionStatement.setInt(2, secondId);
        updateMinionStatement.setInt(3, thirdId);
        updateMinionStatement.executeUpdate();

        PreparedStatement allMinionsStatement = connection.prepareStatement(GET_ALL_MINIONS);

        ResultSet allMinionsResult = allMinionsStatement.executeQuery();

        while (allMinionsResult.next()) {
            String minionName = allMinionsResult.getString("name");
            int minionAge = allMinionsResult.getInt("age");

            System.out.println(minionName + " " + minionAge);
        }

        connection.close();
    }
}
