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

public class TeacherViewController implements Initializable, IController {

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
    private AnchorPane anchorPaneCitizen;
    @FXML
    private AnchorPane anchorPaneCreateCitizen;
    @FXML
    private AnchorPane anchorPaneTeacher;
    @FXML
    private AnchorPane anchorPaneCase;
    @FXML
    private AnchorPane anchorPaneStudent;
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
    @FXML
    private TextField txtFieldSearchCitizens;
    @FXML
    private Button btnSearchCitizens;

    /**
     * CitizenPane
     */
    @FXML
    private TableView<Case> tvCurrentCases;
    @FXML
    private TableView<Citizen> tvCitizens;
    @FXML
    private TableView<Case> tvCasesOnCitizen;

    /**
     * Cases on Citizens
     */
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

    /**
     * Create Citizen Pane
     */
    @FXML
    private TableView<Citizen> tvCreatedCitizens;
    @FXML
    private TableColumn<Citizen, Integer> tcCreatedCitizenID;
    @FXML
    private TableColumn<Citizen, String> tcCreatedCitizenFirstName;
    @FXML
    private TableColumn<Citizen, String> tcCreatedCitizenLastName;
    @FXML
    private TableColumn<Citizen, String> tcCreatedCitizenSSN;
    @FXML
    private TableColumn<Citizen, String> tcCreatedCitizenSchoolId;

    /**
     * General Info citizen info
     */
    @FXML
    private TextField txtFieldCitizenFirstName;
    @FXML
    private TextField txtFieldCitizenLastName;
    @FXML
    private TextField txtFieldCitizenSSN;
    @FXML
    private TextField txtFieldCitizenAddress;

    /**
     * Gender radioButtons
     */
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

    private Case selectedCase;
    private Case selectedCaseOnCitizen;
    private Case selectedCurrentCase;
    private User selectedStudent;
    private Citizen selectedCitizen;
    private Citizen selectedCreatedCitizen;

    private final DataModelFacade dataModelFacade = DataModelFacade.getInstance();
    private StudentViewController studentViewController;
    private EditCaseViewController editCaseViewController;

    private Encryptor encryptor;


    private boolean hasSearched = true;
    private ObservableList<Citizen> searchData = FXCollections.observableArrayList();

    public TeacherViewController() throws IOException, SQLException {
        this.studentViewController = new StudentViewController();
        this.editCaseViewController = new EditCaseViewController();
        this.encryptor = new Encryptor();
    }

    /**
     * Sets text labels with the user that has logged in.
     */
    @Override
    public void setUser(User user) {
        labelTitle.setText("Lærer");
        labelInfo.setText("Du er nu logget ind som lærer: " + user.getFirstName() + " " + user.getLastName());
        labelInfoNewLine.setText("");
        txtFieldSchoolID.setText(String.valueOf(user.getSchoolId()));
        initializeTable();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setToggleGroup();
        setAnchorPanesVisibility();
        selectedStudent();
        selectedCurrentCase();
        selectedCitizen();
        selectedCreatedCitizen();
        selectedCase();
        selectedCaseOnCitizen();
    }

    /**
     * Set the radio button in a group, so that only one button can be picked
     */
    public void setToggleGroup() {
        ToggleGroup group = new ToggleGroup();
        radioMale.setToggleGroup(group);
        radioFemale.setToggleGroup(group);
        radioOther.setToggleGroup(group);
    }

    /**
     * Initializes the different tables views
     */
    private void initializeTable() {
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
        tcCreatedCitizenSchoolId.setCellValueFactory(new PropertyValueFactory<>("schoolID"));
        try {
            allCreatedCitizens = FXCollections.observableList(dataModelFacade.getAssignedCitizen(Integer.parseInt(txtFieldSchoolID.getText())));
            tableViewLoadCreatedCitizens(allCreatedCitizens);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * loads the students tableview.
     */
    private void tableViewLoadStudents(ObservableList<User> allStudents) {
        new Thread(() -> {
            tvStudent.setItems(getStudentData());
        }).start();

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
        new Thread(() -> {
            tvCases.setItems(getCaseData());
        }).start();
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
        new Thread(() -> {
            tvCitizens.setItems(getCitizenData());
        }).start();
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
     * loads the Citizens table view
     */
    private void tableViewLoadCreatedCitizens(ObservableList<Citizen> allCreatedCitizens) {
        new Thread(() -> {
            tvCreatedCitizens.setItems(getCreatedCitizenData());
        }).start();
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
     * loads the CurrentCases tableview.
     */
    private void tableViewLoadCurrentCases(ObservableList<Case> allCurrentCases) {
        new Thread(() -> {
            tvCurrentCases.setItems(getCurrentCasesData());
        }).start();
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
        new Thread(() -> {
            tvCasesOnCitizen.setItems(getCasesOnCitizenData());
        }).start();
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
            ErrorHandlerController.createWarning("Fejl", "Du skal først vælge en sag og en borger");
        }
    }

    /**
     * Deletes a case from a selected citizen
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
            ErrorHandlerController.createWarning("Fejl", "Du skal først vælge en sag og en borger");
        }
    }

    /**
     * Creates a citizen and when press on save button, the different text fields get cleared
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
            reloadCreatedCitizensTable();
            clearTextFieldCreate();
            reloadCitizenTable();
        } else {
            ErrorHandlerController.createWarning("Fejl", "Du skal udfylde alle borgerens informationer");
        }

    }

    /**
     * Clear text fields when creating a citizen
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
            ErrorHandlerController.createWarning("Fejl", "Du skal udfylde alle informationer på den studerende");
        }
    }

    /**
     * Action Event for the edit student button. Fills all textFields, with data from the selected student.
     * Edits the student using the ID.
     */
    @FXML
    private void btnHandleEditStudent() {
        setSelectedStudent(selectedStudent);
        btnEditStudent.setVisible(false);
        btnEditSave.setVisible(true);
        btnEditCancel.setVisible(true);
        btnDeleteStudent.setVisible(false);
        btnSaveStudent.setDisable(true);
    }

    /**
     * Edits a student when selected, and check if the username is already i the database
     * @throws Exception
     */
    @FXML
    private void btnHandleEditSave() throws Exception {
        if (this.selectedStudent != null) {
            String userNames = String.valueOf(dataModelFacade.getUsernames());
            if (userNames.contains(String.valueOf(txtFieldUsername.getText()))) {
                ErrorHandlerController.createWarning("Fejl", "Brugernavn er allerede taget");
            } else if (!txtFieldFirstName.getText().isEmpty() && !txtFieldLastName.getText().isEmpty() && !txtFieldUsername.getText().isEmpty() && !txtFieldPassword.getText().isEmpty()) {
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
                ErrorHandlerController.createWarning("Fejl", "Du skal udfylde alle informationer på den studerende");
            }
        }
    }

    /**
     * Deletes a citizen when selected and the alert box is checked
     * @throws Exception
     */
    @FXML
    private void btnHandleDeleteCitizen() throws Exception {
        if (selectedCreatedCitizen != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Advarsel");
            alert.setHeaderText("Advarsel før du sletter en borger");
            alert.setContentText("Du skal slette alle tager fra en borger, før du kan slette borgeren");
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
     * Clears the selection and text fields cleared when edit cancel is pressed
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
     * Clears all textFields in the student view
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
            alert.setContentText("Denne handling kan ikke fortrydes");
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
     * Selects a citizen from the citizens TableView and when clicked two times on a citizen, then the student view opens with the selected citizens credentials
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
                    studentViewController.initializeTables();
                    studentViewController.initializeCitizenComboBox();
                    studentViewController.setCitizenComboBoxItems(tvCreatedCitizens.getSelectionModel().getSelectedItem());

                    viewCitizenStage.setResizable(false);
                    viewCitizenStage.show();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Selects a citizen from the citizens TableView and when clicked two times on a citizen, then the student view opens with the selected citizens credentials
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
     * Makes you able to select a student from the table when clicked two times on a case, then the case view opens with the selected cases information
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
     * Selects a vase on a citizen
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
     * Reloads the citizen table
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
     * Reloads the current cases table
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
     * Creates a case and give it a date
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
            ErrorHandlerController.createWarning("Fejl", "Du skal give sagen et navn og noget informationer, før du kan oprette den");
        }
    }

    /**
     * Assigns a date when a case is created
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
     * Deletes a case when selected and the alert box is answered
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
     * Makes the different buttons user-friendly
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
     * Edits a selected case
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
                ErrorHandlerController.createWarning("Fejl", "Du skal give sagen et navn og noget informationer");
            }
        }
    }

    /**
     * Clear the different text field
     */
    @FXML
    private void btnHandleEditCaseCancel() {
        reloadCaseTable();
        txtFieldName.clear();
        txtAreaInfo.clear();
        setCaseBtnVisibility();
    }

    /**
     * Copies a case by filling the text fields with the selected case, that you want to copy
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
     * Saves the informations
     * @throws Exception
     */
    @FXML
    private void btnHandleCopySave() throws Exception {
        if (selectedCase != null) {
            btnHandleSaveCase();
            setCaseBtnVisibility();
        } else {
            ErrorHandlerController.createWarning("Fejl", "Du skal vælge en sag først");
        }
    }

    /**
     * Sets the visibility on the buttons
     */
    public void setCaseBtnVisibility() {
        btnEditCase.setDisable(false);
        btnCopyCase.setDisable(false);
        btnSaveCase.setVisible(true);
        btnDeleteCase.setVisible(true);
        btnEditCaseSave.setVisible(false);
        btnEditCaseCancel.setVisible(false);
    }

    /**
     * Method for showing the right anchorPane when opening the program, sets the main view visible and not relevant
     * anchorPanes not visible
     */
    private void setAnchorPanesVisibility() {
        labelInfoNewLine.setText("");
        anchorPaneTeacher.setVisible(true);
        anchorPaneCreateCitizen.setVisible(false);
        anchorPaneStudent.setVisible(false);
        anchorPaneCitizen.setVisible(false);
        anchorPaneCase.setVisible(false);
    }

    /**
     * Loads the student overview anchorPane when clicked.
     */
    @FXML
    private void btnClickStudent() {
        labelTitle.setText("Elever");
        labelInfo.setText("Overblik over alle oprettede elever");
        labelInfoNewLine.setText("");
        anchorPaneCreateCitizen.setVisible(false);
        anchorPaneTeacher.setVisible(false);
        anchorPaneCitizen.setVisible(false);
        anchorPaneCase.setVisible(false);
        anchorPaneStudent.setVisible(true);
    }

    /**
     * Loads the cases overview anchorPane when clicked.
     */
    @FXML
    private void btnClickCase() {
        labelTitle.setText("Sager");
        labelInfo.setText("Overblik over alle oprettede sager. Opret nye sager, eller og kopier sager");
        labelInfoNewLine.setText("");
        anchorPaneCreateCitizen.setVisible(false);
        anchorPaneTeacher.setVisible(false);
        anchorPaneStudent.setVisible(false);
        anchorPaneCase.setVisible(true);
        anchorPaneCitizen.setVisible(false);
    }

    /**
     * Loads the citizen overview anchorPane when clicked.
     */
    @FXML
    private void btnClickSeeCitizens() {
        labelTitle.setText("Borgere");
        labelInfo.setText("Overblik over alle oprettede borgere. Tildel en sag til en borger, se yderligere informationer,");
        labelInfoNewLine.setText("rediger eller slet borger.");
        anchorPaneCreateCitizen.setVisible(false);
        anchorPaneTeacher.setVisible(false);
        anchorPaneStudent.setVisible(false);
        anchorPaneCase.setVisible(false);
        anchorPaneCitizen.setVisible(true);
        reloadCaseTable();
    }

    /**
     * Loads the createCitizen anchorPane when clicked.
     */
    @FXML
    private void btnClickCitizen() {
        labelTitle.setText("Opret Borger");
        labelInfo.setText("Oprettelses vindue til borger");
        labelInfoNewLine.setText("");
        anchorPaneTeacher.setVisible(false);
        anchorPaneStudent.setVisible(false);
        anchorPaneCase.setVisible(false);
        anchorPaneCreateCitizen.setVisible(true);
        anchorPaneCitizen.setVisible(false);
    }

    /**
     * Action event for home button, that loads the main home screen.
     */
    @FXML
    private void btnClickHome() {
        labelTitle.setText("Lærer");
        labelInfo.setText("Logget ind som lærer");
        labelInfoNewLine.setText("");
        anchorPaneTeacher.setVisible(true);
        anchorPaneStudent.setVisible(false);
        anchorPaneCase.setVisible(false);
        anchorPaneCitizen.setVisible(false);
        anchorPaneCreateCitizen.setVisible(false);
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
     * Method that filters the events, with the text input you write in the textfield.
     * Updates the icon with each press and clears the search on every second click
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
