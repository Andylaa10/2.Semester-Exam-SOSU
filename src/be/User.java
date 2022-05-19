package be;

import be.Interface.ILogin;
import be.enums.UserType;

public class User implements ILogin {

    private int id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private UserType userType;
    private int schoolId;


    public User(int id, String firstName, String lastName, String username, UserType userType) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.userType = userType;
    }

    /**
     * Overloaded constructor with id, firstName, lastName, username, password and usertype
     */
    public User(int id, String firstName, String lastName, String username, String password, UserType userType) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.userType = userType;
    }

    public User(int id, String firstName, String lastName, String username, String password, UserType userType, int schoolId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.userType = userType;
        this.schoolId = schoolId;
    }

    public User(int id, String firstName, String lastName, String username, UserType userType, int schoolId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.userType = userType;
        this.schoolId = schoolId;
    }

    public User(String hashedPassword) {
        this.password = hashedPassword;
    }

    /**
     * Gets the id
     */
    @Override
    public int getId() {
        return id;
    }

    /**
     * Sets the id
     */
    @Override
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the username
     */
    @Override
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username
     */
    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the password
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password
     */
    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the userType
     */
    @Override
    public UserType getUsertype() {
        return userType;
    }

    /**
     * Sets the userType
     */
    @Override
    public void setUserType(UserType userType) {
        this.userType = userType;
    }


    public int getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(int schoolId) {
        this.schoolId = schoolId;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userType=" + userType +
                '}';
    }
}
