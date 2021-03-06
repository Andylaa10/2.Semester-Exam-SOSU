package gui.controller;

import be.Case;
import gui.model.Facade.DataModelFacade;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;

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

    private final DataModelFacade dataModelFacade = DataModelFacade.getInstance();

    public EditCaseViewController() throws IOException, SQLException {
    }

    /**
     * Method for setting the selectedCase info.
     * @param aCase
     */
    public void setSelectedCase(Case aCase) {
        txtFieldCaseId.setText(String.valueOf(aCase.getId()));
        txtFieldCaseDate.setText(aCase.getDate());
        txtFieldCaseName.setText(aCase.getName());
        txtAreaCaseInfo.setText(aCase.getInfo());
    }

    /**
     * On action method for saving the edited case.
     * @throws Exception
     */
    @FXML
    private void onActionSave() throws Exception {
        int caseId = Integer.parseInt(txtFieldCaseId.getText());
        String name = txtFieldCaseName.getText();
        String date = txtFieldCaseDate.getText();
        String info = txtAreaCaseInfo.getText();

        Case aCase = new Case(caseId, name, date, info);
        dataModelFacade.editCase(aCase);


        Stage stage = (Stage) btnSave.getScene().getWindow();
        stage.close();
    }

    /**
     * On action method for the cancel button, that closes the window.
     */
    @FXML
    private void onActionCloseWindow() {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }
}
