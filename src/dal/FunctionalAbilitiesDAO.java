package dal;

import be.FunctionalAbilities.FunctionalAbilities;
import be.FunctionalAbilities.SubFunctional;
import be.HealthCondition.SubCategory;
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
    public List<FunctionalAbilities> getFunctionalAbilities() throws SQLException {
        ArrayList<FunctionalAbilities> allFunctionalAbilities = new ArrayList<>();

        try(Connection connection = connector.getConnection()){
            String sql = "SELECT * FROM FunctionalAbilities;";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.getResultSet();
            while (rs.next()){
                int functionalAbilityID = rs.getInt("abilityID");
                int citizenId = rs.getInt("citizenId");
                int functionalAbilitySubCategoryId = rs.getInt("functionalAbilitySubCategoryId");
                FunctionalEnum abilityNow = FunctionalEnum.valueOf(rs.getString("abilityNow"));
                FunctionalEnum abilityExpected = FunctionalEnum.valueOf(rs.getString("abilityExpected"));
                String abilityNote = rs.getString("abilityExcepted");
                String abilityNoteCitizen = rs.getString("abilityNoteCitizen");

                FunctionalAbilities functionalAbilities = new FunctionalAbilities(functionalAbilityID, citizenId, functionalAbilitySubCategoryId, abilityNow, abilityExpected, abilityNote, abilityNoteCitizen);
                allFunctionalAbilities.add(functionalAbilities);
            }
        }
        return null;
    }

    public List<SubFunctional> getFunctionalAbilitySubCategories(int functionalAbilitySubCategoryId) throws SQLException {
        ArrayList<SubFunctional> allFunctionalAbilitySubCategories = new ArrayList<>();

        try (Connection connection = connector.getConnection()) {
            String sql = "SELECT functionalAbilitySubCategoryID, functionalAbilitySubCategoryName " +
                    "FROM FunctionalAbilitySubCategory INNER JOIN FunctionalAbilityName" +
                    " ON FunctionalAbilityName.functionalAbilityNamesID = FunctionalAbilitySubCategory.functionalAbilityNameId" +
                    " WHERE FunctionalAbilitySubCategory.functionalAbilityNameId = ?;";

            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, functionalAbilitySubCategoryId);
            preparedStatement.execute();

            ResultSet resultset = preparedStatement.executeQuery();
            while (resultset.next()) {
                int functionalAbilityNameID = resultset.getInt("functionalAbilitySubCategoryID");
                String functionalAbilitySubCategoryName = resultset.getString("functionalAbilitySubCategoryName");

                SubFunctional functionalAbilitySubCategory = new SubFunctional(functionalAbilityNameID, functionalAbilitySubCategoryName);
                allFunctionalAbilitySubCategories.add(functionalAbilitySubCategory);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return allFunctionalAbilitySubCategories;
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
                int functionalAbilityID = rs.getInt("abilityID");
                int functionalAbilitySubCategoryId = rs.getInt("functionalAbilitySubCategoryId");
                FunctionalEnum abilityNow = FunctionalEnum.valueOf(rs.getString("abilityNow"));
                FunctionalEnum abilityExpected = FunctionalEnum.valueOf(rs.getString("abilityExpected"));
                String abilityNote = rs.getString("abilityExcepted");
                String abilityNoteCitizen = rs.getString("abilityNoteCitizen");

                FunctionalAbilities functionalAbilities = new FunctionalAbilities(functionalAbilityID, citizenId, functionalAbilitySubCategoryId, abilityNow, abilityExpected, abilityNote, abilityNoteCitizen);

                return functionalAbilities;
            }
        }
        return null;
    }


    public FunctionalAbilities createFunctionalAbilities(int citizenId, int functionalAbilitySubCategoryId, FunctionalEnum abilityNow, FunctionalEnum abilityExpected, String abilityNote, String abilityNoteCitizen) throws SQLException{
        try(Connection connection = connector.getConnection()){
            String sql = "INSERT INTO FunctionalAbilities (citizenId, functionalAbilitySubCategoryId, abilityNow, abilityExpected, abilityNote, abilityNoteCitizen) VALUES (?,?,?,?,?,?)";

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
                FunctionalAbilities functionalAbilities = new FunctionalAbilities(id, citizenId, functionalAbilitySubCategoryId, abilityNow, abilityExpected, abilityNote, abilityNoteCitizen);

                return functionalAbilities;
            }
        }
    }

    public void editAbilities(FunctionalAbilities functionalAbilities) throws SQLException{
        try(Connection connection = connector.getConnection()){
            String sql = "UPDATE FunctionalAbilities SET abilityNow = ?, abilityExpected = ?, abilityNote = ?, abilityNoteCitizen = ? WHERE citizenId = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, functionalAbilities.getAbilityNow().getValue());
            preparedStatement.setInt(2, functionalAbilities.getAbilityExcepted().getValue());
            preparedStatement.setString(3, functionalAbilities.getAbilityNote());
            preparedStatement.setString(4, functionalAbilities.getAbilityNoteCitizen());

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
        FunctionalAbilitiesDAO functionalAbilitiesDAO = new FunctionalAbilitiesDAO();
        //System.out.println(functionalAbilitiesDAO.getFunctionalAbilitySubCategories(1));

    }
}
