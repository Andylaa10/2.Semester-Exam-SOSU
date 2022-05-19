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
import java.sql.SQLException;
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

    private final DataModelFacade dataModelFacade;

    private int citizenId;

    public ObservationNoteViewController() throws IOException, SQLException {
        this.dataModelFacade = new DataModelFacade();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }


    public void setSelectedCitizen(Citizen citizen) throws Exception {
        txtFieldCitizenId.setText(String.valueOf(citizen.getId()));
        citizenId = Integer.parseInt(txtFieldCitizenId.getText());
        ObservationNote observationNote = dataModelFacade.getObservationNote(citizenId);
        if (observationNote != null) {
            txtFieldFollowUpDate.setText(observationNote.getDate());
            txtAreaObservationNote.setText(observationNote.getNote());
        } else {
            txtFieldFollowUpDate.setText("");
            txtAreaObservationNote.setText("");
        }
    }


    @FXML
    private void onActionSave() throws Exception {
        Stage stage = (Stage) btnSave.getScene().getWindow();

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

    @FXML
    private void onActionClose() {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }
}
