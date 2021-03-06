package bll;

import be.HealthCondition.HealthCondition;
import be.HealthCondition.HealthConditionSubCategory;
import be.HealthCondition.HealthConditionSubCategoryText;
import dal.HealthConditionsDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class HealthConditionsManager {

    private final HealthConditionsDAO healthConditionsDAO;

    /**
     * Constructor
     */
    public HealthConditionsManager() throws IOException {
        this.healthConditionsDAO = new HealthConditionsDAO();
    }

    /**
     * Get a list of all health conditions, by using the method from healthConditionsDAO
     */
    public List<HealthCondition> getHealthConditions() throws SQLException {
        return healthConditionsDAO.getHealthConditions();
    }

    /**
     * Gets a list of all health condition subcategories, by using the method from healthConditionsDAO
     */
    public List<HealthConditionSubCategory> getSubCategories(int categoryId) throws SQLException {
        return healthConditionsDAO.getSubCategories(categoryId);
    }

    /**
     * Gets text on a selected subcategories noted on the selected citizen, by using the method from healthConditionsDAO
     */
    public HealthConditionSubCategoryText getTextOnSubCategory(int citizenId, int subCategoryId) throws SQLException {
        return healthConditionsDAO.getInfoOnSubCategory(citizenId, subCategoryId);
    }

    /**
     * Gets a list of all the information on subcategories noted on th citizen, by using the method from healthConditionsDAO
     */
    public List<HealthConditionSubCategoryText> getInfoOnSubCategories(int citizenId) throws SQLException {
        return healthConditionsDAO.getInfoOnSubCategories(citizenId);
    }

    /**
     * Inserts information to a selected subcategory, by using the method from healthConditionsDAO
     */
    public void insertIntoSubCategory(int citizenId, int subCategoryId, String professionalnote, String currentLevelAssessment, String expectedLevelAssessment, int condition) throws SQLException {
        healthConditionsDAO.insertIntoSubCategory(citizenId, subCategoryId, professionalnote, currentLevelAssessment, expectedLevelAssessment, condition);
    }

    /**
     * Edits a selected subcategory
     */
    public void editSubcategory(HealthConditionSubCategoryText subCategoryText) throws Exception {
        healthConditionsDAO.editSubcategory(subCategoryText);
    }

    /**
     * Get the health condition data noted on a citizen, by using the method from healthConditionsDAO
     */
    public HealthConditionSubCategoryText getHealthConditionData(int citizenId, int subCategoryId) throws Exception {
        return healthConditionsDAO.getHealthConditionData(citizenId, subCategoryId);
    }
}
