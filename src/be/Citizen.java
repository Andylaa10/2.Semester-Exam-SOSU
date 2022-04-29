package be;

public class Citizen {

    private int id;
    private String firstName;
    private String lastName;
    private String ssn;
    private String address;
    private String sex;
    private int generalInformationID;
    private int functionalAbilitiesID;
    private int functionalLevelID;
    private int loginID;
    private int schoolID;


    public Citizen(int id, String firstName, String lastName, String ssn, String address, String sex, int generalInformationID) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.ssn = ssn;
        this.address = address;
        this.sex = sex;
        this.generalInformationID = generalInformationID;
    }

    public Citizen(int id, String firstName, String lastName, String ssn, String address, String sex, int generalInformationID, int functionalAbilitiesID, int functionalLevelID, int loginID, int schoolID) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.ssn = ssn;
        this.address = address;
        this.sex = sex;
        this.generalInformationID = generalInformationID;
        this.functionalAbilitiesID = functionalAbilitiesID;
        this.functionalLevelID = functionalLevelID;
        this.loginID = loginID;
        this.schoolID = schoolID;
    }

    public Citizen(int id, String firstName, String lastName, String ssn, String address, String sex) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.ssn = ssn;
        this.address = address;
        this.sex = sex;
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

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getGeneralInformationID() {
        return generalInformationID;
    }

    public void setGeneralInformationID(int generalInformationID) {
        this.generalInformationID = generalInformationID;
    }

    public int getFunctionalAbilitiesID() {
        return functionalAbilitiesID;
    }

    public void setFunctionalAbilitiesID(int functionalAbilitiesID) {
        this.functionalAbilitiesID = functionalAbilitiesID;
    }

    public int getFunctionalLevelID() {
        return functionalLevelID;
    }

    public void setFunctionalLevelID(int functionalLevelID) {
        this.functionalLevelID = functionalLevelID;
    }

    public int getLoginID() {
        return loginID;
    }

    public void setLoginID(int loginID) {
        this.loginID = loginID;
    }

    public int getSchoolID() {
        return schoolID;
    }

    public void setSchoolID(int schoolID) {
        this.schoolID = schoolID;
    }

    @Override
    public String toString() {
        return "Citizen{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", ssn='" + ssn + '\'' +
                ", address='" + address + '\'' +
                ", sex='" + sex + '\'' +
                ", generalInformationID=" + generalInformationID +
                ", functionalAbilitiesID=" + functionalAbilitiesID +
                ", functionalLevelID=" + functionalLevelID +
                ", loginID=" + loginID +
                ", schoolID=" + schoolID +
                '}';
    }
}
