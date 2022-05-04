package be;

public class School {

    private int id;
    private String schoolName;

    /**
     * Constructor
     */
    public School() {
    }

    /**
     * Overloaded constructor with id and schoolName
     */
    public School(int id, String schoolName) {
        this.id = id;
        this.schoolName = schoolName;
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
     * Gets the schoolName
     * @return
     */
    public String getSchoolName() {
        return schoolName;
    }

    /**
     * Sets the schoolName
     * @param schoolName
     */
    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

}
