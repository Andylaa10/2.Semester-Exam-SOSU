package gui.controller;

import be.Case;
import be.Citizen;
import be.User;
import be.enums.UserType;
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
    private AnchorPane topPane;
    @FXML
    private Button btnStudent;
    @FXML
    private Button btnCitizens;
    @FXML
    private Button btnCreateCitizen;
    @FXML
    private Button btnSaveCitizen;
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
    private AnchorPane anchorPaneCitizen;
    @FXML
    private AnchorPane anchorPaneCreateCitizen;
    @FXML
    private AnchorPane anchorPaneTeacher;
    @FXML
    private AnchorPane anchorPaneCase;
    @FXML
    private AnchorPane anchorPaneStudent;

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
    private TableColumn<User, String> tcStudentPassword;
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
    private Button btnAssignCase;
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

    private Case selectedCase;
    private Case selectedCaseOnCitizen;
    private Case selectedCurrentCase;
    private User selectedStudent;
    private Citizen selectedCitizen;
    private Citizen selectedCreatedCitizen;

    private DataModelFacade dataModelFacade;
    private StudentViewController studentViewController;

    public TeacherViewController() throws IOException {
        this.dataModelFacade = new DataModelFacade();
        this.studentViewController = new StudentViewController();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setToggleGroup();
        initializeTable();
        setAnchorPanesVisibility();
        selectedStudent();
        selectedCurrentCase();
        selectedCitizen();
        selectedCreatedCitizen();
        selectedCase();
        selectedCaseOnCitizen();
    }

    public void setToggleGroup() {
        ToggleGroup group = new ToggleGroup();
        radioMale.setToggleGroup(group);
        radioFemale.setToggleGroup(group);
        radioOther.setToggleGroup(group);
    }

    private void initializeTable() {
        //Initialize the students table
        tcStudentFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        tcStudentLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tcStudentUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        try {
            allStudents = FXCollections.observableList(dataModelFacade.getStudents());
            tableViewLoadStudents(allStudents);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Initialize the cases table
        tcCasesName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tcCasesDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        try {
            allCases = FXCollections.observableList(dataModelFacade.getCases());
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
            allCitizens = FXCollections.observableList(dataModelFacade.getCitizens());
            tableViewLoadCitizens(allCitizens);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Initialize the current cases table at citizen window
        tcCurrentCasesID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcCurrentCasesName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tcCurrentCasesDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        try {
            allCurrentCases = FXCollections.observableList(dataModelFacade.getCases());
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
            allCreatedCitizens = FXCollections.observableList(dataModelFacade.getCitizens());
            tableViewLoadCreatedCitizens(allCreatedCitizens);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * loads the students tableview.
     *
     * @param allStudents
     */
    private void tableViewLoadStudents(ObservableList<User> allStudents) {
        tvStudent.setItems(getStudentData());
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
     *
     * @param allCases
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
     *
     * @param allCitizens
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
     * loads the Citizens table view
     *
     * @param allCreatedCitizens
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
     * loads the CurrentCases tableview.
     *
     * @param allCurrentCases
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
     *
     * @param allCasesOnCitizen
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
     * Method for showing the right anchorpane when opening the program, sets the main view visible and not relevant
     * anchorpanes not visible
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
     * Loads the student overview anchorpane when clicked.
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
     * Loads the cases overview anchorpane when clicked.
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
     * Loads the citizen overview anchorpane when clicked.
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
     * Loads the create citizen anchorpane when clicked.
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
     *
     * @param
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
     *
     * @param
     * @throws IOException
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
     * Sets text labels with the user that has logged in.
     *
     * @param user
     * @throws SQLException
     * @throws IOException
     */
    @Override
    public void setUser(User user) throws SQLException, IOException {
        labelTitle.setText("Lærer");
        labelInfo.setText("Du er nu logget ind som lærer: " + user.getFirstName() + user.getLastName());
        labelInfoNewLine.setText("");
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

    @FXML
    private void btnHandleSaveCitizen() throws SQLException {
        if (!txtFieldCitizenFirstName.getText().isEmpty() && !txtFieldCitizenLastName.getText().isEmpty() && !txtFieldCitizenSSN.getText().isEmpty() && !txtFieldCitizenAddress.getText().isEmpty()) {
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
            String coping = "";
            String motivation = "";
            String resources = "";
            String roles = "";
            String habits = "";
            String educationandjob = "";
            String lifeStory = "";
            String network = "";
            String healthInformation = "";
            String equipmentAids = "";
            String homeLayout = "";
            dataModelFacade.createCitizen(firstName, lastName, SSN, address, sex);
            dataModelFacade.createGeneralInformation(coping, motivation, resources, roles, habits, educationandjob,
                    lifeStory, network, healthInformation, equipmentAids, homeLayout);
            reloadCreatedCitizensTable();
            reloadCitizenTable();
            clearTextFieldCreate();
        } else {
            ErrorHandlerController.createWarning("Fejl", "Du skal udfylde alle tekstfelter");
        }

    }

    public void clearTextFieldCreate() {
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
            this.tvCreatedCitizens.setItems(FXCollections.observableList(dataModelFacade.getCitizens()));
            tvCreatedCitizens.getSelectionModel().select(index);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Action event for save student button. Gets the text from all the textFields and creates a new student when pressed.
     *
     * @throws SQLException
     */
    @FXML
    private void btnHandleSaveStudent() throws SQLException {
        if (!txtFieldFirstName.getText().isEmpty() && !txtFieldLastName.getText().isEmpty() && !txtFieldUsername.getText().isEmpty() && !txtFieldPassword.getText().isEmpty()) {
            String firstName = txtFieldFirstName.getText();
            String lastName = txtFieldLastName.getText();
            String userName = txtFieldUsername.getText();
            String password = txtFieldPassword.getText();

            dataModelFacade.createStudent(firstName, lastName, userName, password, UserType.STUDENT);
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
     *
     * @throws Exception
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

    @FXML
    private void btnHandleEditSave() throws Exception {
        if (this.selectedStudent != null) {
            if (!txtFieldFirstName.getText().isEmpty() && !txtFieldLastName.getText().isEmpty() && !txtFieldUsername.getText().isEmpty() && !txtFieldPassword.getText().isEmpty()) {
                int id = Integer.parseInt(txtFieldStudentID.getText());
                String firstName = txtFieldFirstName.getText();
                String lastName = txtFieldLastName.getText();
                String userName = txtFieldUsername.getText();
                String password = txtFieldPassword.getText();

                User student = new User(id, firstName, lastName, userName, password, UserType.STUDENT);
                dataModelFacade.editStudent(student);
                reloadStudentTable();
                clearStudentTxtField();
                tvStudent.getSelectionModel().clearSelection();
                btnSaveStudent.setDisable(false);
                btnEditCancel.setVisible(false);
                btnEditStudent.setVisible(true);
                btnEditSave.setVisible(false);
                btnDeleteStudent.setVisible(true);
            } else {
                ErrorHandlerController.createWarning("Fejl", "Du skal udfylde alle informationer på den studerende");
            }
        }
    }

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
                reloadCitizenTable();
            }
        } else {
            ErrorHandlerController.createWarning("Fejl", "Du skal vælge en borger først");
        }

    }

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
     *
     * @throws SQLException
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
            if ((User) newValue != null) {
                this.selectedStudent = (User) newValue;
            }
        }));
    }

    /**
     * Selects a case from the currentCase tableView
     */
    private void selectedCurrentCase() {
        this.tvCurrentCases.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            if ((Case) newValue != null) {
                this.selectedCurrentCase = (Case) newValue;
            }
        }));
    }


    /**
     * Selects a citizen from the citizens TableView
     */
    private void selectedCitizen() {
        this.tvCitizens.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            if ((Citizen) newValue != null) {
                this.selectedCitizen = (Citizen) newValue;
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

                    studentViewController = fxmlLoader.getController();
                    studentViewController.btnClickGeneralInformation();
                    studentViewController.setGeneralInfoFromID(String.valueOf(tvCitizens.getSelectionModel().getSelectedItem().getId()));

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
     */
    private void selectedCreatedCitizen() {
        this.tvCreatedCitizens.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            if ((Citizen) newValue != null) {
                this.selectedCreatedCitizen = (Citizen) newValue;
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

                    studentViewController = fxmlLoader.getController();
                    studentViewController.btnClickGeneralInformation();
                    studentViewController.setGeneralInfoFromID(String.valueOf(tvCreatedCitizens.getSelectionModel().getSelectedItem().getId()));

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
     *
     * @param student
     */
    private void setSelectedStudent(User student) {
        txtFieldStudentID.setText(String.valueOf(student.getId()));
        txtFieldFirstName.setText(student.getFirstName());
        txtFieldLastName.setText(student.getLastName());
        txtFieldUsername.setText(student.getUsername());
        txtFieldPassword.setText(student.getPassword());
    }

    /**
     * Reloads the student table
     */
    private void reloadStudentTable() {
        try {
            int index = tvStudent.getSelectionModel().getFocusedIndex();
            this.tvStudent.setItems(FXCollections.observableList(dataModelFacade.getStudents()));
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
     * Makes you able to select a student from the table
     */
    private void selectedCase() {
        this.tvCases.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            if ((Case) newValue != null) {
                this.selectedCase = (Case) newValue;
            }
        }));
    }

    private void selectedCaseOnCitizen() {
        this.tvCasesOnCitizen.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            if ((Case) newValue != null) {
                this.selectedCaseOnCitizen = (Case) newValue;
            }
        }));
    }

    /**
     * Reloads the student table
     */
    private void reloadCaseTable() {
        try {
            int index = tvCases.getSelectionModel().getFocusedIndex();
            this.tvCases.setItems(FXCollections.observableList(dataModelFacade.getCases()));
            tvCases.getSelectionModel().select(index);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void reloadCitizenTable() {
        try {
            int index = tvCitizens.getSelectionModel().getFocusedIndex();
            this.tvCitizens.setItems(FXCollections.observableList(dataModelFacade.getCitizens()));
            tvCitizens.getSelectionModel().select(index);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void reloadCurrentCasesTable() {
        try {
            int index = tvCurrentCases.getSelectionModel().getFocusedIndex();
            this.tvCurrentCases.setItems(FXCollections.observableList(dataModelFacade.getCases()));
            tvCurrentCases.getSelectionModel().select(index);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Sets the selected event
     *
     * @param aCase
     */
    private void setSelectedCase(Case aCase) {
        txtFieldCaseID.setText(String.valueOf(aCase.getId()));
        txtFieldName.setText(aCase.getName());
        txtAreaInfo.setText(aCase.getInfo());
    }


    @FXML
    private void btnHandleSaveCase() throws Exception {
        if (!txtFieldName.getText().isEmpty() && !txtAreaInfo.getText().isEmpty()) {
            String name = txtFieldName.getText();
            String area = txtAreaInfo.getText();
            allCases.add(dataModelFacade.createCase(name, area));
            assignDate();
            reloadCaseTable();
            reloadCurrentCasesTable();
            txtFieldName.clear();
            txtAreaInfo.clear();
        } else {
            ErrorHandlerController.createWarning("Fejl", "Du skal give sagen et navn og noget informationer, før du kan oprette den");
        }
    }

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

    @FXML
    private void btnHandleEditCaseCancel() {
        reloadCaseTable();
        txtFieldName.clear();
        txtAreaInfo.clear();
        setCaseBtnVisibility();
    }

    @FXML
    private void btnHandleCopyCase() throws Exception {
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

    public void btnHandleCopySave() throws Exception {
        if (selectedCase != null) {
            btnHandleSaveCase();
            setCaseBtnVisibility();
        } else {
            ErrorHandlerController.createWarning("Fejl", "Du skal vælge en sag først");
        }
    }

    public void setCaseBtnVisibility() {
        btnEditCase.setDisable(false);
        btnCopyCase.setDisable(false);
        btnSaveCase.setVisible(true);
        btnDeleteCase.setVisible(true);
        btnEditCaseSave.setVisible(false);
        btnEditCaseCancel.setVisible(false);
    }


}
