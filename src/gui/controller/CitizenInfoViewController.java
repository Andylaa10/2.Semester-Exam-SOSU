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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

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

    @FXML
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

    @FXML
    private void createHealthConditionsVBox(){
        Label labelHealthCondition = new Label("Sagsnavn");
        labelHealthCondition.setFont(Font.font(18.0));
        TextField textFieldHealthCondition = new TextField();
        textFieldHealthCondition.setPrefWidth(382);
        textFieldHealthCondition.setMaxWidth(382);
        textFieldHealthCondition.setPrefHeight(26);
        textFieldHealthCondition.setMaxHeight(26);
        textFieldHealthCondition.setFont(Font.font(12.0));
        Label labelHCSubCategory = new Label("Underkategori");
        labelHCSubCategory.setFont(Font.font(18.0));
        TextField textFieldHCSubCategory = new TextField();
        textFieldHCSubCategory.setPrefWidth(382);
        textFieldHCSubCategory.setMaxWidth(382);
        textFieldHCSubCategory.setPrefHeight(26);
        textFieldHCSubCategory.setMaxHeight(26);
        textFieldHCSubCategory.setFont(Font.font(12.0));
        TextArea txtAreaNote = new TextArea();
        txtAreaNote.setMinHeight(118);
        txtAreaNote.setMinWidth(475);
        txtAreaNote.setMaxHeight(118);
        txtAreaNote.setMaxWidth(475);
        TextArea txtAreaCurrentLevel = new TextArea();
        txtAreaCurrentLevel.setMinHeight(118);
        txtAreaCurrentLevel.setMinWidth(475);
        txtAreaCurrentLevel.setMaxHeight(118);
        txtAreaCurrentLevel.setMaxWidth(475);
        TextField textFieldExpectedLevel = new TextField();
        textFieldExpectedLevel.setPrefWidth(382);
        textFieldExpectedLevel.setMaxWidth(382);
        textFieldExpectedLevel.setPrefHeight(26);
        textFieldExpectedLevel.setMaxHeight(26);
        textFieldExpectedLevel.setFont(Font.font(12.0));
        TextField textFieldHCRelevance = new TextField();
        textFieldHCRelevance.setPrefWidth(382);
        textFieldHCRelevance.setMaxWidth(382);
        textFieldHCRelevance.setPrefHeight(26);
        textFieldHCRelevance.setMaxHeight(26);
        textFieldHCRelevance.setFont(Font.font(12.0));

        vBoxHealthConditions.setSpacing(5);
        vBoxHealthConditions.setPrefHeight(200);
        vBoxHealthConditions.setPrefWidth(522);
        vBoxHealthConditions.setPadding(new Insets(5,5,100,15));
        vBoxHealthConditions.getChildren().addAll(labelHealthCondition, textFieldHealthCondition, labelHCSubCategory,
                textFieldHCSubCategory, txtAreaNote, txtAreaCurrentLevel, textFieldExpectedLevel, textFieldHCRelevance);

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
        vBoxCase.getChildren().addAll(labelName, txtFieldCaseName, labelInfo, textAreaCaseInfo);
    }


    public void addNewHCToVBox(ActionEvent actionEvent) {
        Label labelHealthCondition = new Label("Sagsnavn");
        labelHealthCondition.setFont(Font.font(18.0));
        TextField textFieldHealthCondition = new TextField();
        textFieldHealthCondition.setPrefWidth(382);
        textFieldHealthCondition.setMaxWidth(382);
        textFieldHealthCondition.setPrefHeight(26);
        textFieldHealthCondition.setMaxHeight(26);
        textFieldHealthCondition.setFont(Font.font(12.0));
        Label labelHCSubCategory = new Label("Underkategori");
        labelHCSubCategory.setFont(Font.font(18.0));
        TextField textFieldHCSubCategory = new TextField();
        textFieldHCSubCategory.setPrefWidth(382);
        textFieldHCSubCategory.setMaxWidth(382);
        textFieldHCSubCategory.setPrefHeight(26);
        textFieldHCSubCategory.setMaxHeight(26);
        textFieldHCSubCategory.setFont(Font.font(12.0));
        TextArea txtAreaNote = new TextArea();
        txtAreaNote.setMinHeight(118);
        txtAreaNote.setMinWidth(475);
        txtAreaNote.setMaxHeight(118);
        txtAreaNote.setMaxWidth(475);
        TextArea txtAreaCurrentLevel = new TextArea();
        txtAreaCurrentLevel.setMinHeight(118);
        txtAreaCurrentLevel.setMinWidth(475);
        txtAreaCurrentLevel.setMaxHeight(118);
        txtAreaCurrentLevel.setMaxWidth(475);
        TextField textFieldExpectedLevel = new TextField();
        textFieldExpectedLevel.setPrefWidth(382);
        textFieldExpectedLevel.setMaxWidth(382);
        textFieldExpectedLevel.setPrefHeight(26);
        textFieldExpectedLevel.setMaxHeight(26);
        textFieldExpectedLevel.setFont(Font.font(12.0));
        TextField textFieldHCRelevance = new TextField();
        textFieldHCRelevance.setPrefWidth(382);
        textFieldHCRelevance.setMaxWidth(382);
        textFieldHCRelevance.setPrefHeight(26);
        textFieldHCRelevance.setMaxHeight(26);
        textFieldHCRelevance.setFont(Font.font(12.0));
        vBoxHealthConditions.getChildren().addAll(labelHealthCondition, textFieldHealthCondition, labelHCSubCategory,
                textFieldHCSubCategory, txtAreaNote, txtAreaCurrentLevel, textFieldExpectedLevel, textFieldHCRelevance);
    }
}
