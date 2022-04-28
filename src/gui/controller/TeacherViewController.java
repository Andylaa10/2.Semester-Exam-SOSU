package gui.controller;

import be.User;
import gui.controller.Interface.IController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class TeacherViewController implements IController, Initializable {

    @FXML
    private Label lblWelcome;
    @Override
    public void setUser(User user) throws SQLException, IOException {
        lblWelcome.setText("Welcome " + user.getName());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
