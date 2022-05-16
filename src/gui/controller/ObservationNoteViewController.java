package gui.controller;

import gui.Facade.DataModelFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ObservationNoteViewController implements Initializable {

    @FXML
    private TextArea txtAreaObservationNote;
    @FXML
    private DatePicker datePickerFollowUp;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnClose;

    DataModelFacade dataModelFacade;

    public ObservationNoteViewController() throws IOException {
        this.dataModelFacade = new DataModelFacade();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void onActionSave() {
        Stage stage = (Stage) btnSave.getScene().getWindow();
        datePickerFollowUp.getValue();
        txtAreaObservationNote.getText();



        stage.close();
    }

    @FXML
    private void onActionClose() {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }
}
