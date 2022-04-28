package be;

public class Case {

    private int id;
    private String name;
    private String date;
    private String info;

    /**
     * Constructor
     */
    public Case() {
    }

    /**
     * Overloaded constructor with id, name, date and info
     * @param id
     * @param name
     * @param date
     * @param info
     */
    public Case(int id, String name, String date, String info) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.info = info;
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
     * Set the name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the date
     * @return
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets the date
     * @param date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Gets the info
     * @return
     */
    public String getInfo() {
        return info;
    }

    /**
     * Sets the info
     * @param info
     */
    public void setInfo(String info) {
        this.info = info;
    }
}
