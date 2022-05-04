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


    /**
     * Create teacher pane
     */
    @FXML
    private AnchorPane anchorPaneCreateTeacher;
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
     * Citizen pane
     */
    @FXML
    private TableView<Case> tvCurrentCases;
    @FXML
    private TableView<Citizen> tvCurrentCitizens;
    @FXML
    private TableView<Case> tvCasesOnCitizens;
    @FXML
    private TableColumn<Case, String> tcCurrentCaseName;
    @FXML
    private TableColumn<Case, String> tcCurrentCaseDate;
    @FXML
    private TableColumn<Citizen, String> tcCurrentCitizenFirstName;
    @FXML
    private TableColumn<Citizen, String> tcCurrentCitizenLastName;
    @FXML
    private TableColumn<Citizen, String> tcCurrentCitizenSSN;
    @FXML
    private TableColumn<Case, String> tcCaseOnCitizenID;
    @FXML
    private TableColumn<Case, String> tcCaseOnCitizenName;
    @FXML
    private TableColumn<Case, String> tcCaseOnCitizenDate;
    @FXML
    private TableColumn<Case, String> tcCaseOnCitizenInfo;
    @FXML
    private Button btnDeleteCaseFromCitizen;
    @FXML
    private Button btnAssignCaseToCitizen;
    @FXML
    private AnchorPane anchorPaneCitizen;

    /**
     * Case pane
     */
    @FXML
    private TableView<Case> tvCases;
    @FXML
    private TableColumn<Case, String> tcCaseName;
    @FXML
    private TableColumn<Case, String> tcCaseDate;
    @FXML
    private Button btnSaveCase;
    @FXML
    private Button btnEditCase;
    @FXML
    private Button btnDeleteCase;
    @FXML
    private Button btnCopyCase;
    @FXML
    private TextField txtFieldCaseName;
    @FXML
    private TextField txtFieldCaseDate;
    @FXML
    private TextArea txtAreaCaseInfo;
    @FXML
    private AnchorPane anchorPaneCase;

    /**
     * Student pane
     */
    @FXML
    private TableView<User> tvStudents;
    @FXML
    private TableColumn<User, String> tcStudentFirstName;
    @FXML
    private TableColumn<User, String> tcStudentLastName;
    @FXML
    private TableColumn<User, String> tcStudentUserName;
    @FXML
    private TextField txtFieldStudentID;
    @FXML
    private TextField txtFieldStudentFirstName;
    @FXML
    private TextField txtFieldStudentLastname;
    @FXML
    private TextField txtFieldStudentUsername;
    @FXML
    private TextField txtFieldStudentPassword;
    @FXML
    private Button btnCreateStudent;
    @FXML
    private Button btnEditStudent;
    @FXML
    private Button btnDeleteStudent;
    @FXML
    private Button btnEditStudentSave;
    @FXML
    private Button btnEditStudentCancel;
    @FXML
    private AnchorPane anchorPaneStudent;

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
    private CheckBox checkBoxMale;
    @FXML
    private CheckBox checkBoxFemale;
    @FXML
    private CheckBox checkBoxOther;
    @FXML
    private TextArea txtAreaGeneralInformation;
    @FXML
    private AnchorPane anchorPaneCreateCitizen;

    private ObservableList<User> allStudents = FXCollections.observableArrayList();
    private ObservableList<User> allTeachers = FXCollections.observableArrayList();
    private ObservableList<Citizen> allCitizens = FXCollections.observableArrayList();
    private ObservableList<Case> allCasesOnCitizens = FXCollections.observableArrayList();
    private ObservableList<Case> allCases = FXCollections.observableArrayList();
    private ObservableList<Case> allCurrentCases = FXCollections.observableArrayList();

    private DataModelFacade dataModelFacade;

    private User selectedTeacher;
    private User selectedStudent;
    private Case selectedCurrentCase;
    private Citizen selectedCitizen;
    private Case selectedCaseOnCitizen;

    public AdminViewController() throws IOException {
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
        selectedTeacher();
        selectedStudent();
        selectedCurrentCase();
        selectedCaseOnCitizen();
        selectedCitizen();
    }

    private void initializeTables() throws Exception {
        //Initialize the teachers table
        tcTeacherFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        tcTeacherLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tcTeacherUserName.setCellValueFactory(new PropertyValueFactory<>("username"));
        try {
            allTeachers = FXCollections.observableList(dataModelFacade.getTeachers());
            tableViewLoadTeachers(allTeachers);
        } catch (Exception e) {
            throw new Exception();
        }

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
        tcCurrentCitizenSSN.setCellValueFactory(new PropertyValueFactory<>("SSN"));
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

    private void tableViewLoadTeachers(ObservableList<User> allTeachers) {
        tvTeachers.setItems(getTeacherData());
    }

    private ObservableList<User> getTeacherData() {
        return allTeachers;
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

    /**
     * loads the citizensOnCase tableview.
     *
     * @param
     */
    private void tableViewLoadCasesOnCitizen(ObservableList<Case> allCasesOnCitizen) {
        tvCasesOnCitizens.setItems(getCasesOnCitizensData());
    }

    /**
     * Get the data for citizensOnCase
     *
     * @return ObservableList<Citizen>
     */
    private ObservableList<Case> getCasesOnCitizensData() {
        return allCasesOnCitizens;
    }


    @FXML
    private void onActionSaveCase() {
    }

    @FXML
    private void OnActionEditCase() {
    }

    @FXML
    private void onActionDeleteCase() {
    }

    @FXML
    private void OnActionCopyCase() {
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

    public void onActionEditTeacherCancel() {
        reloadTeacherTable();
        clearTeacherTxtField();
        tvTeachers.getSelectionModel().clearSelection();
        btnCopyTeacher.setDisable(false);
        btnDeleteTeacher.setDisable(false);
        btnEditTeacher.setDisable(false);
        btnCreateTeacher.setVisible(true);
        btnEditTeacherSave.setVisible(false);
        btnEditTeacherCancel.setVisible(false);
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
     * Selects a citizen from the citizensOnCase tableview
     */
    private void selectedCaseOnCitizen() {
        this.tvCasesOnCitizens.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            if ((Case) newValue != null) {
                this.selectedCaseOnCitizen = (Case) newValue;
            }
        }));

    }

    /**
     * Selects a citizen from the citizens TableView
     */
    private void selectedCitizen() {
        this.tvCurrentCitizens.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            if ((Citizen) newValue != null) {
                this.selectedCitizen = (Citizen) newValue;
                seeCasesOnCitizen();
            }
        }));

        this.tvCurrentCitizens.setOnMouseClicked(event -> {
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
     * Loads all data in tableview, from the selected current case ID.
     */
    public void seeCasesOnCitizen() {
        //Initialize the citizens on cases table at citizen window
        tcCaseOnCitizenID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcCaseOnCitizenName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tcCaseOnCitizenDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tcCaseOnCitizenInfo.setCellValueFactory(new PropertyValueFactory<>("info"));
        try {
            allCasesOnCitizens = FXCollections.observableList(dataModelFacade.getCasesOnCitizen(selectedCitizen.getId()));
            tableViewLoadCasesOnCitizen(allCasesOnCitizens);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void onActionCopyTeacher() {
    }

    @FXML
    private void onActionCreateCitizen() {
    }

    @FXML
    private void onActionAssignCaseToCitizen() {
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
        if (selectedCurrentCase != null && selectedCaseOnCitizen != null) {
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
    private void onActionCreateStudent() throws SQLException {
        if (!txtFieldStudentFirstName.getText().isEmpty() && !txtFieldStudentLastname.getText().isEmpty() && !txtFieldStudentUsername.getText().isEmpty() && !txtFieldStudentPassword.getText().isEmpty()) {
            String firstName = txtFieldStudentFirstName.getText();
            String lastName = txtFieldStudentLastname.getText();
            String userName = txtFieldStudentUsername.getText();
            String password = txtFieldStudentPassword.getText();

            dataModelFacade.createStudent(firstName, lastName, userName, password, UserType.STUDENT);
            reloadStudentTable();
        } else {
            System.out.println("Something went wrong with creation");
            //TODO make errorhandler
        }
    }

    @FXML
    private void onActionEditStudent()  {
        setSelectedStudent(selectedStudent);
        btnEditStudent.setVisible(false);
        btnEditStudentSave.setVisible(true);
        btnEditStudentCancel.setVisible(true);
        btnDeleteStudent.setVisible(false);
        btnCreateStudent.setDisable(true);
    }

    public void onActionEditStudentSave() throws Exception {
        if (this.selectedStudent != null) {
            if (!txtFieldStudentFirstName.getText().isEmpty() && !txtFieldStudentLastname.getText().isEmpty() && !txtFieldStudentFirstName.getText().isEmpty() && !txtFieldStudentPassword.getText().isEmpty()) {
                int id = Integer.parseInt(txtFieldStudentID.getText());
                String firstName = txtFieldStudentFirstName.getText();
                String lastName = txtFieldStudentLastname.getText();
                String userName = txtFieldStudentUsername.getText();
                String password = txtFieldStudentPassword.getText();

                User student = new User(id, firstName, lastName, userName, password, UserType.STUDENT);
                dataModelFacade.editStudent(student);
                reloadStudentTable();
                clearStudentTxtField();
                tvStudents.getSelectionModel().clearSelection();
                btnEditStudent.setVisible(true);
                btnEditStudentSave.setVisible(false);
                btnEditStudentCancel.setVisible(false);
                btnDeleteStudent.setVisible(true);
                btnCreateStudent.setDisable(false);
            } else {
                System.out.println("Something went wrong");
                //TODO make errorhandler
            }
        }
    }

    public void onActionEditStudentCancel() {
        reloadStudentTable();
        clearStudentTxtField();
        tvStudents.getSelectionModel().clearSelection();
        btnEditStudent.setVisible(true);
        btnEditStudentSave.setVisible(false);
        btnEditStudentCancel.setVisible(false);
        btnDeleteStudent.setVisible(true);
        btnCreateStudent.setDisable(false);
    }

    @FXML
    private void onActionDeleteStudent() throws SQLException {
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

    /**
     * Makes you able to select a student from the table
     */
    private void selectedStudent() {
        this.tvStudents.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            if ((User) newValue != null) {
                this.selectedStudent = (User) newValue;
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

    public void setSelectedStudent(User student) {
        txtFieldStudentID.setText(String.valueOf(student.getId()));
        txtFieldStudentFirstName.setText(student.getFirstName());
        txtFieldStudentLastname.setText(student.getLastName());
        txtFieldStudentUsername.setText(student.getUsername());
        txtFieldStudentPassword.setText(student.getPassword());
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

    /**
     * Reloads the student table
     */
    private void reloadStudentTable() {
        try {
            int index = tvStudents.getSelectionModel().getFocusedIndex();
            this.tvStudents.setItems(FXCollections.observableList(dataModelFacade.getStudents()));
            tvStudents.getSelectionModel().select(index);
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

    public void clearStudentTxtField() {
        txtFieldStudentID.clear();
        txtFieldStudentFirstName.clear();
        txtFieldStudentLastname.clear();
        txtFieldStudentUsername.clear();
        txtFieldStudentPassword.clear();
    }

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
        labelTitle.setText("Admin");
        labelInfo.setText("Du er nu logget ind som Admin: " + user.getFirstName() + user.getLastName());
        labelInfoNewLine.setText("");
    }



}
