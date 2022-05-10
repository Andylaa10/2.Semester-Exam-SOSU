package be.FunctionalAbilities;

import be.enums.FunctionalEnum;

public class FunctionalAbilitySubCategoryText {

    private int id;
    private String name;
    private int functionalAbilityNameId;
    private String functionalAbilitySubCategoryName;
    private int functionalAbilityID;
    private int citizenID;
    private int abilityNow;
    private int abilityExpected;
    private String abilityNote;
    private String citizenPerformance;
    private String citizenMeaningOfPerformance;
    private String abilityNoteCitizen;


    public FunctionalAbilitySubCategoryText(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public FunctionalAbilitySubCategoryText(int functionalAbilityID, int citId, int abilityNow, int abilityExpected, String abilityNote, String citizenPerformance, String citizenMeaningOfPerformance, String abilityNoteCitizen) {
        this.functionalAbilityID = functionalAbilityID;
        this.citizenID = citId;
        this.abilityNow = abilityNow;
        this.abilityExpected = abilityExpected;
        this.abilityNote = abilityNote;
        this.citizenPerformance = citizenPerformance;
        this.citizenMeaningOfPerformance = citizenMeaningOfPerformance;
        this.abilityNoteCitizen = abilityNoteCitizen;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFunctionalAbilitySubCategoryName() {
        return functionalAbilitySubCategoryName;
    }

    public void setFunctionalAbilitySubCategoryName(String functionalAbilitySubCategoryName) {
        this.functionalAbilitySubCategoryName = functionalAbilitySubCategoryName;
    }

    public int getFunctionalAbilityID() {
        return functionalAbilityID;
    }

    public void setFunctionalAbilityID(int functionalAbilityID) {
        this.functionalAbilityID = functionalAbilityID;
    }

    public int getCitizenID() {
        return citizenID;
    }

    public void setCitizenID(int citizenID) {
        this.citizenID = citizenID;
    }

    public int getAbilityNow() {
        return abilityNow;
    }

    public void setAbilityNow(int abilityNow) {
        this.abilityNow = abilityNow;
    }

    public int getAbilityExpected() {
        return abilityExpected;
    }

    public void setAbilityExpected(int abilityExpected) {
        this.abilityExpected = abilityExpected;
    }

    public String getAbilityNote() {
        return abilityNote;
    }

    public void setAbilityNote(String abilityNote) {
        this.abilityNote = abilityNote;
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

    public String getAbilityNoteCitizen() {
        return abilityNoteCitizen;
    }

    public void setAbilityNoteCitizen(String abilityNoteCitizen) {
        this.abilityNoteCitizen = abilityNoteCitizen;
    }

    /**
     * Gets the id
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the name
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the functionalAbilityNameId
     * @return
     */
    public int getFunctionalAbilityNameId() {
        return functionalAbilityNameId;
    }

    /**
     * Sets the functionalAbilityNameId
     * @param functionalAbilityNameId
     */
    public void setFunctionalAbilityNameId(int functionalAbilityNameId) {
        this.functionalAbilityNameId = functionalAbilityNameId;
    }

    @Override
    public String toString() {
        return "SubFunctional{" +
                ", functionalAbilityID=" + functionalAbilityID +
                ", citizenID=" + citizenID +
                ", abilityNow=" + abilityNow +
                ", abilityExpected=" + abilityExpected +
                ", abilityNote='" + abilityNote + '\'' +
                ", citizenPerformance='" + citizenPerformance + '\'' +
                ", citizenMeaningOfPerformance='" + citizenMeaningOfPerformance + '\'' +
                ", abilityNoteCitizen='" + abilityNoteCitizen + '\'' +
                '}';
    }
}
