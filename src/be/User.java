package be;

import be.Interface.ILogin;
import be.enums.UserType;


public class User implements ILogin {

    private int id;
    private String name;
    private String username;
    private String password;
    private UserType userType;

    /**
     * Constructor
     */
    public User() {
    }

    /**
     * Constructor with id, name, username, password and userType
     * @param id
     * @param name
     * @param username
     * @param password
     * @param userType
     */
    public User(int id, String name, String username, String password, UserType userType) {
        this.id = id;
        this.name = name;
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
     * Gets the name
     * @return
     */
    public String getName(){
        return name;
    }

    /**
     * Sets the name
     * @param name
     */
    public void setName(String name){
        this.name = name;
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
}
