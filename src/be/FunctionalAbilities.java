package be;

import be.enums.FunctionalEnum;

public class FunctionalAbilities {

    private int id;
    private FunctionalEnum abilityNow;
    private String abilityExpected;

    /**
     * Constructor
     */
    public FunctionalAbilities() {
    }

    /**
     * Overloaded constructor with id, abilityNow and abilityExpected
     * @param id
     * @param abilityNow
     * @param abilityExpected
     */
    public FunctionalAbilities(int id, FunctionalEnum abilityNow, String abilityExpected) {
        this.id = id;
        this.abilityNow = abilityNow;
        this.abilityExpected = abilityExpected;
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
     * Gets the abilityNow
     * @return
     */
    public FunctionalEnum getAbilityNow() {
        return abilityNow;
    }

    /**
     * Set the abilityNow
     * @param abilityNow
     */
    public void setAbilityNow(FunctionalEnum abilityNow) {
        this.abilityNow = abilityNow;
    }

    /**
     * Gets the abilityExpected
     * @return
     */
    public String getAbilityExpected() {
        return abilityExpected;
    }

    /**
     * Sets the abilityExpected
     * @param abilityExpected
     */
    public void setAbilityExpected(String abilityExpected) {
        this.abilityExpected = abilityExpected;
    }
}
