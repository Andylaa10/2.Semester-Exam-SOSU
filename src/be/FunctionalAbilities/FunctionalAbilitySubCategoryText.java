package be.FunctionalAbilities;

public class FunctionalAbilitySubCategoryText {

    private int id;
    private String name;
    private int subCategoryId;
    private String subCategoryName;
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


    public FunctionalAbilitySubCategoryText(int id, int functionalAbilitySubCategoryIdColumn, int citId, int abilityNow, int abilityExpected, String abilityNote, String citizenPerformance, String citizenMeaningOfPerformance, String abilityNoteCitizen, int functionalAbilityNameID) {
        this.id = id;
        this.subCategoryId = functionalAbilitySubCategoryIdColumn;
        this.citizenID = citId;
        this.abilityNow = abilityNow;
        this.abilityExpected = abilityExpected;
        this.abilityNote = abilityNote;
        this.citizenPerformance = citizenPerformance;
        this.citizenMeaningOfPerformance = citizenMeaningOfPerformance;
        this.abilityNoteCitizen = abilityNoteCitizen;
        this.functionalAbilityID = functionalAbilityNameID;
    }


    public void setId(int id) {
        this.id = id;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
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
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the functionalAbilityNameId
     */
    public int getSubCategoryId() {
        return subCategoryId;
    }

    /**
     * Sets the functionalAbilityNameId
     */
    public void setSubCategoryId(int subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    @Override
    public String toString() {
        return "FunctionalAbilitySubCategoryText{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", functionalAbilityNameId=" + subCategoryId +
                ", functionalAbilitySubCategoryName='" + subCategoryName + '\'' +
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
