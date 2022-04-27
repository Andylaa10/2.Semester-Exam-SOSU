package gui.controller;

import be.User;
import gui.controller.Interface.IController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class NewTeacherViewController implements Initializable, IController {

    @FXML
    private AnchorPane topPane;
    @FXML
    private Button btnStudent;
    @FXML
    private Button btnCitizen;
    @FXML
    private Button btnCase;
    @FXML
    private Button btnLogOut;
    @FXML
    private Label labelTitle;
    @FXML
    private Label labelInfo;
    @FXML
    private AnchorPane anchorPaneStudent;
    @FXML
    private AnchorPane anchorPaneCitizen;
    @FXML
    private AnchorPane anchorPaneCase;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setAnchorPanesVisibility();
    }

    private void setAnchorPanesVisibility(){
        anchorPaneStudent.setVisible(false);
        anchorPaneCitizen.setVisible(false);
        anchorPaneCase.setVisible(false);
    }

    public void btnClickStudent(ActionEvent actionEvent) {
        labelTitle.setText("Elever");
        labelInfo.setText("Overblik over alle oprettede elever");
        anchorPaneCitizen.setVisible(false);
        anchorPaneCase.setVisible(false);
        anchorPaneStudent.setVisible(true);
    }

    public void btnClickCitizen(ActionEvent actionEvent) {
        labelTitle.setText("Borgere");
        labelInfo.setText("Overblik over alle oprettede borgere");
        anchorPaneStudent.setVisible(false);
        anchorPaneCase.setVisible(false);
        anchorPaneCitizen.setVisible(true);
    }

    public void btnClickCase(ActionEvent actionEvent) {
        labelTitle.setText("Sager");
        labelInfo.setText("Overblik over alle oprettede sager");
        anchorPaneStudent.setVisible(false);
        anchorPaneCitizen.setVisible(false);
        anchorPaneCase.setVisible(true);
    }

    public void btnClickLogout(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/LoginMenu.fxml"));
        Scene scene = new Scene(loader.load());
        Stage switcher = (Stage) btnLogOut.getScene().getWindow();
        switcher.setScene(scene);
        switcher.setTitle("Student");
        switcher.show();
    }

    @Override
    public void setUser(User user) throws SQLException, IOException {
        labelTitle.setText("Lærer");
        labelInfo.setText("Du er nu logget ind som lærer: " + user.getName());
    }
}
