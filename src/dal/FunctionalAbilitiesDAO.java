package dal;

import be.FunctionalAbilities.FunctionalAbility;
import be.enums.FunctionalEnum;
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
    public List<FunctionalAbility> getFunctionalAbilities() throws SQLException {
        ArrayList<FunctionalAbility> allFunctionalAbilities = new ArrayList<>();

        try(Connection connection = connector.getConnection()){
            String sql = "SELECT * FROM FunctionalAbility;";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                int functionalAbilityID = rs.getInt("functionalAbilityID");
                int citizenId = rs.getInt("citizenId");
                int functionalAbilitySubCategoryId = rs.getInt("functionalAbilitySubCategoryId");
                int abilityNow = rs.getInt("abilityNow");
                int abilityExpected = rs.getInt("abilityExpected");
                String abilityNote = rs.getString("abilityNote");
                String abilityNoteCitizen = rs.getString("abilityNoteCitizen");

                FunctionalAbility functionalAbility = new FunctionalAbility(functionalAbilityID, citizenId, functionalAbilitySubCategoryId, abilityNow, abilityExpected, abilityNote, abilityNoteCitizen);
                allFunctionalAbilities.add(functionalAbility);
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
    public FunctionalAbility abilitiesOnCitizen(int citizenId) throws SQLException {
        try (Connection connection = connector.getConnection()){
            String sql = "SELECT abilityNow, abilityExpected FROM FunctionalAbilities INNER JOIN Citizen ON FunctionalAbilities.abilityID = Citizen.functionalAbilityId WHERE citizenID =?;";

            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, citizenId);
            preparedStatement.execute();
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                int functionalAbilityID = rs.getInt("abilityID");
                int functionalAbilitySubCategoryId = rs.getInt("functionalAbilitySubCategoryId");
                int abilityNow = rs.getInt("abilityNow");
                int abilityExpected = rs.getInt("abilityExpected");
                String abilityNote = rs.getString("abilityExcepted");
                String abilityNoteCitizen = rs.getString("abilityNoteCitizen");

                FunctionalAbility functionalAbility = new FunctionalAbility(functionalAbilityID, citizenId, functionalAbilitySubCategoryId, abilityNow, abilityExpected, abilityNote, abilityNoteCitizen);

                return functionalAbility;
            }
        }
        return null;
    }


    public FunctionalAbility createFunctionalAbilities(int citizenId, int functionalAbilitySubCategoryId, FunctionalEnum abilityNow, FunctionalEnum abilityExpected, String abilityNote, String abilityNoteCitizen) throws SQLException{
        try(Connection connection = connector.getConnection()){
            String sql = "INSERT INTO FunctionalAbility (citizenId, functionalAbilitySubCategoryId, abilityNow, abilityExpected, abilityNote, abilityNoteCitizen) VALUES (?,?,?,?,?,?)";

            try(PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
                preparedStatement.setInt(1, citizenId);
                preparedStatement.setInt(2, functionalAbilitySubCategoryId);
                preparedStatement.setInt(3, abilityNow.getValue());
                preparedStatement.setInt(4, abilityExpected.getValue());
                preparedStatement.setString(5, abilityNote);
                preparedStatement.setString(6, abilityNoteCitizen);
                preparedStatement.execute();
                ResultSet rs = preparedStatement.getGeneratedKeys();
                int id = 0;
                if (rs.next()){
                    id = rs.getInt(1);
                }
                FunctionalAbility functionalAbility = new FunctionalAbility(id, citizenId, functionalAbilitySubCategoryId, abilityNow.getValue(), abilityExpected.getValue(), abilityNote, abilityNoteCitizen);

                return functionalAbility;
            }
        }
    }

    public void editAbilities(FunctionalAbility functionalAbility) throws SQLException{
        try(Connection connection = connector.getConnection()){
            String sql = "UPDATE FunctionalAbilities SET abilityNow = ?, abilityExpected = ?, abilityNote = ?, abilityNoteCitizen = ? WHERE citizenId = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, functionalAbility.getAbilityNow());
            preparedStatement.setInt(2, functionalAbility.getAbilityExcepted());
            preparedStatement.setString(3, functionalAbility.getAbilityNote());
            preparedStatement.setString(4, functionalAbility.getAbilityNoteCitizen());

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

    public static void main(String[] args) throws IOException, SQLException {
        FunctionalAbilitiesDAO dao = new FunctionalAbilitiesDAO();
        //dao.createFunctionalAbilities(1,4, FunctionalEnum.SEVERE_LIMIT, FunctionalEnum.SEVERE_LIMIT, "Hello", "Hejhej");
        System.out.println(dao.getFunctionalAbilities());
    }
}
