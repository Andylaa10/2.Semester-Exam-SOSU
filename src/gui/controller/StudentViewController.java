package gui.controller;

import be.*;
import com.microsoft.sqlserver.jdbc.SQLServerException;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class StudentViewController implements IController, Initializable {


    //Generel info on Citizen pane
    @FXML
    private TextField txtFieldFirstName;
    @FXML
    private TextField txtFieldLastName;
    @FXML
    private TextField txtFieldSSN;
    @FXML
    private TextField txtFieldAddress;
    @FXML
    private RadioButton radioMale;
    @FXML
    private RadioButton radioFemale;
    @FXML
    private RadioButton radioOther;
    @FXML
    private Button btnGeneralInfoCancel;
    @FXML
    private Button btnGeneralInfoSave;
    @FXML
    private Label lblInfoState;
    @FXML
    private ImageView imgViewNotSaved;
    @FXML
    private ImageView imgViewSaved;
    @FXML
    private TextField txtFieldCitizenGeneralInfoID;
    @FXML
    private ImageView imageCoping;

    //Studerendes vurdering af funktionelt niveau
    @FXML
    private ComboBox comboBoxFunctionalAbilityExpected;
    @FXML
    private TextArea txtAreaFunctionAbilityNote;

    //Borgerens egen vurdering på funktionel pane
    @FXML
    private ComboBox comboBoxCitizenOwnAbility;
    @FXML
    private ComboBox comboBoxCitizenLimitation;
    @FXML
    private TextArea txtAreaCitizenGoals;

    //Health condition tables
    @FXML
    private TableView<HealthCondition> tvHealthConditions;
    @FXML
    private TableColumn<HealthCondition, Integer> tcHealthConditionID;
    @FXML
    private TableColumn<HealthCondition, String> tcHealthConditionName;

    @FXML
    private TableView<SubCategory> tvSubCategories;
    @FXML
    private TableColumn<SubCategory, Integer> tcSubCategoriesID;
    @FXML
    private TableColumn<SubCategory, String> tcSubCategoriesName;
    @FXML
    private TextArea txtAreaCoping;
    @FXML
    private TextArea txtAreaMotivation;
    @FXML
    private TextArea txtAreaResources;
    @FXML
    private TextArea txtAreaRoles;
    @FXML
    private TextArea txtAreaHabits;
    @FXML
    private TextArea txtAreaEducationAndJobs;
    @FXML
    private TextArea txtAreaLifeStory;
    @FXML
    private TextArea txtAreaHealthInfo;
    @FXML
    private TextArea txtAreaEquipmentAids;
    @FXML
    private TextArea txtAreaHomeLayout;
    @FXML
    private TextArea txtAreaNetwork;


    //Cases table
    @FXML
    private TableView<Case> tvCases;
    @FXML
    private TableColumn<Case, Integer> tcCaseID;
    @FXML
    private TableColumn<Case, String> tcCaseName;
    @FXML
    private TableColumn<Case, String> tcCaseDate;
    @FXML
    private TableColumn<Case, String> tcCaseInfo;

    //Citizen Table
    @FXML
    private TableView<Citizen> tvCitizens;
    @FXML
    private TableColumn<Citizen, Integer> tcCitizenID;
    @FXML
    private TableColumn<Citizen, String> tcCitizenFirstName;
    @FXML
    private TableColumn<Citizen, String> tcCitizenLastName;
    @FXML
    private TableColumn<Citizen, String> tcCitizenSSN;
    @FXML
    private TableColumn<Citizen, String> tcCitizenAddress;
    @FXML
    private TableColumn<Citizen, String> tcCitizenSex;

    //FS III comboBox
    @FXML
    private ComboBox comboBoxCitizen;
    @FXML
    private TextField txtFieldCitizenID;
    @FXML
    private TextArea txtAreaNoteOnSubCategory;
    @FXML
    private Button btnGeneralInformation;
    @FXML
    private Button btnHealthCondition;
    @FXML
    private Button btnFunctionalCondition;
    @FXML
    private Button btnCitizenAssessment;
    @FXML
    private Button btnHome;
    @FXML
    private Button btnCitizens;
    @FXML
    private Button btnFS3;
    @FXML
    private Button btnLogOut;
    @FXML
    private Button btnBack;
    @FXML
    private Label labelTitle;
    @FXML
    private Label labelInfo;
    @FXML
    private Label labelInfoNewLine;
    @FXML
    private AnchorPane topPane;
    @FXML
    private AnchorPane anchorPaneStudent;
    @FXML
    private AnchorPane anchorPaneFS3;
    @FXML
    private AnchorPane anchorPaneGeneralInformation;
    @FXML
    private AnchorPane anchorPaneHealthConditions;
    @FXML
    private AnchorPane anchorPaneCitizens;
    @FXML
    private AnchorPane anchorPaneCitizenAssessment;
    @FXML
    private AnchorPane anchorPaneOBS;
    @FXML
    private AnchorPane anchorPaneFunctionalAbility;
    @FXML
    private RadioButton radioNotRelevant;
    @FXML
    private RadioButton radioPotential;
    @FXML
    private RadioButton radioRelevant;
    @FXML
    private RadioButton radio0;
    @FXML
    private RadioButton radio1;
    @FXML
    private RadioButton radio2;
    @FXML
    private RadioButton radio3;
    @FXML
    private RadioButton radio4;
    @FXML
    private RadioButton radio9;


    private ObservableList<Citizen> allCitizens = FXCollections.observableArrayList();
    private ObservableList<Case> allCasesOnCitizen = FXCollections.observableArrayList();
    private ObservableList<HealthCondition> allHealthConditions = FXCollections.observableArrayList();
    private ObservableList<SubCategory> allSubCategories = FXCollections.observableArrayList();


    private Citizen selectedCitizen;
    private Citizen selectedCitizenOnComboBox;

    private SubCategory selectedSubCategory;
    private HealthCondition selectedHealthCondition;

    private DataModelFacade dataModelFacade;

    private ToggleGroup group;

    public StudentViewController() throws IOException {
        this.dataModelFacade = new DataModelFacade();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setAnchorPanesVisibility();
        selectedCitizen();
        selectedCitizenOnComboBox();
        selectedHealthCondition();
        selectedSubCategory();
        try {
            initializeTables();
            initializeCitizenComboBox();
        } catch (Exception e) {
            e.printStackTrace();
        }
        setupToggleGender();
        setupToggleFunctionCondition();
        setupToggleHealthCondition();
    }

    /**
     * Inserts value from database to the different tables
     * @throws Exception
     */
    private void initializeTables() throws Exception {
        //Initialize the citizens table
        tcCitizenID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcCitizenFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        tcCitizenLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tcCitizenSSN.setCellValueFactory(new PropertyValueFactory<>("SSN"));
        tcCitizenAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        tcCitizenSex.setCellValueFactory(new PropertyValueFactory<>("sex"));
        try {
            allCitizens = FXCollections.observableList(dataModelFacade.getCitizens());
            tableViewLoadCitizens(allCitizens);
        } catch (Exception e) {
            throw new Exception();
        }

        //Initialize the healthConditionsTable
        tcHealthConditionID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcHealthConditionName.setCellValueFactory(new PropertyValueFactory<>("name"));
        try {
            allHealthConditions = FXCollections.observableArrayList(dataModelFacade.getHealthConditions());
            tableViewLoadHealthConditions(allHealthConditions);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads all data in tableview, from the selected current citizen ID.
     */
    public void seeCasesOnCitizen() {
        //Initialize the cases on citizen table
        tcCaseID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcCaseName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tcCaseDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tcCaseInfo.setCellValueFactory(new PropertyValueFactory<>("info"));
        try {
            allCasesOnCitizen = FXCollections.observableList(dataModelFacade.getCasesOnCitizen(selectedCitizen.getId()));
            tableViewLoadCasesOnCitizen(allCasesOnCitizen);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Takes values from the database and inserts them into the sub category tableview
     */
    public void seeSubCategoriesOnCategory() {
        //Initialize the subCategory table
        tcSubCategoriesID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcSubCategoriesName.setCellValueFactory(new PropertyValueFactory<>("subCategoryName"));
        try {
            allSubCategories = FXCollections.observableList(dataModelFacade.getSubCategories(selectedHealthCondition.getId()));
            tableViewLoadSubCategories(allSubCategories);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Load the tableviews with assigned health condition notes
     * @throws SQLServerException
     */
    public void seeTxtOnSubCategory() throws SQLServerException {
        txtFieldCitizenID.getText();
        txtAreaNoteOnSubCategory.setText(String.valueOf(dataModelFacade.getTextOnSubCategory(selectedCitizenOnComboBox.getId(), selectedSubCategory.getId())));
    }

    /**
     * Setup combobox with citizens
     * @throws SQLException
     */
    public void initializeCitizenComboBox() throws SQLException {
        //Initialize the citizens in the dropdown menu
        allCitizens = FXCollections.observableList(dataModelFacade.getCitizens());
        tableViewLoadCitizens(allCitizens);
        comboBoxCitizen.setItems(allCitizens);
    }

    /**
     * When clicked on citizen in the combobox, the different text fields get values based on the selected citizen
     * @throws SQLException
     */
    @FXML
    private void onActionComboClicked() throws SQLException {
        lblInfoState.setText("Ændringer - Ikke Gemt");
        imgViewNotSaved.setVisible(true);
        imgViewSaved.setVisible(false);
        btnClickGeneralInformation();
        Citizen selectedCitizenComboBox = (Citizen) comboBoxCitizen.getSelectionModel().getSelectedItem();
        txtFieldCitizenID.setText(String.valueOf(selectedCitizenComboBox.getId()));
        txtFieldFirstName.setText(selectedCitizenComboBox.getFirstName());
        txtFieldLastName.setText(selectedCitizenComboBox.getLastName());
        txtFieldSSN.setText(selectedCitizenComboBox.getSSN());
        txtFieldAddress.setText(selectedCitizenComboBox.getAddress());
        if (selectedCitizenComboBox.getSex().equals("Male")){
            radioMale.setSelected(true);
        }else if (selectedCitizenComboBox.getSex().equals("Female")){
            radioFemale.setSelected(true);
        }else if(selectedCitizenComboBox.getSex().equals("Other")){
            radioOther.setSelected(true);
        }
        GeneralInformation selectedGeneralInformation = (GeneralInformation) dataModelFacade.getGeneralInformationOnCitizen(Integer.parseInt((txtFieldCitizenID.getText())));
        txtAreaCoping.setText(selectedGeneralInformation.getCoping());
        txtAreaMotivation.setText(selectedGeneralInformation.getMotivation());
        txtAreaResources.setText(selectedGeneralInformation.getResources());
        txtAreaRoles.setText(selectedGeneralInformation.getRoles());
        txtAreaHabits.setText(selectedGeneralInformation.getHabits());
        txtAreaEducationAndJobs.setText(selectedGeneralInformation.getEducationAndJob());
        txtAreaLifeStory.setText(selectedGeneralInformation.getLifeStory());
        txtAreaHealthInfo.setText(selectedGeneralInformation.getHealthInformation());
        txtAreaEquipmentAids.setText(selectedGeneralInformation.getEquipmentAids());
        txtAreaHomeLayout.setText(selectedGeneralInformation.getHomeLayout());
        txtAreaNetwork.setText(selectedGeneralInformation.getNetwork());
    }

    /**
     * Method to pick only functional condition
     */
    public void setupToggleFunctionCondition(){
        group = new ToggleGroup();
        radio0.setToggleGroup(group);
        radio1.setToggleGroup(group);
        radio2.setToggleGroup(group);
        radio3.setToggleGroup(group);
        radio4.setToggleGroup(group);
        radio9.setToggleGroup(group);
    }

    /**
     * Method to only pick one health condition
     */
    public void setupToggleHealthCondition(){
        group = new ToggleGroup();
        radioNotRelevant.setToggleGroup(group);
        radioPotential.setToggleGroup(group);
        radioRelevant.setToggleGroup(group);
    }

    /**
     * Method to only pick one gender
     */
    public void setupToggleGender(){
        group = new ToggleGroup();
        radioMale.setToggleGroup(group);
        radioFemale.setToggleGroup(group);
        radioOther.setToggleGroup(group);
    }

    /**
     * loads the Citizens table view
     * @param allCitizens
     */
    private void tableViewLoadCitizens(ObservableList<Citizen> allCitizens) {
        tvCitizens.setItems(getCitizenData());
    }

    /**
     * Gets the data for citizens
     * @return ObservableList<Citizen>
     */
    private ObservableList<Citizen> getCitizenData() {
        return allCitizens;
    }

    /**
     * loads the casesOnCitizen tableview.
     *
     * @param allCasesOnCitizen
     */
    private void tableViewLoadCasesOnCitizen(ObservableList<Case> allCasesOnCitizen) {
        tvCases.setItems(getCasesOnCitizenData());
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
     * loads the health condition tableview.
     * @param allHealthConditions
     */
    private void tableViewLoadHealthConditions(ObservableList<HealthCondition> allHealthConditions) {
        tvHealthConditions.setItems(getHealthConditionData());
    }

    /**
     * Gets the data for health conditions
     * @return
     */
    private ObservableList<HealthCondition> getHealthConditionData() {
        return allHealthConditions;
    }

    /**
     * loads the sub categories tableview.
     * @param allSubCategories
     */
    private void tableViewLoadSubCategories(ObservableList<SubCategory> allSubCategories) {
        tvSubCategories.setItems(getSubCategories());
    }

    /**
     * Gets the data for sub categories
     * @return
     */
    private ObservableList<SubCategory> getSubCategories() {
        return allSubCategories;
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
    }

    /**
     * Selects a citizen from the combobox
     */
    public void selectedCitizenOnComboBox() {
        this.comboBoxCitizen.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            if ((Citizen) newValue != null) {
                this.selectedCitizenOnComboBox = (Citizen) newValue;
            }
        }));
    }

    /**
     * Selects a health condition from the tableview
     */
    private void selectedHealthCondition() {
        this.tvHealthConditions.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            if ((HealthCondition) newValue != null) {
                this.selectedHealthCondition = (HealthCondition) newValue;
                seeSubCategoriesOnCategory();
            }
        }));
    }

    /**
     * Selects a sub category from the tableview
     */
    private void selectedSubCategory() {
        this.tvSubCategories.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            if ((SubCategory) newValue != null) {
                this.selectedSubCategory = (SubCategory) newValue;
                try {
                    seeTxtOnSubCategory();
                } catch (SQLServerException e) {
                    e.printStackTrace();
                }
            }
        }));
    }

    /**
     * Sets the user
     * @param user
     * @throws SQLException
     * @throws IOException
     */
    @Override
    public void setUser(User user) throws SQLException, IOException {
        labelTitle.setText("Elev");
        labelInfo.setText("Du er nu logget ind som Elev: " + user.getFirstName() + " " + user.getLastName());
        labelInfoNewLine.setText("");
    }

    /**
     * Set up the student anchor pane
     */
    private void setAnchorPanesVisibility() {
        labelInfoNewLine.setText("");
        anchorPaneStudent.setVisible(true);
        anchorPaneCitizens.setVisible(false);
        anchorPaneFS3.setVisible(false);
        anchorPaneOBS.setVisible(false);
        anchorPaneGeneralInformation.setVisible(false);
        anchorPaneHealthConditions.setVisible(false);
        anchorPaneFunctionalAbility.setVisible(false);
        anchorPaneCitizenAssessment.setVisible(false);
    }

    /**
     * Takes you back to the start screen/pane when clicked on logo
     * @param actionEvent
     */
    @FXML
    private void btnClickHome(ActionEvent actionEvent) {
        labelTitle.setText("Elev");
        labelInfo.setText("Du er nu logget ind som Elev");
        labelInfoNewLine.setText("");
        anchorPaneStudent.setVisible(true);
        anchorPaneCitizens.setVisible(false);
        anchorPaneFS3.setVisible(false);
        anchorPaneGeneralInformation.setVisible(false);
        anchorPaneHealthConditions.setVisible(false);
        anchorPaneFunctionalAbility.setVisible(false);
        anchorPaneCitizenAssessment.setVisible(false);
    }

    /**
     * Set up the citizen pane
     */
    @FXML
    private void btnClickCitizensAndCases() {
        labelTitle.setText("Borgere og Sager");
        labelInfo.setText("Overblik over alle borgere, samt hvilke sager de er tildelt");
        labelInfoNewLine.setText("Klik på en borger for at se hvilke sager der er tildelt den valgte borger");
        anchorPaneStudent.setVisible(false);
        anchorPaneCitizens.setVisible(true);
        anchorPaneFS3.setVisible(false);
        anchorPaneGeneralInformation.setVisible(false);
        anchorPaneHealthConditions.setVisible(false);
        anchorPaneFunctionalAbility.setVisible(false);
        anchorPaneCitizenAssessment.setVisible(false);
    }


    /**
     * Set up the FS3 pane
     */
    @FXML
    private void btnClickFS3() {
        labelTitle.setText("Rapporter og øv Fælles Sprog 3");
        labelInfo.setText("I dette vindue kan du vælge at se en borgers generelle info, helbredstilstande og funktionstilstand");
        labelInfoNewLine.setText("Med taskbaren under denne tekst, kan du vælge tre forskellige vinduer, hvor du kan rapportere og øve Fælles Sprog 3");
        anchorPaneStudent.setVisible(false);
        anchorPaneCitizens.setVisible(false);
        anchorPaneFS3.setVisible(true);
        anchorPaneOBS.setVisible(true);
        anchorPaneGeneralInformation.setVisible(false);
        anchorPaneHealthConditions.setVisible(false);
        anchorPaneFunctionalAbility.setVisible(false);
        anchorPaneCitizenAssessment.setVisible(false);
    }

    /**
     * Set up the general information pane
     */
    @FXML
    void btnClickGeneralInformation() {
        labelTitle.setText("Generelle Informationer for borgeren");
        labelInfo.setText("Overblik over borgerens generelle informationer, hvor det er muligt at redigere og gemme eventuelle ændringer");
        labelInfoNewLine.setText("Her kan du se alle informationer for borgeren");
        anchorPaneStudent.setVisible(false);
        anchorPaneCitizens.setVisible(false);
        anchorPaneFS3.setVisible(true);
        anchorPaneOBS.setVisible(false);
        anchorPaneGeneralInformation.setVisible(true);
        anchorPaneHealthConditions.setVisible(false);
        anchorPaneFunctionalAbility.setVisible(false);
        anchorPaneCitizenAssessment.setVisible(false);

    }

    /**
     * Set up the health condition pane
     */
    @FXML
    private void btnClickHealthCondition() {
        labelTitle.setText("Helbreds Tilstande for borgeren");
        labelInfo.setText("Overblik over borgerens helbredstilstande, hvor det er muligt at ændre og gemme eventuelle observationer og ændringer");
        labelInfoNewLine.setText("Her er det muligt at rapportere borgerens helbredstilstande og øve FS3. Der skal tages stilling til alle tilstande for at gemme.");
        anchorPaneStudent.setVisible(false);
        anchorPaneCitizens.setVisible(false);
        anchorPaneFS3.setVisible(true);
        anchorPaneOBS.setVisible(false);
        anchorPaneGeneralInformation.setVisible(false);
        anchorPaneHealthConditions.setVisible(true);
        anchorPaneFunctionalAbility.setVisible(false);
        anchorPaneCitizenAssessment.setVisible(false);
    }

    /**
     * Set up the functional condition pane
     */
    @FXML
    private void btnClickFunctionalCondition() {
        labelTitle.setText("Funktionstilstanden for borgeren");
        labelInfo.setText("Overblik over borgerens funktionstilstand, hvor der skal tages stilling til den nuværende funktionstilstand");
        labelInfoNewLine.setText("Her er det muligt at se et skema over definitionen på funktionstilstandende, men der skal også tages stilling til den forventede funktionstilstand");
        anchorPaneStudent.setVisible(false);
        anchorPaneCitizens.setVisible(false);
        anchorPaneFS3.setVisible(true);
        anchorPaneOBS.setVisible(false);
        anchorPaneGeneralInformation.setVisible(false);
        anchorPaneHealthConditions.setVisible(false);
        anchorPaneFunctionalAbility.setVisible(true);
        anchorPaneCitizenAssessment.setVisible(false);
    }

    /**
     * Set up the Assessment pane
     */
    @FXML
    private void btnClickCitizenAssessment() {
        labelTitle.setText("Borgerens egen vurdering af funktionstilstand");
        labelInfo.setText("Her er det muligt at rapportere på borgerens egen vurdering af sin funktionstilstand.");
        labelInfoNewLine.setText("Der skal tages stilling til udførelse, betydningen af udførelse og borgerens ønsker og mål");
        anchorPaneStudent.setVisible(false);
        anchorPaneCitizens.setVisible(false);
        anchorPaneFS3.setVisible(true);
        anchorPaneOBS.setVisible(false);
        anchorPaneGeneralInformation.setVisible(false);
        anchorPaneHealthConditions.setVisible(false);
        anchorPaneFunctionalAbility.setVisible(false);
        anchorPaneCitizenAssessment.setVisible(true);
    }

    /**
     * When click on back it sends you back to the start pane/screen
     * @param actionEvent
     */
    @FXML
    private void btnClickBack(ActionEvent actionEvent) {
        btnClickHome(actionEvent);
    }

    /**
     * Logs you out when clicked
     * @throws IOException
     */
    @FXML
    private void btnClickLogout() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/LoginView.fxml"));
        Scene scene = new Scene(loader.load());
        Stage switcher = (Stage) btnLogOut.getScene().getWindow();
        switcher.setScene(scene);
        switcher.setTitle("Login");
        switcher.show();
        switcher.centerOnScreen();
    }

    /**
     * When the general information is click it set up the general information pane
     * @param actionEvent
     */
    public void onActionGeneralInfoCancel(ActionEvent actionEvent) {
        btnClickGeneralInformation();
    }

    /**
     * Save the changes if there are any
     * @param actionEvent
     * @throws Exception
     */
    public void onActionGeneralInfoSave(ActionEvent actionEvent) throws Exception {
        int id = Integer.parseInt(txtFieldCitizenID.getText());
        String citizenFirstName = txtFieldFirstName.getText();
        String citizenLastName = txtFieldLastName.getText();
        String citizenSSN = txtFieldSSN.getText();
        String citizenAddress = txtFieldAddress.getText();
        String sex = null;
        if (radioMale.isSelected()) {
            sex = "Male";
        } else if (radioFemale.isSelected()) {
            sex = "Female";
        } else if (radioOther.isSelected()) {
            sex = "Other";
        }

        int generalInformationId = Integer.parseInt(txtFieldCitizenID.getText());
        String coping = txtAreaCoping.getText();
        String motivation = txtAreaMotivation.getText();
        String resources = txtAreaResources.getText();
        String roles = txtAreaRoles.getText();
        String habits = txtAreaHabits.getText();
        String educationandjob = txtAreaEducationAndJobs.getText();
        String lifeStory = txtAreaLifeStory.getText();
        String network = txtAreaNetwork.getText();
        String healthInformation = txtAreaHealthInfo.getText();
        String equipmentAids = txtAreaEquipmentAids.getText();
        String homeLayout = txtAreaHomeLayout.getText();

        Citizen citizen = new Citizen(id, citizenFirstName, citizenLastName, citizenSSN, citizenAddress, sex);
        GeneralInformation generalInformation = new GeneralInformation(generalInformationId,coping, motivation, resources, roles, habits, educationandjob,
                lifeStory, network, healthInformation, equipmentAids, homeLayout);
        dataModelFacade.editCitizen(citizen);
        dataModelFacade.editGeneralInformation(generalInformation);
        lblInfoState.setText("Ændringer - Gemt");
        imgViewNotSaved.setVisible(false);
        imgViewSaved.setVisible(true);

    }

    /**
     * When clicked on the little i-icon it sends you to the FS3 homepage
     * @param actionEvent
     * @throws URISyntaxException
     * @throws IOException
     */
    @FXML
    private void coupingLink(ActionEvent actionEvent) throws URISyntaxException, IOException {
        Desktop.getDesktop().browse(new URI("http://www.fs3.nu/filer/Dokumenter/Metode/FSIII-Guide-til-generelle-oplysninger.pdf?t=1647518630"));
    }

    /**
     * When a citizen is selected all values is loaded in the text fields
     * @param citizenID
     * @throws SQLException
     */
    public void setGeneralInfoFromID(String citizenID) throws SQLException {
        txtFieldCitizenID.setText(citizenID);

        Citizen selectedCitizenInfo = dataModelFacade.getInfoOnCitizen(Integer.parseInt(txtFieldCitizenID.getText()));
        txtFieldFirstName.setText(selectedCitizenInfo.getFirstName());
        txtFieldLastName.setText(selectedCitizenInfo.getLastName());
        txtFieldSSN.setText(selectedCitizenInfo.getSSN());
        txtFieldAddress.setText(selectedCitizenInfo.getAddress());
        radioMale.setToggleGroup(group);
        radioFemale.setToggleGroup(group);
        radioOther.setToggleGroup(group);
        if (selectedCitizenInfo.getSex().equals("Male")){
            radioMale.setSelected(true);
        }else if (selectedCitizenInfo.getSex().equals("Female")){
            radioFemale.setSelected(true);
        }else if(selectedCitizenInfo.getSex().equals("Other")){
            radioOther.setSelected(true);
        }

        GeneralInformation selectedGeneralInformation = dataModelFacade.getGeneralInformationOnCitizen(Integer.parseInt((txtFieldCitizenID.getText())));
        txtAreaCoping.setText(selectedGeneralInformation.getCoping());
        txtAreaMotivation.setText(selectedGeneralInformation.getMotivation());
        txtAreaResources.setText(selectedGeneralInformation.getResources());
        txtAreaRoles.setText(selectedGeneralInformation.getRoles());
        txtAreaHabits.setText(selectedGeneralInformation.getHabits());
        txtAreaEducationAndJobs.setText(selectedGeneralInformation.getEducationAndJob());
        txtAreaLifeStory.setText(selectedGeneralInformation.getLifeStory());
        txtAreaHealthInfo.setText(selectedGeneralInformation.getHealthInformation());
        txtAreaEquipmentAids.setText(selectedGeneralInformation.getEquipmentAids());
        txtAreaHomeLayout.setText(selectedGeneralInformation.getHomeLayout());
        txtAreaNetwork.setText(selectedGeneralInformation.getNetwork());
        lblInfoState.setText("Ændringer - Ikke Gemt");
        imgViewSaved.setVisible(false);
        imgViewNotSaved.setVisible(true);
    }
}
