package be.FunctionalAbilities;

public class FunctionalAbilityName {

    private int id;
    private String name;


    /**
     * Constructor
     */
    public FunctionalAbilityName() {
    }

    /**
     * Overloaded constructor with id and name
     * @param id
     * @param name
     */
    public FunctionalAbilityName(int id, String name) {
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
     * Set the id
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
}
