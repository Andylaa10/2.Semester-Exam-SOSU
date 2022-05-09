package bll;

import be.HealthCondition.HealthCondition;
import be.HealthCondition.HealthConditionSubCategory;
import be.HealthCondition.HealthConditionSubCategoryText;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import dal.HealthConditionsDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class HealthConditionsManager {

    private HealthConditionsDAO healthConditionsDAO;

    public HealthConditionsManager() throws IOException {
        this.healthConditionsDAO = new HealthConditionsDAO();
    }

    public List<HealthCondition> getHealthConditions() throws SQLException {
        return healthConditionsDAO.getHealthConditions();
    }

    public List<HealthConditionSubCategory> getSubCategories(int categoryId) throws SQLException {
        return healthConditionsDAO.getSubCategories(categoryId);
    }

    public HealthConditionSubCategoryText getTextOnSubCategory(int citizenId, int subCategoryId) throws SQLServerException {
        return healthConditionsDAO.getInfoOnSubCategory(citizenId, subCategoryId);
    }

    public void insertIntoSubCategory(int citizenId, int subCategoryId, String note, int condition) throws SQLException {
        healthConditionsDAO.insertIntoSubCategory(citizenId, subCategoryId, note, condition);
    }
}
