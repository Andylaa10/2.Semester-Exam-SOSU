package bll;

import be.ObservationNote;
import dal.ObservationNoteDAO;

import java.io.IOException;
import java.sql.SQLException;

public class ObservationNoteManager {

    private ObservationNoteDAO observationNoteDAO;

    public ObservationNoteManager() throws IOException {
        observationNoteDAO = new ObservationNoteDAO();
    }

    public ObservationNote getObservationNote(int citizenId) throws Exception {
        return observationNoteDAO.getObservationNote(citizenId);
    }

    public ObservationNote createObservationNote(int citizenId, String date, String note) throws SQLException {
        return observationNoteDAO.createObservationNote(citizenId, date, note);
    }

    public void editObservationNote(ObservationNote observationNote) throws Exception {
        observationNoteDAO.editObservationNote(observationNote);
    }

    public void deleteObservationNote(int id) throws Exception {
        observationNoteDAO.deleteObservationNote(id);
    }
}