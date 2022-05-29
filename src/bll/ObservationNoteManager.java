package bll;

import be.ObservationNote;
import dal.ObservationNoteDAO;

import java.io.IOException;
import java.sql.SQLException;

public class ObservationNoteManager {

    private final ObservationNoteDAO observationNoteDAO;

    /**
     * Constructor
     *
     */
    public ObservationNoteManager() throws IOException {
        observationNoteDAO = new ObservationNoteDAO();
    }

    /**
     * Gets an observation note assign to a citizen, by using the method from observationNoteDAO

     */
    public ObservationNote getObservationNote(int citizenId) throws Exception {
        return observationNoteDAO.getObservationNote(citizenId);
    }

    /**
     * Creates oberservation note on a selected citizen, by using the method from observationNoteDAO
     */
    public ObservationNote createObservationNote(int citizenId, String date, String note) throws SQLException {
        return observationNoteDAO.createObservationNote(citizenId, date, note);
    }

    /**
     * Edits an observation note
     */
    public void editObservationNote(ObservationNote observationNote) throws Exception {
        observationNoteDAO.editObservationNote(observationNote);
    }

    /**
     * Deletes a selected observations note
     */
    public void deleteObservationNote(int id) throws Exception {
        observationNoteDAO.deleteObservationNote(id);
    }
}