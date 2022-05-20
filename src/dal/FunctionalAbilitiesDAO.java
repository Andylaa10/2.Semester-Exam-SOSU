package dal;

import be.FunctionalAbilities.FunctionalAbilitySubCategory;
import be.FunctionalAbilities.FunctionalAbilitySubCategoryText;
import be.FunctionalAbilities.FunctionalAbility;
import be.HealthCondition.HealthConditionSubCategory;
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

    public FunctionalAbility getSubcategoryData(int citizenId, int functionalAbilitySubCategoryId) throws SQLException {
        try (Connection connection = databaseConnector.getConnection()) {
            String sql = "SELECT * FROM FunctionalAbility WHERE citizenId = ? AND functionalAbilitySubCategoryId =?;";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, citizenId);
            preparedStatement.setInt(2, functionalAbilitySubCategoryId);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int citId = resultSet.getInt("citizenId");
                int functionalAbilityId = resultSet.getInt("functionalAbilitySubCategoryId");
                int abilityNow = resultSet.getInt("abilityNow");
                int abilityExpected = resultSet.getInt("abilityExpected");
                String abilityNote = resultSet.getString("abilityNote");
                String citizenPerformance = resultSet.getString("citizenPerfomance");
                String citizenMeaningOfPerformance = resultSet.getString("citizenMeaningOfPerfomance");
                String abilityNoteCitizen = resultSet.getString("abilityNoteCitizen");


                FunctionalAbility functionalAbility = new FunctionalAbility(citId, functionalAbilityId, abilityNow,
                        abilityExpected, abilityNote, citizenPerformance, citizenMeaningOfPerformance, abilityNoteCitizen);
                return functionalAbility;

            }
        }
        return null;
    }

    public FunctionalAbilitySubCategoryText getInfoOnSubCategory(int citizenId, int functionalAbilitySubCategoryId) throws SQLServerException {

        try (Connection connection = databaseConnector.getConnection()) {
            String sql = "SELECT *" +
                    " FROM FunctionalAbility" +
                    " INNER JOIN FunctionalAbilitySubCategory" +
                    " ON FunctionalAbility.functionalAbilitySubCategoryId = FunctionalAbilitySubCategory.functionalAbilitySubCategoryID" +
                    " WHERE FunctionalAbility.citizenId = ? AND FunctionalAbilitySubCategory.functionalAbilitySubCategoryID = ?";


            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, citizenId);
            preparedStatement.setInt(2, functionalAbilitySubCategoryId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("functionalAbilityID");
                int functionalAbilitySubCategoryIdColumn = resultSet.getInt("functionalAbilitySubCategoryId");
                int citId = resultSet.getInt("citizenId");
                int abilityNow = resultSet.getInt("abilityNow");
                int abilityExpected = resultSet.getInt("abilityExpected");
                String abilityNote = resultSet.getString("abilityNote");
                String citizenPerformance = resultSet.getString("citizenPerfomance");
                String citizenMeaningOfPerformance = resultSet.getString("citizenMeaningOfPerfomance");
                String abilityNoteCitizen = resultSet.getString("abilityNoteCitizen");
                int functionalAbilityNameID = resultSet.getInt("functionalAbilityNameId");

                FunctionalAbilitySubCategoryText functionalAbilitySubCategoryText = new FunctionalAbilitySubCategoryText(id, functionalAbilitySubCategoryIdColumn, citId, abilityNow,
                        abilityExpected, abilityNote, citizenPerformance, citizenMeaningOfPerformance, abilityNoteCitizen, functionalAbilityNameID);
                return functionalAbilitySubCategoryText;
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }




    public List<FunctionalAbilitySubCategoryText> getInfoOnSubCategories(int citizenId) throws SQLServerException {
        ArrayList<FunctionalAbilitySubCategoryText> allFASubcategories = new ArrayList();

        try (Connection connection = databaseConnector.getConnection()) {
            String sql = "SELECT *" +
                    " FROM FunctionalAbility" +
                    " INNER JOIN FunctionalAbilitySubCategory" +
                    " ON FunctionalAbility.functionalAbilitySubCategoryId = FunctionalAbilitySubCategory.functionalAbilitySubCategoryID" +
                    " WHERE FunctionalAbility.citizenId = ?;";



            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, citizenId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("functionalAbilityID");
                int functionalAbilitySubCategoryIdColumn = resultSet.getInt("functionalAbilitySubCategoryId");
                int citId = resultSet.getInt("citizenId");
                int abilityNow = resultSet.getInt("abilityNow");
                int abilityExpected = resultSet.getInt("abilityExpected");
                String abilityNote = resultSet.getString("abilityNote");
                String citizenPerformance = resultSet.getString("citizenPerfomance");
                String citizenMeaningOfPerformance = resultSet.getString("citizenMeaningOfPerfomance");
                String abilityNoteCitizen = resultSet.getString("abilityNoteCitizen");
                int functionalAbilityNameID = resultSet.getInt("functionalAbilityNameId");

                FunctionalAbilitySubCategoryText functionalAbilitySubCategoryText = new FunctionalAbilitySubCategoryText(id, functionalAbilitySubCategoryIdColumn, citId, abilityNow,
                        abilityExpected, abilityNote, citizenPerformance, citizenMeaningOfPerformance, abilityNoteCitizen, functionalAbilityNameID);
                allFASubcategories.add(functionalAbilitySubCategoryText);



            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return allFASubcategories;
    }



    public FunctionalAbility createFunctionalAbilities(int citizenId, int functionalAbilitySubCategoryId, int abilityNow, int abilityExpected, String abilityNote, String citizenPerformance, String citizenMeaningOfPerformance,  String abilityNoteCitizen) throws SQLException{
        try(Connection connection = databaseConnector.getConnection()){
            String sql = "INSERT INTO FunctionalAbility (citizenId, functionalAbilitySubCategoryId, abilityNow, abilityExpected, abilityNote , citizenPerfomance, citizenMeaningOfPerfomance, abilityNoteCitizen) VALUES (?,?,?,?,?,?,?,?)";

            try(PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
                preparedStatement.setInt(1, citizenId);
                preparedStatement.setInt(2, functionalAbilitySubCategoryId);

                preparedStatement.setInt(3, abilityNow);
                preparedStatement.setInt(4, abilityExpected);
                preparedStatement.setString(5, abilityNote);
                preparedStatement.setString(6, citizenPerformance);
                preparedStatement.setString(7, citizenMeaningOfPerformance);
                preparedStatement.setString(8, abilityNoteCitizen);
                preparedStatement.execute();
                ResultSet rs = preparedStatement.getGeneratedKeys();
                int id = 0;
                if (rs.next()){
                    id = rs.getInt(1);
                }
                FunctionalAbility functionalAbility = new FunctionalAbility(id, citizenId, functionalAbilitySubCategoryId, abilityNow, abilityExpected, abilityNote, citizenPerformance, citizenMeaningOfPerformance, abilityNoteCitizen);
                return functionalAbility;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public void editAbilities(FunctionalAbility functionalAbility) throws SQLException{
        try(Connection connection = databaseConnector.getConnection()){
            String sql = "UPDATE FunctionalAbility SET abilityNow = ?, abilityExpected = ?, abilityNote = ?, citizenPerfomance =?, citizenMeaningOfPerfomance =?, abilityNoteCitizen = ? WHERE citizenId = ? AND functionalAbilitySubCategoryId = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, functionalAbility.getAbilityNow());
            preparedStatement.setInt(2, functionalAbility.getAbilityExcepted());
            preparedStatement.setString(3, functionalAbility.getAbilityNote());
            preparedStatement.setString(4, functionalAbility.getCitizenPerformance());
            preparedStatement.setString(5, functionalAbility.getCitizenMeaningOfPerformance());
            preparedStatement.setString(6, functionalAbility.getAbilityNoteCitizen());
            preparedStatement.setInt(7, functionalAbility.getCitizenId());
            preparedStatement.setInt(8, functionalAbility.getFunctionalAbilityID());

            preparedStatement.executeUpdate();
            if (preparedStatement.executeUpdate() != 1){
                throw new SQLException();
            }
        }catch (Exception e){
            e.printStackTrace();
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
        System.out.println(functionalAbilitiesDAO.getInfoOnSubCategories(7));
    }
}
