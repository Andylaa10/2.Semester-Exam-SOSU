package be.FunctionalAbilities;

public class SubFunctional {

    private int id;
    private String name;
    private int functionalAbilityNameId;
    private String functionalAbilitySubCategoryName;

    /**
     * Constructor
     */
    public SubFunctional() {
    }

    /**
     * Overloaded constructor with id, name and functionalAbilityNameId
     * @param id
     * @param name
     * @param functionalAbilityNameId
     */
    public SubFunctional(int id, String name, int functionalAbilityNameId) {
        this.id = id;
        this.name = name;
        this.functionalAbilityNameId = functionalAbilityNameId;
    }

    public SubFunctional(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Gets the id
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the id
     * @param id
     */
    public void setId(int id) {
        this.id = id;
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
}
