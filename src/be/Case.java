package be;

public class Case {

    private int id;
    private String name;
    private String date;
    private String info;
    private int schoolId;

    /**
     * Overloaded constructor
     */
    public Case(int id, String name, String date, String info) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.info = info;
    }

    /**
     * Overloaded constructor
     */
    public Case(int id, String name, String info) {
        this.id = id;
        this.name = name;
        this.info = info;
    }

    public Case(int id, String name, String date, String info, int schoolId) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.info = info;
        this.schoolId = schoolId;
    }

    public Case(int id, String name, String info, int schoolId) {
        this.id = id;
        this.name = name;
        this.info = info;
        this.schoolId = schoolId;
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
     * Set the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the date
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets the date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Gets the info
     */
    public String getInfo() {
        return info;
    }

    /**
     * Sets the info
     */
    public void setInfo(String info) {
        this.info = info;
    }

    public int getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(int schoolId) {
        this.schoolId = schoolId;
    }

    @Override
    public String toString() {
        return "Case{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", info='" + info + '\'' +
                '}';
    }
}
