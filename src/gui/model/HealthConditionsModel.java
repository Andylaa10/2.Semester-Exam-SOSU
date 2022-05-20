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


    /**
     * Gets a list of HealthConditions using the getHealthConditions from the healthConditionsManager.
     *
     * @return
     * @throws SQLException
     */
    public List<HealthCondition> getHealthConditions() throws SQLException {
        return healthConditionsManager.getHealthConditions();
    }

    /**
     * Gets a list of HealthConditionsSubCategory using the getSubCategories from the healthConditionsManager.
     *
     * @param categoryId
     * @return
     * @throws SQLException
     */
    public List<HealthConditionSubCategory> getSubCategories(int categoryId) throws SQLException {
        return healthConditionsManager.getSubCategories(categoryId);
    }

    /**
     * Gets a single healthConditionSubCategoryText using the getTextOnSubCategory from the healthConditionsManager.
     *
     * @param citizenId
     * @param subCategoryId
     * @return
     * @throws SQLServerException
     */
    public HealthConditionSubCategoryText getTextOnSubCategory(int citizenId, int subCategoryId) throws SQLException {
        return healthConditionsManager.getTextOnSubCategory(citizenId, subCategoryId);
    }

    /**
     * Gets a list of HealthConditionSubCategoryText using the getInfoOnSubCategories from the healthConditionsManager;
     *
     * @param citizenId
     * @return
     */
    public List<HealthConditionSubCategoryText> getInfoOnSubCategories(int citizenId) throws SQLException {
        return healthConditionsManager.getInfoOnSubCategories(citizenId);
    }

    /**
     * Creates all data from subCategory using the insertIntoSubCategory method from the healthConditionsManager.
     *
     * @param citizenId
     * @param subCategoryId
     * @param professionalnote
     * @param currentLevelAssessment
     * @param expectedLevelAssessment
     * @param condition
     * @throws SQLException
     */
    public void insertIntoSubCategory(int citizenId, int subCategoryId, String professionalnote, String currentLevelAssessment, String expectedLevelAssessment, int condition) throws SQLException {
        healthConditionsManager.insertIntoSubCategory(citizenId, subCategoryId, professionalnote, currentLevelAssessment, expectedLevelAssessment, condition);
    }

    /**
     * Edits all data from subCategory using the editSubCategory method from healthConditionsManager.
     *
     * @param subCategoryText
     * @throws Exception
     */
    public void editSubcategory(HealthConditionSubCategoryText subCategoryText) throws Exception {
        healthConditionsManager.editSubcategory(subCategoryText);
    }

    /**
     * Gets data on a single HealthConditionSubCategoryText using the getHealthConditionData method from the -
     * healthConditionsManager.
     *
     * @param citizenId
     * @param subCategoryId
     * @return
     * @throws Exception
     */
    public HealthConditionSubCategoryText getHealthConditionData(int citizenId, int subCategoryId) throws Exception {
        return healthConditionsManager.getHealthConditionData(citizenId, subCategoryId);
    }
}
