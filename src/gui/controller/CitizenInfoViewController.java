package gui.controller;

import be.Case;
import be.Citizen;
import be.FunctionalAbilities.FunctionalAbilitySubCategoryText;
import be.HealthCondition.HealthCondition;
import be.HealthCondition.HealthConditionSubCategory;
import be.HealthCondition.HealthConditionSubCategoryText;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import gui.Facade.DataModelFacade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CitizenInfoViewController implements Initializable {

    @FXML
    private Label lblName;
    @FXML
    private Label lblSSN;
    @FXML
    private Label lblAddress;
    @FXML
    private TextArea txtFieldObservationNote;
    @FXML
    private TextField txtFieldFollowUpDate;
    @FXML
    private Pane casePane;
    @FXML
    private TextField txtFieldCaseName;
    @FXML
    private TextArea txtAreaCaseInfo;
    @FXML
    private Pane healthConditionPane;
    @FXML
    private TextField txtFieldHealthCondition;
    @FXML
    private TextArea txtAreaCurrentLevel;
    @FXML
    private TextField txtFieldHCSubcategory;
    @FXML
    private TextField txtFieldExpectedLevel;
    @FXML
    private TextField txtFieldHCRelevance;
    @FXML
    private Pane functionalAbilityPane;
    @FXML
    private TextField txtFieldFunctionalAbility;
    @FXML
    private TextArea txFieldNote;
    @FXML
    private TextField txtFieldFASubCategory;
    @FXML
    private TextField txtFieldMeaningOfPerformance;
    @FXML
    private TextField txtFieldCurrentLvl;
    @FXML
    private TextField txtFieldFAExpectedLevel;
    @FXML
    private TextField txtFieldPerformance;
    @FXML
    private TextArea txtFieldCitizenGoals;
    @FXML
    private VBox vBoxFunctionalAbilities;
    @FXML
    private VBox vBoxHealthCondition;
    @FXML
    private VBox vBoxCase;
    @FXML
    private TextField txtFieldCitizenID;
    @FXML
    private AnchorPane anchorPaneCases;
    @FXML
    private Button addNewCaseToVboxBtn;
    @FXML
    private Button addNewHCToVBoxBtn;
    @FXML
    private AnchorPane anchorPaneHealthConditions;
    @FXML
    private Button addNewFAToVboxBtn;


    private int citizenId;
    private int caseId = 0;
    private int healthConditionId = 0;
    private int functionalAbilityId = 0;

    private DataModelFacade dataModelFacade;
    private ObservableList<Citizen> allCitizens = FXCollections.observableArrayList();
    private ObservableList<Case> allCasesOnCitizen = FXCollections.observableArrayList();
    private ObservableList<HealthConditionSubCategoryText> allSubCategories = FXCollections.observableArrayList();
    private ObservableList<FunctionalAbilitySubCategoryText> allFunctionalAbilitySubCategories = FXCollections.observableArrayList();

    public CitizenInfoViewController() throws IOException {
        this.dataModelFacade = new DataModelFacade();
    }

    public void setSelectedCitizen(Citizen citizen) throws SQLException {
        txtFieldCitizenID.setText(String.valueOf(citizen.getId()));
        lblName.setText(citizen.getFirstName() + " " + citizen.getLastName());
        lblSSN.setText(citizen.getSSN());
        lblAddress.setText(citizen.getAddress());

        citizenId = Integer.parseInt(txtFieldCitizenID.getText());
        createCases();
        createHealthConditions();
        createFunctionalAbilities();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    private void createCases() throws SQLException {
        allCasesOnCitizen = FXCollections.observableList(dataModelFacade.getCasesOnCitizen(citizenId));
        for (Case casesOnCitizen : allCasesOnCitizen) {
            caseId = casesOnCitizen.getId();
            newCaseToVBox(caseId);
        }
    }

    private void createHealthConditions() throws SQLException {
        allSubCategories = FXCollections.observableList(dataModelFacade.getHCInfoOnSubCategories(citizenId));
        for (HealthConditionSubCategoryText HCSubCategoryText : allSubCategories) {
            healthConditionId = HCSubCategoryText.getCategoryId();
            System.out.println(allFunctionalAbilitySubCategories);
            newHCToVBox(healthConditionId);
        }

    }

    private void createFunctionalAbilities() throws SQLException {
        allFunctionalAbilitySubCategories = FXCollections.observableList(dataModelFacade.getFAInfoOnSubCategories(citizenId));
        for (FunctionalAbilitySubCategoryText FAOnCitizen : allFunctionalAbilitySubCategories) {
            functionalAbilityId = FAOnCitizen.getSubCategoryId();
            newFAToVBox(functionalAbilityId);
        }
    }


    private void newCaseToVBox(int caseId) throws SQLException {
        Case aCase = dataModelFacade.getCaseOnCitizen(Integer.parseInt(txtFieldCitizenID.getText()), caseId);
        Label labelName = new Label("Sagsnavn");
        labelName.setFont(Font.font(18.0));
        TextField txtFieldCaseName = new TextField();
        txtFieldCaseName.setPrefWidth(382);
        txtFieldCaseName.setMaxWidth(382);
        txtFieldCaseName.setPrefHeight(26);
        txtFieldCaseName.setMaxHeight(26);
        txtFieldCaseName.setFont(Font.font(12.0));
        txtFieldCaseName.setText(aCase.getName());
        Label labelInfo = new Label("Sagens informationer");
        labelInfo.setFont(Font.font(18.0));
        TextArea textAreaCaseInfo = new TextArea();
        textAreaCaseInfo.setMinHeight(118);
        textAreaCaseInfo.setMinWidth(486);
        textAreaCaseInfo.setPrefHeight(118);
        textAreaCaseInfo.setMaxHeight(118);
        textAreaCaseInfo.setPrefWidth(486);
        textAreaCaseInfo.setMaxWidth(486);
        textAreaCaseInfo.setText(aCase.getInfo());
        Label labelNewLine = new Label();
        Label labelNewLine2 = new Label();
        Label labelNewLine3 = new Label();
        Line line = new Line(0, 0, 490, 0);
        VBox vBoxNewCase = new VBox();
        vBoxNewCase.getChildren().addAll(labelName, txtFieldCaseName, labelInfo, textAreaCaseInfo, labelNewLine, labelNewLine2, line, labelNewLine3);
        vBoxNewCase.setPadding(new Insets(5, 5, 5, 20));
        vBoxCase.getChildren().add(vBoxNewCase);
    }


    public void newHCToVBox(int healthConditionID) throws SQLServerException {
        HealthConditionSubCategoryText HCSubCategoryText = dataModelFacade.getTextOnSubCategory(Integer.parseInt(txtFieldCitizenID.getText()), healthConditionId);

        HBox hBox1 = new HBox();
        HBox hBox2 = new HBox();
        HBox hBox3 = new HBox();
        HBox hBox4 = new HBox();


        Label lblNewLine = new Label();
        Label lblNewLine2 = new Label();

        Line line = new Line(0, 0, 490, 0);

        Label lblHealthCondition = new Label("Helbredstilstand");
        lblHealthCondition.setFont(Font.font(18.0));

        Label lblHCSubCategory = new Label("Underkategori");
        lblHCSubCategory.setFont(Font.font(18.0));

        hBox1.setSpacing(110);
        hBox1.setPadding(new Insets(10, 0, 0, 0));
        hBox1.getChildren().addAll(lblHealthCondition, lblHCSubCategory);

        TextField txtFieldHC = new TextField();
        txtFieldHC.setEditable(false);
        txtFieldHC.setFont(Font.font(12.0));
        txtFieldHC.setPrefHeight(25);
        txtFieldHC.setPrefWidth(215);
        txtFieldHC.setMinHeight(25);
        txtFieldHC.setMinWidth(215);
        if (HCSubCategoryText.getHealthConditionId() == 1) {
            txtFieldHC.setText("Funktionsniveau");
        } else if (HCSubCategoryText.getHealthConditionId() == 2) {
            txtFieldHC.setText("Bevægeapparat");
        }else if (HCSubCategoryText.getHealthConditionId() == 3) {
            txtFieldHC.setText("Ernæring");
        }else if (HCSubCategoryText.getHealthConditionId() == 4) {
            txtFieldHC.setText("Hud og slimhinder");
        }else if (HCSubCategoryText.getHealthConditionId() == 5) {
            txtFieldHC.setText("Kommunikation");
        }else if (HCSubCategoryText.getHealthConditionId() == 6) {
            txtFieldHC.setText("Psykosociale forhold");
        }else if (HCSubCategoryText.getHealthConditionId() == 7) {
            txtFieldHC.setText("Respiration og cirkulation");
        }else if (HCSubCategoryText.getHealthConditionId() == 8) {
            txtFieldHC.setText("Seksualitet");
        }else if (HCSubCategoryText.getHealthConditionId() == 9) {
            txtFieldHC.setText("Smerter og sanseindtryk");
        }else if (HCSubCategoryText.getHealthConditionId() == 10) {
            txtFieldHC.setText("Søvn og hvile");
        }else if (HCSubCategoryText.getHealthConditionId() == 11) {
            txtFieldHC.setText("Viden og udvikling");
        }else if (HCSubCategoryText.getHealthConditionId() == 12) {
            txtFieldHC.setText("Udskillelse af affaldsstoffer");
        }
        TextField txtFieldHCSubCategory = new TextField();
        txtFieldHCSubCategory.setEditable(false);
        txtFieldHCSubCategory.setFont(Font.font(12.0));
        txtFieldHCSubCategory.setPrefHeight(25);
        txtFieldHCSubCategory.setPrefWidth(250);
        txtFieldHCSubCategory.setMinHeight(25);
        txtFieldHCSubCategory.setMinWidth(250);
        if (HCSubCategoryText.getCategoryId() == 1) {
            txtFieldHCSubCategory.setText("Problemer med personlig pleje");
        } else if (HCSubCategoryText.getCategoryId() == 2) {
            txtFieldHCSubCategory.setText("Problemer med daglige aktiviteter");
        }else if (HCSubCategoryText.getCategoryId() == 3) {
            txtFieldHCSubCategory.setText("Problemer med mobilitet og bevægelse");
        }else if (HCSubCategoryText.getCategoryId() == 4) {
            txtFieldHCSubCategory.setText("Problemer med væskeindtag");
        }else if (HCSubCategoryText.getCategoryId() == 5) {
            txtFieldHCSubCategory.setText("Problemer med fødeindtag");
        }else if (HCSubCategoryText.getCategoryId() == 6) {
            txtFieldHCSubCategory.setText("Uhensigtsmæssig vægtændring");
        }else if (HCSubCategoryText.getCategoryId() == 7) {
            txtFieldHCSubCategory.setText("Problemer med overvægt");
        }else if (HCSubCategoryText.getCategoryId() == 8) {
            txtFieldHCSubCategory.setText("Problemer med undervægt");
        }else if (HCSubCategoryText.getCategoryId() == 9) {
            txtFieldHCSubCategory.setText("Problemer med kirurgisk sår");
        }else if (HCSubCategoryText.getCategoryId() == 10) {
            txtFieldHCSubCategory.setText("Problemer med diabetisk sår");
        }else if (HCSubCategoryText.getCategoryId() == 11) {
            txtFieldHCSubCategory.setText("Problemer med cancersår");
        }else if (HCSubCategoryText.getCategoryId() == 12) {
            txtFieldHCSubCategory.setText("Problemer med tryksår");
        }else if (HCSubCategoryText.getCategoryId() == 13) {
            txtFieldHCSubCategory.setText("Problemer med arterielt sår");
        }else if (HCSubCategoryText.getCategoryId() == 14) {
            txtFieldHCSubCategory.setText("Problemer med venøst sår");
        }else if (HCSubCategoryText.getCategoryId() == 15) {
            txtFieldHCSubCategory.setText("Problemer med blandingssår");
        }else if (HCSubCategoryText.getCategoryId() == 16) {
            txtFieldHCSubCategory.setText("Problemer med traumesår");
        }else if (HCSubCategoryText.getCategoryId() == 17) {
            txtFieldHCSubCategory.setText("Andre problemer med hud og slimhinder");
        }else if (HCSubCategoryText.getCategoryId() == 18) {
            txtFieldHCSubCategory.setText("Problemer med kommunikation");
        }else if (HCSubCategoryText.getCategoryId() == 19) {
            txtFieldHCSubCategory.setText("Problemer med socialt samvær");
        }else if (HCSubCategoryText.getCategoryId() == 20) {
            txtFieldHCSubCategory.setText("Emotionelle problemer");
        }else if (HCSubCategoryText.getCategoryId() == 21) {
            txtFieldHCSubCategory.setText("Problemer med misbrug");
        }else if (HCSubCategoryText.getCategoryId() == 22) {
            txtFieldHCSubCategory.setText("Mentale problemer");
        }else if (HCSubCategoryText.getCategoryId() == 23) {
            txtFieldHCSubCategory.setText("Respirationsproblemer");
        }else if (HCSubCategoryText.getCategoryId() == 24) {
            txtFieldHCSubCategory.setText("Cirkulationsproblemer");
        }else if (HCSubCategoryText.getCategoryId() == 25) {
            txtFieldHCSubCategory.setText("Problemer med seksualitet");
        }else if (HCSubCategoryText.getCategoryId() == 26) {
            txtFieldHCSubCategory.setText("Akutte smerter");
        }else if (HCSubCategoryText.getCategoryId() == 27) {
            txtFieldHCSubCategory.setText("Periodevis smerter");
        }else if (HCSubCategoryText.getCategoryId() == 28) {
            txtFieldHCSubCategory.setText("Kroniske smerter");
        }else if (HCSubCategoryText.getCategoryId() == 29) {
            txtFieldHCSubCategory.setText("Problemer med synssans");
        }else if (HCSubCategoryText.getCategoryId() == 30) {
            txtFieldHCSubCategory.setText("Problemer med lugtesans");
        }else if (HCSubCategoryText.getCategoryId() == 31) {
            txtFieldHCSubCategory.setText("Problemer med hørelse");
        }else if (HCSubCategoryText.getCategoryId() == 32) {
            txtFieldHCSubCategory.setText("Problemer med smagssans");
        }else if (HCSubCategoryText.getCategoryId() == 33) {
            txtFieldHCSubCategory.setText("Problemer med følesans");
        }else if (HCSubCategoryText.getCategoryId() == 34) {
            txtFieldHCSubCategory.setText("Døgnrytmeproblemer");
        }else if (HCSubCategoryText.getCategoryId() == 35) {
            txtFieldHCSubCategory.setText("Søvnproblemer");
        }else if (HCSubCategoryText.getCategoryId() == 36) {
            txtFieldHCSubCategory.setText("Problemer med hukommelse");
        }else if (HCSubCategoryText.getCategoryId() == 37) {
            txtFieldHCSubCategory.setText("Problemer med sygdomsindsigt");
        }else if (HCSubCategoryText.getCategoryId() == 38) {
            txtFieldHCSubCategory.setText("Problemer med indsigt i behandlingsmål");
        }else if (HCSubCategoryText.getCategoryId() == 39) {
            txtFieldHCSubCategory.setText("Kognitive problemer");
        }else if (HCSubCategoryText.getCategoryId() == 40) {
            txtFieldHCSubCategory.setText("Problemer med vandladning");
        }else if (HCSubCategoryText.getCategoryId() == 41) {
            txtFieldHCSubCategory.setText("Problemer med urininkontinens");
        }else if (HCSubCategoryText.getCategoryId() == 42) {
            txtFieldHCSubCategory.setText("Problemer med afføringsinkontinens");
        }else if (HCSubCategoryText.getCategoryId() == 43) {
            txtFieldHCSubCategory.setText("Problemer med mave og tarm");
        }

        hBox2.setSpacing(25);
        hBox2.getChildren().addAll(txtFieldHC, txtFieldHCSubCategory);

        Label lblHCNote = new Label("Fagligt notat");
        lblHCNote.setFont(Font.font(18.0));

        TextArea txtAreaHCNote = new TextArea();
        txtAreaHCNote.setEditable(false);
        txtAreaHCNote.setWrapText(true);
        txtAreaHCNote.setPrefHeight(80);
        txtAreaHCNote.setPrefWidth(490);
        txtAreaHCNote.setMaxHeight(80);
        txtAreaHCNote.setMaxWidth(490);
        txtAreaHCNote.setMinHeight(80);
        txtAreaHCNote.setMinWidth(490);
        txtAreaHCNote.setText(HCSubCategoryText.getProfessionalNote());

        Label lblCurrentLevel = new Label("Nuværende niveau");
        lblCurrentLevel.setFont(Font.font(18.0));

        TextArea txtAreaCurrentLevel = new TextArea();
        txtAreaCurrentLevel.setEditable(false);
        txtAreaCurrentLevel.setWrapText(true);
        txtAreaCurrentLevel.setPrefHeight(80);
        txtAreaCurrentLevel.setPrefWidth(490);
        txtAreaCurrentLevel.setMaxHeight(80);
        txtAreaCurrentLevel.setMaxWidth(490);
        txtAreaCurrentLevel.setMinHeight(80);
        txtAreaCurrentLevel.setMinWidth(490);
        txtAreaCurrentLevel.setText(HCSubCategoryText.getCurrentLevelAssessment());

        Label lblExpectedLevel = new Label("Forventet niveau");
        lblExpectedLevel.setFont(Font.font(18.0));
        Label lblCondition = new Label("Tilstandens relevans");
        lblCondition.setFont(Font.font(18.0));

        hBox3.setSpacing(110);
        hBox3.getChildren().addAll(lblExpectedLevel, lblCondition);

        TextField txtFieldExpectedLevel = new TextField();
        txtFieldExpectedLevel.setEditable(false);
        txtFieldExpectedLevel.setFont(Font.font(12.0));
        txtFieldExpectedLevel.setPrefHeight(25);
        txtFieldExpectedLevel.setPrefWidth(215);
        txtFieldExpectedLevel.setMinHeight(25);
        txtFieldExpectedLevel.setMinWidth(215);
        txtFieldExpectedLevel.setText(HCSubCategoryText.getExpectedLevelAssessment());

        TextField txtFieldCondition = new TextField();
        txtFieldCondition.setEditable(false);
        txtFieldCondition.setFont(Font.font(12.0));
        txtFieldCondition.setPrefHeight(25);
        txtFieldCondition.setPrefWidth(250);
        txtFieldCondition.setMinHeight(25);
        txtFieldCondition.setMinWidth(250);
        if(HCSubCategoryText.getCondition() == 0){
            txtFieldCondition.setText("Ikke relevant");
        }else if (HCSubCategoryText.getCondition() == 1){
            txtFieldCondition.setText("Potentiel");
        }else if (HCSubCategoryText.getCondition() == 2){
            txtFieldCondition.setText("Relevant");
        }

        hBox4.setSpacing(25);
        hBox4.getChildren().addAll(txtFieldExpectedLevel, txtFieldCondition);


        VBox vBoxNewHC = new VBox();

        //vBoxHealthConditions.setBackground(new Background(new BackgroundFill(Color.CYAN, CornerRadii.EMPTY, new Insets(-15, -30,-30,-30))));
        //vBoxHealthConditions.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT, new Insets(-15, -30,-30,-30))));
        vBoxNewHC.getChildren().addAll(hBox1, hBox2, lblHCNote, txtAreaHCNote, lblCurrentLevel, txtAreaCurrentLevel, hBox3, hBox4, lblNewLine, lblNewLine2, line);
        vBoxNewHC.setPadding(new Insets(5, 5, 5, 20));
        vBoxHealthCondition.getChildren().add(vBoxNewHC);
    }

    public void newFAToVBox(int functionalAbilitySubCategoryId) throws SQLServerException {
        FunctionalAbilitySubCategoryText FASubCategoryText = dataModelFacade.getInfoOnSubCategory(Integer.parseInt(txtFieldCitizenID.getText()), functionalAbilitySubCategoryId);
        HBox hBox1 = new HBox();
        HBox hBox2 = new HBox();
        HBox hBox3 = new HBox();
        HBox hBox4 = new HBox();
        HBox hBox5 = new HBox();
        HBox hBox6 = new HBox();
        HBox hBox7 = new HBox();
        HBox hBox8 = new HBox();

        Label lblNewLine = new Label();
        Label lblNewLine2 = new Label();

        Line line = new Line(0, 0, 490, 0);

        Label lblFunctionalAbility = new Label("Funktionsevnetilstand");
        lblFunctionalAbility.setFont(Font.font(18.0));
        lblFunctionalAbility.setPadding(new Insets(0, 0, 0, 1));
        Label lblFASubCategory = new Label("Underkategori");
        lblFASubCategory.setFont(Font.font(18.0));

        hBox1.setSpacing(68);
        hBox1.setPadding(new Insets(10, 0, 0, 0));
        hBox1.getChildren().addAll(lblFunctionalAbility, lblFASubCategory);

        TextField txtFieldFA = new TextField();
        txtFieldFA.setEditable(false);
        txtFieldFA.setFont(Font.font(12.0));
        txtFieldFA.setPrefHeight(25);
        txtFieldFA.setPrefWidth(215);
        txtFieldFA.setMinHeight(25);
        txtFieldFA.setMinWidth(215);
        if (FASubCategoryText.getFunctionalAbilityID() == 1) {
            txtFieldFA.setText("Egenomsorg");
        } else if (FASubCategoryText.getFunctionalAbilityID() == 2) {
            txtFieldFA.setText("Praktiske opgaver");
        } else if (FASubCategoryText.getFunctionalAbilityID() == 3) {
            txtFieldFA.setText("Mobilitet");
        } else if (FASubCategoryText.getFunctionalAbilityID() == 4) {
            txtFieldFA.setText("Mentale Funktioner");
        } else if (FASubCategoryText.getFunctionalAbilityID() == 5) {
            txtFieldFA.setText("Samfundsliv");
        }

        TextField txtFieldFASubCategory = new TextField();
        txtFieldFASubCategory.setEditable(false);
        txtFieldFASubCategory.setFont(Font.font(12.0));
        txtFieldFASubCategory.setPrefHeight(25);
        txtFieldFASubCategory.setPrefWidth(250);
        txtFieldFASubCategory.setMinHeight(25);
        txtFieldFASubCategory.setMinWidth(250);

        if (FASubCategoryText.getSubCategoryId() == 1) {
            txtFieldFASubCategory.setText("Vaske sig");
        } else if (FASubCategoryText.getSubCategoryId() == 2) {
            txtFieldFASubCategory.setText("Gå på toilet");
        } else if (FASubCategoryText.getSubCategoryId() == 3) {
            txtFieldFASubCategory.setText("Kropspleje");
        } else if (FASubCategoryText.getSubCategoryId() == 4) {
            txtFieldFASubCategory.setText("Af- og påklædning");
        } else if (FASubCategoryText.getSubCategoryId() == 5) {
            txtFieldFASubCategory.setText("Spise");
        } else if (FASubCategoryText.getSubCategoryId() == 6) {
            txtFieldFASubCategory.setText("Drikke");
        } else if (FASubCategoryText.getSubCategoryId() == 7) {
            txtFieldFASubCategory.setText("Varetage egen sundhed");
        } else if (FASubCategoryText.getSubCategoryId() == 8) {
            txtFieldFASubCategory.setText("Fødeindtagelse");
        } else if (FASubCategoryText.getSubCategoryId() == 9) {
            txtFieldFASubCategory.setText("Udføre daglige rutiner");
        } else if (FASubCategoryText.getSubCategoryId() == 10) {
            txtFieldFASubCategory.setText("Skaffe sig varer og tjenesteydelser");
        } else if (FASubCategoryText.getSubCategoryId() == 11) {
            txtFieldFASubCategory.setText("Lave mad");
        } else if (FASubCategoryText.getSubCategoryId() == 12) {
            txtFieldFASubCategory.setText("Lave husligt arbejde");
        } else if (FASubCategoryText.getSubCategoryId() == 13) {
            txtFieldFASubCategory.setText("Ændre kropsstilling");
        } else if (FASubCategoryText.getSubCategoryId() == 14) {
            txtFieldFASubCategory.setText("Forflytte sig");
        } else if (FASubCategoryText.getSubCategoryId() == 15) {
            txtFieldFASubCategory.setText("Løfte og bære");
        } else if (FASubCategoryText.getSubCategoryId() == 16) {
            txtFieldFASubCategory.setText("Gå");
        } else if (FASubCategoryText.getSubCategoryId() == 17) {
            txtFieldFASubCategory.setText("Bevæge sig omkring");
        } else if (FASubCategoryText.getSubCategoryId() == 18) {
            txtFieldFASubCategory.setText("Færden i forskellige omgivelser");
        } else if (FASubCategoryText.getSubCategoryId() == 19) {
            txtFieldFASubCategory.setText("Bruge transportmidler");
        } else if (FASubCategoryText.getSubCategoryId() == 20) {
            txtFieldFASubCategory.setText("Færden i forskellige omgivelser");
        } else if (FASubCategoryText.getSubCategoryId() == 21) {
            txtFieldFASubCategory.setText("Udholdenhed");
        } else if (FASubCategoryText.getSubCategoryId() == 22) {
            txtFieldFASubCategory.setText("Muskelstyrke");
        } else if (FASubCategoryText.getSubCategoryId() == 23) {
            txtFieldFASubCategory.setText("Tilegne sig færdigheder");
        } else if (FASubCategoryText.getSubCategoryId() == 24) {
            txtFieldFASubCategory.setText("Problemløsning");
        } else if (FASubCategoryText.getSubCategoryId() == 25) {
            txtFieldFASubCategory.setText("Anvende kommunikationsudstyr- og teknikker");
        } else if (FASubCategoryText.getSubCategoryId() == 26) {
            txtFieldFASubCategory.setText("Orienteringsevne");
        } else if (FASubCategoryText.getSubCategoryId() == 27) {
            txtFieldFASubCategory.setText("Energi og handlekraft");
        } else if (FASubCategoryText.getSubCategoryId() == 28) {
            txtFieldFASubCategory.setText("Følelsesfunktioner");
        } else if (FASubCategoryText.getSubCategoryId() == 29) {
            txtFieldFASubCategory.setText("Overordnede kognitive funktioner");
        } else if (FASubCategoryText.getSubCategoryId() == 30) {
            txtFieldFASubCategory.setText("Have lønnet beskæftigelse");
        }

        hBox2.setSpacing(25);
        hBox2.getChildren().addAll(txtFieldFA, txtFieldFASubCategory);

        Label lblAssessment = new Label("Vurdering af tilstand");
        lblAssessment.setFont(Font.font(18.0));
        lblAssessment.setPadding(new Insets(0, 0, 0, 3));

        Label lblCurrentLevel = new Label("Niveau: ");
        lblCurrentLevel.setFont(Font.font(14.0));
        lblCurrentLevel.setPadding(new Insets(0, 0, 0, 3));

        TextField txtFieldCurrentLevel = new TextField();
        txtFieldCurrentLevel.setEditable(false);
        txtFieldCurrentLevel.setFont(Font.font(12.0));
        txtFieldCurrentLevel.setPrefHeight(25);
        txtFieldCurrentLevel.setPrefWidth(155);
        txtFieldCurrentLevel.setMinHeight(25);
        txtFieldCurrentLevel.setMinWidth(155);
        txtFieldCurrentLevel.setText(String.valueOf(FASubCategoryText.getAbilityNow()));

        Label lblExpectedLevel = new Label("Forventet niveau: ");
        lblExpectedLevel.setFont(Font.font(14.0));
        TextField txtFieldExpectedLevel = new TextField();
        txtFieldExpectedLevel.setEditable(false);
        txtFieldExpectedLevel.setFont(Font.font(12.0));
        txtFieldExpectedLevel.setPrefHeight(25);
        txtFieldExpectedLevel.setPrefWidth(130);
        txtFieldExpectedLevel.setMinHeight(25);
        txtFieldExpectedLevel.setMinWidth(130);
        txtFieldExpectedLevel.setText(String.valueOf(FASubCategoryText.getAbilityExpected()));


        hBox3.getChildren().addAll(lblCurrentLevel, txtFieldCurrentLevel);
        hBox3.setSpacing(5);
        hBox4.getChildren().addAll(lblExpectedLevel, txtFieldExpectedLevel);
        hBox4.setSpacing(5);

        hBox5.getChildren().addAll(hBox3, hBox4);
        hBox5.setSpacing(30);

        Label lblFANote = new Label("Fagligt notat");
        lblFANote.setFont(Font.font(18.0));
        lblFANote.setPadding(new Insets(0, 0, 0, 3));

        TextArea txtAreaFANote = new TextArea();
        txtAreaFANote.setEditable(false);
        txtAreaFANote.setWrapText(true);
        txtAreaFANote.setPrefHeight(80);
        txtAreaFANote.setPrefWidth(490);
        txtAreaFANote.setMaxHeight(80);
        txtAreaFANote.setMaxWidth(490);
        txtAreaFANote.setMinHeight(80);
        txtAreaFANote.setMinWidth(490);
        txtAreaFANote.setText(FASubCategoryText.getAbilityNote());

        Label lblCitizenAssessment = new Label("Borgerens vurdering");
        lblCitizenAssessment.setFont(Font.font(18.0));
        lblCitizenAssessment.setPadding(new Insets(0, 0, 0, 3));

        Label lblCitizenCurrentLevel = new Label("Udførelse: ");
        lblCitizenCurrentLevel.setFont(Font.font(14.0));
        lblCitizenCurrentLevel.setPadding(new Insets(0, 0, 0, 3));

        TextField txtFieldCitizenCurrentLevel = new TextField();
        txtFieldCitizenCurrentLevel.setEditable(false);
        txtFieldCitizenCurrentLevel.setFont(Font.font(12.0));
        txtFieldCitizenCurrentLevel.setPrefHeight(25);
        txtFieldCitizenCurrentLevel.setPrefWidth(155);
        txtFieldCitizenCurrentLevel.setMinHeight(25);
        txtFieldCitizenCurrentLevel.setMinWidth(155);
        txtFieldCitizenCurrentLevel.setText(FASubCategoryText.getCitizenPerformance());

        Label lblCitizenExpectedLevel = new Label("Betydning: ");
        lblCitizenExpectedLevel.setFont(Font.font(14.0));
        TextField txtFieldCitizenExpectedLevel = new TextField();
        txtFieldCitizenExpectedLevel.setEditable(false);
        txtFieldCitizenExpectedLevel.setFont(Font.font(12.0));
        txtFieldCitizenExpectedLevel.setPrefHeight(25);
        txtFieldCitizenExpectedLevel.setPrefWidth(155);
        txtFieldCitizenExpectedLevel.setMinHeight(25);
        txtFieldCitizenExpectedLevel.setMinWidth(155);
        txtFieldCitizenExpectedLevel.setText(FASubCategoryText.getCitizenMeaningOfPerformance());


        hBox6.getChildren().addAll(lblCitizenCurrentLevel, txtFieldCitizenCurrentLevel);
        hBox6.setSpacing(5);
        hBox7.getChildren().addAll(lblCitizenExpectedLevel, txtFieldCitizenExpectedLevel);
        hBox7.setSpacing(5);

        hBox8.getChildren().addAll(hBox6, hBox7);
        hBox8.setPadding(new Insets(5, 0, 0, 0));
        hBox8.setSpacing(28);

        Label lblCitizenGoals = new Label("Borgerens ønsker og mål");
        lblCitizenGoals.setFont(Font.font(18.0));
        lblCitizenGoals.setPadding(new Insets(0, 0, 0, 3));

        TextArea txtAreaCitizenGoals = new TextArea();
        txtAreaCitizenGoals.setEditable(false);
        txtAreaCitizenGoals.setWrapText(true);
        txtAreaCitizenGoals.setPrefHeight(80);
        txtAreaCitizenGoals.setPrefWidth(490);
        txtAreaCitizenGoals.setMaxHeight(80);
        txtAreaCitizenGoals.setMaxWidth(490);
        txtAreaCitizenGoals.setMinHeight(80);
        txtAreaCitizenGoals.setMinWidth(490);
        txtAreaCitizenGoals.setText(FASubCategoryText.getAbilityNoteCitizen());

        VBox vBoxNewFA = new VBox();

        vBoxNewFA.setPadding(new Insets(5, 5, 5, 20));
        vBoxNewFA.setSpacing(4);
        vBoxNewFA.getChildren().addAll(hBox1, hBox2, lblAssessment, hBox5, lblFANote, txtAreaFANote,
                lblCitizenAssessment, hBox8, lblCitizenGoals, txtAreaCitizenGoals, lblNewLine, lblNewLine2, line);
        vBoxFunctionalAbilities.getChildren().add(vBoxNewFA);

    }


}
