package DBAppsIntroductionExercise;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class GetVillainsNames {
    private static final String GET_VILLAIN_NAMES =
            "SELECT v.`name`, COUNT(DISTINCT mv.`minion_id`) AS 'count_minions'" +
                    " FROM `villains` AS v" +
                    " JOIN `minions_villains` AS mv ON v.`id` = mv.`villain_id`" +
                    " GROUP BY v.`id`" +
                    " HAVING `count_minions` > 15" +
                    " ORDER BY `count_minions` DESC";

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        Connection connection = Utils.getSQLConnection();

        PreparedStatement statement = connection.prepareStatement(GET_VILLAIN_NAMES);

        ResultSet result = statement.executeQuery();

        while (result.next()) {
            String villainName = result.getString("v.name");
            int minionsCount = result.getInt("count_minions");

            System.out.println(villainName + " " + minionsCount);
        }

        connection.close();
    }
}