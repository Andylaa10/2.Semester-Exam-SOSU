package gui.controller;

import be.Citizen;
import be.ObservationNote;
import gui.Facade.DataModelFacade;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;

public class ObservationNoteViewController {

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

    private final DataModelFacade dataModelFacade = DataModelFacade.getInstance();

    private int citizenId;

    public ObservationNoteViewController() throws IOException, SQLException {
    }


    /**
     * Method for setting the selected citizen, with the data that follows.
     * @param citizen
     * @throws Exception
     */
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


    /**
     * OnAction method for saving.
     * Gets the text from textfields and uses the createObservationNote method from the dataModelFacade.
     * @throws Exception
     */
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

    /**
     * OnAction method for clicking the close button, which closes the stage.
     */
    @FXML
    private void onActionClose() {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }
}
