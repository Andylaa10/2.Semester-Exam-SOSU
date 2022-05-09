package dal;

import be.Citizen;
import be.HealthCondition;
import be.SubCategory;
import be.SubCategoryText;
import be.enums.ConditionEnum;
import com.microsoft.sqlserver.jdbc.SQLServerException;
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

    public List<SubCategory> getSubCategories(int categoryId) throws SQLException {
        ArrayList<SubCategory> allSubCategories = new ArrayList<>();

        try (Connection connection = databaseConnector.getConnection()) {
            String sql = "SELECT subCategoryID, subCategoryName FROM SubCategory INNER JOIN HealthCondition ON HealthCondition.healthConditionID = subCategory.healthConditionId where HealthCondition.HealthConditionID = ?;";

            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, categoryId);
            preparedStatement.execute();

            ResultSet resultset = preparedStatement.executeQuery();
            while (resultset.next()) {
                int id = resultset.getInt("subCategoryID");
                String name = resultset.getString("subCategoryName");

                SubCategory subCategory = new SubCategory(id, name);
                allSubCategories.add(subCategory);
            }
        } catch (SQLException sqlException) {
            throw new SQLException();
        }
        return allSubCategories;
    }

    public void insertIntoSubCategory(int citizenId, int subCategoryId, String note, int condition) throws SQLException {
        String sql = "INSERT INTO SubCatTextOnCitizen (citizenId, SubCategoryId, Note, Condition) VALUES (?,?,?,?);";
        try (Connection connection = databaseConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setInt(1, citizenId);
                preparedStatement.setInt(2, subCategoryId);
                preparedStatement.setString(3, note);
                preparedStatement.setInt(4, condition);
                preparedStatement.execute();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }


    public SubCategoryText getTextOnSubCategory(int citizenId, int subCategoryId) throws SQLServerException {

        try (Connection connection = databaseConnector.getConnection()) {
            String sql = "SELECT SubCatTextOnCitizen.SubCatTextOnCitizenID, SubCatTextOnCitizen.citizenId, SubCatTextOnCitizen.subCategoryId, SubCatTextOnCitizen.Note, SubCatTextOnCitizen.Condition " +
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
                int subId = resultSet.getInt("subCategoryId");
                String note = resultSet.getString("Note");
                ConditionEnum condition = ConditionEnum.valueOf(resultSet.getString("Condition"));

                SubCategoryText subCategoryText = new SubCategoryText(id, citId, subId, note, condition);
                return subCategoryText;
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }


    public static void main(String[] args) throws IOException, SQLException {
        HealthConditionsDAO healthConditionsDAO = new HealthConditionsDAO();
        //healthConditionsDAO.insertIntoSubCategory(12, 3, "Gider ikke noget", 0);
        //System.out.println(healthConditionsDAO.getTextOnSubCategory(13,3));
        //System.out.println(healthConditionsDAO.getSubCategories(1));
        healthConditionsDAO.insertIntoSubCategory(1, 1, "Gider ikke at gå i bad", ConditionEnum.RELEVANT.getValue());

    }
}
