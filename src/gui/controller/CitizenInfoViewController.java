package gui.controller;

import be.Citizen;
import gui.Facade.DataModelFacade;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class CitizenInfoViewController {
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
    public VBox vBoxCases;
    public TextField txtFieldCitizenID;

    private DataModelFacade dataModelFacade;
    private StudentViewController studentViewController;

    public CitizenInfoViewController() throws IOException {
        this.studentViewController = new StudentViewController();
        this.dataModelFacade = new DataModelFacade();
    }

    public void setSelectedCitizen(Citizen citizen){
        
    }





}
