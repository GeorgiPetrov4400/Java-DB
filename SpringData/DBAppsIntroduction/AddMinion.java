package DBAppsIntroductionExercise;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AddMinion {
    private static final String GET_TOWN_BY_NAME = "SELECT t.id FROM towns AS t WHERE t.name = ?";
    private static final String INSERT_NEW_TOWN = "INSERT INTO towns(name) VALUES(?)";

    private static final String GET_VILLAIN_BY_NAME = "SELECT v.id FROM villains AS v WHERE v.name = ?";
    private static final String INSERT_NEW_VILLAIN = "INSERT INTO villains (name, evilness_factor) VALUES(?,?)";

    private static final String INSERT_NEW_MINION = "INSERT INTO minions (name, age, town_id) VALUES(?,?,?)";
    private static final String GET_LAST_MINION = "SELECT m.id FROM minions AS m ORDER BY m.id DESC LIMIT 1";
    private static final String INSERT_INTO_MINIONS_VILLAINS =
            "INSERT INTO minions_villains(minion_id, villain_id) VALUES(?,?)";

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        Connection connection = Utils.getSQLConnection();

        String[] minionInput = scanner.nextLine().split(" ");

        String minionName = minionInput[1];
        int minionAge = Integer.parseInt(minionInput[2]);
        String minionTown = minionInput[3];

        String villainName = scanner.nextLine().split(" ")[1];

        PreparedStatement townStatement = connection.prepareStatement(GET_TOWN_BY_NAME);
        townStatement.setString(1, minionTown);

        ResultSet townResult = townStatement.executeQuery();

        int townId = 0;

        if (!townResult.next()) {
            PreparedStatement insertNewTownStatement = connection.prepareStatement(INSERT_NEW_TOWN);
            insertNewTownStatement.setString(1, minionTown);
            insertNewTownStatement.executeUpdate();

            ResultSet newTownResult = townStatement.executeQuery();
            newTownResult.next();
            townId = newTownResult.getInt("id");
            System.out.printf("Town %s was added to the database.%n", minionTown);
        } else {
            townId = townResult.getInt("id");
        }


        PreparedStatement villainStatement = connection.prepareStatement(GET_VILLAIN_BY_NAME);
        villainStatement.setString(1, villainName);

        ResultSet villainResult = villainStatement.executeQuery();

        int villainId = 0;

        if (!villainResult.next()) {
            PreparedStatement insertNewVillainStatement = connection.prepareStatement(INSERT_NEW_VILLAIN);
            insertNewVillainStatement.setString(1, villainName);
            insertNewVillainStatement.setString(2, "evil");
            insertNewVillainStatement.executeUpdate();

            ResultSet newVillainResult = villainStatement.executeQuery();
            newVillainResult.next();
            villainId = newVillainResult.getInt("id");
            System.out.printf("Villain %s was added to the database.%n", villainName);
        } else {
            villainId = villainResult.getInt("id");
        }


        PreparedStatement insertNewMinionStatement = connection.prepareStatement(INSERT_NEW_MINION);
        insertNewMinionStatement.setString(1, minionTown);
        insertNewMinionStatement.setInt(2, minionAge);
        insertNewMinionStatement.setInt(3, townId);
        insertNewMinionStatement.executeUpdate();


        PreparedStatement lastMinion = connection.prepareStatement(GET_LAST_MINION);
        ResultSet lastMinionResult = lastMinion.executeQuery();
        lastMinionResult.next();

        int lastMinionId = lastMinionResult.getInt("id");


        PreparedStatement insertIntoMinionsVillains = connection.prepareStatement(INSERT_INTO_MINIONS_VILLAINS);
        insertIntoMinionsVillains.setInt(1, lastMinionId);
        insertIntoMinionsVillains.setInt(2, villainId);
        insertIntoMinionsVillains.executeUpdate();

        System.out.printf("Successfully added %s to be minion of %s.%n", minionName, villainName);

        connection.close();
    }
}
