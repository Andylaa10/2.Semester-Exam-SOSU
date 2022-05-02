package gui.controller;

import be.School;
import be.User;
import gui.Facade.DataModelFacade;
import gui.controller.Interface.IController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SuperAdminViewController extends Application implements Initializable, IController {

    @FXML
    private TableView<School> tvSchools;
    @FXML
    private TableColumn<School, String> tcSchools;

    @FXML
    private TableView<User> tvAdmins;
    @FXML
    private TableColumn<User, String> tcAdminFirstName;
    @FXML
    private TableColumn<User, String> tcAdminLastName;
    @FXML
    private TableColumn<User, String> tcAdminUsername;

    @FXML
    private TableView<School> tvAssignedSchool;
    @FXML
    private TableColumn<School, String> tcAssignedSchoolName;

    @FXML
    private TableView<User> tvAssignAdmin;
    @FXML
    private TableColumn<User, String> tcAssignAdminFirstName;
    @FXML
    private TableColumn<User, String> tcAssignAdminLastName;
    @FXML
    private TableColumn<User, String> tcAssignAdminUsername;

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

    private ObservableList<User> allAdmins = FXCollections.observableArrayList();
    private ObservableList<User> allAssignedAdmins = FXCollections.observableArrayList();
    private ObservableList<School> allSchools = FXCollections.observableArrayList();
    private ObservableList<School> allAssignedSchools = FXCollections.observableArrayList();

    DataModelFacade dataModelFacade;

    public SuperAdminViewController() throws IOException {
        this.dataModelFacade = new DataModelFacade();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setAnchorPanesVisibility();
        try {
            initializeTables();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initializeTables() throws Exception {
        //Initialize the school table
        tcSchools.setCellValueFactory(new PropertyValueFactory<>("schoolName"));
        try {
            allSchools = FXCollections.observableList(dataModelFacade.getSchools());
            tableViewLoadSchools(allSchools);
        } catch (Exception e) {
            throw new Exception();
        }

        //Initialize the admins table
        tcAdminFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        tcAdminLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tcAdminUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        try {
            allAdmins = FXCollections.observableList(dataModelFacade.getAdmins());
            tableViewLoadAdmins(allAdmins);
        } catch (Exception e) {
            throw new Exception();
        }

        //Initialize the admins table on the assign pane
        tcAssignAdminFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        tcAssignAdminLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tcAssignAdminUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        try {
            allAssignedAdmins = FXCollections.observableList(dataModelFacade.getAdmins());
            tableViewLoadAssignedAdmins(allAssignedAdmins);
        } catch (Exception e) {
            throw new Exception();
        }

        //Initialize the school table on the assign pane
        tcAssignedSchoolName.setCellValueFactory(new PropertyValueFactory<>("schoolName"));
        try {
            allAssignedSchools = FXCollections.observableList(dataModelFacade.getSchools());
            tableViewLoadAssignedSchools(allAssignedSchools);
        } catch (Exception e) {
            throw new Exception();
        }
    }

    private void tableViewLoadSchools(ObservableList<School> allSchools) {
        tvSchools.setItems(getSchoolData());
    }

    private ObservableList<School> getSchoolData() {
        return allSchools;
    }

    private void tableViewLoadAdmins(ObservableList<User> allAdmins) {
        tvAdmins.setItems(getAdminData());
    }

    private ObservableList<User> getAdminData() {
        return allAdmins;
    }


    private void tableViewLoadAssignedAdmins(ObservableList<User> allAssignedAdmins) {
        tvAssignAdmin.setItems(getAssignedAdminData());
    }

    private ObservableList<User> getAssignedAdminData() {
        return allAssignedAdmins;
    }

    private void tableViewLoadAssignedSchools(ObservableList<School> allAssignedSchools) {
        tvAssignedSchool.setItems(getAssignedSchoolData());
    }

    private ObservableList<School> getAssignedSchoolData() {
        return allAssignedSchools;
    }


    private void setAnchorPanesVisibility(){
        labelInfoNewLine.setText("");
        anchorPaneSuperAdmin.setVisible(true);
        anchorPaneCreateSchool.setVisible(false);
        anchorPaneCreateAdmin.setVisible(false);
        anchorPaneConfigureSchool.setVisible(false);
    }




    @FXML
    private void btnClickHome() {
        labelTitle.setText("Super Admin");
        labelInfo.setText("Logget ind som Super Admin");
        labelInfoNewLine.setText("");
        anchorPaneSuperAdmin.setVisible(true);
        anchorPaneCreateSchool.setVisible(false);
        anchorPaneCreateAdmin.setVisible(false);
        anchorPaneConfigureSchool.setVisible(false);
    }

    @FXML
    private void btnClickCreateschool() {
        labelTitle.setText("Opret Skole");
        labelInfo.setText("Overblik over alle oprettede skoler, hvor du kan oprette nye skoler, redigere eller slette");
        labelInfoNewLine.setText("");
        anchorPaneCreateSchool.setVisible(true);
        anchorPaneSuperAdmin.setVisible(false);
        anchorPaneCreateAdmin.setVisible(false);
        anchorPaneConfigureSchool.setVisible(false);
    }

    @FXML
    private void btnClickCreateAdmin() {
        labelTitle.setText("Opret Admin");
        labelInfo.setText("Overblik over alle oprettede admins, hvor du kan oprette nye admins, redigere eller slette");
        labelInfoNewLine.setText("");
        anchorPaneCreateAdmin.setVisible(true);
        anchorPaneSuperAdmin.setVisible(false);
        anchorPaneCreateSchool.setVisible(false);
        anchorPaneConfigureSchool.setVisible(false);
    }

    @FXML
    private void btnClickAssignSchools() {
        labelTitle.setText("Tildel Skole");
        labelInfo.setText("Overblik over alle oprettede skoler, hvor du kan tildele skoler til admins");
        labelInfoNewLine.setText("");
        anchorPaneConfigureSchool.setVisible(true);
        anchorPaneSuperAdmin.setVisible(false);
        anchorPaneCreateSchool.setVisible(false);
        anchorPaneCreateAdmin.setVisible(false);
    }

    @FXML
    private void btnClickLogout() throws IOException {
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
