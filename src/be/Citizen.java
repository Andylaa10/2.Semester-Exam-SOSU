package be;

public class Citizen {

    private int id;
    private String firstName;
    private String lastName;
    private String SSN;
    private String address;
    private String sex;
    private int generalInformationID;
    private int functionalAbilitiesID;
    private int loginID;
    private int schoolID;


    public Citizen(int id, String firstName, String lastName, String SSN, String address, String sex) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.SSN = SSN;
        this.address = address;
        this.sex = sex;
    }


    /**
     * Gets the id
     */
    public int getId() {
        return id;
    }


    /**
     * Gets the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the ssn
     */
    public String getSSN() {
        return SSN;
    }

    /**
     * Sets the SSN
     */
    public void setSSN(String SSN) {
        this.SSN = SSN;
    }

    /**
     * Gets the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets the sex
     */
    public String getSex() {
        return sex;
    }

    /**
     * Sets the sex
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * Gets the generalInformation
     */
    public int getGeneralInformationID() {
        return generalInformationID;
    }

    /**
     * Sets the generalInformation
     */
    public void setGeneralInformationID(int generalInformationID) {
        this.generalInformationID = generalInformationID;
    }

    /**
     * Gets the functionalAbilitiesID
     */
    public int getFunctionalAbilitiesID() {
        return functionalAbilitiesID;
    }

    /**
     * Sets the functionalAbilitiesID
     */
    public void setFunctionalAbilitiesID(int functionalAbilitiesID) {
        this.functionalAbilitiesID = functionalAbilitiesID;
    }

    /**
     * Gets the loginID
     */
    public int getLoginID() {
        return loginID;
    }

    /**
     * Sets the loginID
     */
    public void setLoginID(int loginID) {
        this.loginID = loginID;
    }

    /**
     * Gets the schoolID
     */
    public int getSchoolID() {
        return schoolID;
    }

    /**
     * Sets the schoolID
     */
    public void setSchoolID(int schoolID) {
        this.schoolID = schoolID;
    }

    @Override
    public String toString() {
        return getFirstName() + " " + getLastName() + " - " + getSSN();
    }
}
