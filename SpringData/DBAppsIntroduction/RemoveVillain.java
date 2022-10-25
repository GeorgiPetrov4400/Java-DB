package DBAppsIntroductionExercise;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RemoveVillain {

    private static final String GET_VILLAIN_BY_ID = "SELECT name FROM villains WHERE id = ?";
    private static final String GET_MINIONS_BY_VILLAIN_ID =
            "SELECT COUNT(minion_id) AS 'count_minion' FROM minions_villains WHERE villain_id = ?";
    private static final String DELETE_MINIONS_VILLAINS_BY_ID = "DELETE FROM minions_villains WHERE villain_id = ?";
    private static final String DELETE_VILLAINS_BY_ID = "DELETE FROM villains WHERE id = ?";

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        Connection connection = Utils.getSQLConnection();

        int villainId = Integer.parseInt(scanner.nextLine());

        PreparedStatement villainStatement = connection.prepareStatement(GET_VILLAIN_BY_ID);
        villainStatement.setInt(1, villainId);

        ResultSet villainResult = villainStatement.executeQuery();

        if (!villainResult.next()) {
            System.out.println("No such villain was found");
            connection.close();
            return;
        }

        String villainName = villainResult.getString("name");

        PreparedStatement allMinionsVillainsById = connection.prepareStatement(GET_MINIONS_BY_VILLAIN_ID);
        allMinionsVillainsById.setInt(1, villainId);

        ResultSet minionsResult = allMinionsVillainsById.executeQuery();
        minionsResult.next();

        int countMinion = minionsResult.getInt("count_minion");

        connection.setAutoCommit(false);

        try {
            PreparedStatement deleteMinionsVillains = connection.prepareStatement(DELETE_MINIONS_VILLAINS_BY_ID);
            deleteMinionsVillains.setInt(1, villainId);
            deleteMinionsVillains.executeUpdate();

            PreparedStatement deleteVillains = connection.prepareStatement(DELETE_VILLAINS_BY_ID);
            deleteVillains.setInt(1, villainId);
            deleteVillains.executeUpdate();

            connection.commit();

            System.out.printf("%s was deleted%n", villainName);
            System.out.printf("%d minions released%n", countMinion);

        } catch (SQLException e) {
            connection.rollback();
        }

        connection.close();
    }
}
