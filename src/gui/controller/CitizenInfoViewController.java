package gui.controller;

import be.Case;
import be.Citizen;
import be.FunctionalAbilities.FunctionalAbilitySubCategoryText;
import be.HealthCondition.HealthConditionSubCategoryText;
import be.ObservationNote;
import gui.Facade.DataModelFacade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class CitizenInfoViewController {

    @FXML
    private Button btnSaveDateAndNote;
    @FXML
    private Label lblName;
    @FXML
    private Label lblSSN;
    @FXML
    private Label lblAddress;
    @FXML
    private TextArea txtAreaObservationNote;
    @FXML
    private TextField txtFieldFollowUpDate;
    @FXML
    private VBox vBoxFunctionalAbilities;
    @FXML
    private VBox vBoxHealthCondition;
    @FXML
    private VBox vBoxCase;
    @FXML
    private TextField txtFieldCitizenID;

    private int citizenId;
    private int healthConditionId;

    private final DataModelFacade dataModelFacade = DataModelFacade.getInstance();


    public CitizenInfoViewController() throws IOException, SQLException {
    }

    /**
     * Method to set the selected citizen from the studentviews info.
     * Also creates all the vBoxes for cases, healthConditions and functionalAbilities.
     *
     * @param citizen
     * @throws Exception
     */
    public void setSelectedCitizen(Citizen citizen) throws Exception {
        txtFieldCitizenID.setText(String.valueOf(citizen.getId()));
        citizenId = Integer.parseInt(txtFieldCitizenID.getText());

        lblName.setText(citizen.getFirstName() + " " + citizen.getLastName());
        lblSSN.setText(citizen.getSSN());

        ObservationNote observationNote = dataModelFacade.getObservationNote(citizenId);
        if (observationNote != null) {
            txtFieldFollowUpDate.setText(observationNote.getDate());
            txtAreaObservationNote.setText(observationNote.getNote());
        } else {
            txtFieldFollowUpDate.setText("");
            txtAreaObservationNote.setText("");
        }


        createCases();
        createHealthConditions();
        createFunctionalAbilities();
    }


    /**
     * On action method for saving the observationNote and date for the citizen.
     *
     * @throws Exception
     */
    @FXML
    private void onActionSaveDateAndNote() throws Exception {
        Stage stage = (Stage) btnSaveDateAndNote.getScene().getWindow();

        String date = txtFieldFollowUpDate.getText();
        String note = txtAreaObservationNote.getText();

        if (txtFieldFollowUpDate.getText() != null && txtAreaObservationNote.getText() != null) {
            if (dataModelFacade.getObservationNote(citizenId) == null) {
                dataModelFacade.createObservationNote(citizenId, date, note);
            } else {
                ObservationNote observationNote = new ObservationNote(citizenId, date, note);
                dataModelFacade.editObservationNote(observationNote);
            }

            stage.close();
        }
    }

    /**
     * Method for creating all the cases a citizen have.
     * Uses a thread for optimized loading.
     */
    private void createCases() throws SQLException {

        if (dataModelFacade.getCasesOnCitizen(citizenId) != null) {
            Thread t1 = new Thread(() -> {
                ObservableList<Case> allCasesOnCitizen = null;
                try {
                    allCasesOnCitizen = FXCollections.observableList(dataModelFacade.getCasesOnCitizen(citizenId));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                for (Case casesOnCitizen : allCasesOnCitizen) {
                    int caseId = casesOnCitizen.getId();
                    try {
                        newCaseToVBox(caseId);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            });
            t1.start();
        }

    }

    /**
     * Method for creating all healthConditions the citizen have.
     * Uses a thread for optimized loading.
     */
    private void createHealthConditions() throws SQLException {

        if (dataModelFacade.getHCInfoOnSubCategories(citizenId) != null) {
            Thread t1 = new Thread(() -> {
                ObservableList<HealthConditionSubCategoryText> allSubCategories = null;
                try {
                    allSubCategories = FXCollections.observableList(dataModelFacade.getHCInfoOnSubCategories(citizenId));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                for (HealthConditionSubCategoryText HCSubCategoryText : allSubCategories) {
                    healthConditionId = HCSubCategoryText.getCategoryId();
                    try {
                        newHCToVBox(healthConditionId);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            });
            t1.start();
        }

    }

    /**
     * Method for creating the functionalAbilites the citizen have.
     *
     * @throws SQLException
     */

    private void createFunctionalAbilities() throws SQLException {
        if (dataModelFacade.getFAInfoOnSubCategories(citizenId) != null) {
            ObservableList<FunctionalAbilitySubCategoryText> allFunctionalAbilitySubCategories = FXCollections.observableList(dataModelFacade.getFAInfoOnSubCategories(citizenId));
            for (FunctionalAbilitySubCategoryText FAOnCitizen : allFunctionalAbilitySubCategories) {
                int functionalAbilityId = FAOnCitizen.getSubCategoryId();
                newFAToVBox(functionalAbilityId);
            }
        }
    }


    /**
     * Method for creating a vbox, using the caseId.
     * Creates all the elements in the vBox and fills it out with info from the database.
     *
     * @param caseId
     * @throws SQLException
     */
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


    /**
     * Method for creating a vbox for a healthCondition
     * Creates all the elements in the vBox and fills it out with info from the database.
     *
     * @throws SQLException
     */
    private void newHCToVBox(int condition) throws SQLException {
        HealthConditionSubCategoryText hcSubCategoryText = dataModelFacade.getTextOnSubCategory(Integer.parseInt(txtFieldCitizenID.getText()), this.healthConditionId);

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
        int hcCondition = hcSubCategoryText.getHealthConditionId();
        System.out.println(hcCondition);
        switch (hcCondition) {
            case 1 -> txtFieldHC.setText("Funktionsniveau");
            case 2 -> txtFieldHC.setText("Bevægeapparat");
            case 3 -> txtFieldHC.setText("Ernæring");
            case 4 -> txtFieldHC.setText("Hud og slimhinder");
            case 5 -> txtFieldHC.setText("Kommunikation");
            case 6 -> txtFieldHC.setText("Psykosociale forhold");
            case 7 -> txtFieldHC.setText("Respiration og cirkulation");
            case 8 -> txtFieldHC.setText("Seksualitet");
            case 9 -> txtFieldHC.setText("Smerter og sanseindtryk");
            case 10 -> txtFieldHC.setText("Søvn og hvile");
            case 11 -> txtFieldHC.setText("Viden og udvikling");
            case 12 -> txtFieldHC.setText("Udskillelse af affaldsstoffer");
            default -> {
            }
        }

        TextField txtFieldHCSubCategory = new TextField();
        txtFieldHCSubCategory.setEditable(false);
        txtFieldHCSubCategory.setFont(Font.font(12.0));
        txtFieldHCSubCategory.setPrefHeight(25);
        txtFieldHCSubCategory.setPrefWidth(250);
        txtFieldHCSubCategory.setMinHeight(25);
        txtFieldHCSubCategory.setMinWidth(250);

        int hcCategoryId = hcSubCategoryText.getCategoryId();
        switch (hcCategoryId) {
            case 1 -> txtFieldHCSubCategory.setText("Problemer med personlig pleje");
            case 2 -> txtFieldHCSubCategory.setText("Problemer med daglige aktiviteter");
            case 3 -> txtFieldHCSubCategory.setText("Problemer med mobilitet og bevægelse");
            case 4 -> txtFieldHCSubCategory.setText("Problemer med væskeindtag");
            case 5 -> txtFieldHCSubCategory.setText("Problemer med fødeindtag");
            case 6 -> txtFieldHCSubCategory.setText("Uhensigtsmæssig vægtændring");
            case 7 -> txtFieldHCSubCategory.setText("Problemer med overvægt");
            case 8 -> txtFieldHCSubCategory.setText("Problemer med undervægt");
            case 9 -> txtFieldHCSubCategory.setText("Problemer med kirurgisk sår");
            case 10 -> txtFieldHCSubCategory.setText("Problemer med diabetisk sår");
            case 11 -> txtFieldHCSubCategory.setText("Problemer med cancersår");
            case 12 -> txtFieldHCSubCategory.setText("Problemer med tryksår");
            case 13 -> txtFieldHCSubCategory.setText("Problemer med arterielt sår");
            case 14 -> txtFieldHCSubCategory.setText("Problemer med venøst sår");
            case 15 -> txtFieldHCSubCategory.setText("Problemer med blandingssår");
            case 16 -> txtFieldHCSubCategory.setText("Problemer med traumesår");
            case 17 -> txtFieldHCSubCategory.setText("Andre problemer med hud og slimhinder");
            case 18 -> txtFieldHCSubCategory.setText("Problemer med kommunikation");
            case 19 -> txtFieldHCSubCategory.setText("Problemer med socialt samvær");
            case 20 -> txtFieldHCSubCategory.setText("Emotionelle problemer");
            case 21 -> txtFieldHCSubCategory.setText("Problemer med misbrug");
            case 22 -> txtFieldHCSubCategory.setText("Mentale problemer");
            case 23 -> txtFieldHCSubCategory.setText("Respirationsproblemer");
            case 24 -> txtFieldHCSubCategory.setText("Cirkulationsproblemer");
            case 25 -> txtFieldHCSubCategory.setText("Problemer med seksualitet");
            case 26 -> txtFieldHCSubCategory.setText("Akutte smerter");
            case 27 -> txtFieldHCSubCategory.setText("Periodevis smerter");
            case 28 -> txtFieldHCSubCategory.setText("Kroniske smerter");
            case 29 -> txtFieldHCSubCategory.setText("Problemer med synssans");
            case 30 -> txtFieldHCSubCategory.setText("Problemer med lugtesans");
            case 31 -> txtFieldHCSubCategory.setText("Problemer med hørelse");
            case 32 -> txtFieldHCSubCategory.setText("Problemer med smagssans");
            case 33 -> txtFieldHCSubCategory.setText("Problemer med følesans");
            case 34 -> txtFieldHCSubCategory.setText("Døgnrytmeproblemer");
            case 35 -> txtFieldHCSubCategory.setText("Søvnproblemer");
            case 36 -> txtFieldHCSubCategory.setText("Problemer med hukommelse");
            case 37 -> txtFieldHCSubCategory.setText("Problemer med sygdomsindsigt");
            case 38 -> txtFieldHCSubCategory.setText("Problemer med indsigt i behandlingsmål");
            case 39 -> txtFieldHCSubCategory.setText("Kognitive problemer");
            case 40 -> txtFieldHCSubCategory.setText("Problemer med vandladning");
            case 41 -> txtFieldHCSubCategory.setText("Problemer med urininkontinens");
            case 42 -> txtFieldHCSubCategory.setText("Problemer med afføringsinkontinens");
            case 43 -> txtFieldHCSubCategory.setText("Problemer med mave og tarm");
            default -> {
            }
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
        txtAreaHCNote.setText(hcSubCategoryText.getProfessionalNote());

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
        txtAreaCurrentLevel.setText(hcSubCategoryText.getCurrentLevelAssessment());

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
        txtFieldExpectedLevel.setText(hcSubCategoryText.getExpectedLevelAssessment());

        TextField txtFieldCondition = new TextField();
        txtFieldCondition.setEditable(false);
        txtFieldCondition.setFont(Font.font(12.0));
        txtFieldCondition.setPrefHeight(25);
        txtFieldCondition.setPrefWidth(250);
        txtFieldCondition.setMinHeight(25);
        txtFieldCondition.setMinWidth(250);
        if (hcSubCategoryText.getCondition() == 0) {
            txtFieldCondition.setText("Ikke relevant");
        } else if (hcSubCategoryText.getCondition() == 1) {
            txtFieldCondition.setText("Potentiel");
        } else if (hcSubCategoryText.getCondition() == 2) {
            txtFieldCondition.setText("Relevant");
        }

        hBox4.setSpacing(25);
        hBox4.getChildren().addAll(txtFieldExpectedLevel, txtFieldCondition);


        VBox vBoxNewHC = new VBox();

        vBoxNewHC.getChildren().addAll(hBox1, hBox2, lblHCNote, txtAreaHCNote, lblCurrentLevel, txtAreaCurrentLevel, hBox3, hBox4, lblNewLine, lblNewLine2, line);
        vBoxNewHC.setPadding(new Insets(5, 5, 5, 20));
        vBoxHealthCondition.getChildren().add(vBoxNewHC);
    }

    /**
     * Method for creating a vbox for a functionalAbility
     * Creates all the elements in the vBox and fills it out with info from the database.
     *
     * @param functionalAbilitySubCategoryId
     * @throws SQLException
     */
    private void newFAToVBox(int functionalAbilitySubCategoryId) throws SQLException {
        FunctionalAbilitySubCategoryText faSubCategoryText = dataModelFacade.getInfoOnSubCategory(Integer.parseInt(txtFieldCitizenID.getText()), functionalAbilitySubCategoryId);
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

        int faAbilityId = faSubCategoryText.getFunctionalAbilityID();
        switch (faAbilityId) {
            case 1:
                txtFieldFA.setText("Egenomsorg");
                break;
            case 2:
                txtFieldFA.setText("Praktiske opgaver");
                break;
            case 3:
                txtFieldFA.setText("Mobilitet");
                break;
            case 4:
                txtFieldFA.setText("Mentale Funktioner");
                break;
            case 5:
                txtFieldFA.setText("Samfundsliv");
            default:
        }

        TextField txtFieldFASubCategory = new TextField();
        txtFieldFASubCategory.setEditable(false);
        txtFieldFASubCategory.setFont(Font.font(12.0));
        txtFieldFASubCategory.setPrefHeight(25);
        txtFieldFASubCategory.setPrefWidth(250);
        txtFieldFASubCategory.setMinHeight(25);
        txtFieldFASubCategory.setMinWidth(250);

        int faSubCategoryId = faSubCategoryText.getSubCategoryId();
        switch (faSubCategoryId) {
            case 1 -> txtFieldFASubCategory.setText("Vaske sig");
            case 2 -> txtFieldFASubCategory.setText("Gå på toilet");
            case 3 -> txtFieldFASubCategory.setText("Kropspleje");
            case 4 -> txtFieldFASubCategory.setText("Af- og påklædning");
            case 5 -> txtFieldFASubCategory.setText("Spise");
            case 6 -> txtFieldFASubCategory.setText("Drikke");
            case 7 -> txtFieldFASubCategory.setText("Varetage egen sundhed");
            case 8 -> txtFieldFASubCategory.setText("Fødeindtagelse");
            case 9 -> txtFieldFASubCategory.setText("Udføre daglige rutiner");
            case 10 -> txtFieldFASubCategory.setText("Skaffe sig varer og tjenesteydelser");
            case 11 -> txtFieldFASubCategory.setText("Lave mad");
            case 12 -> txtFieldFASubCategory.setText("Lave husligt arbejde");
            case 13 -> txtFieldFASubCategory.setText("Ændre kropsstilling");
            case 14 -> txtFieldFASubCategory.setText("Forflytte sig");
            case 15 -> txtFieldFASubCategory.setText("Løfte og bære");
            case 16 -> txtFieldFASubCategory.setText("Gå");
            case 17 -> txtFieldFASubCategory.setText("Bevæge sig omkring");
            case 18 -> txtFieldFASubCategory.setText("Færden i forskellige omgivelser");
            case 19 -> txtFieldFASubCategory.setText("Bruge transportmidler");
            case 20 -> txtFieldFASubCategory.setText("Færden i forskellige omgivelser");
            case 21 -> txtFieldFASubCategory.setText("Udholdenhed");
            case 22 -> txtFieldFASubCategory.setText("Muskelstyrke");
            case 23 -> txtFieldFASubCategory.setText("Tilegne sig færdigheder");
            case 24 -> txtFieldFASubCategory.setText("Problemløsning");
            case 25 -> txtFieldFASubCategory.setText("Anvende kommunikationsudstyr- og teknikker");
            case 26 -> txtFieldFASubCategory.setText("Orienteringsevne");
            case 27 -> txtFieldFASubCategory.setText("Energi og handlekraft");
            case 28 -> txtFieldFASubCategory.setText("Følelsesfunktioner");
            case 29 -> txtFieldFASubCategory.setText("Overordnede kognitive funktioner");
            case 30 -> txtFieldFASubCategory.setText("Have lønnet beskæftigelse");
            default -> {
            }
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
        txtFieldCurrentLevel.setText(String.valueOf(faSubCategoryText.getAbilityNow()));

        Label lblExpectedLevel = new Label("Forventet niveau: ");
        lblExpectedLevel.setFont(Font.font(14.0));
        TextField txtFieldExpectedLevel = new TextField();
        txtFieldExpectedLevel.setEditable(false);
        txtFieldExpectedLevel.setFont(Font.font(12.0));
        txtFieldExpectedLevel.setPrefHeight(25);
        txtFieldExpectedLevel.setPrefWidth(130);
        txtFieldExpectedLevel.setMinHeight(25);
        txtFieldExpectedLevel.setMinWidth(130);
        txtFieldExpectedLevel.setText(String.valueOf(faSubCategoryText.getAbilityExpected()));


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
        txtAreaFANote.setText(faSubCategoryText.getAbilityNote());

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
        txtFieldCitizenCurrentLevel.setText(faSubCategoryText.getCitizenPerformance());

        Label lblCitizenExpectedLevel = new Label("Betydning: ");
        lblCitizenExpectedLevel.setFont(Font.font(14.0));
        TextField txtFieldCitizenExpectedLevel = new TextField();
        txtFieldCitizenExpectedLevel.setEditable(false);
        txtFieldCitizenExpectedLevel.setFont(Font.font(12.0));
        txtFieldCitizenExpectedLevel.setPrefHeight(25);
        txtFieldCitizenExpectedLevel.setPrefWidth(155);
        txtFieldCitizenExpectedLevel.setMinHeight(25);
        txtFieldCitizenExpectedLevel.setMinWidth(155);
        txtFieldCitizenExpectedLevel.setText(faSubCategoryText.getCitizenMeaningOfPerformance());


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
        txtAreaCitizenGoals.setText(faSubCategoryText.getAbilityNoteCitizen());

        VBox vBoxNewFA = new VBox();

        vBoxNewFA.setPadding(new Insets(5, 5, 5, 20));
        vBoxNewFA.setSpacing(4);
        vBoxNewFA.getChildren().addAll(hBox1, hBox2, lblAssessment, hBox5, lblFANote, txtAreaFANote,
                lblCitizenAssessment, hBox8, lblCitizenGoals, txtAreaCitizenGoals, lblNewLine, lblNewLine2, line);
        vBoxFunctionalAbilities.getChildren().add(vBoxNewFA);

    }

}
