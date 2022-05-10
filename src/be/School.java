package be;

public class School {

    private int id;
    private String schoolName;


    /**
     * Constructor
     */
    public School(int id, String schoolName) {
        this.id = id;
        this.schoolName = schoolName;
    }

    /**
     * Gets the id
     */
    public int getId() {
        return id;
    }


    /**
     * Gets the schoolName
     */
    public String getSchoolName() {
        return schoolName;
    }

}
