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

    /**
     * Constructor
     */
    public User() {
    }


    /**
     * Overloaded constructor with id, firstName, lastName, username, password and usertype
     * @param id
     * @param firstName
     * @param lastName
     * @param username
     * @param password
     * @param userType
     */
    public User(int id, String firstName, String lastName, String username, String password, UserType userType) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.userType = userType;
    }

    /**
     * Constructor with id, username, and
     * @param id
     * @param username
     * @param password
     * @param userType
     */
    public User(int id, String username, String password, UserType userType) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.userType = userType;
    }

    /**
     * Gets the id
     * @return
     */
    @Override
    public int getId() {
        return id;
    }

    /**
     * Sets the id
     * @param id
     */
    @Override
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the first name
     * @return
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the last name
     * @return
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the username
     * @return
     */
    @Override
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username
     * @param username
     */
    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the password
     * @return
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password
     * @param password
     */
    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the userType
     * @return
     */
    @Override
    public UserType getUsertype() {
        return userType;
    }

    /**
     * Sets the userType
     * @param userType
     */
    @Override
    public void setUserType(UserType userType) {
        this.userType = userType;
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
