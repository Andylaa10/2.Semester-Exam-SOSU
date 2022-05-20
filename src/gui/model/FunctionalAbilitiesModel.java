package gui.model;

import be.FunctionalAbilities.FunctionalAbilitySubCategoryText;
import be.FunctionalAbilities.FunctionalAbility;

import bll.FunctionalAbilityManager;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class FunctionalAbilitiesModel {

    private final FunctionalAbilityManager functionalAbilityManager;

    public FunctionalAbilitiesModel() throws IOException {
        functionalAbilityManager = new FunctionalAbilityManager();
    }

    /**
     * Gets a list of functionalAbilities using the getFunctionalAbilities method from functionalAbilityManager.
     * @return
     * @throws SQLException
     */
    public List<FunctionalAbility> getFunctionalAbilities() throws SQLException {
        return functionalAbilityManager.getFunctionalAbilities();
    }

    /**
     * Gets a single info from the subcategory using the getInfoOnSubCategory method from functionalAbilityManager.
     * @param citizenId
     * @param functionalAbilitySubCategoryId
     * @return
     * @throws SQLServerException
     */
    public FunctionalAbilitySubCategoryText getInfoOnSubCategory(int citizenId, int functionalAbilitySubCategoryId) throws SQLServerException {
        return functionalAbilityManager.getInfoOnSubCategory(citizenId, functionalAbilitySubCategoryId);
    }

    /***
     * Gets a list of info from the subcategory using the getInfoOnSubCategory method from functionalAbilityManager.
     * @param citizenId
     * @return
     * @throws SQLServerException
     */
    public List<FunctionalAbilitySubCategoryText> getInfoOnSubCategories(int citizenId) throws SQLServerException{
        return functionalAbilityManager.getInfoOnSubCategories(citizenId);
    }

    /**
     * Gets a list of subCategories using the getFunctionalAbilitySubCategories method from functionalAbilityManager.
     * @param functionalAbilitySubCategoryId
     * @return
     * @throws SQLException
     */
    public List<FunctionalAbilitySubCategoryText> getFunctionalAbilitySubCategories(int functionalAbilitySubCategoryId) throws SQLException{
        return functionalAbilityManager.getFunctionalAbilitySubCategories(functionalAbilitySubCategoryId);
    }

    /**
     * Gets subCategory Data using the getSubcategoryData method from functionalAbilityManager.
     * @param citizenId
     * @param functionalAbilitySubCategoryId
     * @return
     * @throws SQLException
     */
    public FunctionalAbility getSubcategoryData(int citizenId, int functionalAbilitySubCategoryId) throws SQLException {
        return functionalAbilityManager.getSubcategoryData(citizenId, functionalAbilitySubCategoryId);
    }

    /**
     * Creates a functionalAbility using the createFunctionalAbility from the functionalAbilityManager.
     * @param citizenId
     * @param functionalAbilitySubCategoryId
     * @param abilityNow
     * @param abilityExpected
     * @param abilityNote
     * @param citizenPerformance
     * @param citizenMeaningOfPerformance
     * @param abilityNoteCitizen
     * @return
     * @throws SQLException
     */
    public FunctionalAbility createFunctionalAbility(int citizenId, int functionalAbilitySubCategoryId, int abilityNow, int abilityExpected, String abilityNote, String citizenPerformance, String citizenMeaningOfPerformance, String abilityNoteCitizen) throws SQLException {
        return functionalAbilityManager.createFunctionalAbility(citizenId, functionalAbilitySubCategoryId, abilityNow, abilityExpected, abilityNote, citizenPerformance,citizenMeaningOfPerformance, abilityNoteCitizen);
    }

    /**
     * Edits a functionalAbility using the editAbilities from the functionalAbilityManager.
     * @param functionalAbility
     * @throws SQLException
     */
    public void editAbilities(FunctionalAbility functionalAbility) throws SQLException {
        functionalAbilityManager.editAbilities(functionalAbility);
    }

    /**
     * Deletes a functionalAbility using the deleteAbilities from the functionalAbilityManager.
     * @param id
     * @throws Exception
     */
    public void deleteAbilities(int id) throws Exception {
        functionalAbilityManager.deleteAbilities(id);
    }
}
