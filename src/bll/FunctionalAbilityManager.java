package bll;

import be.FunctionalAbilities.FunctionalAbilitySubCategoryText;
import be.FunctionalAbilities.FunctionalAbility;
import be.enums.FunctionalEnum;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import dal.FunctionalAbilitiesDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class FunctionalAbilityManager {

    private FunctionalAbilitiesDAO functionalAbilitiesDAO;

    public FunctionalAbilityManager() throws IOException {
        functionalAbilitiesDAO = new FunctionalAbilitiesDAO();
    }

    public List<FunctionalAbility> getFunctionalAbilities() throws SQLException {
        return functionalAbilitiesDAO.getFunctionalAbilities();
    }

    public FunctionalAbilitySubCategoryText getInfoOnSubCategory(int citizenId, int functionalAbilitySubCategoryId) throws SQLServerException {
        return functionalAbilitiesDAO.getInfoOnSubCategory(citizenId, functionalAbilitySubCategoryId);
    }

    public List<FunctionalAbilitySubCategoryText> getInfoOnSubCategories(int citizenId) throws SQLServerException{
        return functionalAbilitiesDAO.getInfoOnSubCategories(citizenId);
    }

    public List<FunctionalAbilitySubCategoryText> getFunctionalAbilitySubCategories(int functionalAbilitySubCategoryId) throws SQLException{
        return functionalAbilitiesDAO.getFunctionalAbilitySubCategories(functionalAbilitySubCategoryId);
    }

    public FunctionalAbility getSubcategoryData(int citizenId, int functionalAbilitySubCategoryId) throws SQLException {
        return functionalAbilitiesDAO.getSubcategoryData(citizenId, functionalAbilitySubCategoryId);
    }

    public FunctionalAbility abilitiesOnCitizen(int citizenId) throws SQLException {
        return functionalAbilitiesDAO.abilitiesOnCitizen(citizenId);
    }

    public FunctionalAbility createFunctionalAbility(int citizenId, int functionalAbilitySubCategoryId, int abilityNow, int abilityExpected, String abilityNote, String citizenPerformance, String citizenMeaningOfPerformance, String abilityNoteCitizen) throws SQLException {
        return functionalAbilitiesDAO.createFunctionalAbilities(citizenId, functionalAbilitySubCategoryId, abilityNow, abilityExpected, abilityNote,citizenPerformance,citizenMeaningOfPerformance, abilityNoteCitizen);
    }

    public void editAbilities(FunctionalAbility functionalAbility) throws SQLException {
        functionalAbilitiesDAO.editAbilities(functionalAbility);
    }

    public void deleteAbilities(int id) throws Exception {
        functionalAbilitiesDAO.deleteFunctionalAbility(id);
    }


}
