package bll;

import be.FunctionalAbilities.FunctionalAbilitySubCategoryText;
import be.FunctionalAbilities.FunctionalAbility;
import be.enums.FunctionalEnum;
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

    public List<FunctionalAbilitySubCategoryText> getFunctionalAbilitySubCategories(int functionalAbilitySubCategoryId) throws SQLException{
        return functionalAbilitiesDAO.getFunctionalAbilitySubCategories(functionalAbilitySubCategoryId);
    }

    public FunctionalAbility abilitiesOnCitizen(int citizenId) throws SQLException {
        return functionalAbilitiesDAO.abilitiesOnCitizen(citizenId);
    }

    public FunctionalAbility createFunctionalAbility(int citizenId, int functionalAbilitySubCategoryId, int abilityNow, int abilityExpected, String abilityNote, String citizenPerformance, String citizenMeaningOfPerformance, String abilityNoteCitizen) throws SQLException {
        return functionalAbilitiesDAO.createFunctionalAbilities(citizenId, functionalAbilitySubCategoryId, abilityNow, abilityExpected, abilityNote, abilityNoteCitizen,citizenPerformance,citizenMeaningOfPerformance);
    }

    public void editAbilities(FunctionalAbility functionalAbility) throws SQLException {
        functionalAbilitiesDAO.editAbilities(functionalAbility);
    }

    public void deleteAbilities(int id) throws Exception {
        functionalAbilitiesDAO.deleteFunctionalAbility(id);
    }


}
