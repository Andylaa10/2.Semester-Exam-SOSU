package be.FunctionalAbilities;

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
    public int getFunctionalAbilityNameId() {
        return functionalAbilityNameId;
    }

    /**
     * Sets the functionalAbilityNameId
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
