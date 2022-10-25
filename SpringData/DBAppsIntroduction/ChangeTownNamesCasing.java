package DBAppsIntroductionExercise;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChangeTownNamesCasing {

    private static final String UPDATE_TOWN_NAMES = "UPDATE towns SET name = UPPER(name) WHERE country = ?";
    private static final String SELECT_UPDATED_TOWNS = "SELECT name FROM towns WHERE country = ?";

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        Connection connection = Utils.getSQLConnection();

        String countryName = scanner.nextLine();

        PreparedStatement statement = connection.prepareStatement(UPDATE_TOWN_NAMES);
        statement.setString(1, countryName);

        int townsCount = statement.executeUpdate();

        if (townsCount == 0) {
            System.out.println("No town names were affected.");
        } else {
            System.out.printf("%d town names were affected.%n", townsCount);
            PreparedStatement updatedTowns = connection.prepareStatement(SELECT_UPDATED_TOWNS);
            updatedTowns.setString(1, countryName);

            ResultSet townSetResult = updatedTowns.executeQuery();

            List<String> towns = new ArrayList<>();

            while (townSetResult.next()) {
                towns.add(townSetResult.getString("name"));
            }

            System.out.println(towns);
        }

        connection.close();
    }
}
