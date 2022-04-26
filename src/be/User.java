package be;

import be.Interface.ILogin;
import be.enums.UserType;

public class User implements ILogin {
    private int id;
    private String name;
    private String username;
    private String password;
    private UserType type;


    public User(int id, String name, String username, String password, UserType type) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.type = type;
    }

    public User(int id, String username, String password, UserType type) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.type = type;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    private String getName(){
        return name;
    }

    private void setName(String name){
        this.name = name;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public UserType getUsertype() {
        return type;
    }

    @Override
    public void setUserType(UserType type) {
        this.type = type;
    }
}
