package be.FunctionalAbilities;

public class FunctionalAbility {

    private int functionalAbilityID;
    private int citizenId;
    private int abilityNow;
    private int abilityExcepted;
    private int functionalAbilitySubCategoryId;
    private String abilityNote;
    private String abilityNoteCitizen;
    private String functionalAbilitySubCategoryName;
    private String citizenPerformance;
    private String citizenMeaningOfPerformance;

    public FunctionalAbility(int functionalAbilityID, int citizenId, int functionalAbilitySubCategoryId, int abilityNow, int abilityExpected, String abilityNote, String abilityNoteCitizen) {
        this.functionalAbilityID = functionalAbilityID;
        this.citizenId = citizenId;
        this.functionalAbilitySubCategoryId = functionalAbilitySubCategoryId;
        this.abilityNow = abilityNow;
        this.abilityExcepted = abilityExpected;
        this.abilityNote = abilityNote;
        this.abilityNoteCitizen = abilityNoteCitizen;
    }


    /**
     * Overloaded constructor with functionalAbilityID, citizenId, functionalAbilitySubCategoryId, abilityNow, abilityExcepted, abilityNote and abilityNoteCitizen
     */
    public FunctionalAbility(int functionalAbilityID, int citizenId, int functionalAbilitySubCategoryId, int abilityNow, int abilityExcepted, String abilityNote, String abilityNoteCitizen,  String citizenPerformance, String citizenMeaningOfPerformance) {
        this.functionalAbilityID = functionalAbilityID;
        this.citizenId = citizenId;
        this.functionalAbilitySubCategoryId = functionalAbilitySubCategoryId;
        this.abilityNow = abilityNow;
        this.abilityExcepted = abilityExcepted;
        this.abilityNote = abilityNote;
        this.abilityNoteCitizen = abilityNoteCitizen;
        this.citizenPerformance = citizenPerformance;
        this.citizenMeaningOfPerformance = citizenMeaningOfPerformance;
    }

    public FunctionalAbility(int functionalAbilityID, String functionalAbilitySubCategoryName) {
        this.functionalAbilityID = functionalAbilityID;
        this.functionalAbilitySubCategoryName = functionalAbilitySubCategoryName;
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

    public String getCitizenPerformance() {
        return citizenPerformance;
    }

    public void setCitizenPerformance(String citizenPerformance) {
        this.citizenPerformance = citizenPerformance;
    }

    public String getCitizenMeaningOfPerformance() {
        return citizenMeaningOfPerformance;
    }

    public void setCitizenMeaningOfPerformance(String citizenMeaningOfPerformance) {
        this.citizenMeaningOfPerformance = citizenMeaningOfPerformance;
    }

    @Override
    public String toString() {
        return "FunctionalAbilities{" +
                "functionalAbilityID=" + functionalAbilityID +
                ", functionalAbilitySubCategoryName='" + functionalAbilitySubCategoryName + '\'' +
                '}';
    }
}
