package be.FunctionalAbilities;

import be.enums.FunctionalEnum;

public class FunctionalAbility {

    private int functionalAbilityID;
    private int citizenId;
    private int functionalAbilitySubCategoryId;
    private int abilityNow;
    private int abilityExcepted;
    private String abilityNote;
    private String abilityNoteCitizen;

    /**
     * Constructor
     */
    public FunctionalAbility() {
    }

    /**
     * Overloaded constructor with functionalAbilityID, citizenId, functionalAbilitySubCategoryId, abilityNow, abilityExcepted, abilityNote and abilityNoteCitizen
     * @param functionalAbilityID
     * @param citizenId
     * @param functionalAbilitySubCategoryId
     * @param abilityNow
     * @param abilityExcepted
     * @param abilityNote
     * @param abilityNoteCitizen
     */
    public FunctionalAbility(int functionalAbilityID, int citizenId, int functionalAbilitySubCategoryId, int abilityNow, int abilityExcepted, String abilityNote, String abilityNoteCitizen) {
        this.functionalAbilityID = functionalAbilityID;
        this.citizenId = citizenId;
        this.functionalAbilitySubCategoryId = functionalAbilitySubCategoryId;
        this.abilityNow = abilityNow;
        this.abilityExcepted = abilityExcepted;
        this.abilityNote = abilityNote;
        this.abilityNoteCitizen = abilityNoteCitizen;
    }

    /**
     * Gets the functionalAbilityID
     * @return
     */
    public int getFunctionalAbilityID() {
        return functionalAbilityID;
    }

    /**
     * Sets the functionalAbilityID
     * @return
     */
    public void setFunctionalAbilityID(int functionalAbilityID) {
        this.functionalAbilityID = functionalAbilityID;
    }

    /**
     * Gets the citizenId
     * @return
     */
    public int getCitizenId() {
        return citizenId;
    }

    /**
     * Sets the citizenId
     * @return
     */
    public void setCitizenId(int citizenId) {
        this.citizenId = citizenId;
    }

    /**
     * Gets the functionalAbilitySubCategoryId
     * @return
     */
    public int getFunctionalAbilitySubCategoryId() {
        return functionalAbilitySubCategoryId;
    }

    /**
     * Sets the functionalAbilitySubCategoryId
     * @return
     */
    public void setFunctionalAbilitySubCategoryId(int functionalAbilitySubCategoryId) {
        this.functionalAbilitySubCategoryId = functionalAbilitySubCategoryId;
    }

    /**
     * Gets the abilityNow
     * @return
     */
    public int getAbilityNow() {
        return abilityNow;
    }

    /**
     * Sets the abilityNow
     * @return
     */
    public void setAbilityNow(int abilityNow) {
        this.abilityNow = abilityNow;
    }

    /**
     * Gets the abilityExcepted
     * @return
     */
    public int getAbilityExcepted() {
        return abilityExcepted;
    }

    /**
     * Sets the abilityExcepted
     * @return
     */
    public void setAbilityExcepted(int abilityExcepted) {
        this.abilityExcepted = abilityExcepted;
    }

    /**
     * Gets the abilityNote
     * @return
     */
    public String getAbilityNote() {
        return abilityNote;
    }

    /**
     * Sets the abilityNote
     * @return
     */
    public void setAbilityNote(String abilityNote) {
        this.abilityNote = abilityNote;
    }

    /**
     * Gets the abilityNoteCitizen
     * @return
     */
    public String getAbilityNoteCitizen() {
        return abilityNoteCitizen;
    }

    /**
     * Sets the abilityNoteCitizen
     * @return
     */
    public void setAbilityNoteCitizen(String abilityNoteCitizen) {
        this.abilityNoteCitizen = abilityNoteCitizen;
    }
}
