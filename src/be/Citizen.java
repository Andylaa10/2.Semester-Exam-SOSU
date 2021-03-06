package be;

public class Citizen {

    private int id;
    private String firstName;
    private String lastName;
    private String SSN;
    private String sex;
    private int schoolID;


    public Citizen(int id, String firstName, String lastName, String SSN, String sex) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.SSN = SSN;
        this.sex = sex;
    }

    public Citizen(int id, String firstName, String lastName, String SSN, String sex, int schoolID) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.SSN = SSN;
        this.sex = sex;
        this.schoolID = schoolID;
    }

    public Citizen(String firstName, String lastName, String ssn) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.SSN = ssn;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSSN() {
        return SSN;
    }

    public String getSex() {
        return sex;
    }

    public int getSchoolID() {
        return schoolID;
    }

    public void setSchoolID(int schoolID) {
        this.schoolID = schoolID;
    }

    @Override
    public String toString() {
        return getFirstName() + " " + getLastName() + " - " + getSSN();
    }
}
