package gui.controller;

import be.Case;
import be.Citizen;
import be.FunctionalAbilities.FunctionalAbility;
import be.FunctionalAbilities.FunctionalAbilitySubCategoryText;
import be.GeneralInformation;
import be.HealthCondition.HealthCondition;
import be.HealthCondition.HealthConditionSubCategory;
import be.HealthCondition.HealthConditionSubCategoryText;
import be.User;
import be.enums.ConditionEnum;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import gui.Facade.DataModelFacade;
import gui.controller.Interface.IController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class StudentViewController implements IController, Initializable {

    /**
     * General info on Citizen pane
     */
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
    private TextField txtFieldSchoolID;


    /**
     * Functional Ability Pane
     */
    @FXML
    private TextField txtFieldFunctionalAbilityID;
    @FXML
    private TableView<FunctionalAbilitySubCategoryText> tvFunctionalConditions;
    @FXML
    private TableColumn<FunctionalAbilitySubCategoryText, Integer> tcFunctionalConditionID;
    @FXML
    private TableColumn<FunctionalAbilitySubCategoryText, String> tcFunctionalConditionName;

    /**
     * Students judgement on functional pane
     */
    @FXML
    private ComboBox comboBoxFunctionalAbilityExpected;
    /**
     * Citizens own judgement on functional pane
     */
    @FXML
    private TextArea txtAreaNoteOnCondition;
    @FXML
    private ComboBox comboBoxCurrentLevel;
    @FXML
    private ComboBox comboBoxExpectedLevel;
    @FXML
    private TextArea txtAreaCitizenGoals;
    @FXML
    private ComboBox comboboxPerformance;
    @FXML
    private ComboBox comboboxMeaningOfPerformance;
    /**
     * HealthCondition Tables
     */
    @FXML
    private TableView<HealthCondition> tvHealthConditions;
    @FXML
    private TableColumn<HealthCondition, Integer> tcHealthConditionID;
    @FXML
    private TableColumn<HealthCondition, String> tcHealthConditionName;
    @FXML
    private TableView<HealthConditionSubCategory> tvSubCategories;
    @FXML
    private TableColumn<HealthConditionSubCategory, Integer> tcSubCategoriesID;
    @FXML
    private TableColumn<HealthConditionSubCategory, String> tcSubCategoriesName;
    @FXML
    private TextArea txtAreaNoteOnSubCategory;
    @FXML
    private Button btnMobility;
    @FXML
    private TextArea txtAreaCurrentLevelAssessment;
    @FXML
    private ComboBox comboBoxExpectedLevelAssessment;

    /**
     * General Information
     */
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
    @FXML
    private TextField subCatTxtID;


    /**
     * Cases Table
     */
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

    /**
     * Citizen Table
     */
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

    /**
     * FS III comboBox
     */
    @FXML
    private ComboBox comboBoxCitizen;
    @FXML
    private TextField txtFieldCitizenID;
    @FXML
    private Button btnLogOut;
    @FXML
    private Label labelTitle;
    @FXML
    private Label labelInfo;
    @FXML
    private Label labelInfoNewLine;
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
    private AnchorPane anchorPaneFunctionalCondition;
    @FXML
    private AnchorPane anchorPaneOBS;
    @FXML
    private RadioButton radioNotRelevant;
    @FXML
    private RadioButton radioPotential;
    @FXML
    private RadioButton radioRelevant;
    @FXML
    private Button btnCitizenInfo;

    private ObservableList<Citizen> allCitizens = FXCollections.observableArrayList();
    private ObservableList<Case> allCasesOnCitizen = FXCollections.observableArrayList();
    private ObservableList<HealthCondition> allHealthConditions = FXCollections.observableArrayList();
    private ObservableList<HealthConditionSubCategory> allSubCategories = FXCollections.observableArrayList();
    private ObservableList<FunctionalAbilitySubCategoryText> allFunctionalAbilitySubCategories = FXCollections.observableArrayList();

    private Citizen selectedCitizen;
    private Citizen selectedCitizenOnComboBox;
    private FunctionalAbilitySubCategoryText selectedFunctionalAbilitySubCategory;

    private HealthConditionSubCategory selectedHealthConditionSubCategory;
    private HealthConditionSubCategoryText selectedHealthConditionSubCategoryText;
    private HealthCondition selectedHealthCondition;

    private DataModelFacade dataModelFacade;
    private CitizenInfoViewController citizenInfoViewController;
    private ToggleGroup group;

    public StudentViewController() throws IOException {
        this.dataModelFacade = new DataModelFacade();
    }

    /**
     * Sets the user
     */
    @Override
    public void setUser(User user) throws Exception {
        labelTitle.setText("Elev");
        labelInfo.setText("Du er nu logget ind som Elev: " + user.getFirstName() + " " + user.getLastName());
        labelInfoNewLine.setText("");
        txtFieldSchoolID.setText(String.valueOf(user.getSchoolId()));
        initializeTables();
        initializeCitizenComboBox();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setAnchorPanesVisibility();
        selectedCitizen();
        selectedCitizenOnComboBox();
        selectedHealthCondition();
        selectedSubCategory();
        SelectedFunctionalAbilitySubCategory();
        setupToggleGender();
        setupToggleHealthCondition();
        setFunctionalAbilityComboBoxItems();
        setExpectedLevelAssessmentComboBoxItems();
    }

    /**
     * Inserts value from database to the different tables
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
            allCitizens = FXCollections.observableList(dataModelFacade.getAssignedCitizen(Integer.parseInt(txtFieldSchoolID.getText())));
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
        //https://stackoverflow.com/questions/30889732/javafx-tableview-change-row-color-based-on-column-value
        //https://coderanch.com/t/633408/java/combobox-picture
        //https://stackoverflow.com/questions/14327275/java-combobox-how-to-add-icon

    }

    /**
     * Loads all data in tableview, from the selected current citizen ID.
     */
    private void seeCasesOnCitizen() {
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
    private void seeSubCategoriesOnCategory() {
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
     */
    private void seeTxtOnSubCategory() throws SQLServerException {
        if (txtFieldCitizenID != null && subCatTxtID != null) {
            HealthConditionSubCategoryText subCategoryText = dataModelFacade.getTextOnSubCategory(Integer.parseInt(txtFieldCitizenID.getText()), Integer.parseInt(subCatTxtID.getText()));
            if (subCategoryText != null) {
                txtAreaNoteOnSubCategory.setText(subCategoryText.getProfessionalNote());
                txtAreaCurrentLevelAssessment.setText(subCategoryText.getCurrentLevelAssessment());
                if (subCategoryText.getExpectedLevelAssessment().equals("Mindskes")) {
                    comboBoxExpectedLevelAssessment.getSelectionModel().select(0);
                } else if (subCategoryText.getExpectedLevelAssessment().equals("Forbliver uændret")) {
                    comboBoxExpectedLevelAssessment.getSelectionModel().select(1);
                } else if (subCategoryText.getExpectedLevelAssessment().equals("Forsvinder")) {
                    comboBoxExpectedLevelAssessment.getSelectionModel().select(2);
                }
                if (subCategoryText.getCondition() == 0) {
                    radioNotRelevant.setSelected(true);
                } else if (subCategoryText.getCondition() == 1) {
                    radioPotential.setSelected(true);
                } else if (subCategoryText.getCondition() == 2) {
                    radioRelevant.setSelected(true);
                }
            } else {
                clearHealthConditionTxtField();
            }
        }
    }

    public void clearHealthConditionTxtField() {
        txtAreaNoteOnSubCategory.clear();
        txtAreaCurrentLevelAssessment.clear();
        comboBoxExpectedLevelAssessment.getSelectionModel().clearSelection();
        radioPotential.setSelected(false);
        radioRelevant.setSelected(false);
        radioNotRelevant.setSelected(false);
    }

    /**
     * Method to only pick one health condition
     */
    private void setupToggleHealthCondition() {
        group = new ToggleGroup();
        radioNotRelevant.setToggleGroup(group);
        radioPotential.setToggleGroup(group);
        radioRelevant.setToggleGroup(group);
    }

    /**
     * Method to only pick one gender
     */
    private void setupToggleGender() {
        group = new ToggleGroup();
        radioMale.setToggleGroup(group);
        radioFemale.setToggleGroup(group);
        radioOther.setToggleGroup(group);
    }

    private void setExpectedLevelAssessmentComboBoxItems(){
        comboBoxExpectedLevelAssessment.getItems().addAll(
                "Mindskes", "Forbliver uændret", "Forsvinder"
        );
    }

    private void setFunctionalAbilityComboBoxItems() {
        comboBoxCurrentLevel.getItems().addAll(
                "0", "1", "2", "3", "4", "9"
        );
        comboBoxExpectedLevel.getItems().addAll(
                "0", "1", "2", "3", "4", "9"
        );
        comboboxPerformance.getItems().addAll(
                "Udfører selv",
                "Udfører dele selv",
                "Udfører ikke selv",
                "Ikke relevant"
        );
        comboboxMeaningOfPerformance.getItems().addAll(
                "Oplever ikke begrænsninger",
                "Oplever begrænsninger"
        );
    }

    /**
     * Setup combobox with citizens
     */
    private void initializeCitizenComboBox() throws SQLException {
        //Initialize the citizens in the dropdown menu
        allCitizens = FXCollections.observableList(dataModelFacade.getAssignedCitizen(Integer.parseInt(txtFieldSchoolID.getText())));
        tableViewLoadCitizens(allCitizens);
        comboBoxCitizen.setItems(allCitizens);
    }

    /**
     * When clicked on citizen in the combobox, the different text fields get values based on the selected citizen
     */
    @FXML
    private void onActionComboClicked() throws SQLException {
        lblInfoState.setText("Ændringer - Ikke Gemt");
        imgViewNotSaved.setVisible(true);
        imgViewSaved.setVisible(false);
        btnClickGeneralInformation();
        Citizen selectedCitizenComboBox = (Citizen) comboBoxCitizen.getSelectionModel().getSelectedItem();
        txtFieldCitizenID.setText(String.valueOf(selectedCitizenComboBox.getId()));
        Citizen selectedCitizenInfo = dataModelFacade.getInfoOnCitizen(Integer.parseInt(txtFieldCitizenID.getText()));
        txtFieldFirstName.setText(selectedCitizenInfo.getFirstName());
        txtFieldLastName.setText(selectedCitizenInfo.getLastName());
        txtFieldSSN.setText(selectedCitizenInfo.getSSN());
        txtFieldAddress.setText(selectedCitizenInfo.getAddress());
        switch (selectedCitizenInfo.getSex()) {
            case "Male" -> radioMale.setSelected(true);
            case "Female" -> radioFemale.setSelected(true);
            case "Other" -> radioOther.setSelected(true);
        }

        GeneralInformation selectedGeneralInformation = dataModelFacade.getGeneralInformationOnCitizen(Integer.parseInt((txtFieldCitizenID.getText())));
        if(selectedGeneralInformation != null){
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
        btnCitizenInfo.setVisible(true);
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
     * loads the casesOnCitizen tableview.
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
     */
    private void tableViewLoadHealthConditions(ObservableList<HealthCondition> allHealthConditions) {
        tvHealthConditions.setItems(getHealthConditionData());
    }

    /**
     * Gets the data for health conditions
     */
    private ObservableList<HealthCondition> getHealthConditionData() {
        return allHealthConditions;
    }

    /**
     * loads the sub categories tableview.
     */
    private void tableViewLoadSubCategories(ObservableList<HealthConditionSubCategory> allSubCategories) {
        tvSubCategories.setItems(getSubCategories());
    }

    /**
     * Gets the data for sub categories
     */
    private ObservableList<HealthConditionSubCategory> getSubCategories() {
        return allSubCategories;
    }

    private void tableViewLoadFunctionalAbilitySubCategories(ObservableList<FunctionalAbilitySubCategoryText> allFunctionalAbilitySubCategories) {
        tvFunctionalConditions.setItems(getFunctionalAbilitySubCategories());
    }

    private ObservableList<FunctionalAbilitySubCategoryText> getFunctionalAbilitySubCategories() {
        return allFunctionalAbilitySubCategories;
    }


    /**
     * Selects a citizen from the citizens TableView
     */
    private void selectedCitizen() {
        this.tvCitizens.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            if (newValue != null) {
                this.selectedCitizen = newValue;
                seeCasesOnCitizen();
            }
        }));
    }

    /**
     * Selects a citizen from the combobox
     */
    public void selectedCitizenOnComboBox() {
        this.comboBoxCitizen.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            if (newValue != null) {
                this.selectedCitizenOnComboBox = (Citizen) newValue;
            }
        }));
    }

    /**
     * Selects a health condition from the tableview
     */
    private void selectedHealthCondition() {
        this.tvHealthConditions.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            if (newValue != null) {
                this.selectedHealthCondition = newValue;
                seeSubCategoriesOnCategory();
            }
        }));
    }

    /**
     * Selects a sub category from the tableview
     */
    private void selectedSubCategory() {
        this.tvSubCategories.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            if (newValue != null) {
                this.selectedHealthConditionSubCategory = newValue;
                subCatTxtID.setText(String.valueOf(newValue.getId()));
                try {
                    seeTxtOnSubCategory();
                } catch (SQLServerException e) {
                    e.printStackTrace();
                }
            }
        }));
    }

    /**
     * Selects a sub category from the tableview
     */
    private void SelectedFunctionalAbilitySubCategory() {
        this.tvFunctionalConditions.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            if (newValue != null) {
                this.selectedFunctionalAbilitySubCategory = newValue;
                txtFieldFunctionalAbilityID.setText(String.valueOf(selectedFunctionalAbilitySubCategory.getId()));
                try {
                    setFunctionalAbilityInfo();
                } catch (SQLServerException e) {
                    e.printStackTrace();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }));
    }

    /**
     * Save the changes if there are any
     */
    @FXML
    private void onActionGeneralInfoSave() throws Exception {
        int citizenId = Integer.parseInt(txtFieldCitizenID.getText());
        String citizenFirstName = txtFieldFirstName.getText();
        String citizenLastName = txtFieldLastName.getText();
        String citizenSSN = txtFieldSSN.getText();
        String citizenAddress = txtFieldAddress.getText();
        int schoolId = Integer.parseInt(txtFieldSchoolID.getText());
        String sex = null;
        if (radioMale.isSelected()) {
            sex = "Male";
        } else if (radioFemale.isSelected()) {
            sex = "Female";
        } else if (radioOther.isSelected()) {
            sex = "Other";
        }

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

        Citizen citizen = new Citizen(citizenId, citizenFirstName, citizenLastName, citizenSSN, citizenAddress, sex, schoolId);
        dataModelFacade.editCitizen(citizen);
        dataModelFacade.createGeneralInformation(citizenId, coping, motivation, resources, roles, habits, educationandjob,
                lifeStory, network, healthInformation, equipmentAids, homeLayout);
        lblInfoState.setText("Ændringer - Gemt");
        imgViewNotSaved.setVisible(false);
        imgViewSaved.setVisible(true);
    }

    /**
     * When a citizen is selected all values is loaded in the text fields
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
        switch (selectedCitizenInfo.getSex()) {
            case "Male" -> radioMale.setSelected(true);
            case "Female" -> radioFemale.setSelected(true);
            case "Other" -> radioOther.setSelected(true);
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

    private void setFunctionalAbilityInfo() throws SQLException {
        if (txtFieldCitizenID != null && txtFieldFunctionalAbilityID != null) {
            FunctionalAbilitySubCategoryText functionalAbilitySubCategoryText = dataModelFacade.getInfoOnSubCategory(Integer.parseInt((txtFieldCitizenID.getText())), Integer.parseInt((txtFieldFunctionalAbilityID.getText())));

            if (functionalAbilitySubCategoryText != null) {

                if (functionalAbilitySubCategoryText.getAbilityNow() == 0) {
                    comboBoxCurrentLevel.getSelectionModel().select(0);
                } else if (functionalAbilitySubCategoryText.getAbilityNow() == 1) {
                    comboBoxCurrentLevel.getSelectionModel().select(1);
                } else if (functionalAbilitySubCategoryText.getAbilityNow() == 2) {
                    comboBoxCurrentLevel.getSelectionModel().select(2);
                } else if (functionalAbilitySubCategoryText.getAbilityNow() == 3) {
                    comboBoxCurrentLevel.getSelectionModel().select(3);
                } else if (functionalAbilitySubCategoryText.getAbilityNow() == 4) {
                    comboBoxCurrentLevel.getSelectionModel().select(4);
                } else if (functionalAbilitySubCategoryText.getAbilityNow() == 9) {
                    comboBoxCurrentLevel.getSelectionModel().select(5);
                }

                if (functionalAbilitySubCategoryText.getAbilityExpected() == 0) {
                    comboBoxExpectedLevel.getSelectionModel().select(0);
                } else if (functionalAbilitySubCategoryText.getAbilityExpected() == 1) {
                    comboBoxExpectedLevel.getSelectionModel().select(1);
                } else if (functionalAbilitySubCategoryText.getAbilityExpected() == 2) {
                    comboBoxExpectedLevel.getSelectionModel().select(2);
                } else if (functionalAbilitySubCategoryText.getAbilityExpected() == 3) {
                    comboBoxExpectedLevel.getSelectionModel().select(3);
                } else if (functionalAbilitySubCategoryText.getAbilityExpected() == 4) {
                    comboBoxExpectedLevel.getSelectionModel().select(4);
                } else if (functionalAbilitySubCategoryText.getAbilityExpected() == 9) {
                    comboBoxExpectedLevel.getSelectionModel().select(5);
                }

                txtAreaNoteOnCondition.setText(functionalAbilitySubCategoryText.getAbilityNote());

                if (functionalAbilitySubCategoryText.getCitizenPerformance().equals("Udfører selv")) {
                    comboboxPerformance.getSelectionModel().select(0);
                } else if (functionalAbilitySubCategoryText.getCitizenPerformance().equals("Udfører dele selv")) {
                    comboboxPerformance.getSelectionModel().select(1);
                } else if (functionalAbilitySubCategoryText.getCitizenPerformance().equals("Udfører ikke selv")) {
                    comboboxPerformance.getSelectionModel().select(2);
                } else if (functionalAbilitySubCategoryText.getCitizenPerformance().equals("Ikke relevant")) {
                    comboboxPerformance.getSelectionModel().select(3);
                }

                if (functionalAbilitySubCategoryText.getCitizenMeaningOfPerformance().equals("Oplever ikke begrænsninger")) {
                    comboboxMeaningOfPerformance.getSelectionModel().select(0);
                } else if (functionalAbilitySubCategoryText.getCitizenMeaningOfPerformance().equals("Oplever begrænsninger")) {
                    comboboxMeaningOfPerformance.getSelectionModel().select(1);
                }

                txtAreaCitizenGoals.setText(functionalAbilitySubCategoryText.getAbilityNoteCitizen());
            } else {
                clearFunctionalAbilityTextFields();

            }
        }
    }

    private void clearFunctionalAbilityTextFields() {
        comboBoxCurrentLevel.getSelectionModel().clearSelection();
        comboBoxExpectedLevel.getSelectionModel().clearSelection();
        txtAreaNoteOnCondition.clear();
        comboboxPerformance.getSelectionModel().clearSelection();
        comboboxMeaningOfPerformance.getSelectionModel().clearSelection();
        txtAreaCitizenGoals.clear();
    }

    @FXML
    private void btnHandleSaveFunctionalAbilityInfo() throws SQLException {
        if (txtFieldCitizenID != null && txtFieldFunctionalAbilityID != null) {
            if (dataModelFacade.getSubcategoryData(Integer.parseInt((txtFieldCitizenID.getText())), Integer.parseInt((txtFieldFunctionalAbilityID.getText()))) == null) {
                int citizenID = Integer.parseInt(txtFieldCitizenID.getText());
                int functionalAbilityID = Integer.parseInt(txtFieldFunctionalAbilityID.getText());
                int abilityNow = Integer.parseInt(String.valueOf(comboBoxCurrentLevel.getSelectionModel().getSelectedItem()));
                int abilityExpected = Integer.parseInt(String.valueOf(comboBoxExpectedLevel.getSelectionModel().getSelectedItem()));
                String abilityNote = txtAreaNoteOnCondition.getText();
                String citizenPerformance = String.valueOf(comboboxPerformance.getSelectionModel().getSelectedItem());
                String citizenMeaningOfPerformance = String.valueOf(comboboxMeaningOfPerformance.getSelectionModel().getSelectedItem());
                String abilityNoteCitizen = txtAreaCitizenGoals.getText();

                dataModelFacade.createFunctionalAbility(citizenID, functionalAbilityID, abilityNow, abilityExpected,
                        abilityNote, citizenPerformance, citizenMeaningOfPerformance, abilityNoteCitizen);

            } else {
                int citizenID = Integer.parseInt(txtFieldCitizenID.getText());
                int functionalAbilityID = Integer.parseInt(txtFieldFunctionalAbilityID.getText());
                int abilityNow = Integer.parseInt(String.valueOf(comboBoxCurrentLevel.getSelectionModel().getSelectedItem()));
                int abilityExpected = Integer.parseInt(String.valueOf(comboBoxExpectedLevel.getSelectionModel().getSelectedItem()));
                String abilityNote = txtAreaNoteOnCondition.getText();
                String citizenPerformance = String.valueOf(comboboxPerformance.getSelectionModel().getSelectedItem());
                String citizenMeaningOfPerformance = String.valueOf(comboboxMeaningOfPerformance.getSelectionModel().getSelectedItem());
                String abilityNoteCitizen = txtAreaCitizenGoals.getText();

                FunctionalAbility functionalAbility = new FunctionalAbility(citizenID, functionalAbilityID, abilityNow, abilityExpected,
                        abilityNote, citizenPerformance, citizenMeaningOfPerformance, abilityNoteCitizen);
                dataModelFacade.editAbilities(functionalAbility);
            }
        }

    }

    @FXML
    private void btnHandleSaveHC() throws Exception {
        if (txtFieldCitizenID != null && subCatTxtID != null) {
            if (dataModelFacade.getHealthConditionData(Integer.parseInt(txtFieldCitizenID.getText()), Integer.parseInt(subCatTxtID.getText())) == null) {
                int citizenId = Integer.parseInt(txtFieldCitizenID.getText());
                int subCategoryId = Integer.parseInt(subCatTxtID.getText());
                String professionalNote = txtAreaNoteOnSubCategory.getText();
                String currentLevelAssessment = txtAreaCurrentLevelAssessment.getText();
                String expectedLevelAssessment = String.valueOf(comboBoxExpectedLevelAssessment.getSelectionModel().getSelectedItem());
                if (radioNotRelevant.isSelected()) {
                    int conditionValue = ConditionEnum.NOT_RELEVANT.getValue();
                    dataModelFacade.insertIntoSubCategory(citizenId, subCategoryId, professionalNote, currentLevelAssessment, expectedLevelAssessment, conditionValue);
                } else if (radioPotential.isSelected()) {
                    int conditionValue = ConditionEnum.POTENTIAL.getValue();
                    dataModelFacade.insertIntoSubCategory(citizenId, subCategoryId, professionalNote,  currentLevelAssessment, expectedLevelAssessment, conditionValue);
                } else if (radioRelevant.isSelected()) {
                    int conditionValue = ConditionEnum.RELEVANT.getValue();
                    dataModelFacade.insertIntoSubCategory(citizenId, subCategoryId, professionalNote, currentLevelAssessment, expectedLevelAssessment, conditionValue);
                }
                System.out.println("Create");
            } else {
                int citizenId = Integer.parseInt(txtFieldCitizenID.getText());
                int subCategoryId = Integer.parseInt(subCatTxtID.getText());
                String note = txtAreaNoteOnSubCategory.getText();
                if (radioNotRelevant.isSelected()) {
                    int conditionValue = ConditionEnum.NOT_RELEVANT.getValue();
                    HealthConditionSubCategoryText subCategoryText = new HealthConditionSubCategoryText(citizenId, subCategoryId, note, conditionValue);
                    dataModelFacade.editSubcategory(subCategoryText);
                } else if (radioPotential.isSelected()) {
                    int conditionValue = ConditionEnum.POTENTIAL.getValue();
                    HealthConditionSubCategoryText subCategoryText = new HealthConditionSubCategoryText(citizenId, subCategoryId, note, conditionValue);
                    dataModelFacade.editSubcategory(subCategoryText);
                } else if (radioRelevant.isSelected()) {
                    int conditionValue = ConditionEnum.RELEVANT.getValue();
                    HealthConditionSubCategoryText subCategoryText = new HealthConditionSubCategoryText(citizenId, subCategoryId, note, conditionValue);
                    dataModelFacade.editSubcategory(subCategoryText);
                }
                System.out.println("Edit");
            }
        }
        btnHandelCancelChangesHC();
    }

    @FXML
    private void btnHandelCancelChangesHC() {
        txtAreaNoteOnSubCategory.clear();
        radioNotRelevant.setSelected(false);
        radioPotential.setSelected(false);
        radioRelevant.setSelected(false);
        tvSubCategories.getSelectionModel().clearSelection();
        tvHealthConditions.getSelectionModel().clearSelection();
    }


    @FXML
    private void btnLoadSelfCare() {
        tcFunctionalConditionID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcFunctionalConditionName.setCellValueFactory(new PropertyValueFactory<>("name"));
        try {
            allFunctionalAbilitySubCategories = FXCollections.observableList(dataModelFacade.getFunctionalAbilitySubCategories(1));
            tableViewLoadFunctionalAbilitySubCategories(allFunctionalAbilitySubCategories);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @FXML
    private void btnLoadMentalFunctions() {
        tcFunctionalConditionID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcFunctionalConditionName.setCellValueFactory(new PropertyValueFactory<>("name"));
        try {
            allFunctionalAbilitySubCategories = FXCollections.observableList(dataModelFacade.getFunctionalAbilitySubCategories(2));
            tableViewLoadFunctionalAbilitySubCategories(allFunctionalAbilitySubCategories);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @FXML
    private void btnLoadMobility() {
        tcFunctionalConditionID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcFunctionalConditionName.setCellValueFactory(new PropertyValueFactory<>("name"));
        try {
            allFunctionalAbilitySubCategories = FXCollections.observableList(dataModelFacade.getFunctionalAbilitySubCategories(3));
            tableViewLoadFunctionalAbilitySubCategories(allFunctionalAbilitySubCategories);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @FXML
    private void btnLoadPracticalAssignments() {
        tcFunctionalConditionID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcFunctionalConditionName.setCellValueFactory(new PropertyValueFactory<>("name"));
        try {
            allFunctionalAbilitySubCategories = FXCollections.observableList(dataModelFacade.getFunctionalAbilitySubCategories(4));
            tableViewLoadFunctionalAbilitySubCategories(allFunctionalAbilitySubCategories);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @FXML
    private void btnLoadCommunityLife() {
        tcFunctionalConditionID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcFunctionalConditionName.setCellValueFactory(new PropertyValueFactory<>("name"));
        try {
            allFunctionalAbilitySubCategories = FXCollections.observableList(dataModelFacade.getFunctionalAbilitySubCategories(5));
            tableViewLoadFunctionalAbilitySubCategories(allFunctionalAbilitySubCategories);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    /**
     * When clicked on the little i-icon it sends you to the FS3 homepage
     */
    @FXML
    private void coupingLink() throws URISyntaxException, IOException {
        Desktop.getDesktop().browse(new URI("https://www.fs3.nu/filer/Dokumenter/Metode/FSIII-Guide-til-generelle-oplysninger.pdf?t=1647518630"));
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
        anchorPaneFunctionalCondition.setVisible(false);
    }

    /**
     * Takes you back to the start screen/pane when clicked on logo
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
        anchorPaneFunctionalCondition.setVisible(false);
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
        anchorPaneFunctionalCondition.setVisible(false);
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
        anchorPaneFunctionalCondition.setVisible(false);
        btnCitizenInfo.setVisible(false);
    }

    /**
     * Set up the general information pane
     */
    public void btnClickGeneralInformation() {
        labelTitle.setText("Generelle Informationer for borgeren");
        labelInfo.setText("Overblik over borgerens generelle informationer, hvor det er muligt at redigere og gemme eventuelle ændringer");
        labelInfoNewLine.setText("Her kan du se alle informationer for borgeren");
        anchorPaneStudent.setVisible(false);
        anchorPaneCitizens.setVisible(false);
        anchorPaneFS3.setVisible(true);
        anchorPaneOBS.setVisible(false);
        anchorPaneGeneralInformation.setVisible(true);
        anchorPaneHealthConditions.setVisible(false);
        anchorPaneFunctionalCondition.setVisible(false);
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
        anchorPaneFunctionalCondition.setVisible(false);
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
        anchorPaneFunctionalCondition.setVisible(true);
    }

    /**
     * When click on back it sends you back to the start pane/screen
     */
    @FXML
    private void btnClickBack(ActionEvent actionEvent) {
        btnClickHome(actionEvent);
    }

    /**
     * Logs you out when clicked
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
     */
    @FXML
    private void onActionGeneralInfoCancel() {
        btnClickGeneralInformation();
    }

    public void btnOpenCitizenInfo(ActionEvent actionEvent) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/gui/view/CitizenInfoView.fxml"));

        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();


        citizenInfoViewController = fxmlLoader.getController();
        citizenInfoViewController.setSelectedCitizen(selectedCitizenOnComboBox);

        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
