package gui.controller;

import be.Case;
import be.Citizen;
import be.GeneralInformation;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

public class AdminViewController implements Initializable, IController {


    /**
     * Top Pane
     */
    @FXML
    private Button btnLogOut;
    @FXML
    private Label labelTitle;
    @FXML
    private Label labelInfo;
    @FXML
    private Label labelInfoNewLine;
    @FXML
    private AnchorPane anchorPaneAdmin;
    @FXML
    private AnchorPane anchorPaneCitizen;
    @FXML
    private AnchorPane anchorPaneCreateTeacher;
    @FXML
    private AnchorPane anchorPaneCase;
    @FXML
    private AnchorPane anchorPaneStudent;
    @FXML
    private AnchorPane anchorPaneCreateCitizen;


    /**
     * Create teacher pane
     */
    @FXML
    private TableView<User> tvTeachers;
    @FXML
    private TableColumn<User, String> tcTeacherFirstName;
    @FXML
    private TableColumn<User, String> tcTeacherLastName;
    @FXML
    private TableColumn<User, String> tcTeacherUserName;
    @FXML
    private Button btnCreateTeacher;
    @FXML
    private Button btnEditTeacher;
    @FXML
    private Button btnDeleteTeacher;
    @FXML
    private Button btnEditTeacherSave;
    @FXML
    private Button btnEditTeacherCancel;
    @FXML
    private TextField txtFieldTeacherFirstName;
    @FXML
    private TextField txtFieldTeacherLastName;
    @FXML
    private TextField txtFieldTeacherUsername;
    @FXML
    private TextField txtFieldTeacherPassword;
    @FXML
    private TextField txtFieldTeacherID;
    @FXML
    private TextField txtFieldSchoolID;


    /**
     * Student pane
     */
    @FXML
    private TableView<User> tvStudent;
    @FXML
    private TableColumn<User, String> tcStudentFirstName;
    @FXML
    private TableColumn<User, String> tcStudentLastName;
    @FXML
    private TableColumn<User, String> tcStudentUsername;
    @FXML
    private Button btnSaveStudent;
    @FXML
    private Button btnEditStudent;
    @FXML
    private Button btnEditSave;
    @FXML
    private Button btnEditCancel;
    @FXML
    private Button btnDeleteStudent;
    @FXML
    private TextField txtFieldFirstName;
    @FXML
    private TextField txtFieldLastName;
    @FXML
    private TextField txtFieldUsername;
    @FXML
    private TextField txtFieldPassword;
    @FXML
    private TextField txtFieldStudentID;

    /**
     * CasePane
     */
    @FXML
    private TableView<Case> tvCases;
    @FXML
    private TableColumn<Case, String> tcCasesName;
    @FXML
    private TableColumn<Case, String> tcCasesDate;
    @FXML
    private Button btnSaveCase;
    @FXML
    private Button btnEditCase;
    @FXML
    private Button btnDeleteCase;
    @FXML
    private Button btnCopyCase;
    @FXML
    private Button btnEditCaseSave;
    @FXML
    private Button btnEditCaseCancel;
    @FXML
    private TextField txtFieldCaseID;
    @FXML
    private TextField txtFieldName;
    @FXML
    private TextArea txtAreaInfo;

    /**
     * CitizenPane
     */
    @FXML
    private TableView<Case> tvCurrentCases;
    @FXML
    private TableView<Citizen> tvCitizens;
    @FXML
    private TableView<Case> tvCasesOnCitizen;
    @FXML
    private TableColumn<Case, String> tcCurrentCasesID;
    @FXML
    private TableColumn<Case, String> tcCurrentCasesName;
    @FXML
    private TableColumn<Case, String> tcCurrentCasesDate;
    @FXML
    private TableColumn<Citizen, Integer> tcCitizenID;
    @FXML
    private TableColumn<Citizen, String> tcCitizenFirstName;
    @FXML
    private TableColumn<Citizen, String> tcCitizenLastName;
    @FXML
    private TableColumn<Citizen, String> tcCitizenSSN;
    @FXML
    private TableColumn<Case, Integer> tcCitizenOnCaseID;
    @FXML
    private TableColumn<Case, String> tcCasesOnCitizenName;
    @FXML
    private TableColumn<Case, String> tcCasesOnCitizenDate;
    @FXML
    private TableColumn<Case, String> tcCasesOnCitizenInfo;
    @FXML
    private Button btnCopySave;
    @FXML
    private TextField txtFieldSearchCitizens;
    @FXML
    private Button btnSearchCitizens;

    /**
     * Create Citizen Pane
     */
    @FXML
    private TableView<Citizen> tvCreatedCitizens;
    @FXML
    private TableColumn<Citizen, Integer> tcCreatedCitizenID;
    @FXML
    private TableColumn<Citizen, Integer> tcCreatedCitizenSchoolId;
    @FXML
    private TableColumn<Citizen, String> tcCreatedCitizenFirstName;
    @FXML
    private TableColumn<Citizen, String> tcCreatedCitizenLastName;
    @FXML
    private TableColumn<Citizen, String> tcCreatedCitizenSSN;
    @FXML
    private TextField txtFieldCitizenFirstName;
    @FXML
    private TextField txtFieldCitizenLastName;
    @FXML
    private TextField txtFieldCitizenSSN;
    @FXML
    private TextField txtFieldCitizenAddress;
    @FXML
    private RadioButton radioMale;
    @FXML
    private RadioButton radioFemale;
    @FXML
    private RadioButton radioOther;

    private ObservableList<User> allStudents = FXCollections.observableArrayList();
    private ObservableList<Citizen> allCitizens = FXCollections.observableArrayList();
    private ObservableList<Citizen> allCreatedCitizens = FXCollections.observableArrayList();
    private ObservableList<Case> allCases = FXCollections.observableArrayList();
    private ObservableList<Case> allCurrentCases = FXCollections.observableArrayList();
    private ObservableList<Case> allCasesOnCitizen = FXCollections.observableArrayList();
    private ObservableList<User> allTeachers = FXCollections.observableArrayList();

    private Case selectedCase;
    private Case selectedCaseOnCitizen;
    private Case selectedCurrentCase;
    private User selectedStudent;
    private User selectedTeacher;
    private Citizen selectedCitizen;
    private Citizen selectedCreatedCitizen;

    private final DataModelFacade dataModelFacade = DataModelFacade.getInstance();
    private StudentViewController studentViewController;
    private EditCaseViewController editCaseViewController;

    private Encryptor encryptor;

    private boolean hasSearched = true;
    private ObservableList<Citizen> searchData = FXCollections.observableArrayList();


    public AdminViewController() throws IOException, SQLException {
        this.studentViewController = new StudentViewController();
        this.editCaseViewController = new EditCaseViewController();
        this.encryptor = new Encryptor();
    }

    /**
     * Sets text labels with the user that has logged in.
     */
    @Override
    public void setUser(User user) {
        labelTitle.setText("Admin");
        labelInfo.setText("Du er nu logget ind som admin: " + user.getFirstName() + " " + user.getLastName());
        labelInfoNewLine.setText("");
        txtFieldSchoolID.setText(String.valueOf(user.getSchoolId()));
        initializeTable();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setToggleGroup();
        setAnchorPanesVisibility();
        selectedTeacher();
        selectedStudent();
        selectedCurrentCase();
        selectedCitizen();
        selectedCreatedCitizen();
        selectedCase();
        selectedCaseOnCitizen();
    }

    /**
     * Method to fill all the tables with information from the database.
     */
    private void initializeTable() {
        //Initialize the teacher table
        tcTeacherFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        tcTeacherLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tcTeacherUserName.setCellValueFactory(new PropertyValueFactory<>("username"));
        try {
            allTeachers = FXCollections.observableList(dataModelFacade.getAssignedTeachers(Integer.parseInt(txtFieldSchoolID.getText())));
            tableViewLoadTeachers(allTeachers);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Initialize the students table
        tcStudentFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        tcStudentLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tcStudentUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        try {
            allStudents = FXCollections.observableList(dataModelFacade.getAssignedStudents(Integer.parseInt(txtFieldSchoolID.getText())));
            tableViewLoadStudents(allStudents);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Initialize the cases table
        tcCasesName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tcCasesDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        try {
            allCases = FXCollections.observableList(dataModelFacade.getAssignedCases(Integer.parseInt(txtFieldSchoolID.getText())));
            tableViewLoadCases(allCases);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Initialize the citizens table
        tcCitizenID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcCitizenFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        tcCitizenLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tcCitizenSSN.setCellValueFactory(new PropertyValueFactory<>("SSN"));
        try {
            allCitizens = FXCollections.observableList(dataModelFacade.getAssignedCitizen(Integer.parseInt(txtFieldSchoolID.getText())));
            tableViewLoadCitizens(allCitizens);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Initialize the current cases table at citizen window
        tcCurrentCasesID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcCurrentCasesName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tcCurrentCasesDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        try {
            allCurrentCases = FXCollections.observableList(dataModelFacade.getAssignedCases(Integer.parseInt(txtFieldSchoolID.getText())));
            tableViewLoadCurrentCases(allCurrentCases);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Initialize the citizens table
        tcCreatedCitizenID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcCreatedCitizenFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        tcCreatedCitizenLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tcCreatedCitizenSSN.setCellValueFactory(new PropertyValueFactory<>("SSN"));
        try {
            allCreatedCitizens = FXCollections.observableList(dataModelFacade.getAssignedCitizen(Integer.parseInt(txtFieldSchoolID.getText())));
            tableViewLoadCreatedCitizens(allCreatedCitizens);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Method to set togglegroup for radiobuttons.
     */
    private void setToggleGroup() {
        ToggleGroup group = new ToggleGroup();
        radioMale.setToggleGroup(group);
        radioFemale.setToggleGroup(group);
        radioOther.setToggleGroup(group);
    }

    /**
     * loads the teacher view
     */
    private void tableViewLoadTeachers(ObservableList<User> allTeachers) {
        tvTeachers.setItems(getTeacherData());
    }

    /**
     * loads the students tableview.
     */
    private void tableViewLoadStudents(ObservableList<User> allStudents) {
        tvStudent.setItems(getStudentData());
    }

    /**
     * gets the data for teachers
     */
    private ObservableList<User> getTeacherData() {
        return allTeachers;
    }

    /**
     * gets the data for students.
     *
     * @return ObservableList<User>
     */
    private ObservableList<User> getStudentData() {
        return allStudents;
    }

    /**
     * loads the cases TableView.
     */
    private void tableViewLoadCases(ObservableList<Case> allCases) {
        tvCases.setItems(getCaseData());
    }

    /**
     * Gets the data for case
     *
     * @return ObservableList<Case>
     */
    private ObservableList<Case> getCaseData() {
        return allCases;
    }

    /**
     * loads the Citizens table view
     */
    private void tableViewLoadCitizens(ObservableList<Citizen> allCitizens) {
        tvCitizens.setItems(getCitizenData());
    }

    /**
     * Gets the data for citizens
     *
     * @return ObservableList<Citizen>
     */
    private ObservableList<Citizen> getCitizenData() {
        return allCitizens;
    }

    /**
     * loads the CurrentCases tableview.
     */
    private void tableViewLoadCurrentCases(ObservableList<Case> allCurrentCases) {
        tvCurrentCases.setItems(getCurrentCasesData());
    }

    /**
     * Gets the data for the currentCases.
     *
     * @return ObservableList<Case>
     */
    private ObservableList<Case> getCurrentCasesData() {
        return allCurrentCases;
    }

    /**
     * loads the casesOnCitizen tableview.
     */
    private void tableViewLoadCasesOnCitizen(ObservableList<Case> allCasesOnCitizen) {
        tvCasesOnCitizen.setItems(getCasesOnCitizenData());
    }

    /**
     * Get the data for casesOnCitizens
     *
     * @return ObservableList<Citizen>
     */
    private ObservableList<Case> getCasesOnCitizenData() {
        return allCasesOnCitizen;
    }

    /**
     * loads the Citizens table view
     */
    private void tableViewLoadCreatedCitizens(ObservableList<Citizen> allCreatedCitizens) {
        tvCreatedCitizens.setItems(getCreatedCitizenData());
    }

    /**
     * Gets the data for citizens
     *
     * @return ObservableList<Citizen>
     */
    private ObservableList<Citizen> getCreatedCitizenData() {
        return allCreatedCitizens;
    }


    /**
     * On Action method for creating a teacher.
     * Gets the text from all the txtfields and creates a teacher using the dataModelFacade method createTeacher.
     * @throws SQLException
     * @throws IOException
     */
    @FXML
    private void onActionCreateTeacher() throws SQLException, IOException {
        String userNames = String.valueOf(dataModelFacade.getUsernames());
        if (userNames.contains(String.valueOf(txtFieldTeacherUsername.getText()))) {
            ErrorHandlerController.createWarning("Fejl", "Brugernavn er allerede taget");
        } else if (!txtFieldTeacherFirstName.getText().isEmpty() && !txtFieldTeacherLastName.getText().isEmpty() && !txtFieldTeacherUsername.getText().isEmpty() && !txtFieldTeacherPassword.getText().isEmpty()) {
            String firstName = txtFieldTeacherFirstName.getText();
            String lastName = txtFieldTeacherLastName.getText();
            String userName = txtFieldTeacherUsername.getText();
            String password = txtFieldTeacherPassword.getText();
            dataModelFacade.createTeacher(firstName, lastName, userName, encryptor.encrypt(password), UserType.TEACHER, Integer.parseInt(txtFieldSchoolID.getText()));
            reloadTeacherTable();
        } else {
            ErrorHandlerController.createWarning("Fejl", "Læreren kunne ikke oprettes,"
                    + " husk at tilføje alle de nødvendige informationer");
        }
    }

    /**
     * On Action method for the edit button in the create teacher view.
     * Sets the visibility of buttons and disables buttons, so you dont make a mistake.
     */
    @FXML
    private void onActionEditTeacher() {
        if (selectedTeacher != null) {
            setSelectedTeacher(selectedTeacher);
            btnDeleteTeacher.setDisable(true);
            btnEditTeacher.setDisable(true);
            btnCreateTeacher.setVisible(false);
            btnEditTeacherSave.setVisible(true);
            btnEditTeacherCancel.setVisible(true);
            txtFieldPassword.setStyle("-fx-border-color: red");
        } else {
            ErrorHandlerController.createWarning("Fejl", "Du skal vælge en lærer først");
        }

    }

    /**
     * On action method for saving the edited teacher info.
     * Gets all the text from the textFields and uses the editTeacher method from dataModelFacade.
     * @throws Exception
     */
    @FXML
    private void onActionEditTeacherSave() throws Exception {
        if (this.selectedTeacher != null) {
            if (!txtFieldTeacherFirstName.getText().isEmpty() && !txtFieldTeacherLastName.getText().isEmpty() && !txtFieldTeacherUsername.getText().isEmpty() && !txtFieldTeacherPassword.getText().isEmpty()) {
                int id = Integer.parseInt(txtFieldTeacherID.getText());

                String firstName = txtFieldTeacherFirstName.getText();
                String lastName = txtFieldTeacherLastName.getText();
                String userName = txtFieldTeacherUsername.getText();
                String password = txtFieldTeacherPassword.getText();

                User teacher = new User(id, firstName, lastName, userName, encryptor.encrypt(password), UserType.TEACHER);
                dataModelFacade.editTeacher(teacher);
                reloadTeacherTable();
                clearTeacherTxtField();
                tvTeachers.getSelectionModel().clearSelection();
                btnDeleteTeacher.setDisable(false);
                btnEditTeacher.setDisable(false);
                btnCreateTeacher.setVisible(true);
                btnEditTeacherSave.setVisible(false);
                btnEditTeacherCancel.setVisible(false);
                txtFieldPassword.setStyle("-fx-border-color: transparent");
            } else {
                ErrorHandlerController.createWarning("Fejl", "Husk at vælge en lærer først");
            }
        }
    }

    /**
     * On action method for deleting a teacher.
     * Uses the deleteTeacher method from the dataModelFacade.
     * @throws SQLException
     */

    @FXML
    private void onActionDeleteTeacher() throws SQLException {
        if (selectedTeacher != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Advarsel");
            alert.setHeaderText("Advarsel før du sletter en lærer");
            alert.setContentText("Denne handling kan ikke fortrydes");
            if (selectedTeacher != null) {
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    selectedTeacher();
                    dataModelFacade.deleteTeacher(selectedTeacher.getId(), UserType.TEACHER);
                    reloadTeacherTable();
                }
            }
        } else {
            ErrorHandlerController.createWarning("Fejl", "Husk at vælge en lærer først");
        }

    }

    /**
     * On action method for cancelling your edit.
     * Sets the visibility and disables back to normal when cancelling.
     */
    @FXML
    private void onActionEditTeacherCancel() {
        reloadTeacherTable();
        clearTeacherTxtField();
        tvStudent.getSelectionModel().clearSelection();

        btnEditTeacher.setDisable(false);
        btnDeleteTeacher.setDisable(false);

        btnEditTeacherCancel.setVisible(false);
        btnEditTeacherSave.setVisible(false);
        btnCreateTeacher.setVisible(true);
        txtFieldPassword.setStyle("-fx-border-color: transparent");
    }

    /**
     * Makes you able to select a teacher from the table
     */
    private void selectedTeacher() {
        this.tvTeachers.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            if (newValue != null) {
                this.selectedTeacher = newValue;
            }
        }));
    }

    /**
     * Method for setting the selected teacher.
     * @param teacher
     */
    private void setSelectedTeacher(User teacher) {
        txtFieldTeacherID.setText(String.valueOf(teacher.getId()));
        txtFieldTeacherFirstName.setText(teacher.getFirstName());
        txtFieldTeacherLastName.setText(teacher.getLastName());
        txtFieldTeacherUsername.setText(teacher.getUsername());
        txtFieldTeacherPassword.setPromptText("Indtast nyt password");

    }

    /**
     * Method for reloading the teacher table, so it is always up to date with displayed info.
     */
    private void reloadTeacherTable() {
        try {
            int index = tvTeachers.getSelectionModel().getFocusedIndex();
            this.tvTeachers.setItems(FXCollections.observableList(dataModelFacade.getAssignedTeachers(Integer.parseInt(txtFieldSchoolID.getText()))));
            tvTeachers.getSelectionModel().select(index);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Method for clearing textFields.
     */
    private void clearTeacherTxtField() {
        txtFieldTeacherID.clear();
        txtFieldTeacherFirstName.clear();
        txtFieldTeacherLastName.clear();
        txtFieldTeacherUsername.clear();
        txtFieldTeacherPassword.clear();
    }

    /**
     * Assigns case to citizen, with current case that is selected and citizen selected.
     */
    @FXML
    private void btnHandleAssignCase() {
        if (selectedCurrentCase != null && selectedCitizen != null) {
            try {
                dataModelFacade.assignCaseToCitizen(selectedCurrentCase.getId(), selectedCitizen.getId());
                seeCasesOnCitizen();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            ErrorHandlerController.createWarning("Fejl", "Du skal først vælge en borger og en sag, før du kan tildele en sag");
        }
    }

    /**
     * Method to delete a case from a citizen.
     */
    @FXML
    private void onActionDeleteCaseFromCitizen() {
        if (selectedCaseOnCitizen != null && selectedCitizen != null) {
            try {
                dataModelFacade.deleteCaseFromCitizen(selectedCaseOnCitizen.getId(), selectedCitizen.getId());
                seeCasesOnCitizen();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            ErrorHandlerController.createWarning("Fejl", "Du skal først vælge en borger og en sag, før du kan slette en sag fra borgeren");
        }
    }

    /**
     * On action method for saving and creating a new citizen.
     * Gets all text from textFields and creates a citizen using the createCitizen method from dataModelFacade
     * @throws SQLException
     */
    @FXML
    private void btnHandleSaveCitizen() throws SQLException {
        if (!txtFieldCitizenFirstName.getText().isEmpty() && !txtFieldCitizenLastName.getText().isEmpty()
                && !txtFieldCitizenSSN.getText().isEmpty() && !txtFieldCitizenAddress.getText().isEmpty() &&
                radioMale.isSelected() || radioFemale.isSelected() || radioOther.isSelected()) {
            String firstName = txtFieldCitizenFirstName.getText();
            String lastName = txtFieldCitizenLastName.getText();
            String SSN = txtFieldCitizenSSN.getText();
            String address = txtFieldCitizenAddress.getText();
            String sex = null;
            if (radioMale.isSelected()) {
                sex = "Male";
            } else if (radioFemale.isSelected()) {
                sex = "Female";
            } else if (radioOther.isSelected()) {
                sex = "Other";
            }
            dataModelFacade.createCitizen(firstName, lastName, SSN, address, sex, Integer.parseInt(txtFieldSchoolID.getText()));
            clearTextFieldCreate();
            reloadCreatedCitizensTable();
            reloadCitizenTable();
        } else {
            ErrorHandlerController.createWarning("Fejl", "Du skal udfylde alle borgerens informationer");
        }

    }

    /**
     * Method to clear textfields from create citizen.
     */
    private void clearTextFieldCreate() {
        txtFieldCitizenFirstName.clear();
        txtFieldCitizenLastName.clear();
        txtFieldCitizenSSN.clear();
        txtFieldCitizenAddress.clear();

    }

    /**
     * Reloads the createdCitizens table
     */
    private void reloadCitizenTable() {
        try {
            int index = tvCitizens.getSelectionModel().getFocusedIndex();
            this.tvCitizens.setItems(FXCollections.observableList(dataModelFacade.getAssignedCitizen(Integer.parseInt(txtFieldSchoolID.getText()))));
            tvCitizens.getSelectionModel().select(index);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Reloads the createdCitizens table
     */
    private void reloadCreatedCitizensTable() {
        try {
            int index = tvCreatedCitizens.getSelectionModel().getFocusedIndex();
            this.tvCreatedCitizens.setItems(FXCollections.observableList(dataModelFacade.getAssignedCitizen(Integer.parseInt(txtFieldSchoolID.getText()))));
            tvCreatedCitizens.getSelectionModel().select(index);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Action event for save student button. Gets the text from all the textFields and creates a new student when pressed.
     */
    @FXML
    private void btnHandleSaveStudent() throws SQLException, IOException {
        String userNames = String.valueOf(dataModelFacade.getUsernames());
        if (userNames.contains(String.valueOf(txtFieldUsername.getText()))) {
            ErrorHandlerController.createWarning("Fejl", "Brugernavn er allerede taget");
        } else if (!txtFieldFirstName.getText().isEmpty() && !txtFieldLastName.getText().isEmpty() && !txtFieldUsername.getText().isEmpty() && !txtFieldPassword.getText().isEmpty()) {
            String firstName = txtFieldFirstName.getText();
            String lastName = txtFieldLastName.getText();
            String userName = txtFieldUsername.getText();
            String password = txtFieldPassword.getText();
            dataModelFacade.createStudent(firstName, lastName, userName, encryptor.encrypt(password), UserType.STUDENT, Integer.parseInt(txtFieldSchoolID.getText()));
            reloadStudentTable();
            clearStudentTxtField();
            tvStudent.getSelectionModel().clearSelection();
        } else {
            ErrorHandlerController.createWarning("Fejl", "Du skal først udfylde alle den studerendes oplysninger");
        }
    }

    /**
     * Action Event for the edit student button. Fills all textFields, with data from the selected student.
     * Edits the student using the ID.
     */
    @FXML
    private void btnHandleEditStudent() {
        if (selectedStudent != null) {
            setSelectedStudent(selectedStudent);
            btnEditStudent.setVisible(false);
            btnEditSave.setVisible(true);
            btnEditCancel.setVisible(true);
            btnDeleteStudent.setVisible(false);
            btnSaveStudent.setDisable(true);
        } else {
            ErrorHandlerController.createWarning("Fejl", "Du skal vælge en studerende først");
        }
    }

    /**
     * On action method for saving edits on a student.
     * Gets all information from textfields and saves the edits using the editStudent method from dataModelFacade.
     * @throws Exception
     */
    @FXML
    private void btnHandleEditSave() throws Exception {
        if (this.selectedStudent != null) {
            if (!txtFieldFirstName.getText().isEmpty() && !txtFieldLastName.getText().isEmpty() && !txtFieldUsername.getText().isEmpty() && !txtFieldPassword.getText().isEmpty()) {
                int id = Integer.parseInt(txtFieldStudentID.getText());
                String firstName = txtFieldFirstName.getText();
                String lastName = txtFieldLastName.getText();
                String userName = txtFieldUsername.getText();
                String password = txtFieldPassword.getText();

                User student = new User(id, firstName, lastName, userName, encryptor.encrypt(password), UserType.STUDENT);
                dataModelFacade.editStudent(student);
                reloadStudentTable();
                clearStudentTxtField();
                tvStudent.getSelectionModel().clearSelection();
                btnSaveStudent.setDisable(false);
                btnEditCancel.setVisible(false);
                btnEditStudent.setVisible(true);
                btnEditSave.setVisible(false);
                btnDeleteStudent.setVisible(true);
                txtFieldPassword.setStyle("-fx-border-color: transparent");
            } else {
                ErrorHandlerController.createWarning("Fejl", "Alle den studerendes informationer skal være udfyldt");
            }
        }
    }

    /**
     * On action method for deleting citizen.
     * Uses the selectedCreatedCitizen and deletes using the id from citizen, with the deleteCitizen method from -
     * dataModelFacade.
     * @throws Exception
     */
    @FXML
    private void btnHandleDeleteCitizen() throws Exception {
        if (selectedCreatedCitizen != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Advarsel");
            alert.setHeaderText("Advarsel før du sletter en borger");
            alert.setContentText("Denne handling kan ikke fortrydes");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                selectedCitizen();
                dataModelFacade.deleteCitizen(selectedCreatedCitizen.getId());
                reloadCreatedCitizensTable();
                reloadCitizenTable();
            }
        } else {
            ErrorHandlerController.createWarning("Fejl", "Du skal vælge en borger først");
        }
    }

    /**
     * On action method for cancelling an edit.
     * Clears textfields and sets buttons visibility and disable back to normal.
     */
    @FXML
    private void btnHandleEditCancel() {
        reloadStudentTable();
        clearStudentTxtField();
        tvStudent.getSelectionModel().clearSelection();
        btnSaveStudent.setDisable(false);
        btnEditStudent.setVisible(true);
        btnDeleteStudent.setVisible(true);
        btnEditSave.setVisible(false);
        btnEditCancel.setVisible(false);
        txtFieldPassword.setStyle("-fx-border-color: transparent");
    }

    /**
     * Clears all textfields in the student view
     */
    private void clearStudentTxtField() {
        txtFieldStudentID.clear();
        txtFieldFirstName.clear();
        txtFieldLastName.clear();
        txtFieldUsername.clear();
        txtFieldPassword.clear();
    }

    /**
     * Action event for button to delete selected student.
     */
    @FXML
    private void btnHandleDeleteStudent() throws SQLException {
        if (selectedStudent != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Advarsel");
            alert.setHeaderText("Advarsel før du sletter en studerende");
            alert.setContentText("Handlingen kan ikke fortrydes");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                selectedStudent();
                dataModelFacade.deleteStudent(selectedStudent.getId(), UserType.STUDENT);
                reloadStudentTable();
            }
        } else {
            ErrorHandlerController.createWarning("Fejl", "Du skal vælge en studerende først");
        }
    }

    /**
     * Selects a student from the student tableView
     */
    private void selectedStudent() {
        this.tvStudent.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            if (newValue != null) {
                this.selectedStudent = newValue;
            }
        }));
    }

    /**
     * Selects a case from the currentCase tableView
     */
    private void selectedCurrentCase() {
        this.tvCurrentCases.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            if (newValue != null) {
                this.selectedCurrentCase = newValue;
            }
        }));
    }


    /**
     * Selects a citizen from the citizens TableView
     * Also opens a new view when a citizen is doubleClicked.
     */
    private void selectedCitizen() {
        this.tvCitizens.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            if (newValue != null) {
                this.selectedCitizen = newValue;
                seeCasesOnCitizen();
            }
        }));

        this.tvCitizens.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && selectedCitizen != null) {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("/gui/view/StudentView.fxml"));

                    Scene mainWindowScene = null;
                    try {
                        mainWindowScene = new Scene(fxmlLoader.load());
                    } catch (IOException exception) {
                        exception.printStackTrace();
                    }
                    Stage viewCitizenStage;
                    viewCitizenStage = new Stage();
                    viewCitizenStage.setScene(mainWindowScene);

                    GeneralInformation generalInformation = dataModelFacade.getGeneralInformationOnCitizen(tvCitizens.getSelectionModel().getSelectedItem().getId());
                    studentViewController = fxmlLoader.getController();
                    studentViewController.btnClickGeneralInformation();
                    studentViewController.setCitizenInfo(tvCitizens.getSelectionModel().getSelectedItem().getId());
                    if (generalInformation != null) {
                        studentViewController.setGeneralInfoFromID(String.valueOf(tvCitizens.getSelectionModel().getSelectedItem().getId()), String.valueOf(generalInformation.getId()));
                    }
                    studentViewController.setCitizenComboBoxItems(tvCitizens.getSelectionModel().getSelectedItem());
                    studentViewController.initializeTables();
                    studentViewController.initializeCitizenComboBox();

                    viewCitizenStage.setResizable(false);
                    viewCitizenStage.show();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Selects a citizen from the citizens TableView
     * Also opens a new view when a citizen is doubleClicked.
     */
    private void selectedCreatedCitizen() {
        this.tvCreatedCitizens.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            if (newValue != null) {
                this.selectedCreatedCitizen = newValue;
            }
        }));

        this.tvCreatedCitizens.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && selectedCreatedCitizen != null) {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("/gui/view/StudentView.fxml"));

                    Scene mainWindowScene = null;
                    try {
                        mainWindowScene = new Scene(fxmlLoader.load());
                    } catch (IOException exception) {
                        exception.printStackTrace();
                    }
                    Stage viewCitizenStage;
                    viewCitizenStage = new Stage();
                    viewCitizenStage.setScene(mainWindowScene);

                    GeneralInformation generalInformation = dataModelFacade.getGeneralInformationOnCitizen(tvCreatedCitizens.getSelectionModel().getSelectedItem().getId());
                    studentViewController = fxmlLoader.getController();
                    studentViewController.btnClickGeneralInformation();
                    studentViewController.setCitizenInfo(tvCreatedCitizens.getSelectionModel().getSelectedItem().getId());
                    if (generalInformation != null) {
                        studentViewController.setGeneralInfoFromID(String.valueOf(tvCreatedCitizens.getSelectionModel().getSelectedItem().getId()), String.valueOf(generalInformation.getId()));
                    }
                    studentViewController.setCitizenComboBoxItems(tvCreatedCitizens.getSelectionModel().getSelectedItem());
                    studentViewController.initializeTables();
                    studentViewController.initializeCitizenComboBox();

                    viewCitizenStage.setResizable(false);
                    viewCitizenStage.show();


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Sets the selected student
     */
    private void setSelectedStudent(User student) {
        txtFieldStudentID.setText(String.valueOf(student.getId()));
        txtFieldFirstName.setText(student.getFirstName());
        txtFieldLastName.setText(student.getLastName());
        txtFieldUsername.setText(student.getUsername());
        txtFieldPassword.setPromptText("Indtast nyt password");
        txtFieldPassword.setStyle("-fx-border-color: red");
    }

    /**
     * Reloads the student table
     */
    private void reloadStudentTable() {
        try {
            int index = tvStudent.getSelectionModel().getFocusedIndex();
            this.tvStudent.setItems(FXCollections.observableList(dataModelFacade.getAssignedStudents(Integer.parseInt(txtFieldSchoolID.getText()))));
            tvStudent.getSelectionModel().select(index);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }


    /**
     * Loads all data in tableview, from the selected current case ID.
     */
    private void seeCasesOnCitizen() {
        //Initialize the citizens on cases table at citizen window
        tcCitizenOnCaseID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcCasesOnCitizenName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tcCasesOnCitizenDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tcCasesOnCitizenInfo.setCellValueFactory(new PropertyValueFactory<>("info"));
        try {
            allCasesOnCitizen = FXCollections.observableList(dataModelFacade.getCasesOnCitizen(selectedCitizen.getId()));
            tableViewLoadCasesOnCitizen(allCasesOnCitizen);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Makes you able to select a case from the table
     * Also opens a new view when a case is doubleClicked.
     */
    private void selectedCase() {
        this.tvCases.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            if (newValue != null) {
                this.selectedCase = newValue;
            }
        }));

        this.tvCases.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && selectedCase != null) {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("/gui/view/EditCaseView.fxml"));

                    Scene scene = new Scene(fxmlLoader.load());

                    Stage stage = new Stage();
                    stage.setScene(scene);

                    editCaseViewController = fxmlLoader.getController();
                    editCaseViewController.setSelectedCase(selectedCase);

                    stage.setResizable(false);
                    stage.show();
                    stage.setOnHiding(aCase ->
                    {
                        try {
                            reloadCaseTable();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Makes you able to select a case on citizen, from the tvCasesOnCitizen.
     */
    private void selectedCaseOnCitizen() {
        this.tvCasesOnCitizen.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            if (newValue != null) {
                this.selectedCaseOnCitizen = newValue;
            }
        }));
    }

    /**
     * Reloads the student table
     */
    private void reloadCaseTable() {
        try {
            int index = tvCases.getSelectionModel().getFocusedIndex();
            this.tvCases.setItems(FXCollections.observableList(dataModelFacade.getAssignedCases(Integer.parseInt(txtFieldSchoolID.getText()))));
            tvCases.getSelectionModel().select(index);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Method for reloading the current cases table.
     */
    private void reloadCurrentCasesTable() {
        try {
            int index = tvCurrentCases.getSelectionModel().getFocusedIndex();
            this.tvCurrentCases.setItems(FXCollections.observableList(dataModelFacade.getAssignedCases(Integer.parseInt(txtFieldSchoolID.getText()))));
            tvCurrentCases.getSelectionModel().select(index);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Sets the selected event
     */
    private void setSelectedCase(Case aCase) {
        txtFieldCaseID.setText(String.valueOf(aCase.getId()));
        txtFieldName.setText(aCase.getName());
        txtAreaInfo.setText(aCase.getInfo());
    }

    /**
     * On action method for saving a case.
     * Get the text from textFields and creates a case using the createCase method from the dataModelFacade
     * @throws Exception
     */
    @FXML
    private void btnHandleSaveCase() throws Exception {
        if (!txtFieldName.getText().isEmpty() && !txtAreaInfo.getText().isEmpty()) {
            String name = txtFieldName.getText();
            String area = txtAreaInfo.getText();
            allCases.add(dataModelFacade.createCase(name, area, Integer.parseInt(txtFieldSchoolID.getText())));
            assignDate();
            reloadCaseTable();
            reloadCurrentCasesTable();
            txtFieldName.clear();
            txtAreaInfo.clear();
        } else {
            ErrorHandlerController.createWarning("Fejl", "Du skal udfylde sagens navn og info først");
        }
    }

    /**
     * Method for assigning date when creating/editing a case.
     * @throws Exception
     */
    private void assignDate() throws Exception {
        selectedCase = allCases.get(allCases.size() - 1);
        if (selectedCase != null) {
            Date caseDate = new Date(System.currentTimeMillis());
            String pattern = "dd/MM/yyyy  HH:mm:ss";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            String date = simpleDateFormat.format(caseDate);
            selectedCase.setDate(date);
            dataModelFacade.editCase(selectedCase);
        }
    }

    /**
     * On action method for deleting a case.
     * Uses the caseId when deleting a case, using the deleteCase method from dataModelFacade
     * @throws Exception
     */
    @FXML
    private void btnHandleDeleteCase() throws Exception {
        if (selectedCase != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Advarsel");
            alert.setHeaderText("Advarsel før du sletter en sag");
            alert.setContentText("Denne handling kan ikke fortrydes");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                selectedCase();
                dataModelFacade.deleteCase(selectedCase.getId());
                reloadCaseTable();
                reloadCurrentCasesTable();
            }
        } else {
            ErrorHandlerController.createWarning("Fejl", "Du skal vælge en sag først");
        }
    }

    /**
     * On action method for when you press edit case in the view.
     * Sets the buttons visibility and disables not useful buttons, to avoid mistakes.
     */
    @FXML
    private void btnHandleEditCase() {
        if (selectedCase != null) {
            setSelectedCase(selectedCase);
            btnEditCase.setDisable(true);
            btnCopyCase.setDisable(true);
            btnSaveCase.setVisible(false);
            btnDeleteCase.setVisible(false);
            btnEditCaseSave.setVisible(true);
            btnEditCaseCancel.setVisible(true);
        } else {
            ErrorHandlerController.createWarning("Fejl", "Du skal vælge en sag først");
        }
    }

    /**
     * On action method for saving edited cases.
     * Gets all the information from textFields and uses editCase method from dataModelFacade.
     * @throws Exception
     */
    @FXML
    private void btnHandleEditCaseSave() throws Exception {
        if (this.selectedCase != null) {
            if (!txtFieldName.getText().isEmpty() && !txtAreaInfo.getText().isEmpty()) {
                int id = Integer.parseInt(txtFieldCaseID.getText());
                String name = txtFieldName.getText();
                String date = selectedCase.getDate();
                String info = txtAreaInfo.getText();
                Case aCase = new Case(id, name, date, info);
                dataModelFacade.editCase(aCase);

                reloadCaseTable();

                txtFieldName.clear();
                txtAreaInfo.clear();

                setCaseBtnVisibility();
            } else {
                ErrorHandlerController.createWarning("Fejl", "Sagens navn og info skal være udfyldt");
            }
        }
    }

    /**
     * On action method for when you press cancel when editing a case.
     */
    @FXML
    private void btnHandleEditCaseCancel() {
        reloadCaseTable();
        txtFieldName.clear();
        txtAreaInfo.clear();
        setCaseBtnVisibility();
    }

    /**
     * On action method for when you copy a case.
     * It fills the textarea with the picked case's information, and then you can save it as a new one.
     */
    @FXML
    private void btnHandleCopyCase() {
        if (this.selectedCase != null) {
            txtFieldName.setText(selectedCase.getName());
            txtAreaInfo.setText(selectedCase.getInfo());
            setSelectedCase(selectedCase);
            btnEditCase.setDisable(true);
            btnCopyCase.setDisable(true);
            btnSaveCase.setVisible(false);
            btnDeleteCase.setVisible(false);
            btnCopySave.setVisible(true);
            btnEditCaseCancel.setVisible(true);
        } else {
            ErrorHandlerController.createWarning("Fejl", "Du skal vælge en sag først");
        }
    }

    /**
     * On action method for saving a copied case.
     * @throws Exception
     */
    @FXML
    private void btnHandleCopySave() throws Exception {
        if (selectedCase != null) {
            btnHandleSaveCase();
            setCaseBtnVisibility();
        }
    }

    /**
     * Method to set the only useful buttons visible, and disabling the not useful buttons.
     */
    @FXML
    private void setCaseBtnVisibility() {
        btnEditCase.setDisable(false);
        btnCopyCase.setDisable(false);
        btnSaveCase.setVisible(true);
        btnDeleteCase.setVisible(true);
        btnEditCaseSave.setVisible(false);
        btnEditCaseCancel.setVisible(false);
        btnCopySave.setVisible(false);
    }

    /**
     * Method for showing the right anchorpane when opening the program, sets the main view visible and not relevant
     * anchorpanes not visible
     */
    private void setAnchorPanesVisibility() {
        labelInfoNewLine.setText("");
        anchorPaneAdmin.setVisible(true);
        anchorPaneCreateCitizen.setVisible(false);
        anchorPaneStudent.setVisible(false);
        anchorPaneCitizen.setVisible(false);
        anchorPaneCase.setVisible(false);
        anchorPaneCreateTeacher.setVisible(false);
    }

    /**
     * Loads the teacher overview anchorpane when clicked.
     */
    @FXML
    private void btnClickCreateTeacher() {
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

    /**
     * Loads the student overview anchorpane when clicked.
     */
    @FXML
    private void btnClickStudent() {
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

    /**
     * Loads the cases overview anchorpane when clicked.
     */
    @FXML
    private void btnClickCase() {
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

    /**
     * Loads the citizen overview anchorPane when clicked.
     */
    @FXML
    private void btnClickSeeCitizens() {
        labelTitle.setText("Borgere");
        labelInfo.setText("Overblik over alle oprettede borgere. Tildel en sag til en borger, se yderligere informationer,");
        labelInfoNewLine.setText("rediger eller slet borger.");
        anchorPaneCitizen.setVisible(true);
        anchorPaneCreateCitizen.setVisible(false);
        anchorPaneAdmin.setVisible(false);
        anchorPaneStudent.setVisible(false);
        anchorPaneCase.setVisible(false);
        anchorPaneCreateTeacher.setVisible(false);
    }

    /**
     * Loads the create citizen overview anchorPane when clicked.
     */
    @FXML
    private void btnClickCitizen() {
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

    /**
     * Loads the home anchorPane when clicked.
     */
    @FXML
    private void btnClickHome() {
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

    /**
     * Action event for logout button, that gets the login view and loads that when pressed.
     * Closes current stage
     */
    @FXML
    private void btnClickLogout() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/LoginView.fxml"));
        Scene scene = new Scene(loader.load());
        Stage switcher = (Stage) btnLogOut.getScene().getWindow();
        switcher.setScene(scene);
        switcher.setTitle("Lærer");
        switcher.show();
        switcher.centerOnScreen();
    }

    /**
     * On action method for searching a citizen.
     * Uses the input from the search textField to load the tableview with the search results.
     */
    @FXML
    private void onActionSearchCitizens() {
        if (hasSearched && !txtFieldSearchCitizens.getText().equals("")) {
            btnSearchCitizens.setText("X");
            hasSearched = false;
        } else {
            btnSearchCitizens.setText("🔍");
            hasSearched = true;
            txtFieldSearchCitizens.clear();
        }
        try {
            searchData = FXCollections.observableList(dataModelFacade.searchCitizen(txtFieldSearchCitizens.getText(), Integer.parseInt(txtFieldSchoolID.getText())));
            searchTableViewLoad(searchData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the tableview for the citizens, when search is pressed.
     *
     * @param searchData
     */
    private void searchTableViewLoad(ObservableList<Citizen> searchData) {
        tvCreatedCitizens.setItems(getSearchData());
    }

    /**
     * @return searchData;
     */
    private ObservableList<Citizen> getSearchData() {
        return searchData;
    }

}
