package dal;

import be.FunctionalAbilities.FunctionalAbilitySubCategoryText;
import be.FunctionalAbilities.FunctionalAbility;
import be.enums.FunctionalEnum;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import dal.db.DatabaseConnector;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FunctionalAbilitiesDAO {

    private DatabaseConnector databaseConnector = DatabaseConnector.getInstance();

    public FunctionalAbilitiesDAO() throws IOException {
    }

    /**
     * Gets a list of every functional ability
     * @return
     * @throws SQLException
     */
    public List<FunctionalAbility> getFunctionalAbilities() throws SQLException {
        ArrayList<FunctionalAbility> allFunctionalAbilities = new ArrayList<>();

        try(Connection connection = databaseConnector.getConnection()){
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
                String citizenPerformance = rs.getString("citizenPerformance");
                String citizenMeaningOfPerformance = rs.getString("citizenMeaningOfPerformance");

                FunctionalAbility functionalAbility = new FunctionalAbility(functionalAbilityID, citizenId, functionalAbilitySubCategoryId, abilityNow, abilityExpected, abilityNote, abilityNoteCitizen, citizenPerformance, citizenMeaningOfPerformance);
                allFunctionalAbilities.add(functionalAbility);
            }
        } catch (SQLServerException throwables) {
            throw new SQLException();
        }
        return allFunctionalAbilities;
    }

    public List<FunctionalAbilitySubCategoryText> getFunctionalAbilitySubCategories(int functionalAbilitySubCategoryId) throws SQLException {
        ArrayList<FunctionalAbilitySubCategoryText> allFunctionalAbilitySubCategories = new ArrayList<>();

        try (Connection connection = databaseConnector.getConnection()) {
            String sql = "SELECT functionalAbilitySubCategoryID, functionalAbilitySubCategoryName " +
                    "FROM FunctionalAbilitySubCategory INNER JOIN FunctionalAbilityName" +
                    " ON FunctionalAbilityName.functionalAbilityNamesID = FunctionalAbilitySubCategory.functionalAbilityNameId" +
                    " WHERE FunctionalAbilitySubCategory.functionalAbilityNameId = ?;";

            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, functionalAbilitySubCategoryId);
            preparedStatement.execute();

            ResultSet resultset = preparedStatement.executeQuery();
            while (resultset.next()) {
                int id = resultset.getInt("functionalAbilitySubCategoryID");
                String name = resultset.getString("functionalAbilitySubCategoryName");

                FunctionalAbilitySubCategoryText functionalAbilitySubCategory = new FunctionalAbilitySubCategoryText(id, name);
                allFunctionalAbilitySubCategories.add(functionalAbilitySubCategory);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return allFunctionalAbilitySubCategories;
    }

    public FunctionalAbilitySubCategoryText getInfoOnSubCategory(int citizenId, int functionalAbilitySubCategoryId) throws SQLServerException {

        try (Connection connection = databaseConnector.getConnection()) {
            String sql = "SELECT FunctionalAbility.functionalAbilityID, FunctionalAbility.citizenId," +
                    " FunctionalAbility.abilityNow, FunctionalAbility.abilityExpected, FunctionalAbility.abilityNote," +
                    " FunctionalAbility.citizenPerfomance, FunctionalAbility.citizenMeaningOfPerfomance," +
                    " FunctionalAbility.abilityNoteCitizen" +
                    " FROM FunctionalAbility" +
                    " INNER JOIN FunctionalAbilitySubCategory" +
                    " ON FunctionalAbility.functionalAbilitySubCategoryId = FunctionalAbilitySubCategory.functionalAbilitySubCategoryID" +
                    " WHERE FunctionalAbility.citizenId = ? AND FunctionalAbility.functionalAbilityID = ?;";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, citizenId);
            preparedStatement.setInt(2, functionalAbilitySubCategoryId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int functionalAbilityID = resultSet.getInt("functionalAbilityID");
                int citId = resultSet.getInt("citizenId");
                int abilityNow = resultSet.getInt("abilityNow");
                int abilityExpected = resultSet.getInt("abilityExpected");
                String abilityNote = resultSet.getString("abilityNote");
                String citizenPerformance = resultSet.getString("citizenPerfomance");
                String citizenMeaningOfPerformance = resultSet.getString("citizenMeaningOfPerfomance");
                String abilityNoteCitizen = resultSet.getString("abilityNoteCitizen");

                FunctionalAbilitySubCategoryText functionalAbilitySubCategoryText = new FunctionalAbilitySubCategoryText(functionalAbilityID, citId, abilityNow,
                        abilityExpected, abilityNote, citizenPerformance, citizenMeaningOfPerformance, abilityNoteCitizen);
                return functionalAbilitySubCategoryText;
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
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
        try (Connection connection = databaseConnector.getConnection()){
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


    public FunctionalAbility createFunctionalAbilities(int citizenId, int functionalAbilitySubCategoryId, FunctionalEnum abilityNow, FunctionalEnum abilityExpected, String abilityNote, String abilityNoteCitizen, String citizenPerformance, String citizenMeaningOfPerformance) throws SQLException{
        try(Connection connection = databaseConnector.getConnection()){
            String sql = "INSERT INTO FunctionalAbility (citizenId, functionalAbilitySubCategoryId, abilityNow, abilityExpected, abilityNote, abilityNoteCitizen, citizenPerformance, citizenMeaningOfPerformance) VALUES (?,?,?,?,?,?,?,?)";

            try(PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
                preparedStatement.setInt(1, citizenId);
                preparedStatement.setInt(2, functionalAbilitySubCategoryId);
                preparedStatement.setInt(3, abilityNow.getValue());
                preparedStatement.setInt(4, abilityExpected.getValue());
                preparedStatement.setString(5, abilityNote);
                preparedStatement.setString(6, abilityNoteCitizen);
                preparedStatement.setString(7, citizenPerformance);
                preparedStatement.setString(8, citizenMeaningOfPerformance);
                preparedStatement.execute();
                ResultSet rs = preparedStatement.getGeneratedKeys();
                int id = 0;
                if (rs.next()){
                    id = rs.getInt(1);
                }
                FunctionalAbility functionalAbility = new FunctionalAbility(id, citizenId, functionalAbilitySubCategoryId, abilityNow.getValue(), abilityExpected.getValue(), abilityNote, abilityNoteCitizen, citizenPerformance, citizenMeaningOfPerformance);

                return functionalAbility;
            }
        }
    }

    public void editAbilities(FunctionalAbility functionalAbility) throws SQLException{
        try(Connection connection = databaseConnector.getConnection()){
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
        try (Connection connection = databaseConnector.getConnection()) {
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
        FunctionalAbilitiesDAO functionalAbilitiesDAO = new FunctionalAbilitiesDAO();
        System.out.println(functionalAbilitiesDAO.getInfoOnSubCategory(1,1));
    }
}
