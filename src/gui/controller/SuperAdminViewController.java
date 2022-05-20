package gui.controller;

import be.School;
import be.User;
import be.enums.UserType;
import bll.utilities.Encryptor;
import gui.Facade.DataModelFacade;
import gui.controller.Interface.IController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.util.Optional;
import java.util.ResourceBundle;

public class SuperAdminViewController implements Initializable, IController {

    @FXML
    private TableView<User> tvAssignedAdminsOnSchool;
    @FXML
    private TableColumn<User, String> tcAssignedAdminFirstName;
    @FXML
    private TableColumn<User, String> tcAssignedAdminLastName;
    @FXML
    private TableColumn<User, String> tcAssignedAdminUsername;

    @FXML
    private Button btnEditSchool;
    @FXML
    private Button btnEditSave;
    @FXML
    private Button btnEditCancel;
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
    private Button btnEditSaveAdmin;
    @FXML
    private Button btnEditAdminCancel;
    @FXML
    private Button btnDeleteAdmin;

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
    private TableColumn<School, Integer> tcAssignedSchoolID;
    @FXML
    private TableColumn<School, String> tcAssignedSchoolName;

    @FXML
    private TableView<User> tvAssignAdmin;
    @FXML
    private TableColumn<User, String> tcAssignAdminID;
    @FXML
    private TableColumn<User, String> tcAssignAdminFirstName;
    @FXML
    private TableColumn<User, String> tcAssignAdminLastName;
    @FXML
    private TableColumn<User, String> tcAssignAdminUsername;


    @FXML
    private Button btnCreateSchool;
    @FXML
    private Button btnCreateAdmin;
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
    @FXML
    private ComboBox<School> comboSchool;
    @FXML
    private TextField txtSchoolID;

    private ObservableList<User> allAdmins = FXCollections.observableArrayList();
    private ObservableList<User> allAssignAdmins = FXCollections.observableArrayList();
    private ObservableList<User> allAssignedAdmins = FXCollections.observableArrayList();
    private ObservableList<School> allSchools = FXCollections.observableArrayList();
    private ObservableList<School> allAssignedSchools = FXCollections.observableArrayList();

    private final DataModelFacade dataModelFacade;

    private School selectedSchool;
    private School selectedSchoolOnComboBox;
    private User selectedAdmin;

    private Encryptor encryptor;

    private School selectedSchoolToAssign;


    public SuperAdminViewController() throws IOException, SQLException {
        this.dataModelFacade = new DataModelFacade();
        this.encryptor = new Encryptor();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setAnchorPanesVisibility();
        try {
            initializeTables();
            initializeComboBox();
        } catch (Exception e) {
            e.printStackTrace();
        }
        selectedSchool();
        selectedAdmin();
        selectedSchoolToAssign();
        selectedSchoolOnComboBox();
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
        tcAssignAdminID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcAssignAdminFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        tcAssignAdminLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tcAssignAdminUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        try {
            allAssignAdmins = FXCollections.observableList(dataModelFacade.getAdmins());
            tableViewLoadAssignAdmins(allAssignAdmins);
        } catch (Exception e) {
            throw new Exception();
        }

        //Initialize the school table on the assign pane
        tcAssignedSchoolID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcAssignedSchoolName.setCellValueFactory(new PropertyValueFactory<>("schoolName"));
        try {
            allAssignedSchools = FXCollections.observableList(dataModelFacade.getSchools());
            tableViewLoadAssignedSchools(allAssignedSchools);
        } catch (Exception e) {
            throw new Exception();
        }
    }

    private void initializeComboBox() throws SQLException {
        ObservableList<School> allSchoolsOnCombo = FXCollections.observableArrayList(dataModelFacade.getSchools());
        comboSchool.setItems(allSchoolsOnCombo);
    }

    @FXML
    private void onActionCreateSchool() throws SQLException {
        if (!txtFieldSchoolName.getText().isEmpty()) {
            String schoolName = txtFieldSchoolName.getText();

            dataModelFacade.createSchool(schoolName);
            reloadSchoolTable();
            reloadAssignSchoolTable();
            clearSchoolTxtField();
        } else {
            ErrorHandlerController.createWarning("Fejl", "Du skal først give skolen et navn");
        }
    }

    @FXML
    private void onActionEditSchool() {
        if (selectedSchool != null) {
            setSelectedSchool(selectedSchool);
            btnEditSchool.setDisable(true);
            btnEditSave.setVisible(true);
            btnEditCancel.setVisible(true);
            btnDeleteSchool.setVisible(false);
            btnCreateSchool.setVisible(false);
        } else {
            ErrorHandlerController.createWarning("Fejl", "Du skal vælge en skole først");
        }

    }

    @FXML
    private void onActionEditSaveSchool() throws Exception {
        if (this.selectedSchool != null) {
            if (!txtFieldSchoolName.getText().isEmpty()) {
                int id = Integer.parseInt(txtFieldSchoolID.getText());
                String schoolName = txtFieldSchoolName.getText();

                School school = new School(id, schoolName);
                dataModelFacade.editSchool(school);
                reloadSchoolTable();
                clearSchoolTxtField();
                tvSchools.getSelectionModel().clearSelection();
                btnEditSchool.setDisable(false);
                btnEditSave.setVisible(false);
                btnDeleteSchool.setVisible(true);
                btnCreateSchool.setVisible(true);
                btnEditCancel.setVisible(false);
            } else {
                ErrorHandlerController.createWarning("Fejl", "Skolen skal have et navn, før du kan gemme ændringen");
            }
        }
    }

    @FXML
    private void onActionEditCancel() {
        reloadSchoolTable();
        clearSchoolTxtField();
        tvSchools.getSelectionModel().clearSelection();
        btnEditSchool.setDisable(false);
        btnEditSave.setVisible(false);
        btnDeleteSchool.setVisible(true);
        btnCreateSchool.setVisible(true);
        btnEditCancel.setVisible(false);
    }

    @FXML
    private void onActionDeleteSchool() throws SQLException {
        if (selectedSchool != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Advarsel");
            alert.setHeaderText("Advarsel før du sletter en skole");
            alert.setContentText("Denne handling kan ikke fortrydes");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                selectedSchool();
                dataModelFacade.deleteSchool(selectedSchool.getId());
                tvSchools.getSelectionModel().clearSelection();
                reloadSchoolTable();
                reloadAssignSchoolTable();
            }
        } else {
            ErrorHandlerController.createWarning("Fejl", "Du skal vælge en skole først");
        }

    }

    @FXML
    private void onActionCreateAdmin() throws SQLException {
        String userNames = String.valueOf(dataModelFacade.getUsernames());
        if (userNames.contains(String.valueOf(txtFieldAdminUsername.getText()))) {
            ErrorHandlerController.createWarning("Fejl", "Brugernavn er allerede taget");
            System.out.println(userNames);
        } else if (!txtFieldAdminFirstName.getText().isEmpty() && !txtFieldAdminLastName.getText().isEmpty() && !txtFieldAdminUsername.getText().isEmpty() && !txtFieldAdminPassword.getText().isEmpty() && comboSchool.getSelectionModel().getSelectedItem() != null) {
            String firstName = txtFieldAdminFirstName.getText();
            String lastName = txtFieldAdminLastName.getText();
            String userName = txtFieldAdminUsername.getText();
            String password = txtFieldAdminPassword.getText();
            int schoolId = Integer.parseInt(txtSchoolID.getText());


            //TODO Warning Samme username
            dataModelFacade.createAdmin(firstName, lastName, userName, password, UserType.ADMINISTRATOR, schoolId);
            clearAdminTxtField();
            tvAdmins.getSelectionModel().clearSelection();
            reloadAdminTable();
            reloadAssignedAdminTable();
        } else {
            ErrorHandlerController.createWarning("Fejl", "Du skal udfylde alle administratorens informationer først");
        }
    }

    @FXML
    private void onActionEditAdmin() {
        if (selectedAdmin != null) {
            setSelectedAdmin(selectedAdmin);
            btnEditAdmin.setDisable(true);
            btnEditSaveAdmin.setVisible(true);
            btnEditAdminCancel.setVisible(true);
            btnCreateAdmin.setVisible(false);
            btnDeleteAdmin.setVisible(false);
        } else {
            ErrorHandlerController.createWarning("Fejl", "Du skal vælge en admin først");
        }
    }

    @FXML
    private void onActionEditAdminSave() throws Exception {
        if (this.selectedAdmin != null) {
            if (!txtFieldAdminFirstName.getText().isEmpty() && !txtFieldAdminLastName.getText().isEmpty() && !txtFieldAdminUsername.getText().isEmpty() && !txtFieldAdminPassword.getText().isEmpty()) {
                int id = Integer.parseInt(txtFieldAdminID.getText());
                String firstName = txtFieldAdminFirstName.getText();
                String lastName = txtFieldAdminLastName.getText();
                String userName = txtFieldAdminUsername.getText();
                String password = txtFieldAdminPassword.getText();

                User admin = new User(id, firstName, lastName, userName, encryptor.encrypt(password), UserType.STUDENT);
                dataModelFacade.editAdmin(admin);
                reloadAdminTable();
                clearAdminTxtField();
                tvAdmins.getSelectionModel().clearSelection();
                btnEditAdmin.setDisable(false);
                btnEditSaveAdmin.setVisible(false);
                btnEditAdminCancel.setVisible(false);
                btnCreateAdmin.setVisible(true);
                btnDeleteAdmin.setVisible(true);
                txtFieldAdminPassword.setStyle("-fx-border-color: transparent");
            } else {
                ErrorHandlerController.createWarning("Fejl", "Du skal vælge en admin først");
            }
        }
    }

    @FXML
    private void onActionEditAdminCancel() {
        reloadAdminTable();
        clearAdminTxtField();
        tvAdmins.getSelectionModel().clearSelection();
        btnEditAdmin.setDisable(false);
        btnEditSaveAdmin.setVisible(false);
        btnEditAdminCancel.setVisible(false);
        btnCreateAdmin.setVisible(true);
        btnDeleteAdmin.setVisible(true);
        txtFieldAdminPassword.setStyle("-fx-border-color: transparent");
    }

    @FXML
    private void OnActionDeleteAdmin() throws SQLException {
        if (selectedAdmin != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Advarsel");
            alert.setHeaderText("Advarsel før du sletter en admin");
            alert.setContentText("Denne handling kan ikke fortrydes");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                selectedSchool();
                dataModelFacade.deleteAdmin(selectedAdmin.getId(), UserType.ADMINISTRATOR);
                reloadAdminTable();
                clearAdminTxtField();
            }
        } else {
            ErrorHandlerController.createWarning("Fejl", "Du skal vælge en admin først");
        }
    }

    private void tableViewLoadSchools(ObservableList<School> allSchools) {
        new Thread(() -> {
            tvSchools.setItems(getSchoolData());
        }).start();
    }

    private ObservableList<School> getSchoolData() {
        return allSchools;
    }

    private void tableViewLoadAdmins(ObservableList<User> allAdmins) {
        new Thread(() -> {
            tvAdmins.setItems(getAdminData());
        }).start();
    }

    private ObservableList<User> getAdminData() {
        return allAdmins;
    }


    private void tableViewLoadAssignAdmins(ObservableList<User> allAssignAdmins) {
        new Thread(() -> {
            tvAssignAdmin.setItems(getAssignAdminData());
        }).start();
    }

    private ObservableList<User> getAssignAdminData() {
        return allAssignAdmins;
    }

    private void tableViewLoadAssignedSchools(ObservableList<School> allAssignedSchools) {
        new Thread(() -> {
            tvAssignedSchool.setItems(getAssignedSchoolData());
        }).start();
    }

    private ObservableList<School> getAssignedSchoolData() {
        return allAssignedSchools;
    }

    private void tableViewLoadAssignedAdmins(ObservableList<User> allAssignedAdmins) {
        new Thread(() -> {
            tvAssignedAdminsOnSchool.setItems(getAssignedAdminData());
        }).start();
    }

    private ObservableList<User> getAssignedAdminData() {
        return allAssignedAdmins;
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

    private void reloadAssignSchoolTable() {
        try {
            int index = tvAssignedSchool.getSelectionModel().getFocusedIndex();
            this.tvAssignedSchool.setItems(FXCollections.observableList(dataModelFacade.getSchools()));
            tvAssignedSchool.getSelectionModel().select(index);
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

    private void reloadAssignedAdminTable() {
        try {
            int index = tvAssignedAdminsOnSchool.getSelectionModel().getFocusedIndex();
            this.tvAssignedAdminsOnSchool.setItems(FXCollections.observableList(dataModelFacade.getAdminsOnSchool(selectedSchoolToAssign.getId())));
            tvAssignedAdminsOnSchool.getSelectionModel().select(index);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void clearSchoolTxtField() {
        txtFieldSchoolID.clear();
        txtFieldSchoolName.clear();
    }

    private void clearAdminTxtField() {
        txtFieldAdminID.clear();
        txtFieldAdminFirstName.clear();
        txtFieldAdminLastName.clear();
        txtFieldAdminUsername.clear();
        txtFieldAdminPassword.clear();
    }

    private void setSelectedSchool(School school) {
        txtFieldSchoolID.setText(String.valueOf(school.getId()));
        txtFieldSchoolName.setText(school.getSchoolName());
    }

    private void setSelectedAdmin(User admin) {
        txtFieldAdminID.setText(String.valueOf(admin.getId()));
        txtFieldAdminFirstName.setText(admin.getFirstName());
        txtFieldAdminLastName.setText(admin.getLastName());
        txtFieldAdminUsername.setText(admin.getUsername());
        txtFieldAdminPassword.setPromptText("Indtast nyt password");
        txtFieldAdminPassword.setStyle("-fx-border-color: red");
    }

    private void selectedSchoolOnComboBox() {
        this.comboSchool.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            if (newValue != null) {
                this.selectedSchoolOnComboBox = newValue;
                comboSchool.getSelectionModel().getSelectedItem();
                txtSchoolID.setText(String.valueOf(selectedSchoolOnComboBox.getId()));
            }
        }));
    }

    private void selectedSchool() {
        this.tvSchools.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            if (newValue != null) {
                this.selectedSchool = newValue;
            }
        }));
    }

    private void selectedAdmin() {
        this.tvAdmins.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            if (newValue != null) {
                this.selectedAdmin = newValue;
            }
        }));
    }

    private void selectedSchoolToAssign() {
        this.tvAssignedSchool.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            if (newValue != null) {
                this.selectedSchoolToAssign = newValue;
                seeAssignedAdminsOnSchool();
            }
        }));
    }


    private void seeAssignedAdminsOnSchool() {
        //Initialize the assigned admins table on the assign admin view
        tcAssignedSchoolID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcAssignedAdminFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        tcAssignedAdminLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tcAssignedAdminUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        try {
            allAssignedAdmins = FXCollections.observableList(dataModelFacade.getAdminsOnSchool(selectedSchoolToAssign.getId()));
            tableViewLoadAssignedAdmins(allAssignedAdmins);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setAnchorPanesVisibility() {
        labelTitle.setText("Superadmin");
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
    private void btnClickCreateSchool() {
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
        switcher.centerOnScreen();
    }

    @Override
    public void setUser(User user) {
        labelTitle.setText("Super Admin");
        labelInfo.setText("Du er nu logget ind som Super Admin: " + user.getFirstName() + user.getLastName());
        labelInfoNewLine.setText("");
    }

}
