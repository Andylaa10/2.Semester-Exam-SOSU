package gui.model;

import be.FunctionalAbilities.FunctionalAbilitySubCategoryText;
import be.FunctionalAbilities.FunctionalAbility;

import be.enums.FunctionalEnum;
import bll.FunctionalAbilityManager;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class FunctionalAbilitiesModel {

    private final FunctionalAbilityManager manager;

    public FunctionalAbilitiesModel() throws IOException {
        manager = new FunctionalAbilityManager();
    }

    public List<FunctionalAbility> getFunctionalAbilities() throws SQLException {
        return manager.getFunctionalAbilities();
    }

    public FunctionalAbilitySubCategoryText getInfoOnSubCategory(int citizenId, int functionalAbilitySubCategoryId) throws SQLServerException {
        return manager.getInfoOnSubCategory(citizenId, functionalAbilitySubCategoryId);
    }

    public List<FunctionalAbilitySubCategoryText> getInfoOnSubCategories(int citizenId) throws SQLServerException{
        return manager.getInfoOnSubCategories(citizenId);
    }

    public List<FunctionalAbilitySubCategoryText> getFunctionalAbilitySubCategories(int functionalAbilitySubCategoryId) throws SQLException{
        return manager.getFunctionalAbilitySubCategories(functionalAbilitySubCategoryId);
    }

    public FunctionalAbility getSubcategoryData(int citizenId, int functionalAbilitySubCategoryId) throws SQLException {
        return manager.getSubcategoryData(citizenId, functionalAbilitySubCategoryId);
    }

    public FunctionalAbility abilitiesOnCitizen(int citizenId) throws SQLException {
        return manager.abilitiesOnCitizen(citizenId);
    }

    public FunctionalAbility createFunctionalAbility(int citizenId, int functionalAbilitySubCategoryId, int abilityNow, int abilityExpected, String abilityNote, String citizenPerformance, String citizenMeaningOfPerformance, String abilityNoteCitizen) throws SQLException {
        return manager.createFunctionalAbility(citizenId, functionalAbilitySubCategoryId, abilityNow, abilityExpected, abilityNote, citizenPerformance,citizenMeaningOfPerformance, abilityNoteCitizen);
    }

    public void editAbilities(FunctionalAbility functionalAbility) throws SQLException {
        manager.editAbilities(functionalAbility);
    }

    public void deleteAbilities(int id) throws Exception {
        manager.deleteAbilities(id);
    }
}
