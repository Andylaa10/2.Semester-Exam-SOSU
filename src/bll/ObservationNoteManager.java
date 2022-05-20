package bll;

import be.ObservationNote;
import dal.ObservationNoteDAO;

import java.io.IOException;
import java.sql.SQLException;

public class ObservationNoteManager {

    private ObservationNoteDAO observationNoteDAO;

    /**
     * Constructor
     * @throws IOException
     */
    public ObservationNoteManager() throws IOException {
        observationNoteDAO = new ObservationNoteDAO();
    }

    /**
     * Gets an observation note assign to a citizen, by using the method from observationNoteDAO
     * @param citizenId
     * @return
     * @throws Exception
     */
    public ObservationNote getObservationNote(int citizenId) throws Exception {
        return observationNoteDAO.getObservationNote(citizenId);
    }

    /**
     * Creates oberservation note on a selected citizen, by using the method from observationNoteDAO
     * @param citizenId
     * @param date
     * @param note
     * @return
     * @throws SQLException
     */
    public ObservationNote createObservationNote(int citizenId, String date, String note) throws SQLException {
        return observationNoteDAO.createObservationNote(citizenId, date, note);
    }

    /**
     * Edits an observation note
     * @param observationNote
     * @throws Exception
     */
    public void editObservationNote(ObservationNote observationNote) throws Exception {
        observationNoteDAO.editObservationNote(observationNote);
    }

    /**
     * Deletes a selected observations note
     * @param id
     * @throws Exception
     */
    public void deleteObservationNote(int id) throws Exception {
        observationNoteDAO.deleteObservationNote(id);
    }
}