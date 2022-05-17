package gui.controller;

import be.Case;
import be.Citizen;
import gui.Facade.DataModelFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;


public class EditCaseViewController {


    @FXML
    private Button btnCancel;
    @FXML
    private Button btnSave;
    @FXML
    private TextField txtFieldCaseId;
    @FXML
    private TextField txtFieldCaseName;
    @FXML
    private TextArea txtAreaCaseInfo;

    private DataModelFacade dataModelFacade;


    public void setSelectedCase(Case aCase){
        txtFieldCaseId.setText(String.valueOf(aCase.getId()));
        txtFieldCaseName.setText(aCase.getName());
        txtAreaCaseInfo.setText(aCase.getInfo());
    }

    public EditCaseViewController() throws IOException {
        this.dataModelFacade = new DataModelFacade();
    }

    @FXML
    private void onActionSave() {
        int caseId = Integer.parseInt(txtFieldCaseId.getText());
    }

    @FXML
    private void onActionCloseWindow() {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }
}
