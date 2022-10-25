package DBAppsIntroductionExercise;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PrintAllMinionNames {

    private static final String GET_MINIONS_NAME_ORDER_BY_ID = "SELECT name FROM minions ORDER BY id LIMIT 25";
    private static final String GET_MINIONS_NAME_ORDER_BY_ID_DESCENDING =
            "SELECT name FROM minions ORDER BY id DESC LIMIT 25";

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        Connection connection = Utils.getSQLConnection();

        PreparedStatement ascendingStatement = connection.prepareStatement(GET_MINIONS_NAME_ORDER_BY_ID);
        ResultSet resultAscendingOrder = ascendingStatement.executeQuery();

        PreparedStatement descendingStatement = connection.prepareStatement(GET_MINIONS_NAME_ORDER_BY_ID_DESCENDING);
        ResultSet resultDescendingOrder = descendingStatement.executeQuery();


        while (resultAscendingOrder.next() && resultDescendingOrder.next()) {
            String firstMinionName = resultAscendingOrder.getString("name");
            String lastMinionName = resultDescendingOrder.getString("name");

            System.out.println(firstMinionName);
            System.out.println(lastMinionName);
        }

        connection.close();
    }
}
