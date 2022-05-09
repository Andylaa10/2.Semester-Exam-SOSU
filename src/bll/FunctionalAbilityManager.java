package bll;

import be.FunctionalAbilities.FunctionalAbilities;
import be.FunctionalAbilities.SubFunctional;
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

    public List<FunctionalAbilities> getFunctionalAbilities() throws SQLException {
        return functionalAbilitiesDAO.getFunctionalAbilities();
    }

    public List<SubFunctional> getFunctionalAbilitySubCategories(int functionalAbilitySubCategoryId) throws SQLException{
        return functionalAbilitiesDAO.getFunctionalAbilitySubCategories(functionalAbilitySubCategoryId);
    }

    public FunctionalAbilities abilitiesOnCitizen(int citizenId) throws SQLException {
        return functionalAbilitiesDAO.abilitiesOnCitizen(citizenId);
    }

    public FunctionalAbilities createFunctionalAbility(int citizenId, int functionalAbilitySubCategoryId, FunctionalEnum abilityNow, FunctionalEnum abilityExpected, String abilityNote, String abilityNoteCitizen) throws SQLException {
        return functionalAbilitiesDAO.createFunctionalAbilities(citizenId, functionalAbilitySubCategoryId, abilityNow, abilityExpected, abilityNote, abilityNoteCitizen);
    }

    public void editAbilities(FunctionalAbilities functionalAbilities) throws SQLException {
        functionalAbilitiesDAO.editAbilities(functionalAbilities);
    }

    public void deleteAbilities(int id) throws Exception {
        functionalAbilitiesDAO.deleteFunctionalAbility(id);
    }
}
