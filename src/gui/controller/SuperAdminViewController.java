package gui.controller;

import be.School;
import be.User;
import be.enums.UserType;
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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class SuperAdminViewController implements Initializable, IController {



    @FXML
    private Button btnCreateSchoolOnAssignPane;
    @FXML
    private Button btnCreateAdminOnAssignPage;

    @FXML
    private Button btnEditSchool;
    @FXML
    private Button btnDeleteSchool;

    @FXML
    private TextField txtFieldSchoolID;
    @FXML
    private TextField txtFieldSchoolName;

    @FXML
    private TextField txtFieldAdminID;
    @FXML
    private TextField txtFieldAdminFirstName;
    @FXML
    private TextField txtFieldAdminLastName;
    @FXML
    private TextField txtFieldAdminUsername;
    @FXML
    private TextField txtFieldAdminPassword;

    @FXML
    private Button btnEditAdmin;
    @FXML
    private Button btnDeleteAdmin;
    @FXML
    private Button btnAssignAdminToSchool;

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

    private School selectedSchool;
    private User selectedAdmin;


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
        selectedSchool();
        selectedAdmin();
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

    @FXML
    private void onActionCreateSchool() throws SQLException {
        if (!txtFieldSchoolName.getText().isEmpty()){
            String schoolName = txtFieldSchoolName.getText();

            dataModelFacade.createSchool(schoolName);
            reloadSchoolTable();
        } else{
            System.out.println("Something is wrong");
            //TODO add errorHandler
        }
    }

    @FXML
    private void onActionEditSchool() throws Exception {
        if (this.selectedSchool != null){
            if (!txtFieldSchoolName.getText().isEmpty()) {
                int id = Integer.parseInt(txtFieldSchoolID.getText());;
                String schoolName = txtFieldSchoolName.getText();

                School school = new School(id, schoolName);
                dataModelFacade.editSchool(school);
                reloadSchoolTable();
                clearSchoolTxtField();
                tvSchools.getSelectionModel().clearSelection();
            }else{
                System.out.println("Something is wrong");
                //TODO add errorhandler
            }
        }
    }

    @FXML
    private void onActionDeleteSchool() throws SQLException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("WARNING MESSAGE");
        alert.setHeaderText("Warning before you delete a school");
        alert.setContentText("Joe");
        if (selectedSchool != null) {
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                selectedSchool();
                dataModelFacade.deleteSchool(selectedSchool.getId());
                reloadSchoolTable();
            }
        } else {
            return;
        }
    }

    @FXML
    private void onActionCreateAdmin() throws SQLException {
        if (!txtFieldAdminFirstName.getText().isEmpty() && !txtFieldAdminLastName.getText().isEmpty() && !txtFieldAdminUsername.getText().isEmpty() && !txtFieldAdminPassword.getText().isEmpty()){
            String firstName = txtFieldAdminFirstName.getText();
            String lastName = txtFieldAdminLastName.getText();
            String userName = txtFieldAdminUsername.getText();
            String password = txtFieldAdminPassword.getText();

            dataModelFacade.createAdmin(firstName, lastName, userName, password, UserType.ADMINISTRATOR);
            clearAdminTxtField();
            tvAdmins.getSelectionModel().clearSelection();
            reloadAdminTable();
        } else{
            System.out.println("Couldn't create admin");
            //TODO Make proper errorhandler
        }
    }

    @FXML
    private void onActionEditAdmin() throws Exception {
        if (this.selectedAdmin != null){
            if (!txtFieldAdminFirstName.getText().isEmpty() && !txtFieldAdminLastName.getText().isEmpty() && !txtFieldAdminUsername.getText().isEmpty() && !txtFieldAdminPassword.getText().isEmpty()) {
                int id = Integer.parseInt(txtFieldAdminID.getText());;
                String firstName = txtFieldAdminFirstName.getText();
                String lastName = txtFieldAdminLastName.getText();
                String userName = txtFieldAdminUsername.getText();
                String password = txtFieldAdminPassword.getText();

                User admin = new User(id, firstName, lastName, userName, password, UserType.STUDENT);
                dataModelFacade.editAdmin(admin);
                reloadAdminTable();
                clearAdminTxtField();
                tvAdmins.getSelectionModel().clearSelection();
            }else{
                System.out.println("Something went wrong");
                //TODO ADD ERRORHANDLER
            }
        }
    }

    @FXML
    private void OnActionDeleteAdmin() throws SQLException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("WARNING MESSAGE");
        alert.setHeaderText("Warning before you delete admin");
        alert.setContentText("Joe");
        if (selectedAdmin != null) {
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                selectedSchool();
                dataModelFacade.deleteAdmin(selectedAdmin.getId(), UserType.ADMINISTRATOR);
                reloadAdminTable();
                clearAdminTxtField();
            }
        } else {
            return;
        }
    }

    @FXML
    private void onActionAssignAdminToSchool() {

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

    private void reloadSchoolTable() {
        try {
            int index = tvSchools.getSelectionModel().getFocusedIndex();
            this.tvSchools.setItems(FXCollections.observableList(dataModelFacade.getSchools()));
            tvSchools.getSelectionModel().select(index);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void reloadAdminTable() {
        try {
            int index = tvAdmins.getSelectionModel().getFocusedIndex();
            this.tvAdmins.setItems(FXCollections.observableList(dataModelFacade.getAdmins()));
            tvAdmins.getSelectionModel().select(index);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void clearSchoolTxtField(){
        txtFieldSchoolID.clear();
        txtFieldSchoolName.clear();
    }

    public void clearAdminTxtField() {
        txtFieldAdminID.clear();
        txtFieldAdminFirstName.clear();
        txtFieldAdminLastName.clear();
        txtFieldAdminUsername.clear();
        txtFieldAdminPassword.clear();
    }

    public void setSelectedSchool(School school) {
        txtFieldSchoolID.setText(String.valueOf(school.getId()));
        txtFieldSchoolName.setText(school.getSchoolName());
    }

    private void setSelectedAdmin(User admin) {
        txtFieldAdminID.setText(String.valueOf(admin.getId()));
        txtFieldAdminFirstName.setText(admin.getFirstName());
        txtFieldAdminLastName.setText(admin.getLastName());
        txtFieldAdminUsername.setText(admin.getUsername());
        txtFieldAdminPassword.setText(admin.getPassword());
    }

    private void selectedSchool() {
        this.tvSchools.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            if ((School) newValue != null) {
                this.selectedSchool = (School) newValue;
                setSelectedSchool(newValue);
            }
        }));
    }

    private void selectedAdmin() {
        this.tvAdmins.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            if ((User) newValue != null) {
                this.selectedAdmin = (User) newValue;
                setSelectedAdmin(newValue);
            }
        }));
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
    public void setUser(User user) throws SQLException, IOException {
        labelTitle.setText("Super Admin");
        labelInfo.setText("Du er nu logget ind som Super Admin: " + user.getFirstName() + user.getLastName());
        labelInfoNewLine.setText("");
    }


}
