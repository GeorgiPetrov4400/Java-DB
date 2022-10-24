package DBAppsIntroductionExercise;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class GetMinionNames {

    private static final String GET_MINIONS_NAME = "SELECT v.name, m.name, m.age FROM villains AS v" +
            " JOIN minions_villains mv on v.id = mv.villain_id" +
            " JOIN minions m on mv.minion_id = m.id" +
            " WHERE v.id = ?";

    private static final String GET_VILLAIN_BY_ID = "SELECT v.name FROM villains AS v WHERE v.id = ?";

    public static void main(String[] args) throws SQLException {

        Connection connection = Utils.getSQLConnection();

        int villainId = new Scanner(System.in).nextInt();

        PreparedStatement villainStatement = connection.prepareStatement(GET_VILLAIN_BY_ID);
        villainStatement.setInt(1, villainId);

        ResultSet villainResult = villainStatement.executeQuery();

        PreparedStatement minionStatement = connection.prepareStatement(GET_MINIONS_NAME);
        minionStatement.setInt(1, villainId);

        ResultSet minionResult = minionStatement.executeQuery();

        if (!villainResult.next()) {
            System.out.printf("No villain with ID %s exists in the database.", villainId);
            connection.close();
            return;
        } else {
            String villainName = villainResult.getString("v.name");
            System.out.println("Villain: " + villainName);

            int minionCount = 1;
            while (minionResult.next()) {
                String minionName = minionResult.getString("m.name");
                int minionAge = minionResult.getInt("m.age");

                System.out.println(minionCount + ". " + minionName + " " + minionAge);
                minionCount++;
            }
        }
        connection.close();
    }
}
