package gui.controller;

import be.User;
import gui.controller.Interface.IController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class StudentViewController implements IController, Initializable {

    @FXML
    private Label lblWelcome;
    @FXML
    private AnchorPane loginPane;

    @Override
    public void setUser(User user) throws SQLException, IOException {
        lblWelcome.setText("Welcome " + user.getName());
    }

    public void clickMe() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui/view/TeacherView.fxml"));
        loginPane.getChildren().setAll(pane);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
