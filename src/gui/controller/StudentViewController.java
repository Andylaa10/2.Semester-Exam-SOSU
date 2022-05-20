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

public class StudentViewController implements IController, Initializable {

    public TextField txtFieldSearchCitizens;
    public Button btnSearchCitizens;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setAnchorPanesVisibility();
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

        System.out.println(Thread.currentThread().isAlive());
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
                switch (subCategoryText.getExpectedLevelAssessment()) {
                    case "Mindskes" -> comboBoxExpectedLevelAssessment.getSelectionModel().select(0);
                    case "Forbliver uÃ¦ndret" -> comboBoxExpectedLevelAssessment.getSelectionModel().select(1);
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
            changeColorOnTV(subCategoryText);
        }
    }

    public void changeColorOnTV(HealthConditionSubCategoryText subCategoryText) {
        Callback<TableColumn<HealthConditionSubCategory, String>, TableCell<HealthConditionSubCategory, String>> cellFactory = new Callback<>() {
            @Override
            public TableCell<HealthConditionSubCategory, String> call(final TableColumn<HealthConditionSubCategory, String> param) {
                return new TableCell<>() {

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            setText(item);
                            TableRow<HealthConditionSubCategory> row = getTableRow();
                            if (subCategoryText == null) {
                                row.setStyle("");
                            } else if (row.getItem().getCondition(subCategoryText) == 0 && row.getItem().getSubCategoryName().equals(selectedHealthConditionSubCategory.getSubCategoryName())) {
                                row.getStyleClass().clear();
                                row.setStyle("-fx-background-color: red");
                            } else if (row.getItem().getCondition(subCategoryText) == 1 && row.getItem().getSubCategoryName().equals(selectedHealthConditionSubCategory.getSubCategoryName())) {
                                row.getStyleClass().clear();
                                row.setStyle("-fx-background-color: yellow");
                            } else if (row.getItem().getCondition(subCategoryText) == 2 && row.getItem().getSubCategoryName().equals(selectedHealthConditionSubCategory.getSubCategoryName())) {
                                row.getStyleClass().clear();
                                row.setStyle("-fx-background-color: green");
                            } else {
                                row.setStyle("");
                            }
                        }
                    }
                };
            }
        };
        tcSubCategoriesName.setCellFactory(cellFactory);
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

    private void setExpectedLevelAssessmentComboBoxItems() {
        comboBoxExpectedLevelAssessment.getItems().addAll(
                "Mindskes", "Forbliver uændret", "Forsvinder"
        );
    }

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
    public void initializeCitizenComboBox() {
        //Initialize the citizens in the dropdown menu
        allCitizens = FXCollections.observableList(dataModelFacade.getAssignedCitizen(Integer.parseInt(txtFieldSchoolID.getText())));
        tableViewLoadCitizens(allCitizens);
        comboBoxCitizen.setItems(allCitizens);
    }

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
        lblInfoState.setText("Ændringer - Ikke Gemt");
        imgViewNotSaved.setVisible(true);
        imgViewSaved.setVisible(false);
        btnClickGeneralInformation();
        Citizen selectedCitizenComboBox = comboBoxCitizen.getSelectionModel().getSelectedItem();
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
        new Thread(() -> {
            tvCases.setItems(getCasesOnCitizenData());
        }).start();
        System.out.println(Thread.currentThread().isAlive());

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
        new Thread(() -> {
            tvHealthConditions.setItems(getHealthConditionData());
        }).start();
        System.out.println(Thread.currentThread().isAlive());

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
        new Thread(() -> {
            tvSubCategories.setItems(getSubCategories());
        }).start();
        System.out.println(Thread.currentThread().isAlive());

    }

    /**
     * Gets the data for sub categories
     */
    private ObservableList<HealthConditionSubCategory> getSubCategories() {
        return allSubCategories;
    }

    private void tableViewLoadFunctionalAbilitySubCategories(ObservableList<FunctionalAbilitySubCategoryText> allFunctionalAbilitySubCategories) {
        new Thread(() -> {
            tvFunctionalConditions.setItems(getFunctionalAbilitySubCategories());

        }).start();
        System.out.println(Thread.currentThread().isAlive());
    }

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
                } catch (SQLServerException e) {
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
            Citizen citizen = new Citizen(citizenId, citizenFirstName, citizenLastName, citizenSSN, citizenAddress, sex, schoolId);
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

            lblInfoState.setText("Ændringer - Gemt");
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
        lblInfoState.setText("Ændringer - Ikke Gemt");
        imgViewSaved.setVisible(false);
        imgViewNotSaved.setVisible(true);


    }

    public void setCitizenInfo(int citizenID) throws SQLException {
        txtFieldCitizenID.setText(String.valueOf(citizenID));

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
    }


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
                    case "Udfører selv" -> comboboxPerformance.getSelectionModel().select(0);
                    case "Udfører dele selv" -> comboboxPerformance.getSelectionModel().select(1);
                    case "Udfører ikke selv" -> comboboxPerformance.getSelectionModel().select(2);
                    case "Ikke relevant" -> comboboxPerformance.getSelectionModel().select(3);
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
            lblInfoStateFC.setText("Ændringer - Gemt");
            imgViewNotSavedFC.setVisible(false);
            imgViewSavedFC.setVisible(true);
            clearFunctionalAbilityTextFields();
            tvFunctionalConditions.getSelectionModel().clearSelection();
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
                    tcSubCategoriesName.setStyle("");
                    tcSubCategoriesName.setStyle("-fx-text-fill: red");
                } else if (radioPotential.isSelected()) {
                    int conditionValue = ConditionEnum.POTENTIAL.getValue();
                    dataModelFacade.insertIntoSubCategory(citizenId, subCategoryId, professionalNote, currentLevelAssessment, expectedLevelAssessment, conditionValue);
                    tcSubCategoriesName.setStyle("");
                    tcSubCategoriesName.setStyle("-fx-text-fill: yellow");
                } else if (radioRelevant.isSelected()) {
                    int conditionValue = ConditionEnum.RELEVANT.getValue();
                    dataModelFacade.insertIntoSubCategory(citizenId, subCategoryId, professionalNote, currentLevelAssessment, expectedLevelAssessment, conditionValue);
                    tcSubCategoriesName.setStyle("");
                    tcSubCategoriesName.setStyle("-fx-text-fill: green");
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
                    tcSubCategoriesName.setStyle("");
                    tcSubCategoriesName.setStyle("-fx-text-fill: red");
                } else if (radioPotential.isSelected()) {
                    int conditionValue = ConditionEnum.POTENTIAL.getValue();
                    HealthConditionSubCategoryText subCategoryText = new HealthConditionSubCategoryText(citizenId, subCategoryId, professionalNote, currentLevelAssessment, expectedLevelAssessment, conditionValue);
                    dataModelFacade.editSubcategory(subCategoryText);
                    tcSubCategoriesName.setStyle("");
                    tcSubCategoriesName.setStyle("-fx-text-fill: yellow");
                } else if (radioRelevant.isSelected()) {
                    int conditionValue = ConditionEnum.RELEVANT.getValue();
                    HealthConditionSubCategoryText subCategoryText = new HealthConditionSubCategoryText(citizenId, subCategoryId, professionalNote, currentLevelAssessment, expectedLevelAssessment, conditionValue);
                    dataModelFacade.editSubcategory(subCategoryText);
                    tcSubCategoriesName.setStyle("");
                    tcSubCategoriesName.setStyle("-fx-text-fill: green");
                } else {
                    tcSubCategoriesName.setStyle("");
                }
            }
            lblInfoStateHC.setText("Ændringer - Gemt");
            imgViewNotSavedHC.setVisible(false);
            imgViewSavedHC.setVisible(true);
            clearHealthConditionTxtField();
            tvSubCategories.getSelectionModel().clearSelection();
        }
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
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

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

    @FXML
    private void btnHandleObservationFA() throws Exception {
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

    @FXML
    private void btnHandleObservationHC() throws Exception {
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
        labelInfoNewLine.setText("Her er det muligt at se et skema over definitionen på funktionsevnetilstandene, men der skal også tages stilling til den forventede funktionstilstand");
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

    public void btnOpenCitizenInfo() throws Exception {
        long startTime = System.currentTimeMillis();

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/gui/view/CitizenInfoView.fxml"));

        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();


        CitizenInfoViewController citizenInfoViewController = fxmlLoader.getController();
        citizenInfoViewController.setSelectedCitizen(selectedCitizenOnComboBox);

        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        long endTime = System.currentTimeMillis();

        System.out.println("That took " + (endTime - startTime) + " milliseconds");
    }


    public void btnClickClose(ActionEvent actionEvent) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

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
}
