package be.Interface;

import be.enums.UserType;

public interface ILogin {

    //Interface used for the database userLogin
    int getId();

    void setId(int id);

    String getUsername();

    void setUsername(String username);

    String getPassword();

    void setPassword(String password);

    UserType getUsertype();

    void setUserType(UserType type);


}
