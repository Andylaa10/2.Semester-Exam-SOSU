package bll;

import be.FunctionalAbilities.FunctionalAbilitySubCategoryText;
import be.FunctionalAbilities.FunctionalAbility;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import dal.FunctionalAbilitiesDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class FunctionalAbilityManager {

    private FunctionalAbilitiesDAO functionalAbilitiesDAO;

    /**
     * Constructor
     *
     * @throws IOException
     */
    public FunctionalAbilityManager() throws IOException {
        functionalAbilitiesDAO = new FunctionalAbilitiesDAO();
    }

    /**
     * Gets a list of all functional abilities, by using the method from functionalAbilitiesDAO
     *
     * @return
     * @throws SQLException
     */
    public List<FunctionalAbility> getFunctionalAbilities() throws SQLException {
        return functionalAbilitiesDAO.getFunctionalAbilities();
    }

    /**
     * Gets functional abilities information based on selected citizen and selected functional abilities, by using the method from functionalAbilitiesDAO
     *
     * @param citizenId
     * @param functionalAbilitySubCategoryId
     * @return
     * @throws SQLServerException
     */
    public FunctionalAbilitySubCategoryText getInfoOnSubCategory(int citizenId, int functionalAbilitySubCategoryId) throws SQLException {
        return functionalAbilitiesDAO.getInfoOnSubCategory(citizenId, functionalAbilitySubCategoryId);
    }

    /**
     * Gets functional abilities information based on selected citizen, by using the method from functionalAbilitiesDAO
     *
     * @param citizenId
     * @return
     * @throws SQLServerException
     */
    public List<FunctionalAbilitySubCategoryText> getInfoOnSubCategories(int citizenId) throws SQLException {
        return functionalAbilitiesDAO.getInfoOnSubCategories(citizenId);
    }

    /**
     * Gets all functional abilities subcategories based on selected functional abilities, by using the method from functionalAbilitiesDAO
     *
     * @param functionalAbilitySubCategoryId
     * @return
     * @throws SQLException
     */
    public List<FunctionalAbilitySubCategoryText> getFunctionalAbilitySubCategories(int functionalAbilitySubCategoryId) throws SQLException {
        return functionalAbilitiesDAO.getFunctionalAbilitySubCategories(functionalAbilitySubCategoryId);
    }

    /**
     * Get all subcategory data on a selected citizen, by using the method from functionalAbilitiesDAO
     * * @param citizenId
     *
     * @param citizenId
     * @param functionalAbilitySubCategoryId
     * @return
     * @throws SQLException
     */
    public FunctionalAbility getSubcategoryData(int citizenId, int functionalAbilitySubCategoryId) throws SQLException {
        return functionalAbilitiesDAO.getSubcategoryData(citizenId, functionalAbilitySubCategoryId);
    }

    /**
     * Creates functional ability on a citizen, by selecting a citizen and assign the credentials, by using the method from functionalAbilitiesDAO
     * * @param citizenId
     *
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
        return functionalAbilitiesDAO.createFunctionalAbilities(citizenId, functionalAbilitySubCategoryId, abilityNow, abilityExpected, abilityNote, citizenPerformance, citizenMeaningOfPerformance, abilityNoteCitizen);
    }

    /**
     * Edits a functional ability when a functional ability is selected, by using the method from functionalAbilitiesDAO
     *
     * @param functionalAbility
     * @throws SQLException
     */
    public void editAbilities(FunctionalAbility functionalAbility) throws SQLException {
        functionalAbilitiesDAO.editAbilities(functionalAbility);
    }

    /**
     * Deletes a functional ability when a functional ability selected, by using the method from functionalAbilitiesDAO
     *
     * @param id
     * @throws Exception
     */
    public void deleteAbilities(int id) throws Exception {
        functionalAbilitiesDAO.deleteFunctionalAbility(id);
    }


}
