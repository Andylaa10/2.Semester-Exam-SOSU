package gui.model;

import be.ObservationNote;
import bll.ObservationNoteManager;
import dal.ObservationNoteDAO;

import java.io.IOException;
import java.sql.SQLException;

public class ObservationNoteModel {

    private ObservationNoteManager observationNoteManager;

    public ObservationNoteModel() throws IOException {
        observationNoteManager = new ObservationNoteManager();
    }

    public ObservationNote getObservationNote(int citizenId) throws Exception {
        return observationNoteManager.getObservationNote(citizenId);
    }

    public ObservationNote createObservationNote(int citizenId, String date, String note) throws SQLException {
        return observationNoteManager.createObservationNote(citizenId, date, note);
    }

    public void editObservationNote(ObservationNote observationNote) throws Exception {
        observationNoteManager.editObservationNote(observationNote);
    }

    public void deleteObservationNote(int id) throws Exception {
        observationNoteManager.deleteObservationNote(id);
    }

}
