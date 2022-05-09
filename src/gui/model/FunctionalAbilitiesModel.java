package gui.model;

import be.FunctionalAbilities;
import bll.FunctionalAbilityManager;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class FunctionalAbilitiesModel {

    private FunctionalAbilityManager manager;

    public FunctionalAbilitiesModel() throws IOException {
        manager = new FunctionalAbilityManager();
    }

    public List<FunctionalAbilities> getFunctionalAbilities() throws SQLException {
        return manager.getFunctionalAbilities();
    }

    public FunctionalAbilities abilitiesOnCitizen(int citizenId) throws SQLException {
        return manager.abilitiesOnCitizen(citizenId);
    }

    public FunctionalAbilities createFunctionalAbility(int abilityNow, String abilityExcepted) throws SQLException {
        return manager.createFunctionalAbility(abilityNow, abilityExcepted);
    }

    public void editAbilities(FunctionalAbilities functionalAbilities) throws SQLException {
        manager.editAbilities(functionalAbilities);
    }

    public void deleteAbilities(int id) throws Exception {
        manager.deleteAbilities(id);
    }
}