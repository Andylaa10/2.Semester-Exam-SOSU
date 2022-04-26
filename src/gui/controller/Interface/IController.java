package gui.controller.Interface;

import be.User;

import java.io.IOException;
import java.sql.SQLException;

public interface IController {

    void setUser(User user) throws SQLException, IOException;
}
