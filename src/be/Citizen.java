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
     * @return
     */
    public int getId() {
        return id;
    }


    /**
     * Gets the firstName
     * @return
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the firstName
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the lastName
     * @return
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the lastName
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the ssn
     * @return
     */
    public String getSSN() {
        return SSN;
    }

    /**
     * Sets the SSN
     * @param SSN
     */
    public void setSSN(String SSN) {
        this.SSN = SSN;
    }

    /**
     * Gets the address
     * @return
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets the sex
     * @return
     */
    public String getSex() {
        return sex;
    }

    /**
     * Sets the sex
     * @param sex
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * Gets the generalInformation
     * @return
     */
    public int getGeneralInformationID() {
        return generalInformationID;
    }

    /**
     * Sets the generalInformation
     * @param generalInformationID
     */
    public void setGeneralInformationID(int generalInformationID) {
        this.generalInformationID = generalInformationID;
    }

    /**
     * Gets the functionalAbilitiesID
     * @return
     */
    public int getFunctionalAbilitiesID() {
        return functionalAbilitiesID;
    }

    /**
     * Sets the functionalAbilitiesID
     * @param functionalAbilitiesID
     */
    public void setFunctionalAbilitiesID(int functionalAbilitiesID) {
        this.functionalAbilitiesID = functionalAbilitiesID;
    }

    /**
     * Gets the loginID
     * @return
     */
    public int getLoginID() {
        return loginID;
    }

    /**
     * Sets the loginID
     * @param loginID
     */
    public void setLoginID(int loginID) {
        this.loginID = loginID;
    }

    /**
     * Gets the schoolID
     * @return
     */
    public int getSchoolID() {
        return schoolID;
    }

    /**
     * Sets the schoolID
     * @param schoolID
     */
    public void setSchoolID(int schoolID) {
        this.schoolID = schoolID;
    }

    @Override
    public String toString() {
        return getFirstName() + " " + getLastName() + " - " + getSSN();
    }
}
