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
    private Button btnCreateCitizen;
    @FXML
    private Button btnCreateTeacherPane;
    @FXML
    private Button btnStudentPane;
    @FXML
    private Button btnCasePane;
    @FXML
    private Button btnCitizensPane;
    @FXML
    private Button btnCreateCitizenPane;
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
    private AnchorPane topPane;
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
    private Button btnCopyTeacher;
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
    private ObservableList<User> allTeachers = FXCollections.observableArrayList();

    private Case selectedCase;
    private Case selectedCaseOnCitizen;
    private Case selectedCurrentCase;
    private User selectedStudent;
    private User selectedTeacher;
    private Citizen selectedCitizen;
    private Citizen selectedCreatedCitizen;

    private DataModelFacade dataModelFacade;

    public AdminViewController() throws IOException {
        this.dataModelFacade = new DataModelFacade();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setToggleGroup();
        setAnchorPanesVisibility();
        initializeTable();
        setAnchorPanesVisibility();
        selectedStudent();
        selectedCurrentCase();
        selectedCitizen();
        selectedCreatedCitizen();
        selectedCase();
        selectedCaseOnCitizen();
    }

    private void initializeTable() {
        //Initialize the teacher table
        tcTeacherFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        tcTeacherLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tcTeacherUserName.setCellValueFactory(new PropertyValueFactory<>("username"));
        try {
            allTeachers = FXCollections.observableList(dataModelFacade.getTeachers());
            tableViewLoadTeachers(allTeachers);
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public void setToggleGroup(){
        ToggleGroup group = new ToggleGroup();
        radioMale.setToggleGroup(group);
        radioFemale.setToggleGroup(group);
        radioOther.setToggleGroup(group);
    }

    /**
     * loads the teacher view
     * @param allTeachers
     */
    private void tableViewLoadTeachers(ObservableList<User> allTeachers) {
        tvTeachers.setItems(getTeacherData());
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
     * gets the data for teachers
     * @return
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
     *
     * @param
     * @throws IOException
     */
    @FXML
    private void btnClickLogout( ) throws IOException {
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
        labelTitle.setText("Admin");
        labelInfo.setText("Du er nu logget ind som admin: " + user.getFirstName() + user.getLastName());
        labelInfoNewLine.setText("");
    }

    @FXML
    private void onActionCreateTeacher() throws SQLException {
        if (!txtFieldTeacherFirstName.getText().isEmpty() && !txtFieldTeacherLastName.getText().isEmpty() && !txtFieldTeacherUsername.getText().isEmpty() && !txtFieldTeacherPassword.getText().isEmpty()) {
            String firstName = txtFieldTeacherFirstName.getText();
            String lastName = txtFieldTeacherLastName.getText();
            String userName = txtFieldTeacherUsername.getText();
            String password = txtFieldTeacherPassword.getText();

            dataModelFacade.createTeacher(firstName, lastName, userName, password, UserType.TEACHER);
            reloadTeacherTable();
        } else {
            System.out.println("Couldn't create teacher");
            //TODO Make proper errorhandler
        }
    }

    @FXML
    private void onActionEditTeacher(){
        setSelectedTeacher(selectedTeacher);
        btnCopyTeacher.setDisable(true);
        btnDeleteTeacher.setDisable(true);
        btnEditTeacher.setDisable(true);
        btnCreateTeacher.setVisible(false);
        btnEditTeacherSave.setVisible(true);
        btnEditTeacherCancel.setVisible(true);
    }

    public void onActionEditTeacherSave() throws Exception {
        if (this.selectedTeacher != null) {
            if (!txtFieldTeacherFirstName.getText().isEmpty() && !txtFieldTeacherLastName.getText().isEmpty() && !txtFieldTeacherUsername.getText().isEmpty() && !txtFieldTeacherPassword.getText().isEmpty()) {
                int id = Integer.parseInt(txtFieldTeacherID.getText());

                String firstName = txtFieldTeacherFirstName.getText();
                String lastName = txtFieldTeacherLastName.getText();
                String userName = txtFieldTeacherUsername.getText();
                String password = txtFieldTeacherPassword.getText();

                User teacher = new User(id, firstName, lastName, userName, password, UserType.TEACHER);
                dataModelFacade.editTeacher(teacher);
                reloadTeacherTable();
                clearTeacherTxtField();
                tvTeachers.getSelectionModel().clearSelection();
                btnCopyTeacher.setDisable(false);
                btnDeleteTeacher.setDisable(false);
                btnEditTeacher.setDisable(false);
                btnCreateTeacher.setVisible(true);
                btnEditTeacherSave.setVisible(false);
                btnEditTeacherCancel.setVisible(false);
            } else {
                System.out.println("Probably didn't select a teacher");
                //TODO errorhandler
            }
        }
    }

    @FXML
    private void onActionDeleteTeacher() throws SQLException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("WARNING MESSAGE");
        alert.setHeaderText("Warning before you delete teacher");
        alert.setContentText("Joe");
        if (selectedTeacher != null) {
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                selectedTeacher();
                dataModelFacade.deleteTeacher(selectedTeacher.getId(), UserType.TEACHER);
                reloadTeacherTable();
            }
        } else {
            return;
        }
    }

    public void onActionCopyTeacher(ActionEvent actionEvent) {
    }

    public void onActionEditTeacherCancel(ActionEvent actionEvent) {
    }

    /**
     * Makes you able to select a teacher from the table
     *
     * @param
     */
    private void selectedTeacher() {
        this.tvTeachers.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            if ((User) newValue != null) {
                this.selectedTeacher = (User) newValue;
            }
        }));
    }


    public void setSelectedTeacher(User teacher) {
        txtFieldTeacherID.setText(String.valueOf(teacher.getId()));
        txtFieldTeacherFirstName.setText(teacher.getFirstName());
        txtFieldTeacherLastName.setText(teacher.getLastName());
        txtFieldTeacherUsername.setText(teacher.getUsername());
        txtFieldTeacherPassword.setText(teacher.getPassword());
    }
    /**
     * Reloads the teacher table
     */
    private void reloadTeacherTable() {
        try {
            int index = tvTeachers.getSelectionModel().getFocusedIndex();
            this.tvTeachers.setItems(FXCollections.observableList(dataModelFacade.getTeachers()));
            tvTeachers.getSelectionModel().select(index);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
    public void clearTeacherTxtField() {
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
            System.out.println("Something went wrong");
            //TODO add errorHandler
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
            System.out.println("Something went wrong");
            //TODO add errorHandler
        }
    }

    @FXML
    private void btnHandleSaveCitizen() throws SQLException {
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
        dataModelFacade.createCitizen(firstName, lastName, SSN, address, sex);
        clearTextFieldCreate();
        reloadCreatedCitizensTable();
    }

    public void clearTextFieldCreate(){
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
            System.out.println("NOOO");
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
                System.out.println("Noo");
            }
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
            System.out.println("NOOO");
        }
    }

    private void assignDate() throws Exception {
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

    @FXML
    private void btnHandleDeleteCase() throws Exception {
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
                reloadCurrentCasesTable();
            }
        } else {
            return;
        }
    }

    @FXML
    private void btnHandleEditCase() {
        setSelectedCase(selectedCase);
        btnEditCase.setDisable(true);
        btnCopyCase.setDisable(true);
        btnSaveCase.setVisible(false);
        btnDeleteCase.setVisible(false);
        btnEditCaseSave.setVisible(true);
        btnEditCaseCancel.setVisible(true);
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
                System.out.println("Something went wrong");
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
        }

    }

    public void btnHandleCopySave() throws Exception {
        if (selectedCase != null){
            btnHandleSaveCase();
            setCaseBtnVisibility();
        }
    }

    public void setCaseBtnVisibility(){
        btnEditCase.setDisable(false);
        btnCopyCase.setDisable(false);
        btnSaveCase.setVisible(true);
        btnDeleteCase.setVisible(true);
        btnEditCaseSave.setVisible(false);
        btnEditCaseCancel.setVisible(false);
    }
}
