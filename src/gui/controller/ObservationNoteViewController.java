package gui.controller;

import be.Citizen;
import be.ObservationNote;
import gui.Facade.DataModelFacade;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ObservationNoteViewController implements Initializable {

    @FXML
    private TextField txtFieldFollowUpDate;
    @FXML
    private TextField txtFieldCitizenId;
    @FXML
    private TextArea txtAreaObservationNote;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnClose;

    DataModelFacade dataModelFacade;

    private int citizenId;

    public ObservationNoteViewController() throws IOException {
        this.dataModelFacade = new DataModelFacade();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    
    public void setSelectedCitizen(Citizen citizen) throws Exception {
        txtFieldCitizenId.setText(String.valueOf(citizen.getId()));
        citizenId = Integer.parseInt(txtFieldCitizenId.getText());

        ObservationNote observationNote = dataModelFacade.getObservationNote(citizenId);
        txtAreaObservationNote.setText(observationNote.getNote());
        txtFieldFollowUpDate.setText(observationNote.getDate());
    }


    @FXML
    private void onActionSave() throws Exception {
        Stage stage = (Stage) btnSave.getScene().getWindow();

        String date = String.valueOf(txtFieldFollowUpDate);
        String note = String.valueOf(txtAreaObservationNote);

        if (txtFieldFollowUpDate != null && txtAreaObservationNote.getText() != null) {
            dataModelFacade.createObservationNote(citizenId, date, note);
        } else {
            ObservationNote observationNote = new ObservationNote(citizenId, date, note);
            dataModelFacade.editObservationNote(observationNote);
        }

        stage.close();
    }

    @FXML
    private void onActionClose() {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }
}
