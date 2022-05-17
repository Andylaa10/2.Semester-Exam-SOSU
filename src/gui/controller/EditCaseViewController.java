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
import org.w3c.dom.Text;

import java.io.IOException;


public class EditCaseViewController {


    @FXML
    private Button btnCancel;
    @FXML
    private Button btnSave;
    @FXML
    private TextField txtFieldCaseId;
    @FXML
    private TextField txtFieldCaseDate;
    @FXML
    private TextField txtFieldCaseName;
    @FXML
    private TextArea txtAreaCaseInfo;

    private DataModelFacade dataModelFacade;
    private StudentViewController studentViewController;

    public EditCaseViewController() throws IOException {
        this.dataModelFacade = new DataModelFacade();
        this.studentViewController = new StudentViewController();
    }


    public void setSelectedCase(Case aCase){
        txtFieldCaseId.setText(String.valueOf(aCase.getId()));
        txtFieldCaseDate.setText(aCase.getDate());
        txtFieldCaseName.setText(aCase.getName());
        txtAreaCaseInfo.setText(aCase.getInfo());
    }

    public void setSelectedCaseAsTeacher(Case aCase){
        txtFieldCaseId.setText(String.valueOf(aCase.getId()));
        txtFieldCaseDate.setText(aCase.getDate());
        txtFieldCaseName.setText(aCase.getName());
        txtAreaCaseInfo.setText(aCase.getInfo());
        btnCancel.setText("Tilbage");
        btnSave.setVisible(false);
        txtFieldCaseName.setEditable(false);
        txtAreaCaseInfo.setEditable(false);
    }

    public void setSelectedCaseAsAdmin(Case aCase){
        txtFieldCaseId.setText(String.valueOf(aCase.getId()));
        txtFieldCaseDate.setText(aCase.getDate());
        txtFieldCaseName.setText(aCase.getName());
        txtAreaCaseInfo.setText(aCase.getInfo());
        btnCancel.setText("Tilbage");
        btnSave.setVisible(false);
        txtFieldCaseName.setEditable(false);
        txtAreaCaseInfo.setEditable(false);
    }

    @FXML
    private void onActionSave() throws Exception {
        int caseId = Integer.parseInt(txtFieldCaseId.getText());
        String name = txtFieldCaseName.getText();
        String info = txtAreaCaseInfo.getText();

        Case aCase = new Case(caseId, name, info);

        dataModelFacade.editCase(aCase);

    }

    @FXML
    private void onActionCloseWindow() {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }
}
