package gui.model;

import be.FunctionalAbilities.FunctionalAbility;
import be.enums.FunctionalEnum;
import bll.FunctionalAbilityManager;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class FunctionalAbilitiesModel {

    private FunctionalAbilityManager manager;

    public FunctionalAbilitiesModel() throws IOException {
        manager = new FunctionalAbilityManager();
    }

    public List<FunctionalAbility> getFunctionalAbilities() throws SQLException {
        return manager.getFunctionalAbilities();
    }

    public FunctionalAbility abilitiesOnCitizen(int citizenId) throws SQLException {
        return manager.abilitiesOnCitizen(citizenId);
    }

    public FunctionalAbility createFunctionalAbility(int citizenId, int functionalAbilitySubCategoryId, FunctionalEnum abilityNow, FunctionalEnum abilityExpected, String abilityNote, String abilityNoteCitizen) throws SQLException {
        return manager.createFunctionalAbility(citizenId, functionalAbilitySubCategoryId, abilityNow, abilityExpected, abilityNote, abilityNoteCitizen);
    }

    public void editAbilities(FunctionalAbility functionalAbility) throws SQLException {
        manager.editAbilities(functionalAbility);
    }

    public void deleteAbilities(int id) throws Exception {
        manager.deleteAbilities(id);
    }
}
