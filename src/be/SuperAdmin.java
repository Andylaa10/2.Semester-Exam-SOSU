package be;

public class SuperAdmin {

    private int id;
    private String username;
    private String password;

    /**
     * Constructor with id, username and password
     */
    public SuperAdmin(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    /**
     * Gets the id
     */
    public int getId() {
        return id;
    }


    /**
     * Gets the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "SuperAdmin{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
