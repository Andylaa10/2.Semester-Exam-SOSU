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
     * Overloaded constructor
     */
    public FunctionalAbility(int functionalAbilityID, int citizenId, int functionalAbilitySubCategoryId, int abilityNow, int abilityExcepted, String abilityNote, String citizenPerformance, String citizenMeaningOfPerformance, String abilityNoteCitizen) {
        this.functionalAbilityID = functionalAbilityID;
        this.citizenId = citizenId;
        this.functionalAbilitySubCategoryId = functionalAbilitySubCategoryId;
        this.abilityNow = abilityNow;
        this.abilityExcepted = abilityExcepted;
        this.abilityNote = abilityNote;
        this.citizenPerformance = citizenPerformance;
        this.citizenMeaningOfPerformance = citizenMeaningOfPerformance;
        this.abilityNoteCitizen = abilityNoteCitizen;
    }

    public FunctionalAbility(int citizenID, int functionalAbilityID, int abilityNow, int abilityExpected, String abilityNote, String citizenPerformance, String citizenMeaningOfPerformance, String abilityNoteCitizen) {
        this.citizenId = citizenID;
        this.functionalAbilityID = functionalAbilityID;
        this.abilityNow = abilityNow;
        this.abilityExcepted = abilityExpected;
        this.abilityNote = abilityNote;
        this.citizenPerformance = citizenPerformance;
        this.citizenMeaningOfPerformance = citizenMeaningOfPerformance;
        this.abilityNoteCitizen = abilityNoteCitizen;
    }

    public FunctionalAbility(String name) {
    }


    /**
     * Gets the functionalAbilityID
     */
    public int getFunctionalAbilityID() {
        return functionalAbilityID;
    }

    /**
     * Sets the functionalAbilityID
     */
    public void setFunctionalAbilityID(int functionalAbilityID) {
        this.functionalAbilityID = functionalAbilityID;
    }

    /**
     * Gets the citizenId
     */
    public int getCitizenId() {
        return citizenId;
    }

    /**
     * Sets the citizenId
     */
    public void setCitizenId(int citizenId) {
        this.citizenId = citizenId;
    }

    /**
     * Gets the functionalAbilitySubCategoryId
     */
    public int getFunctionalAbilitySubCategoryId() {
        return functionalAbilitySubCategoryId;
    }

    /**
     * Sets the functionalAbilitySubCategoryId
     */
    public void setFunctionalAbilitySubCategoryId(int functionalAbilitySubCategoryId) {
        this.functionalAbilitySubCategoryId = functionalAbilitySubCategoryId;
    }

    /**
     * Gets the abilityNow
     */
    public int getAbilityNow() {
        return abilityNow;
    }

    /**
     * Sets the abilityNow
     */
    public void setAbilityNow(int abilityNow) {
        this.abilityNow = abilityNow;
    }

    /**
     * Gets the abilityExcepted
     */
    public int getAbilityExcepted() {
        return abilityExcepted;
    }

    /**
     * Sets the abilityExcepted
     */
    public void setAbilityExcepted(int abilityExcepted) {
        this.abilityExcepted = abilityExcepted;
    }

    /**
     * Gets the abilityNote
     */
    public String getAbilityNote() {
        return abilityNote;
    }

    /**
     * Sets the abilityNote
     */
    public void setAbilityNote(String abilityNote) {
        this.abilityNote = abilityNote;
    }

    /**
     * Gets the abilityNoteCitizen
     */
    public String getAbilityNoteCitizen() {
        return abilityNoteCitizen;
    }

    /**
     * Sets the abilityNoteCitizen
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
