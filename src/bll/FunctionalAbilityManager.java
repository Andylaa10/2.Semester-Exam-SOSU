package bll;

import be.FunctionalAbilities;
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

    public FunctionalAbilities abilitiesOnCitizen(int citizenId) throws SQLException {
        return functionalAbilitiesDAO.abilitiesOnCitizen(citizenId);
    }

    public FunctionalAbilities createFunctionalAbility(FunctionalEnum abilityNow, String abilityExcepted) throws SQLException {
        return functionalAbilitiesDAO.createFunctionalAbilities(abilityNow, abilityExcepted);
    }

    public void editAbilities(FunctionalAbilities functionalAbilities) throws SQLException {
        functionalAbilitiesDAO.editAbilities(functionalAbilities);
    }

    public void deleteAbilities(int id) throws Exception {
        functionalAbilitiesDAO.deleteFunctionalAbility(id);
    }
}
