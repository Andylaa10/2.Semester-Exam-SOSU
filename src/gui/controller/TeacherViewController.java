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

public class TeacherViewController extends Application implements Initializable, IController{

    @FXML
    private AnchorPane topPane;
    @FXML
    private Button btnStudent;
    @FXML
    private Button btnCitizens;
    @FXML
    private Button btnCreateCitizen;
    @FXML
    private Button btnCase;
    @FXML
    private Button btnHome;
    @FXML
    private Button btnLogOut;
    @FXML
    private Label labelTitle;
    @FXML
    private Label labelInfo;
    @FXML
    private Label labelInfoNewLine;
    @FXML
    private AnchorPane anchorPaneStudent;
    @FXML
    private AnchorPane anchorPaneCitizen;
    @FXML
    private AnchorPane anchorPaneCreateCitizen;
    @FXML
    private AnchorPane anchorPaneCase;
    @FXML
    private AnchorPane anchorPaneTeacher;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setAnchorPanesVisibility();
    }

    private void setAnchorPanesVisibility(){
        labelInfoNewLine.setText("");
        anchorPaneTeacher.setVisible(true);
        anchorPaneCreateCitizen.setVisible(false);
        anchorPaneStudent.setVisible(false);
        anchorPaneCitizen.setVisible(false);
        anchorPaneCase.setVisible(false);
    }

    public void btnClickStudent(ActionEvent actionEvent) {
        labelTitle.setText("Elever");
        labelInfo.setText("Overblik over alle oprettede elever");
        labelInfoNewLine.setText("");
        anchorPaneCreateCitizen.setVisible(false);
        anchorPaneTeacher.setVisible(false);
        anchorPaneCitizen.setVisible(false);
        anchorPaneCase.setVisible(false);
        anchorPaneStudent.setVisible(true);
    }

    public void btnClickCase(ActionEvent actionEvent) {
        labelTitle.setText("Sager");
        labelInfo.setText("Overblik over alle oprettede sager. Opret nye sager, eller og kopier sager");
        labelInfoNewLine.setText("");
        anchorPaneCreateCitizen.setVisible(false);
        anchorPaneTeacher.setVisible(false);
        anchorPaneStudent.setVisible(false);
        anchorPaneCase.setVisible(true);
        anchorPaneCitizen.setVisible(false);
    }

    public void btnClickSeeCitizens(ActionEvent actionEvent){
        labelTitle.setText("Borgere");
        labelInfo.setText("Overblik over alle oprettede borgere. Tildel en sag til en borger, se yderligere informationer,");
        labelInfoNewLine.setText( "rediger eller slet borger.");
        anchorPaneCreateCitizen.setVisible(false);
        anchorPaneTeacher.setVisible(false);
        anchorPaneStudent.setVisible(false);
        anchorPaneCase.setVisible(false);
        anchorPaneCitizen.setVisible(true);
    }

    public void btnClickCitizen(ActionEvent actionEvent) {
        labelTitle.setText("Opret Borger");
        labelInfo.setText("Oprettelses vindue til borger");
        labelInfoNewLine.setText("");
        anchorPaneTeacher.setVisible(false);
        anchorPaneStudent.setVisible(false);
        anchorPaneCase.setVisible(false);
        anchorPaneCreateCitizen.setVisible(true);
        anchorPaneCitizen.setVisible(false);
    }

    public void btnClickHome(ActionEvent actionEvent){
        labelTitle.setText("Lærer");
        labelInfo.setText("Logget ind som lærer");
        labelInfoNewLine.setText("");
        anchorPaneTeacher.setVisible(true);
        anchorPaneStudent.setVisible(false);
        anchorPaneCase.setVisible(false);
        anchorPaneCitizen.setVisible(false);
        anchorPaneCreateCitizen.setVisible(false);
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
        labelInfo.setText("Du er nu logget ind som lærer: " + user.getFirstName() + user.getLastName());
        labelInfoNewLine.setText("");
    }



    public static void main(String[] args) {launch(args);}

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/gui/view/TeacherView.fxml"));
        primaryStage.setTitle("SOSU Simulation");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
