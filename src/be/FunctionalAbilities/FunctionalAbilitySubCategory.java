package be.FunctionalAbilities;

public class FunctionalAbilitySubCategory {

    private int id;
    private String name;


    public FunctionalAbilitySubCategory(int id, String name) {
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
}
