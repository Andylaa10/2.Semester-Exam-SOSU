package gui.controller;

import be.User;
import gui.controller.Interface.IController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;
import java.sql.SQLException;

public class TeacherViewController implements IController {

    @FXML
    private Label lblWelcome;
    @Override
    public void setUser(User user) throws SQLException, IOException {
        lblWelcome.setText("Welcome " + user.getName());
    }
}
