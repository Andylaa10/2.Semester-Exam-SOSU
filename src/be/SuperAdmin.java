package be;

public class SuperAdmin {

    private int id;
    private String username;
    private String password;


    /**
     * Overloaded constructor with id, username and password
     * @param id
     * @param username
     * @param password
     */
    public SuperAdmin(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    /**
     * Gets the id
     * @return
     */
    public int getId() {
        return id;
    }


    /**
     * Gets the username
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the password
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
