package gui.model;

import be.HealthCondition.HealthCondition;
import be.HealthCondition.SubCategory;
import be.HealthCondition.SubCategoryText;
import bll.HealthConditionsManager;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class HealthConditionsModel {

    private final HealthConditionsManager healthConditionsManager;

    public HealthConditionsModel() throws IOException {
        this.healthConditionsManager = new HealthConditionsManager();
    }

    public List<HealthCondition> getHealthConditions() throws SQLException {
        return healthConditionsManager.getHealthConditions();
    }

    public List<SubCategory> getSubCategories(int categoryId) throws SQLException {
        return healthConditionsManager.getSubCategories(categoryId);
    }

    public SubCategoryText getTextOnSubCategory(int citizenId, int subCategoryId) throws SQLServerException {
        return healthConditionsManager.getTextOnSubCategory(citizenId, subCategoryId);
    }

    public void insertIntoSubCategory(int citizenId, int subCategoryId, String note, int condition) throws SQLException {
        healthConditionsManager.insertIntoSubCategory(citizenId, subCategoryId, note, condition);
    }
}
