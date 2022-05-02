package gui.controller;

import be.Case;
import be.Citizen;
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
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AdminViewController extends Application implements Initializable, IController{

    @FXML
    public TableView<User> tvTeachers;
    @FXML
    public TableColumn<User, String> tcTeacherFirstName;
    @FXML
    public TableColumn<User, String> tcTeacherLastName;
    @FXML
    public TableColumn<User, String> tcTeacherUserName;

    @FXML
    public TableView<Case> tvCurrentCases;
    @FXML
    public TableColumn<Case, String> tcCurrentCaseName;
    @FXML
    public TableColumn<Case, String> tcCurrentCaseDate;

    @FXML
    public TableView<Citizen> tvCurrentCitizens;
    @FXML
    public TableColumn<Citizen, String> tcCurrentCitizenFirstName;
    @FXML
    public TableColumn<Citizen, String> tcCurrentCitizenLastName;
    @FXML
    public TableColumn<Citizen, String> tcCurrentCitizenSSN;
    @FXML
    public Button btnAssignCaseToCitizen;

    @FXML
    public TableView<User> tvStudents;
    @FXML
    public TableColumn<User, String> tcStudentFirstName;
    @FXML
    public TableColumn<User, String> tcStudentLastName;
    @FXML
    public TableColumn<User, String> tcStudentUserName;

    @FXML
    public TextField txtFieldCaseName;
    @FXML
    public TextField txtFieldCaseDate;
    @FXML
    public TextArea TxtAreaCaseInfo;
    @FXML
    public Button btnSaveCase;
    @FXML
    public TableView<Case> tvCases;
    @FXML
    public TableColumn<Case, String> tcCaseName;
    @FXML
    public TableColumn<Case, String> tcCaseDate;
    @FXML
    public Button btnEditCase;
    @FXML
    public Button btnDeleteCase;
    @FXML
    public Button btnCopyCase;

    @FXML
    private AnchorPane topPane;
    @FXML
    private Button btnHome;
    @FXML
    private Button btnCreateTeacher;
    @FXML
    private Button btnStudent;
    @FXML
    private Button btnCase;
    @FXML
    private Button btnCitizens;
    @FXML
    private Button btnCreateCitizen;
    @FXML
    private Button btnLogOut;
    @FXML
    private Label labelTitle;
    @FXML
    private Label labelInfo;
    @FXML
    private Label labelInfoNewLine;
    @FXML
    private AnchorPane anchorPaneCreateTeacher;
    @FXML
    private AnchorPane anchorPaneAdmin;
    @FXML
    private AnchorPane anchorPaneCitizen;
    @FXML
    private AnchorPane anchorPaneCreateCitizen;
    @FXML
    private AnchorPane anchorPaneStudent;
    @FXML
    private AnchorPane anchorPaneCase;

    private ObservableList<User> allStudents = FXCollections.observableArrayList();
    private ObservableList<Citizen> allCitizens = FXCollections.observableArrayList();
    private ObservableList<Case> allCases = FXCollections.observableArrayList();
    private ObservableList<Case> allCurrentCases = FXCollections.observableArrayList();

    private DataModelFacade dataModelFacade;

    public AdminViewController() throws IOException {
        this.dataModelFacade = new DataModelFacade();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

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
        //Initialize the teachers table

        //Initialize the students table
        tcStudentFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        tcStudentLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tcStudentUserName.setCellValueFactory(new PropertyValueFactory<>("username"));
        try {
            allStudents = FXCollections.observableList(dataModelFacade.getStudents());
            tableViewLoadStudents(allStudents);
        } catch (Exception e) {
            throw new Exception();
        }

        //Initialize the cases table
        tcCaseName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tcCaseDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        try {
            allCases = FXCollections.observableList(dataModelFacade.getCases());
            tableViewLoadCases(allCases);
        } catch (Exception e) {
            throw new Exception();
        }

        //Initialize the citizens table
        tcCurrentCitizenFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        tcCurrentCitizenLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tcCurrentCitizenSSN.setCellValueFactory(new PropertyValueFactory<>("ssn"));
        try {
            allCitizens = FXCollections.observableList(dataModelFacade.getCitizens());
            tableViewLoadCitizens(allCitizens);
        } catch (Exception e) {
            throw new Exception();
        }

        //Initialize the current cases table at citizen window
        tcCurrentCaseName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tcCurrentCaseDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        try {
            allCurrentCases = FXCollections.observableList(dataModelFacade.getCases());
            tableViewLoadCurrentCases(allCurrentCases);
        } catch (Exception e) {
            throw new Exception();
        }
    }

    private void tableViewLoadStudents(ObservableList<User> allStudents) {
        tvStudents.setItems(getStudentData());
    }

    private ObservableList<User> getStudentData() {
        return allStudents;
    }

    private void tableViewLoadCases(ObservableList<Case> allCases) {
        tvCases.setItems(getCaseData());
    }

    private ObservableList<Case> getCaseData() {
        return allCases;
    }

    private void tableViewLoadCitizens(ObservableList<Citizen> allCitizens) {
        tvCurrentCitizens.setItems(getCitizenData());
    }

    private ObservableList<Citizen> getCitizenData() {
        return allCitizens;
    }

    private void tableViewLoadCurrentCases(ObservableList<Case> allCurrentCases) {
        tvCurrentCases.setItems(getCurrentCasesData());
    }

    private ObservableList<Case> getCurrentCasesData() {
        return allCurrentCases;
    }


    @FXML
    private void onActionSaveCase(ActionEvent actionEvent) {
    }

    @FXML
    private void OnActionEditCase(ActionEvent actionEvent) {
    }

    @FXML
    private void onActionDeleteCase(ActionEvent actionEvent) {
    }

    @FXML
    private void OnActionCopyCase(ActionEvent actionEvent) {
    }

    private void setAnchorPanesVisibility(){
        labelInfoNewLine.setText("");
        anchorPaneAdmin.setVisible(true);
        anchorPaneCreateCitizen.setVisible(false);
        anchorPaneStudent.setVisible(false);
        anchorPaneCitizen.setVisible(false);
        anchorPaneCase.setVisible(false);
        anchorPaneCreateTeacher.setVisible(false);
    }

    public void btnClickCreateTeacher(ActionEvent actionEvent){
        labelTitle.setText("Lærere");
        labelInfo.setText("Overblik over alle oprettede lærere, hvor du kan oprette nye lærere, redigere eller slette");
        labelInfoNewLine.setText("");
        anchorPaneCreateTeacher.setVisible(true);
        anchorPaneStudent.setVisible(false);
        anchorPaneCreateCitizen.setVisible(false);
        anchorPaneAdmin.setVisible(false);
        anchorPaneCitizen.setVisible(false);
        anchorPaneCase.setVisible(false);

    }

    public void btnClickStudent(ActionEvent actionEvent) {
        labelTitle.setText("Elever");
        labelInfo.setText("Overblik over alle oprettede elever");
        labelInfoNewLine.setText("");
        anchorPaneStudent.setVisible(true);
        anchorPaneCreateCitizen.setVisible(false);
        anchorPaneAdmin.setVisible(false);
        anchorPaneCitizen.setVisible(false);
        anchorPaneCase.setVisible(false);
        anchorPaneCreateTeacher.setVisible(false);
    }

    public void btnClickCase(ActionEvent actionEvent) {
        labelTitle.setText("Sager");
        labelInfo.setText("Overblik over alle oprettede sager. Opret nye sager, eller og kopier sager");
        labelInfoNewLine.setText("");
        anchorPaneCase.setVisible(true);
        anchorPaneCreateCitizen.setVisible(false);
        anchorPaneAdmin.setVisible(false);
        anchorPaneStudent.setVisible(false);
        anchorPaneCitizen.setVisible(false);
        anchorPaneCreateTeacher.setVisible(false);
    }

    public void btnClickSeeCitizens(ActionEvent actionEvent){
        labelTitle.setText("Borgere");
        labelInfo.setText("Overblik over alle oprettede borgere. Tildel en sag til en borger, se yderligere informationer,");
        labelInfoNewLine.setText( "rediger eller slet borger.");
        anchorPaneCitizen.setVisible(true);
        anchorPaneCreateCitizen.setVisible(false);
        anchorPaneAdmin.setVisible(false);
        anchorPaneStudent.setVisible(false);
        anchorPaneCase.setVisible(false);
        anchorPaneCreateTeacher.setVisible(false);
    }

    public void btnClickCitizen(ActionEvent actionEvent) {
        labelTitle.setText("Opret Borger");
        labelInfo.setText("Oprettelses vindue til borger");
        labelInfoNewLine.setText("");
        anchorPaneCreateCitizen.setVisible(true);
        anchorPaneAdmin.setVisible(false);
        anchorPaneStudent.setVisible(false);
        anchorPaneCase.setVisible(false);
        anchorPaneCitizen.setVisible(false);
        anchorPaneCreateTeacher.setVisible(false);
    }

    public void btnClickHome(ActionEvent actionEvent){
        labelTitle.setText("Admin");
        labelInfo.setText("Logget ind som Admin");
        labelInfoNewLine.setText("");
        anchorPaneAdmin.setVisible(true);
        anchorPaneStudent.setVisible(false);
        anchorPaneCase.setVisible(false);
        anchorPaneCitizen.setVisible(false);
        anchorPaneCreateCitizen.setVisible(false);
        anchorPaneCreateTeacher.setVisible(false);
    }

    public void btnClickLogout(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/LoginView.fxml"));
        Scene scene = new Scene(loader.load());
        Stage switcher = (Stage) btnLogOut.getScene().getWindow();
        switcher.setScene(scene);
        switcher.setTitle("Log In");
        switcher.show();
    }

    @Override
    public void setUser(User user) throws SQLException, IOException {
        labelTitle.setText("Admin");
        labelInfo.setText("Du er nu logget ind som Admin: " + user.getFirstName() + user.getLastName());
        labelInfoNewLine.setText("");
    }


}
