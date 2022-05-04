package dal;

import be.HealthCondition;
import be.SubCategory;
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
            String sql = "SELECT subCategoryID, subCategoryName FROM SubCategory INNER JOIN HealthCondition ON HealthCondtion.healthConditionID = subCategory.healthCondtionId where HealthCondition.HealthCondtionID = ?;";

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


    public static void main(String[] args) throws IOException, SQLException {
        HealthConditionsDAO healthConditionsDAO = new HealthConditionsDAO();
        //System.out.println(healthConditionsDAO.getHealthConditions());
        //System.out.println(healthConditionsDAO.getSubCategories());

    }
}
