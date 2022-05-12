package gui.controller;

import be.Citizen;
import gui.Facade.DataModelFacade;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CitizenInfoViewController implements Initializable {
    public Label lblName;
    public Label lblSSN;
    public Label lblAddress;
    public TextArea txtFieldObservationNote;
    public TextField txtFieldFollowUpDate;
    public Pane casePane;
    public TextField txtFieldCaseName;
    public TextArea txtAreaCaseInfo;
    public Pane healthConditionPane;
    public TextField txtFieldHealthCondition;
    public TextArea txtAreaCurrentLevel;
    public TextField txtFieldHCSubcategory;
    public TextField txtFieldExpectedLevel;
    public TextField txtFieldHCRelevance;
    public Pane functionalAbilityPane;
    public TextField txtFieldFunctionalAbility;
    public TextArea txFieldNote;
    public TextField txtFieldFASubCategory;
    public TextField txtFieldMeaningOfPerformance;
    public TextField txtFieldCurrentLvl;
    public TextField txtFieldFAExpectedLevel;
    public TextField txtFieldPerformance;
    public TextArea txtFieldCitizenGoals;
    public VBox vBoxFunctionalAbilities;
    public VBox vBoxHealthCondition;
    public VBox vBoxCase;
    public TextField txtFieldCitizenID;
    public AnchorPane anchorPaneCases;
    public Button addnewCaseToVboxBtn;
    public Button addnewHCToVBoxBtn;
    public AnchorPane anchorPaneHealthConditions;
    public Button addnewFAToVboxBtn;


    private int citizenId;

    private DataModelFacade dataModelFacade;


    public CitizenInfoViewController() throws IOException {
        this.dataModelFacade = new DataModelFacade();
    }

    public void setSelectedCitizen(Citizen citizen) throws SQLException {
        txtFieldCitizenID.setText(String.valueOf(citizen.getId()));
        lblName.setText(citizen.getFirstName() + " " + citizen.getLastName());
        lblSSN.setText(citizen.getSSN());
        lblAddress.setText(citizen.getAddress());

        citizenId = Integer.parseInt(txtFieldCitizenID.getText());
        setCaseInfo();
        createCasesVBox();
        createHealthConditionsVBox();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    private void setCaseInfo() throws SQLException {
        //Case aCase = dataModelFacade.getCaseOnCitizen(citizenId, 1);
        //txtFieldCaseName.setText(aCase.getName());
        //txtAreaCaseInfo.setText(aCase.getInfo());
    }


    private void createCasesVBox() {
    }


    public void createHealthConditionsVBox() {

    }

    @FXML
    private void addNewCaseToVBox() {
        Label labelName = new Label("Sagsnavn");
        labelName.setFont(Font.font(18.0));
        TextField txtFieldCaseName = new TextField();
        txtFieldCaseName.setPrefWidth(382);
        txtFieldCaseName.setMaxWidth(382);
        txtFieldCaseName.setPrefHeight(26);
        txtFieldCaseName.setMaxHeight(26);
        txtFieldCaseName.setFont(Font.font(12.0));
        Label labelInfo = new Label("Sagens informationer");
        labelInfo.setFont(Font.font(18.0));
        TextArea textAreaCaseInfo = new TextArea();
        textAreaCaseInfo.setMinHeight(118);
        textAreaCaseInfo.setMinWidth(486);
        textAreaCaseInfo.setPrefHeight(118);
        textAreaCaseInfo.setMaxHeight(118);
        textAreaCaseInfo.setPrefWidth(486);
        textAreaCaseInfo.setMaxWidth(486);
        Label labelNewLine = new Label();
        Label labelNewLine2 = new Label();
        Label labelNewLine3 = new Label();
        Line line = new Line(0, 0, 490, 0);
        VBox vBoxNewCase = new VBox();
        vBoxNewCase.getChildren().addAll(labelName, txtFieldCaseName, labelInfo, textAreaCaseInfo, labelNewLine, labelNewLine2, line, labelNewLine3);
        vBoxNewCase.setPadding(new Insets(5, 5, 5, 20));
        vBoxCase.getChildren().add(vBoxNewCase);
    }


    public void addNewHCToVBox() {

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
        TextField txtFieldHCSubCategory = new TextField();
        txtFieldHCSubCategory.setEditable(false);
        txtFieldHCSubCategory.setFont(Font.font(12.0));
        txtFieldHCSubCategory.setPrefHeight(25);
        txtFieldHCSubCategory.setPrefWidth(250);
        txtFieldHCSubCategory.setMinHeight(25);
        txtFieldHCSubCategory.setMinWidth(250);

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

        TextField txtFieldCondition = new TextField();
        txtFieldCondition.setEditable(false);
        txtFieldCondition.setFont(Font.font(12.0));
        txtFieldCondition.setPrefHeight(25);
        txtFieldCondition.setPrefWidth(250);
        txtFieldCondition.setMinHeight(25);
        txtFieldCondition.setMinWidth(250);

        hBox4.setSpacing(25);
        hBox4.getChildren().addAll(txtFieldExpectedLevel, txtFieldCondition);


        VBox vBoxNewHC = new VBox();

        //vBoxHealthConditions.setBackground(new Background(new BackgroundFill(Color.CYAN, CornerRadii.EMPTY, new Insets(-15, -30,-30,-30))));
        //vBoxHealthConditions.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT, new Insets(-15, -30,-30,-30))));
        vBoxNewHC.getChildren().addAll(hBox1, hBox2, lblHCNote, txtAreaHCNote, lblCurrentLevel, txtAreaCurrentLevel, hBox3, hBox4, lblNewLine, lblNewLine2, line);
        vBoxNewHC.setPadding(new Insets(5, 5, 5, 20));
        vBoxHealthCondition.getChildren().add(vBoxNewHC);
    }

    public void addNewFAToVBox() {
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
        TextField txtFieldFASubCategory = new TextField();
        txtFieldFASubCategory.setEditable(false);
        txtFieldFASubCategory.setFont(Font.font(12.0));
        txtFieldFASubCategory.setPrefHeight(25);
        txtFieldFASubCategory.setPrefWidth(250);
        txtFieldFASubCategory.setMinHeight(25);
        txtFieldFASubCategory.setMinWidth(250);

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

        Label lblExpectedLevel = new Label("Forventet niveau: ");
        lblExpectedLevel.setFont(Font.font(14.0));
        TextField txtFieldExpectedLevel = new TextField();
        txtFieldExpectedLevel.setEditable(false);
        txtFieldExpectedLevel.setFont(Font.font(12.0));
        txtFieldExpectedLevel.setPrefHeight(25);
        txtFieldExpectedLevel.setPrefWidth(130);
        txtFieldExpectedLevel.setMinHeight(25);
        txtFieldExpectedLevel.setMinWidth(130);


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

        Label lblCitizenExpectedLevel = new Label("Betydning: ");
        lblCitizenExpectedLevel.setFont(Font.font(14.0));
        TextField txtFieldCitizenExpectedLevel = new TextField();
        txtFieldCitizenExpectedLevel.setEditable(false);
        txtFieldCitizenExpectedLevel.setFont(Font.font(12.0));
        txtFieldCitizenExpectedLevel.setPrefHeight(25);
        txtFieldCitizenExpectedLevel.setPrefWidth(155);
        txtFieldCitizenExpectedLevel.setMinHeight(25);
        txtFieldCitizenExpectedLevel.setMinWidth(155);


        hBox6.getChildren().addAll(lblCitizenCurrentLevel, txtFieldCitizenCurrentLevel);
        hBox6.setSpacing(5);
        hBox7.getChildren().addAll(lblCitizenExpectedLevel, txtFieldCitizenExpectedLevel);
        hBox7.setSpacing(5);

        hBox8.getChildren().addAll(hBox6, hBox7);
        hBox8.setPadding(new Insets(5, 0, 0, 0));
        hBox8.setSpacing(28);

        Label lblCitizenGoals = new Label("Borgerens ønsker og mål");
        lblFANote.setFont(Font.font(18.0));
        lblFANote.setPadding(new Insets(0, 0, 0, 3));

        TextArea txtAreaCitizenGoals = new TextArea();
        txtAreaCitizenGoals.setEditable(false);
        txtAreaCitizenGoals.setWrapText(true);
        txtAreaCitizenGoals.setPrefHeight(80);
        txtAreaCitizenGoals.setPrefWidth(490);
        txtAreaCitizenGoals.setMaxHeight(80);
        txtAreaCitizenGoals.setMaxWidth(490);
        txtAreaCitizenGoals.setMinHeight(80);
        txtAreaCitizenGoals.setMinWidth(490);

        VBox vBoxNewFA = new VBox();

        vBoxNewFA.setPadding(new Insets(5, 5, 5, 20));
        vBoxNewFA.setSpacing(4);
        vBoxNewFA.getChildren().addAll(hBox1, hBox2, lblAssessment, hBox5, lblFANote, txtAreaFANote,
                lblCitizenAssessment, hBox8, lblCitizenGoals, txtAreaCitizenGoals, lblNewLine, lblNewLine2, line);
        vBoxFunctionalAbilities.getChildren().add(vBoxNewFA);

    }


}
