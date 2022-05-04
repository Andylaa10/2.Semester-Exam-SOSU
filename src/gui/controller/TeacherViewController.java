package gui.controller;

import be.Case;
import be.Citizen;
import be.User;
import be.enums.UserType;
import gui.Facade.DataModelFacade;
import gui.controller.Interface.IController;
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

    /**
     * Student pane
     */
    @FXML
    private AnchorPane anchorPaneStudent;
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
    private AnchorPane anchorPaneCase;
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

    /**
     * Create Citizen Pane
     */
    @FXML
    private TextField txtFieldCitizenFirstName;
    @FXML
    private TextField txtFieldCitizenLastName;
    @FXML
    private TextField txtFieldCitizenSSN;
    @FXML
    private TextField txtFieldCitizenAddress;
    @FXML
    private TextArea txtAreaCitizenGeneralInfo;
    @FXML
    private CheckBox checkBoxMale;
    @FXML
    private CheckBox checkBoxFemale;
    @FXML
    private CheckBox checkBoxOther;


    private ObservableList<User> allStudents = FXCollections.observableArrayList();
    private ObservableList<Citizen> allCitizens = FXCollections.observableArrayList();
    private ObservableList<Case> allCases = FXCollections.observableArrayList();
    private ObservableList<Case> allCurrentCases = FXCollections.observableArrayList();
    private ObservableList<Citizen> allCitizensOnCase = FXCollections.observableArrayList();
    private ObservableList<Case> allCasesOnCitizen = FXCollections.observableArrayList();

    private User selectedStudent;
    private Case selectedCase;
    private Citizen selectedCitizen;
    private Case selectedCurrentCase;

    private DataModelFacade dataModelFacade;

    public TeacherViewController() throws IOException {
        this.dataModelFacade = new DataModelFacade();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeTable();
        setAnchorPanesVisibility();
        selectedStudent();
        selectedCurrentCase();
        selectedCitizen();
        selectedCase();
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
     * loads the citizensOnCase tableview.
     *
     * @param allCitizensOnCase
     */
    private void tableViewLoadCasesOnCitizen(ObservableList<Case> allCitizensOnCase) {
        tvCasesOnCitizen.setItems(getCasesOnCitizenData());
    }

    /**
     * Get the data for citizensOnCase
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
    public void btnClickStudent() {
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
    public void btnClickCase() {
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
    public void btnClickSeeCitizens() {
        labelTitle.setText("Borgere");
        labelInfo.setText("Overblik over alle oprettede borgere. Tildel en sag til en borger, se yderligere informationer,");
        labelInfoNewLine.setText("rediger eller slet borger.");
        anchorPaneCreateCitizen.setVisible(false);
        anchorPaneTeacher.setVisible(false);
        anchorPaneStudent.setVisible(false);
        anchorPaneCase.setVisible(false);
        anchorPaneCitizen.setVisible(true);
    }

    /**
     * Loads the create citizen anchorpane when clicked.
     */
    public void btnClickCitizen() {
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
     * @param actionEvent
     */
    public void btnClickHome(ActionEvent actionEvent) {
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
     * @param actionEvent
     * @throws IOException
     */
    public void btnClickLogout(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/LoginView.fxml"));
        Scene scene = new Scene(loader.load());
        Stage switcher = (Stage) btnLogOut.getScene().getWindow();
        switcher.setScene(scene);
        switcher.setTitle("Lærer");
        switcher.show();
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
    public void btnHandleAssignCase() {
        if (selectedCurrentCase != null && selectedCitizen != null) {
            try {
                dataModelFacade.assignCaseToCitizen(selectedCurrentCase.getId(), selectedCitizen.getId());
                seeCasesOnCitizen();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Something went wrong");
            //TODO add errorHandler
        }
    }

    public void btnHandleSaveCitizen() throws SQLException {
        String firstName = txtFieldCitizenFirstName.getText();
        String lastName = txtFieldCitizenLastName.getText();
        String SSN = txtFieldCitizenSSN.getText();
        String address = txtFieldCitizenAddress.getText();
        String info = txtAreaCitizenGeneralInfo.getText();
        String sex = null;
        if (checkBoxMale.isSelected()) {
            sex = "Male";
        } else if (checkBoxFemale.isSelected()) {
            sex = "Female";
        } else if (checkBoxOther.isSelected()) {
            sex = "Other";
        }
        dataModelFacade.createCitizen(firstName, lastName, SSN, address, sex, info);
    }

    /**
     * Action event for save student button. Gets the text from all the textfields and creates a new student when pressed.
     *
     * @throws SQLException
     */
    public void btnHandleSaveStudent() throws SQLException {
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
            System.out.println("NOOO");
        }
    }

    /**
     * Action Event for the edit student button. Fills all textfields, with data from the selected student.
     * Edits the student using the ID.
     *
     * @throws Exception
     */

    public void btnHandleEditStudent() {
        setSelectedStudent(selectedStudent);
        btnEditStudent.setVisible(false);
        btnEditSave.setVisible(true);
        btnEditCancel.setVisible(true);
        btnDeleteStudent.setVisible(false);
        btnSaveStudent.setDisable(true);

    }

    public void btnHandleEditSave() throws Exception {
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
                System.out.println("Noo");
            }
        }
    }

    public void btnHandleEditCancel() {
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
    public void clearStudentTxtField() {
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
    public void btnHandleDeleteStudent() throws SQLException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("WARNING MESSAGE");
        alert.setHeaderText("Warning before you delete student");
        alert.setContentText("Joe");
        if (selectedStudent != null) {
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                selectedStudent();
                dataModelFacade.deleteStudent(selectedStudent.getId(), UserType.STUDENT);
                reloadStudentTable();
            }
        } else {
            return;
        }
    }

    /**
     * Selects a student from the student tableView
     */
    public void selectedStudent() {
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
                    FXMLLoader parent = new FXMLLoader(getClass().getResource("/gui/view/StudentView.fxml"));
                    Scene mainWindowScene = null;
                    try {
                        mainWindowScene = new Scene(parent.load());
                    } catch (IOException exception) {
                        exception.printStackTrace();
                    }
                    Stage viewCitizenStage;
                    viewCitizenStage = new Stage();
                    viewCitizenStage.setScene(mainWindowScene);
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
    public void setSelectedStudent(User student) {
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
    public void seeCasesOnCitizen() {
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
                setSelectedCase(newValue);
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

    /**
     * Sets the selected event
     *
     * @param aCase
     */
    public void setSelectedCase(Case aCase) {
        txtFieldName.setText(aCase.getName());
        txtAreaInfo.setText(aCase.getInfo());
    }

    //TODO self selection inc

    public void btnHandleSaveCase() throws Exception {
        if (!txtFieldName.getText().isEmpty() && !txtAreaInfo.getText().isEmpty()) {
            String name = txtFieldName.getText();
            String area = txtAreaInfo.getText();
            allCases.add(dataModelFacade.createCase(name, area));
            assignDate();
            reloadCaseTable();
            txtFieldName.clear();
            txtAreaInfo.clear();
        } else {
            System.out.println("NOOO");
        }
    }

    public void assignDate() throws Exception {
        selectedCase = allCases.get(allCases.size() -1);
        if (selectedCase != null) {
            Date caseDate = new Date(System.currentTimeMillis());
            String pattern = "dd/MM/yyyy  HH:mm:ss";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            String date = simpleDateFormat.format(caseDate);
            selectedCase.setDate(date);
            dataModelFacade.editCase(selectedCase);
        }
    }

    public void btnHandleDeleteCase() throws Exception {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("WARNING MESSAGE");
        alert.setHeaderText("Warning before you delete case");
        alert.setContentText("Joe");
        if (selectedCase != null) {
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                selectedCase();
                dataModelFacade.deleteCase(selectedCase.getId());
                reloadCaseTable();
            }
        } else {
            return;
        }
    }

    public void btnHandleEditCase() {
    }

    public void btnHandleCopyCase() throws Exception {
        btnHandleSaveCase();
    }
}
