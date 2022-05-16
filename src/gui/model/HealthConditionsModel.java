package gui.model;

import be.HealthCondition.HealthCondition;
import be.HealthCondition.HealthConditionSubCategory;
import be.HealthCondition.HealthConditionSubCategoryText;
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

    public List<HealthConditionSubCategory> getSubCategories(int categoryId) throws SQLException {
        return healthConditionsManager.getSubCategories(categoryId);
    }

    public HealthConditionSubCategoryText getTextOnSubCategory(int citizenId, int subCategoryId) throws SQLServerException {
        return healthConditionsManager.getTextOnSubCategory(citizenId, subCategoryId);
    }

    public List<HealthConditionSubCategoryText> getInfoOnSubCategories(int citizenId){
        return healthConditionsManager.getInfoOnSubCategories(citizenId);
    }

    public void insertIntoSubCategory(int citizenId, int subCategoryId, String professionalnote, String currentLevelAssessment, String expectedLevelAssessment, int condition) throws SQLException {
        healthConditionsManager.insertIntoSubCategory(citizenId, subCategoryId, professionalnote, currentLevelAssessment, expectedLevelAssessment, condition);
    }

    public void editSubcategory(HealthConditionSubCategoryText subCategoryText) throws Exception {
        healthConditionsManager.editSubcategory(subCategoryText);
    }
    public HealthConditionSubCategoryText getHealthConditionData(int citizenId, int subCategoryId) throws Exception{
        return healthConditionsManager.getHealthConditionData(citizenId, subCategoryId);
    }
}
