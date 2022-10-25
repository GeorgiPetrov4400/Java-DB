package DBAppsIntroductionExercise;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class IncreaseAgeStoredProcedure {

    private static final String INCREASE_MINION_YEARS_PROCEDURE = "CALL minions_db.usp_get_older(?)";
    private static final String GET_MINION_NAME_AND_AGE = "SELECT name, age FROM minions WHERE id = ?";

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        int idInput = Integer.parseInt(scanner.nextLine());

        Connection connection = Utils.getSQLConnection();

        PreparedStatement increaseMinionAge = connection.prepareStatement(INCREASE_MINION_YEARS_PROCEDURE);
        increaseMinionAge.setInt(1, idInput);
        increaseMinionAge.executeUpdate();

        PreparedStatement selectMinionStatement = connection.prepareStatement(GET_MINION_NAME_AND_AGE);
        selectMinionStatement.setInt(1, idInput);

        ResultSet minionResult = selectMinionStatement.executeQuery();
        minionResult.next();

        String minionName = minionResult.getString("name");
        int minionAge = minionResult.getInt("age");

        System.out.println(minionName + " " + minionAge);

        connection.close();
    }
}
