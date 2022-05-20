package dal;

import be.HealthCondition.HealthCondition;
import be.HealthCondition.HealthConditionSubCategory;
import be.HealthCondition.HealthConditionSubCategoryText;
import dal.db.DatabaseConnector;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HealthConditionsDAO {

    private DatabaseConnector databaseConnector = DatabaseConnector.getInstance();


    public HealthConditionsDAO() throws IOException {
    }

    public List<HealthCondition> getHealthConditions() throws SQLException {
        ArrayList<HealthCondition> allHealthConditions = new ArrayList<>();

        try (Connection connection = databaseConnector.getConnection()) {
            String sql = "SELECT * FROM HealthCondition;";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultset = preparedStatement.executeQuery();
            while (resultset.next()) {
                int id = resultset.getInt("healthConditionID");
                String name = resultset.getString("healthConditionName");

                HealthCondition healthCondition = new HealthCondition(id, name);
                allHealthConditions.add(healthCondition);
            }
        } catch (SQLException sqlException) {
            throw new SQLException();
        }
        return allHealthConditions;
    }

    public HealthConditionSubCategoryText getHealthConditionData(int citizenId, int subCategoryId) throws SQLException {
        try (Connection connection = databaseConnector.getConnection()) {
            String sql = "SELECT * FROM SubCatTextOnCitizen WHERE citizenId =? AND subCategoryId = ?;";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, citizenId);
            preparedStatement.setInt(2, subCategoryId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("subCatTextOnCitizenID");
                int citId = resultSet.getInt("citizenId");
                int subId = resultSet.getInt("subCategoryId");
                String professionalNote = resultSet.getString("professionalNote");
                String currentLevelAssessment = resultSet.getString("currentLevelAssessment");
                String expectedLevelAssessment = resultSet.getString("expectedLevelAssessment");
                int condition = resultSet.getInt("Condition");

                HealthConditionSubCategoryText healthConditionSubCategoryText = new HealthConditionSubCategoryText(id, citId, subId, professionalNote, currentLevelAssessment, expectedLevelAssessment, condition);
                return healthConditionSubCategoryText;
            }
        }
        return null;
    }

    public HealthConditionSubCategoryText getInfoOnSubCategory(int citizenId, int subCategoryId) throws SQLException {

        try (Connection connection = databaseConnector.getConnection()) {
            String sql = "SELECT * " +
                    "FROM SubCatTextOnCitizen " +
                    "INNER JOIN SubCategory " +
                    "ON SubCatTextOnCitizen.subCategoryId = SubCategory.subCategoryID " +
                    "WHERE SubCatTextOnCitizen.citizenId = ? AND SubCatTextOnCitizen.SubCategoryId = ?;";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, citizenId);
            preparedStatement.setInt(2, subCategoryId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("subCatTextOnCitizenID");
                int citId = resultSet.getInt("citizenId");
                int subCatId = resultSet.getInt("subCategoryId");
                String professionalNote = resultSet.getString("professionalNote");
                String currentLevelAssessment = resultSet.getString("currentLevelAssessment");
                String expectedLevelAssessment = resultSet.getString("expectedLevelAssessment");
                int condition = resultSet.getInt("condition");
                int healthConditionId = resultSet.getInt("healthConditionId");

                HealthConditionSubCategoryText healthConditionSubCategoryText = new HealthConditionSubCategoryText(id,
                        citId, subCatId, professionalNote, currentLevelAssessment, expectedLevelAssessment, condition, healthConditionId);
                return healthConditionSubCategoryText;
            }
        } catch (SQLException e) {
            throw new SQLException();
        }
        return null;
    }

    public List<HealthConditionSubCategoryText> getInfoOnSubCategories(int citizenId) throws SQLException {
        ArrayList<HealthConditionSubCategoryText> allHCSubCategoryInfo = new ArrayList<>();


        try (Connection connection = databaseConnector.getConnection()) {
            String sql = "SELECT * " +
                    "FROM SubCatTextOnCitizen " +
                    "INNER JOIN SubCategory " +
                    "ON SubCatTextOnCitizen.subCategoryId = SubCategory.subCategoryID " +
                    "WHERE SubCatTextOnCitizen.citizenId = ?;";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, citizenId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("subCatTextOnCitizenID");
                int citId = resultSet.getInt("citizenId");
                int subCatId = resultSet.getInt("subCategoryId");
                String professionalNote = resultSet.getString("professionalNote");
                String currentLevelAssessment = resultSet.getString("currentLevelAssessment");
                String expectedLevelAssessment = resultSet.getString("expectedLevelAssessment");
                int condition = resultSet.getInt("condition");
                int healthConditionId = resultSet.getInt("healthConditionId");

                HealthConditionSubCategoryText healthConditionSubCategoryText = new HealthConditionSubCategoryText(id,
                        citId, subCatId, professionalNote, currentLevelAssessment, expectedLevelAssessment, condition, healthConditionId);
                allHCSubCategoryInfo.add(healthConditionSubCategoryText);
            }
        } catch (Exception e) {
            throw new SQLException();
        }
        return allHCSubCategoryInfo;
    }

    public List<HealthConditionSubCategory> getSubCategories(int categoryId) throws SQLException {
        ArrayList<HealthConditionSubCategory> allSubCategories = new ArrayList<>();

        try (Connection connection = databaseConnector.getConnection()) {
            String sql = "SELECT subCategoryID, subCategoryName FROM SubCategory INNER JOIN HealthCondition ON HealthCondition.healthConditionID = subCategory.healthConditionId where HealthCondition.HealthConditionID = ?;";

            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, categoryId);
            preparedStatement.execute();

            ResultSet resultset = preparedStatement.executeQuery();
            while (resultset.next()) {
                int id = resultset.getInt("subCategoryID");
                String name = resultset.getString("subCategoryName");

                HealthConditionSubCategory healthConditionSubCategory = new HealthConditionSubCategory(id, name);
                allSubCategories.add(healthConditionSubCategory);
            }
        } catch (SQLException sqlException) {
            throw new SQLException();
        }
        return allSubCategories;
    }

    public void insertIntoSubCategory(int citizenId, int subCategoryId, String professionalNote, String currentLevelAssessment, String expectedLevelAssessment, int condition) throws SQLException {
        String sql = "INSERT INTO SubCatTextOnCitizen (citizenId, SubCategoryId, professionalNote, currentLevelAssessment, expectedLevelAssessment, Condition) VALUES (?,?,?,?,?,?);";
        try (Connection connection = databaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, citizenId);
            preparedStatement.setInt(2, subCategoryId);
            preparedStatement.setString(3, professionalNote);
            preparedStatement.setString(4, currentLevelAssessment);
            preparedStatement.setString(5, expectedLevelAssessment);
            preparedStatement.setInt(6, condition);
            preparedStatement.execute();

        } catch (SQLException exception) {
            throw new SQLException();
        }
    }

    public void editSubcategory(HealthConditionSubCategoryText subCategoryText) throws SQLException {
        try (Connection connection = databaseConnector.getConnection()) {
            String sql = "UPDATE SubCatTextOnCitizen SET professionalNote = ?, currentLevelAssessment = ?, expectedLevelAssessment = ?, Condition = ? WHERE citizenId =? AND subCategoryId=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, subCategoryText.getProfessionalNote());
            preparedStatement.setString(2, subCategoryText.getCurrentLevelAssessment());
            preparedStatement.setString(3, subCategoryText.getExpectedLevelAssessment());
            preparedStatement.setInt(4, subCategoryText.getCondition());
            preparedStatement.setInt(5, subCategoryText.getCitizenId());
            preparedStatement.setInt(6, subCategoryText.getCategoryId());
            preparedStatement.executeUpdate();
        }catch(Exception e){
            throw new SQLException();
        }
    }


    public static void main(String[] args) throws IOException, SQLException {
        HealthConditionsDAO healthConditionsDAO = new HealthConditionsDAO();
        //healthConditionsDAO.insertIntoSubCategory(12, 3, "Gider ikke noget", 0);
        //System.out.println(healthConditionsDAO.getTextOnSubCategory(13,3));
        //System.out.println(healthConditionsDAO.getSubCategories(1));
        //healthConditionsDAO.insertIntoSubCategory(1, 1, "Gider ikke at gå i bad", ConditionEnum.RELEVANT.getValue());
        //System.out.println(healthConditionsDAO.getTextOnSubCategory(1,1));
        System.out.println(healthConditionsDAO.getInfoOnSubCategory(7, 1));
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.println(healthConditionsDAO.getInfoOnSubCategory(7, 2));

    }
}
