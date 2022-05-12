package gui.controller;

import be.Citizen;
import gui.Facade.DataModelFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.awt.*;
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
    public VBox vBoxHealthConditions;
    public VBox vBoxCase;
    public TextField txtFieldCitizenID;
    public AnchorPane anchorPaneCases;
    public Button addnewCaseToVboxBtn;
    public Button addnewHCToVBoxBtn;
    public AnchorPane anchorPaneHealthConditions;


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


    private void createCasesVBox(){
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
        textAreaCaseInfo.setPrefHeight(250);
        textAreaCaseInfo.setMaxHeight(250);
        textAreaCaseInfo.setPrefWidth(486);
        textAreaCaseInfo.setMaxWidth(486);


        vBoxCase.setSpacing(5);
        vBoxCase.setPrefHeight(214);
        vBoxCase.setPrefWidth(514);
        vBoxCase.setPadding(new Insets(5,5,100,15));
        vBoxCase.getChildren().addAll(labelName, txtFieldCaseName, labelInfo, textAreaCaseInfo);
    }


    public void createHealthConditionsVBox(){
        HBox hBox1 = new HBox();
        HBox hBox2 = new HBox();
        HBox hBox3 = new HBox();
        HBox hBox4 = new HBox();

        Label lblHealthCondition = new Label("Helbredstilstand");
        lblHealthCondition.setFont(Font.font(18.0));
        Label lblHCSubCategory = new Label("Underkategori");
        lblHCSubCategory.setFont(Font.font(18.0));

        hBox1.setSpacing(110);
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
        txtFieldHCSubCategory.setMinWidth(215);

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
        hBox3.getChildren().addAll(lblExpectedLevel,lblCondition);

        TextField txtFieldExpectedLevel = new TextField();
        txtFieldExpectedLevel.setEditable(false);
        txtFieldExpectedLevel.setFont(Font.font(12.0));
        txtFieldExpectedLevel.setPrefHeight(25);
        txtFieldExpectedLevel.setPrefWidth(250);
        txtFieldExpectedLevel.setMinHeight(25);
        txtFieldExpectedLevel.setMinWidth(215);

        TextField txtFieldCondition = new TextField();
        txtFieldCondition.setEditable(false);
        txtFieldCondition.setFont(Font.font(12.0));
        txtFieldCondition.setPrefHeight(25);
        txtFieldCondition.setPrefWidth(250);
        txtFieldCondition.setMinHeight(25);
        txtFieldCondition.setMinWidth(215);

        hBox4.setSpacing(25);
        hBox4.getChildren().addAll(txtFieldExpectedLevel,txtFieldCondition);

        vBoxHealthConditions.setPrefHeight(214);
        vBoxHealthConditions.setPrefWidth(514);
        vBoxHealthConditions.setPadding(new Insets(5,5,100,15));
        vBoxHealthConditions.getChildren().addAll(hBox1, hBox2, lblHCNote, txtAreaHCNote, lblCurrentLevel, txtAreaCurrentLevel, hBox3, hBox4);
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
        textAreaCaseInfo.setPrefHeight(250);
        textAreaCaseInfo.setMaxHeight(250);
        textAreaCaseInfo.setPrefWidth(486);
        textAreaCaseInfo.setMaxWidth(486);
        VBox vBoxNewCase = new VBox();
        vBoxNewCase.getChildren().addAll(labelName, txtFieldCaseName, labelInfo, textAreaCaseInfo);
        anchorPaneCases.getChildren().add(vBoxNewCase);
    }


    public void addNewHCToVBox() {

        HBox hBox1 = new HBox();
        HBox hBox2 = new HBox();
        HBox hBox3 = new HBox();
        HBox hBox4 = new HBox();


        Label lblNewLine = new Label();
        Label lblNewLine2 = new Label();

        Label lblHealthCondition = new Label("Helbredstilstand");
        lblHealthCondition.setFont(Font.font(18.0));

        Label lblHCSubCategory = new Label("Underkategori");
        lblHCSubCategory.setFont(Font.font(18.0));

        hBox1.setSpacing(110);
        hBox1.setPadding(new Insets(10,0,0,0));
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
        hBox3.getChildren().addAll(lblExpectedLevel,lblCondition);

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
        hBox4.getChildren().addAll(txtFieldExpectedLevel,txtFieldCondition);


        VBox vBoxNewHC = new VBox();

        //vBoxHealthConditions.setBackground(new Background(new BackgroundFill(Color.CYAN, CornerRadii.EMPTY, new Insets(-15, -30,-30,-30))));
        //vBoxHealthConditions.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT, new Insets(-15, -30,-30,-30))));
        vBoxNewHC.getChildren().addAll(hBox1, hBox2, lblHCNote, txtAreaHCNote, lblCurrentLevel, txtAreaCurrentLevel, hBox3, hBox4, lblNewLine, lblNewLine2);
        anchorPaneHealthConditions.getChildren().add(vBoxNewHC);
    }


}
