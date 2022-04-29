package gui.controller;

import be.User;
import gui.controller.Interface.IController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SuperAdminViewController extends Application implements Initializable, IController {

    @FXML
    private AnchorPane topPane;
    @FXML
    private Button btnHome;
    @FXML
    private Button btnCreateSchool;
    @FXML
    private Button btnCreateAdmin;
    @FXML
    private Button btnConfigureSchools;
    @FXML
    private Button btnLogOut;
    @FXML
    private Label labelTitle;
    @FXML
    private Label labelInfo;
    @FXML
    private Label labelInfoNewLine;
    @FXML
    private AnchorPane anchorPaneSuperAdmin;
    @FXML
    private AnchorPane anchorPaneCreateSchool;
    @FXML
    private AnchorPane anchorPaneCreateAdmin;
    @FXML
    private AnchorPane anchorPaneConfigureSchool;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setAnchorPanesVisibility();
    }

    private void setAnchorPanesVisibility(){
        labelInfoNewLine.setText("");
        anchorPaneSuperAdmin.setVisible(true);
        anchorPaneCreateSchool.setVisible(false);
        anchorPaneCreateAdmin.setVisible(false);
        anchorPaneConfigureSchool.setVisible(false);
    }

    @FXML
    private void btnClickHome(ActionEvent actionEvent) {
        labelTitle.setText("Super Admin");
        labelInfo.setText("Logget ind som Super Admin");
        labelInfoNewLine.setText("");
        anchorPaneSuperAdmin.setVisible(true);
        anchorPaneCreateSchool.setVisible(false);
        anchorPaneCreateAdmin.setVisible(false);
        anchorPaneConfigureSchool.setVisible(false);
    }

    @FXML
    private void btnClickCreateschool(ActionEvent actionEvent) {
        labelTitle.setText("Opret Skole");
        labelInfo.setText("Overblik over alle oprettede skoler, hvor du kan oprette nye skoler, redigere eller slette");
        labelInfoNewLine.setText("");
        anchorPaneCreateSchool.setVisible(true);
        anchorPaneSuperAdmin.setVisible(false);
        anchorPaneCreateAdmin.setVisible(false);
        anchorPaneConfigureSchool.setVisible(false);
    }

    @FXML
    private void btnClickCreateAdmin(ActionEvent actionEvent) {
        labelTitle.setText("Opret Admin");
        labelInfo.setText("Overblik over alle oprettede admins, hvor du kan oprette nye admins, redigere eller slette");
        labelInfoNewLine.setText("");
        anchorPaneCreateAdmin.setVisible(true);
        anchorPaneSuperAdmin.setVisible(false);
        anchorPaneCreateSchool.setVisible(false);
        anchorPaneConfigureSchool.setVisible(false);
    }

    @FXML
    private void btnClickConfigureSchools(ActionEvent actionEvent) {
        labelTitle.setText("Opret Skole");
        labelInfo.setText("Overblik over alle oprettede skoler, hvor du kan oprette nye skoler, redigere eller slette");
        labelInfoNewLine.setText("");
        anchorPaneConfigureSchool.setVisible(true);
        anchorPaneSuperAdmin.setVisible(false);
        anchorPaneCreateSchool.setVisible(false);
        anchorPaneCreateAdmin.setVisible(false);
    }

    @FXML
    private void btnClickLogout(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/LoginView.fxml"));
        Scene scene = new Scene(loader.load());
        Stage switcher = (Stage) btnLogOut.getScene().getWindow();
        switcher.setScene(scene);
        switcher.setTitle("Log In");
        switcher.show();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/gui/view/SuperAdminView.fxml"));
        primaryStage.setTitle("SOSU Simulation");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void setUser(User user) throws SQLException, IOException {
        labelTitle.setText("Super Admin");
        labelInfo.setText("Du er nu logget ind som Super Admin: " + user.getFirstName() + user.getLastName());
        labelInfoNewLine.setText("");
    }
}
