package dal;

import be.FunctionalAbilities;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import dal.db.DatabaseConnector;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FunctionalAbilitiesDAO {

    private DatabaseConnector connector = DatabaseConnector.getInstance();

    public FunctionalAbilitiesDAO() throws IOException {
    }

    /**
     * Gets a list of every functional ability
     * @return
     * @throws SQLException
     */
    public List<FunctionalAbilities> getFunctionalAbilities() throws SQLException {
        ArrayList<FunctionalAbilities> allFunctionalAbilities = new ArrayList<>();

        try(Connection connection = connector.getConnection()){
            String sql = "SELECT * FROM FunctionalAbilities;";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.getResultSet();
            while (rs.next()){
                int id = rs.getInt("abilityID");
                int abilityNow = rs.getInt("abilityNow");
                String abilityExcepted = rs.getString("abilityExcepted");

                FunctionalAbilities functionalAbilities = new FunctionalAbilities(id, abilityNow, abilityExcepted);
                allFunctionalAbilities.add(functionalAbilities);
            }
        }
        return null;
    }

    /**
     * Gets all the functional abilities on the selected citizen
     * @param citizenId
     * @return
     * @throws SQLException
     */
    public FunctionalAbilities abilitiesOnCitizen(int citizenId) throws SQLException {
        try (Connection connection = connector.getConnection()){
            String sql = "SELECT abilityNow, abilityExpected FROM FunctionalAbilities INNER JOIN Citizen ON FunctionalAbilities.abilityID = Citizen.functionalAbilityId WHERE citizenID =?;";

            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, citizenId);
            preparedStatement.execute();
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                int id = rs.getInt("abilityID");
                int abilityNow = rs.getInt("abilityNow");
                String abilityExcepted = rs.getString("abilityExcepted");

                FunctionalAbilities functionalAbilities = new FunctionalAbilities(id, abilityNow, abilityExcepted);
                return functionalAbilities;
            }
        }
        return null;
    }

    /**
     * Creates functionalAbilities
     * @param abilityNow
     * @param abilityExcepted
     * @return
     * @throws SQLException
     */
    public FunctionalAbilities createFunctionalAbilities(int abilityNow, String abilityExcepted) throws SQLException{
        try(Connection connection = connector.getConnection()){
            String sql = "INSERT INTO FunctionalAbilities (abilityNow, abilityExcepted) VALUES (?,?)";

            try(PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
                preparedStatement.setInt(1, abilityNow);
                preparedStatement.setString(2, abilityExcepted);
                preparedStatement.execute();
                ResultSet rs = preparedStatement.getGeneratedKeys();
                int id = 0;
                if (rs.next()){
                    id = rs.getInt(1);
                }
                FunctionalAbilities functionalAbilities = new FunctionalAbilities(id, abilityNow, abilityExcepted);
                return functionalAbilities;
            }
        }
    }

    public void editAbilities(FunctionalAbilities functionalAbilities) throws SQLException{
        try(Connection connection = connector.getConnection()){
            String sql = "UPDATE FunctionalAbilities SET abilityNow = ?, abilityExcepted = ? WHERE citizenId = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, functionalAbilities.getAbilityNow());
            preparedStatement.setString(2, functionalAbilities.getAbilityExpected());

            preparedStatement.executeUpdate();
            if (preparedStatement.executeUpdate() != -1){
                throw new SQLException();
            }
        }
    }

    public void deleteFunctionalAbility(int id) throws Exception {
        try (Connection connection = connector.getConnection()) {
            String sql = "DELETE FROM FunctionalAbility WHERE abilityID =?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            if (preparedStatement.executeUpdate() != 1) {
                throw new Exception();
            }
        } catch (SQLException throwables) {
            throw new SQLException();
        }
    }
}
