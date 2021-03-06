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
import be.Utilities.ImageWithText;
import be.enums.ConditionEnum;
import gui.model.Facade.DataModelFacade;
import gui.controller.Interface.IController;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import javafx.util.Callback;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
    private Label lblInfoState;
    @FXML
    private ImageView imgViewNotSaved;
    @FXML
    private ImageView imgViewSaved;
    @FXML
    private TextField txtFieldSchoolID;
    @FXML
    private TextField txtFieldGeneralInfoId;
    @FXML
    private Button btnObservationNote;
    @FXML
    private ImageView imageLoadingGif;

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
     * Citizens own judgement on functional pane
     */
    @FXML
    private TextArea txtAreaNoteOnCondition;
    @FXML
    private ComboBox<ImageWithText> comboBoxCurrentLevel;
    @FXML
    private ComboBox<ImageWithText> comboBoxExpectedLevel;
    @FXML
    private TextArea txtAreaCitizenGoals;
    @FXML
    private ComboBox<String> comboboxPerformance;
    @FXML
    private ComboBox<String> comboboxMeaningOfPerformance;
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
    private TextArea txtAreaCurrentLevelAssessment;
    @FXML
    private ComboBox<String> comboBoxExpectedLevelAssessment;

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
    @FXML
    private TextField txtFieldSearchCitizens;
    @FXML
    private Button btnSearchCitizens;


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
    private ComboBox<Citizen> comboBoxCitizen;
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
    private Label lblInfoStateHC;
    @FXML
    private Label lblInfoStateFC;
    @FXML
    private ImageView imgViewNotSavedHC;
    @FXML
    private ImageView imgViewSavedHC;
    @FXML
    private ImageView imgViewNotSavedFC;
    @FXML
    private ImageView imgViewSavedFC;
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
    @FXML
    private Button btnClose;

    private ObservableList<Citizen> allCitizens = FXCollections.observableArrayList();
    private ObservableList<Case> allCasesOnCitizen = FXCollections.observableArrayList();
    private ObservableList<HealthCondition> allHealthConditions = FXCollections.observableArrayList();
    private ObservableList<HealthConditionSubCategory> allSubCategories = FXCollections.observableArrayList();
    private ObservableList<FunctionalAbilitySubCategoryText> allFunctionalAbilitySubCategories = FXCollections.observableArrayList();

    private Citizen selectedCitizen;
    private Case selectedCase;
    private Citizen selectedCitizenOnComboBox;
    private FunctionalAbilitySubCategoryText selectedFunctionalAbilitySubCategory;
    private HealthConditionSubCategory selectedHealthConditionSubCategory;
    private HealthCondition selectedHealthCondition;

    private final DataModelFacade dataModelFacade = DataModelFacade.getInstance();
    private EditCaseViewController editCaseViewController;
    private ObservationNoteViewController observationNoteViewController;
    private ExecutorService executorService = Executors.newCachedThreadPool();
    private ToggleGroup group;

    private boolean hasSearched = true;
    private ObservableList<Citizen> searchData = FXCollections.observableArrayList();

    public StudentViewController() throws IOException, SQLException {
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

    /**
     * Initialize method for studentViewController.
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnClickFS3();
        selectedCitizen();
        selectedCase();
        selectedCitizenOnComboBox();
        selectedHealthCondition();
        selectedSubCategory();
        selectedFunctionalAbilitySubCategory();
        setupToggleGender();
        setupToggleHealthCondition();
        setFunctionalAbilityComboBoxItems();
        setExpectedLevelAssessmentComboBoxItems();

    }

    /**
     * Inserts value from database to the different tables
     */
    public void initializeTables() throws Exception {
        //Initialize the citizens table
        tcCitizenID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcCitizenFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        tcCitizenLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tcCitizenSSN.setCellValueFactory(new PropertyValueFactory<>("SSN"));
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
    private void seeTxtOnSubCategory() throws SQLException {
        if (txtFieldCitizenID != null && subCatTxtID != null) {
            HealthConditionSubCategoryText subCategoryText = dataModelFacade.getTextOnSubCategory(Integer.parseInt(txtFieldCitizenID.getText()), Integer.parseInt(subCatTxtID.getText()));
            if (subCategoryText != null) {
                txtAreaNoteOnSubCategory.setText(subCategoryText.getProfessionalNote());
                txtAreaCurrentLevelAssessment.setText(subCategoryText.getCurrentLevelAssessment());
                switch (subCategoryText.getExpectedLevelAssessment()) {
                    case "Mindskes" -> comboBoxExpectedLevelAssessment.getSelectionModel().select(0);
                    case "Forbliver u????ndret" -> comboBoxExpectedLevelAssessment.getSelectionModel().select(1);
                    case "Forsvinder" -> comboBoxExpectedLevelAssessment.getSelectionModel().select(2);
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


    /**
     * Method for clearing all the generalInformation txtAreas.
     */
    private void clearGeneralInformation(){
        txtAreaCoping.clear();
        txtAreaMotivation.clear();
        txtAreaResources.clear();
        txtAreaRoles.clear();
        txtAreaHabits.clear();
        txtAreaEducationAndJobs.clear();
        txtAreaLifeStory.clear();
        txtAreaHealthInfo.clear();
        txtAreaEquipmentAids.clear();
        txtAreaHomeLayout.clear();
        txtAreaNetwork.clear();
    }

    /**
     * Method for clearing the HealthCondtionTextFields and radiobuttons.
     */
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

    /**
     * Method for setting the ExpectedLevelAssessment comboBox items.
     */
    private void setExpectedLevelAssessmentComboBoxItems() {
        comboBoxExpectedLevelAssessment.getItems().addAll(
                "Mindskes", "Forbliver u??ndret", "Forsvinder"
        );
    }

    /**
     * Method for setting the functionalAbility comboBox items.
     */
    private void setFunctionalAbilityComboBoxItems() {
        ImageWithText iWT0 = new ImageWithText("gui/view/img/funktionstilstand0.png", 100, 100, false, false, "0");
        ImageWithText iWT1 = new ImageWithText("gui/view/img/funktionstilstand1.png", 100, 100, false, false, "1");
        ImageWithText iWT2 = new ImageWithText("gui/view/img/funktionstilstand2.png", 100, 100, false, false, "2");
        ImageWithText iWT3 = new ImageWithText("gui/view/img/funktionstilstand3.png", 100, 100, false, false, "3");
        ImageWithText iWT4 = new ImageWithText("gui/view/img/funktionstilstand4.png", 100, 100, false, false, "4");
        ImageWithText iWT9 = new ImageWithText("gui/view/img/funktionstilstand9.png", 100, 100, false, false, "9");

        comboBoxCurrentLevel.getItems().addAll(iWT0, iWT1, iWT2, iWT3, iWT4, iWT9);
        comboBoxCurrentLevel.setCellFactory(new Callback<>() {
            @Override
            public ListCell<ImageWithText> call(ListView<ImageWithText> p) {
                return new ListCell<>() {
                    private final ImageView view;

                    {
                        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                        view = new ImageView();
                    }

                    @Override
                    protected void updateItem(ImageWithText item, boolean empty) {
                        super.updateItem(item, empty);

                        if (item == null || empty) {
                            setGraphic(null);
                        } else {
                            view.setImage(item);
                            setGraphic(view);
                        }
                    }
                };
            }
        });

        comboBoxExpectedLevel.getItems().addAll(iWT0, iWT1, iWT2, iWT3, iWT4, iWT9);
        comboBoxExpectedLevel.setCellFactory(new Callback<>() {
            @Override
            public ListCell<ImageWithText> call(ListView<ImageWithText> i) {
                return new ListCell<>() {
                    private final ImageView view;

                    {
                        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                        view = new ImageView();
                    }

                    @Override
                    protected void updateItem(ImageWithText item, boolean empty) {
                        super.updateItem(item, empty);

                        if (item == null || empty) {
                            setGraphic(null);
                        } else {
                            view.setImage(item);
                            setGraphic(view);
                        }
                    }
                };
            }
        });

        comboboxPerformance.getItems().addAll(
                "Udf??rer selv",
                "Udf??rer dele selv",
                "Udf??rer ikke selv",
                "Ikke relevant"
        );
        comboboxMeaningOfPerformance.getItems().addAll(
                "Oplever ikke begr??nsninger",
                "Oplever begr??nsninger"
        );
    }

    /**
     * Setup combobox with citizens
     */
    public void initializeCitizenComboBox() throws SQLException {
        //Initialize the citizens in the dropdown menu
        allCitizens = FXCollections.observableList(dataModelFacade.getAssignedCitizen(Integer.parseInt(txtFieldSchoolID.getText())));
        tableViewLoadCitizens(allCitizens);
        comboBoxCitizen.setItems(allCitizens);
    }

    /**
     * Method for setting the citizens in the comboBoxCitzen.
     *
     * @param citizen
     */
    public void setCitizenComboBoxItems(Citizen citizen) {
        txtFieldCitizenID.setText(String.valueOf(citizen.getId()));
        txtFieldSchoolID.setText(String.valueOf(citizen.getSchoolID()));

        comboBoxCitizen.getSelectionModel().select(citizen);
        btnLogOut.setVisible(false);
        btnClose.setVisible(true);

    }

    /**
     * When clicked on citizen in the combobox, the different text fields get values based on the selected citizen
     */
    @FXML
    private void onActionComboClicked() throws SQLException {
        clearGeneralInformation();
        lblInfoState.setText("??ndringer - Ikke Gemt");
        imgViewNotSaved.setVisible(true);
        imgViewSaved.setVisible(false);
        btnClickGeneralInformation();
        Citizen selectedCitizenComboBox = comboBoxCitizen.getSelectionModel().getSelectedItem();
        txtFieldCitizenID.setText(String.valueOf(selectedCitizenComboBox.getId()));
        Citizen selectedCitizenInfo = dataModelFacade.getInfoOnCitizen(Integer.parseInt(txtFieldCitizenID.getText()));
        txtFieldFirstName.setText(selectedCitizenInfo.getFirstName());
        txtFieldLastName.setText(selectedCitizenInfo.getLastName());
        txtFieldSSN.setText(selectedCitizenInfo.getSSN());
        switch (selectedCitizenInfo.getSex()) {
            case "Mand" -> radioMale.setSelected(true);
            case "Kvinde" -> radioFemale.setSelected(true);
            case "Andet" -> radioOther.setSelected(true);
        }

        GeneralInformation generalInformation = dataModelFacade.getGeneralInformationOnCitizen(Integer.parseInt((txtFieldCitizenID.getText())));
        if (generalInformation != null) {
            txtFieldGeneralInfoId.setText(String.valueOf(generalInformation.getId()));
            txtAreaCoping.setText(generalInformation.getCoping());
            txtAreaMotivation.setText(generalInformation.getMotivation());
            txtAreaResources.setText(generalInformation.getResources());
            txtAreaRoles.setText(generalInformation.getRoles());
            txtAreaHabits.setText(generalInformation.getHabits());
            txtAreaEducationAndJobs.setText(generalInformation.getEducationAndJob());
            txtAreaLifeStory.setText(generalInformation.getLifeStory());
            txtAreaHealthInfo.setText(generalInformation.getHealthInformation());
            txtAreaEquipmentAids.setText(generalInformation.getEquipmentAids());
            txtAreaHomeLayout.setText(generalInformation.getHomeLayout());
            txtAreaNetwork.setText(generalInformation.getNetwork());
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

    /**
     * loads the functionalAbilitySubCategories tableview.
     *
     * @param allFunctionalAbilitySubCategories
     */
    private void tableViewLoadFunctionalAbilitySubCategories(ObservableList<FunctionalAbilitySubCategoryText> allFunctionalAbilitySubCategories) {
        tvFunctionalConditions.setItems(getFunctionalAbilitySubCategories());
    }

    /**
     * Gets the functionalAbilitySubCategories data.
     *
     * @return
     */
    private ObservableList<FunctionalAbilitySubCategoryText> getFunctionalAbilitySubCategories() {
        return allFunctionalAbilitySubCategories;
    }

    /**
     * Loads the tableview for the citizens, when search is pressed.
     */
    private void searchTableViewLoad(ObservableList<Citizen> searchData) {
        tvCitizens.setItems(getSearchData());
    }

    /**
     * @return searchData;
     */
    private ObservableList<Citizen> getSearchData() {
        return searchData;
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
     * Selects a case from the citizens TableView
     * Also opens the case window if you choose to double click
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
                            seeCasesOnCitizen();
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
     * Selects a citizen from the combobox
     */
    public void selectedCitizenOnComboBox() {
        this.comboBoxCitizen.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            if (newValue != null) {
                this.selectedCitizenOnComboBox = newValue;
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
                clearHealthConditionTxtField();
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
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }));
    }

    /**
     * Selects a sub category from the tableview
     */
    private void selectedFunctionalAbilitySubCategory() {
        this.tvFunctionalConditions.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            if (newValue != null) {
                this.selectedFunctionalAbilitySubCategory = newValue;
                txtFieldFunctionalAbilityID.setText(String.valueOf(selectedFunctionalAbilitySubCategory.getId()));
                try {
                    setFunctionalAbilityInfo();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }));
    }

    /**
     * Save the changes if there are any
     */
    @FXML
    private void onActionGeneralInfoSave() throws Exception {
        if (selectedCitizenOnComboBox == null) {
            ErrorHandlerController.createWarning("Select Citizen", "Remember to select a citizen from the Combobox");
            clearHealthCondition();
        } else {
            int citizenId = Integer.parseInt(txtFieldCitizenID.getText());
            String citizenFirstName = txtFieldFirstName.getText();
            String citizenLastName = txtFieldLastName.getText();
            String citizenSSN = txtFieldSSN.getText();
            int schoolId = Integer.parseInt(txtFieldSchoolID.getText());
            String sex = null;
            if (radioMale.isSelected()) {
                sex = "Mand";
            } else if (radioFemale.isSelected()) {
                sex = "Kvinde";
            } else if (radioOther.isSelected()) {
                sex = "Andet";
            }
            Citizen citizen = new Citizen(citizenId, citizenFirstName, citizenLastName, citizenSSN, sex, schoolId);
            dataModelFacade.editCitizen(citizen);

            String coping = txtAreaCoping.getText();
            String motivation = txtAreaMotivation.getText();
            String resources = txtAreaResources.getText();
            String roles = txtAreaRoles.getText();
            String habits = txtAreaHabits.getText();
            String educationAndJob = txtAreaEducationAndJobs.getText();
            String lifeStory = txtAreaLifeStory.getText();
            String network = txtAreaNetwork.getText();
            String healthInformation = txtAreaHealthInfo.getText();
            String equipmentAids = txtAreaEquipmentAids.getText();
            String homeLayout = txtAreaHomeLayout.getText();

            if (txtFieldGeneralInfoId.getText().isEmpty()) {
                dataModelFacade.createGeneralInformation(citizenId, coping, motivation, resources, roles, habits, educationAndJob,
                        lifeStory, network, healthInformation, equipmentAids, homeLayout);
            } else {
                int generalInfoId = Integer.parseInt(txtFieldGeneralInfoId.getText());
                GeneralInformation generalInformation = new GeneralInformation(generalInfoId, coping, motivation, resources, roles, habits, educationAndJob,
                        lifeStory, network, healthInformation, equipmentAids, homeLayout);
                dataModelFacade.editGeneralInformation(generalInformation);
            }

            lblInfoState.setText("??ndringer - Gemt");
            imgViewNotSaved.setVisible(false);
            imgViewSaved.setVisible(true);
        }
    }

    /**
     * When a citizen is selected all values is loaded in the text fields
     */
    public void setGeneralInfoFromID(String citizenID, String generalInfoId) throws SQLException {
        txtFieldCitizenID.setText(citizenID);
        GeneralInformation selectedGeneralInformation = dataModelFacade.getGeneralInformationOnCitizen(Integer.parseInt((txtFieldCitizenID.getText())));
        txtFieldGeneralInfoId.setText(generalInfoId);
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
        lblInfoState.setText("??ndringer - Ikke Gemt");
        imgViewSaved.setVisible(false);
        imgViewNotSaved.setVisible(true);


    }

    /**
     * Method for setting the info for a citizen, with data from the database.
     *
     * @param citizenID
     * @throws SQLException
     */
    public void setCitizenInfo(int citizenID) throws SQLException {
        txtFieldCitizenID.setText(String.valueOf(citizenID));

        Citizen selectedCitizenInfo = dataModelFacade.getInfoOnCitizen(Integer.parseInt(txtFieldCitizenID.getText()));
        txtFieldFirstName.setText(selectedCitizenInfo.getFirstName());
        txtFieldLastName.setText(selectedCitizenInfo.getLastName());
        txtFieldSSN.setText(selectedCitizenInfo.getSSN());
        radioMale.setToggleGroup(group);
        radioFemale.setToggleGroup(group);
        radioOther.setToggleGroup(group);
        switch (selectedCitizenInfo.getSex()) {
            case "Mand" -> radioMale.setSelected(true);
            case "Kvinde" -> radioFemale.setSelected(true);
            case "Andet" -> radioOther.setSelected(true);
        }
    }


    /**
     * Method for setting the functionalAbility info.
     *
     * @throws SQLException
     */
    private void setFunctionalAbilityInfo() throws SQLException {

        if (txtFieldCitizenID != null && txtFieldFunctionalAbilityID != null) {
            FunctionalAbilitySubCategoryText functionalAbilitySubCategoryText = dataModelFacade.getInfoOnSubCategory(Integer.parseInt((txtFieldCitizenID.getText())), Integer.parseInt((txtFieldFunctionalAbilityID.getText())));

            if (functionalAbilitySubCategoryText != null) {

                int abilityCurrentLevel = functionalAbilitySubCategoryText.getAbilityNow();
                switch (abilityCurrentLevel) {
                    case 0 -> comboBoxCurrentLevel.getSelectionModel().select(0);
                    case 1 -> comboBoxCurrentLevel.getSelectionModel().select(1);
                    case 2 -> comboBoxCurrentLevel.getSelectionModel().select(2);
                    case 3 -> comboBoxCurrentLevel.getSelectionModel().select(3);
                    case 4 -> comboBoxCurrentLevel.getSelectionModel().select(4);
                    case 9 -> comboBoxCurrentLevel.getSelectionModel().select(5);
                    default -> {
                    }
                }


                int abilityExpectedLevel = functionalAbilitySubCategoryText.getAbilityExpected();
                switch (abilityExpectedLevel) {
                    case 0 -> comboBoxExpectedLevel.getSelectionModel().select(0);
                    case 1 -> comboBoxExpectedLevel.getSelectionModel().select(1);
                    case 2 -> comboBoxExpectedLevel.getSelectionModel().select(2);
                    case 3 -> comboBoxExpectedLevel.getSelectionModel().select(3);
                    case 4 -> comboBoxExpectedLevel.getSelectionModel().select(4);
                    case 9 -> comboBoxExpectedLevel.getSelectionModel().select(5);
                    default -> {
                    }
                }

                txtAreaNoteOnCondition.setText(functionalAbilitySubCategoryText.getAbilityNote());

                switch (functionalAbilitySubCategoryText.getCitizenPerformance()) {
                    case "Udf??rer selv" -> comboboxPerformance.getSelectionModel().select(0);
                    case "Udf??rer dele selv" -> comboboxPerformance.getSelectionModel().select(1);
                    case "Udf??rer ikke selv" -> comboboxPerformance.getSelectionModel().select(2);
                    case "Ikke relevant" -> comboboxPerformance.getSelectionModel().select(3);
                }

                if (functionalAbilitySubCategoryText.getCitizenMeaningOfPerformance().equals("Oplever ikke begr??nsninger")) {
                    comboboxMeaningOfPerformance.getSelectionModel().select(0);
                } else if (functionalAbilitySubCategoryText.getCitizenMeaningOfPerformance().equals("Oplever begr??nsninger")) {
                    comboboxMeaningOfPerformance.getSelectionModel().select(1);
                }

                txtAreaCitizenGoals.setText(functionalAbilitySubCategoryText.getAbilityNoteCitizen());
            } else {
                clearFunctionalAbilityTextFields();
            }
        }
    }

    /**
     * Method for clearing the functionalAbilityTextFields.
     */
    private void clearFunctionalAbilityTextFields() {
        comboBoxCurrentLevel.getSelectionModel().clearSelection();
        comboBoxExpectedLevel.getSelectionModel().clearSelection();
        txtAreaNoteOnCondition.clear();
        comboboxPerformance.getSelectionModel().clearSelection();
        comboboxMeaningOfPerformance.getSelectionModel().clearSelection();
        txtAreaCitizenGoals.clear();
    }

    /**
     * Method for clearing the healthConditions.
     */
    private void clearHealthCondition() {
        txtAreaCoping.clear();
        txtAreaMotivation.clear();
        txtAreaResources.clear();
        txtAreaRoles.clear();
        txtAreaHabits.clear();
        txtAreaEducationAndJobs.clear();
        txtAreaLifeStory.clear();
        txtAreaNetwork.clear();
        txtAreaHealthInfo.clear();
        txtAreaEquipmentAids.clear();
        txtAreaHomeLayout.clear();
    }

    /**
     * On action method for saving the functionalAbility info.
     * If there is already data, the editFunctionalAbility method from the dataModelFacade is used.
     * If there is not data, the createFunctionalAbility method from the dataModelFacade is used.
     * The citizen data will always be using the editCitizen method from the facade.
     *
     * @throws SQLException
     */
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
            lblInfoStateFC.setText("??ndringer - Gemt");
            imgViewNotSavedFC.setVisible(false);
            imgViewSavedFC.setVisible(true);
            clearFunctionalAbilityTextFields();
            tvFunctionalConditions.getSelectionModel().clearSelection();
        }

    }

    /**
     * OnAction method for saving the HealthConditions.
     * Uses the inserIntoSubCategory method if data is created, and uses the editSubCategory method if data is edited.
     *
     * @throws Exception
     */
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
                    dataModelFacade.insertIntoSubCategory(citizenId, subCategoryId, professionalNote, currentLevelAssessment, expectedLevelAssessment, conditionValue);
                } else if (radioRelevant.isSelected()) {
                    int conditionValue = ConditionEnum.RELEVANT.getValue();
                    dataModelFacade.insertIntoSubCategory(citizenId, subCategoryId, professionalNote, currentLevelAssessment, expectedLevelAssessment, conditionValue);
                }
            } else {
                int citizenId = Integer.parseInt(txtFieldCitizenID.getText());
                int subCategoryId = Integer.parseInt(subCatTxtID.getText());
                String professionalNote = txtAreaNoteOnSubCategory.getText();
                String currentLevelAssessment = txtAreaCurrentLevelAssessment.getText();
                String expectedLevelAssessment = String.valueOf(comboBoxExpectedLevelAssessment.getSelectionModel().getSelectedItem());
                if (radioNotRelevant.isSelected()) {
                    int conditionValue = ConditionEnum.NOT_RELEVANT.getValue();
                    HealthConditionSubCategoryText subCategoryText = new HealthConditionSubCategoryText(citizenId, subCategoryId, professionalNote, currentLevelAssessment, expectedLevelAssessment, conditionValue);
                    dataModelFacade.editSubcategory(subCategoryText);
                } else if (radioPotential.isSelected()) {
                    int conditionValue = ConditionEnum.POTENTIAL.getValue();
                    HealthConditionSubCategoryText subCategoryText = new HealthConditionSubCategoryText(citizenId, subCategoryId, professionalNote, currentLevelAssessment, expectedLevelAssessment, conditionValue);
                    dataModelFacade.editSubcategory(subCategoryText);
                } else if (radioRelevant.isSelected()) {
                    int conditionValue = ConditionEnum.RELEVANT.getValue();
                    HealthConditionSubCategoryText subCategoryText = new HealthConditionSubCategoryText(citizenId, subCategoryId, professionalNote, currentLevelAssessment, expectedLevelAssessment, conditionValue);
                    dataModelFacade.editSubcategory(subCategoryText);
                }
            }
            lblInfoStateHC.setText("??ndringer - Gemt");
            imgViewNotSavedHC.setVisible(false);
            imgViewSavedHC.setVisible(true);
            clearHealthConditionTxtField();
            tvSubCategories.getSelectionModel().clearSelection();
        }
    }

    /**
     * OnACtion method for when cancel button is pressed.
     */
    @FXML
    private void btnHandelCancelChangesHC() {
        txtAreaNoteOnSubCategory.clear();
        radioNotRelevant.setSelected(false);
        radioPotential.setSelected(false);
        radioRelevant.setSelected(false);
        tvSubCategories.getSelectionModel().clearSelection();
        tvHealthConditions.getSelectionModel().clearSelection();
    }


    /**
     * Method for loading the tableview with selfCare subcategories when selfCare button is pressed.
     */
    @FXML
    private void btnLoadSelfCare() {
        tcFunctionalConditionID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcFunctionalConditionName.setCellValueFactory(new PropertyValueFactory<>("name"));
        try {
            allFunctionalAbilitySubCategories = FXCollections.observableList(dataModelFacade.getFunctionalAbilitySubCategories(1));
            tableViewLoadFunctionalAbilitySubCategories(allFunctionalAbilitySubCategories);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    /**
     * Method for loading the tableview with mental function subcategories when mentalFunctions button is pressed.
     */
    @FXML
    private void btnLoadMentalFunctions() throws SQLException {
        tcFunctionalConditionID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcFunctionalConditionName.setCellValueFactory(new PropertyValueFactory<>("name"));
        try {
            allFunctionalAbilitySubCategories = FXCollections.observableList(dataModelFacade.getFunctionalAbilitySubCategories(2));
            tableViewLoadFunctionalAbilitySubCategories(allFunctionalAbilitySubCategories);
        } catch (SQLException sqlException) {
            throw new SQLException();
        }
    }

    /**
     * Method for loading the tableview with mobility subcategories when mobility button is pressed.
     */
    @FXML
    private void btnLoadMobility() throws SQLException {
        tcFunctionalConditionID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcFunctionalConditionName.setCellValueFactory(new PropertyValueFactory<>("name"));
        try {
            allFunctionalAbilitySubCategories = FXCollections.observableList(dataModelFacade.getFunctionalAbilitySubCategories(3));
            tableViewLoadFunctionalAbilitySubCategories(allFunctionalAbilitySubCategories);
        } catch (SQLException sqlException) {
            throw new SQLException();
        }
    }

    /**
     * Method for loading the tableview with practical assignments subcategories when PracticalAssignments button is pressed.
     */
    @FXML
    private void btnLoadPracticalAssignments() throws SQLException {
        tcFunctionalConditionID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcFunctionalConditionName.setCellValueFactory(new PropertyValueFactory<>("name"));
        try {
            allFunctionalAbilitySubCategories = FXCollections.observableList(dataModelFacade.getFunctionalAbilitySubCategories(4));
            tableViewLoadFunctionalAbilitySubCategories(allFunctionalAbilitySubCategories);
        } catch (SQLException sqlException) {
            throw new SQLException();
        }
    }

    /**
     * Method for loading the tableview with Community Life subcategories when CommunityLife button is pressed.
     */
    @FXML
    private void btnLoadCommunityLife() throws SQLException {
        tcFunctionalConditionID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcFunctionalConditionName.setCellValueFactory(new PropertyValueFactory<>("name"));
        try {
            allFunctionalAbilitySubCategories = FXCollections.observableList(dataModelFacade.getFunctionalAbilitySubCategories(5));
            tableViewLoadFunctionalAbilitySubCategories(allFunctionalAbilitySubCategories);
        } catch (SQLException sqlException) {
            throw new SQLException();
        }
    }


    /**
     * OnAction method for pressing the observationNote button.
     * Opens the observationNoteView when pressed.
     *
     * @throws Exception
     */
    @FXML
    private void btnHandleObservationNote() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/gui/view/ObservationNoteView.fxml"));

        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();


        observationNoteViewController = fxmlLoader.getController();
        observationNoteViewController.setSelectedCitizen(selectedCitizenOnComboBox);

        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }


    /**
     * When clicked on the little i-icon it sends you to the FS3 homepage
     */
    @FXML
    private void coupingLink() throws URISyntaxException, IOException {
        Desktop.getDesktop().browse(new URI("http://www.fs3.nu/filer/Dokumenter/Metode/FSIII-Guide-til-generelle-oplysninger.pdf?t=1647518630"));
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
        labelInfoNewLine.setText("Klik p?? en borger for at se hvilke sager der er tildelt den valgte borger");
        anchorPaneStudent.setVisible(false);
        anchorPaneCitizens.setVisible(true);
        anchorPaneFS3.setVisible(false);
        anchorPaneGeneralInformation.setVisible(false);
        anchorPaneHealthConditions.setVisible(false);
        anchorPaneFunctionalCondition.setVisible(false);
        tvSubCategories.getSelectionModel().clearSelection();
        tvHealthConditions.getSelectionModel().clearSelection();
    }


    /**
     * Set up the FS3 pane
     */
    @FXML
    private void btnClickFS3() {
        labelTitle.setText("Rapporter og ??v F??lles Sprog 3");
        labelInfo.setText("I dette vindue kan du v??lge at se en borgers generelle info, helbredstilstande og funktionstilstand");
        labelInfoNewLine.setText("Med taskbaren under denne tekst, kan du v??lge tre forskellige vinduer, hvor du kan rapportere og ??ve F??lles Sprog 3");
        anchorPaneStudent.setVisible(false);
        anchorPaneCitizens.setVisible(false);
        anchorPaneFS3.setVisible(true);
        anchorPaneOBS.setVisible(true);
        anchorPaneGeneralInformation.setVisible(false);
        anchorPaneHealthConditions.setVisible(false);
        anchorPaneFunctionalCondition.setVisible(false);
        btnCitizenInfo.setVisible(false);
        btnObservationNote.setVisible(false);
    }

    /**
     * Set up the general information pane
     */
    public void btnClickGeneralInformation() {
        labelTitle.setText("Generelle Informationer for borgeren");
        labelInfo.setText("Overblik over borgerens generelle informationer, hvor det er muligt at redigere og gemme eventuelle ??ndringer");
        labelInfoNewLine.setText("Her kan du se alle informationer for borgeren, samt se alle opsummerede informationer ved at trykke p?? knappen 'Se alle borgerens informationer'");
        anchorPaneStudent.setVisible(false);
        anchorPaneCitizens.setVisible(false);
        anchorPaneFS3.setVisible(true);
        anchorPaneOBS.setVisible(false);
        anchorPaneGeneralInformation.setVisible(true);
        anchorPaneHealthConditions.setVisible(false);
        anchorPaneFunctionalCondition.setVisible(false);
        btnCitizenInfo.setVisible(true);
        btnObservationNote.setVisible(false);

    }

    /**
     * Set up the health condition pane
     */
    @FXML
    private void btnClickHealthCondition() {
        labelTitle.setText("Helbredstilstande for borgeren");
        labelInfo.setText("Overblik over borgerens helbredstilstande, hvor det er muligt at ??ndre og gemme eventuelle observationer og ??ndringer");
        labelInfoNewLine.setText("Her er det muligt at rapportere borgerens helbredstilstande og ??ve FS3. Der skal tages stilling til alle tilstande.");
        anchorPaneStudent.setVisible(false);
        anchorPaneCitizens.setVisible(false);
        anchorPaneFS3.setVisible(true);
        anchorPaneOBS.setVisible(false);
        anchorPaneGeneralInformation.setVisible(false);
        anchorPaneHealthConditions.setVisible(true);
        anchorPaneFunctionalCondition.setVisible(false);
        btnCitizenInfo.setVisible(true);
        btnObservationNote.setVisible(true);
    }

    /**
     * Set up the functional condition pane
     */
    @FXML
    private void btnClickFunctionalCondition() {
        labelTitle.setText("Funktionsevnetilstanden for borgeren");
        labelInfo.setText("Overblik over borgerens funktionsevnetilstand, hvor der skal tages stilling til den nuv??rende funktionstilstand");
        labelInfoNewLine.setText("Her er det muligt at se et skema over definitionen p?? funktionsevnetilstandene, men der skal ogs?? tages stilling til den forventede funktionstilstand");
        anchorPaneStudent.setVisible(false);
        anchorPaneCitizens.setVisible(false);
        anchorPaneFS3.setVisible(true);
        anchorPaneOBS.setVisible(false);
        anchorPaneGeneralInformation.setVisible(false);
        anchorPaneHealthConditions.setVisible(false);
        anchorPaneFunctionalCondition.setVisible(true);
        btnCitizenInfo.setVisible(true);
        btnObservationNote.setVisible(true);
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

    /**
     * OnAction method for clicking the citizenInfo button.
     * Opens the citizenInfoView when clicked, and sets the selected citizens info, using the citizenInfoViewController-
     * method setSelectedCitizen.
     *
     * @throws Exception
     */
    public void btnOpenCitizenInfo() {
        executorService.submit(() -> {
            try {
                imageLoadingGif.setVisible(true);
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/gui/view/CitizenInfoView.fxml"));

                Scene scene = new Scene(fxmlLoader.load());

                CitizenInfoViewController citizenInfoViewController = fxmlLoader.getController();
                citizenInfoViewController.setSelectedCitizen(selectedCitizenOnComboBox);

                Platform.runLater(() -> {
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setResizable(false);
                    stage.show();
                    imageLoadingGif.setVisible(false);

                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }


    /**
     * OnAction method for clicking the btnClose. It closes the stage when pressed.
     *
     * @param actionEvent
     */
    public void btnClickClose(ActionEvent actionEvent) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
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
            btnSearchCitizens.setText("????");
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
}
