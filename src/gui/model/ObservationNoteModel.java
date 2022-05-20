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

    /**
     * Gets a single observationNote using te getObservationNote method from the observationNoteManager.
     *
     * @param citizenId
     * @return
     * @throws Exception
     */
    public ObservationNote getObservationNote(int citizenId) throws Exception {
        return observationNoteManager.getObservationNote(citizenId);
    }

    /**
     * Creates an observationNote using the createObservationNote method from the observationNoteManager.
     *
     * @param citizenId
     * @param date
     * @param note
     * @return
     * @throws SQLException
     */
    public ObservationNote createObservationNote(int citizenId, String date, String note) throws SQLException {
        return observationNoteManager.createObservationNote(citizenId, date, note);
    }

    /**
     * Edits an observationNote using the editObservationNote method from the observationNoteManger.
     *
     * @param observationNote
     * @throws Exception
     */
    public void editObservationNote(ObservationNote observationNote) throws Exception {
        observationNoteManager.editObservationNote(observationNote);
    }

    /**
     * Deletes an observationNote using the deleteObservationNote method from the observationNoteManager.
     *
     * @param id
     * @throws Exception
     */
    public void deleteObservationNote(int id) throws Exception {
        observationNoteManager.deleteObservationNote(id);
    }

}
