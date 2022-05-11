package gui.model;

import be.Citizen;
import bll.CitizenManager;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CitizenModel {

    private final CitizenManager citizenManager;

    public CitizenModel() throws IOException {
        citizenManager = new CitizenManager();
    }

    /**
     * Get a list of citizen using the getCitizens method from citizenManager
     */
    public List<Citizen> getCitizens() throws SQLException {
        return citizenManager.getCitizens();
    }

    public Citizen getInfoOnCitizen(int citizenId) throws SQLException {
        return citizenManager.getInfoOnCitizen(citizenId);
    }


    /**
     * Creates a case using the createCitizen method from citizenManager
     */
    public Citizen createCitizen(String firstname, String lastName, String SSN, String address, String sex, int schoolId) throws SQLException {
        return citizenManager.createCitizen(firstname, lastName, SSN, address, sex, schoolId);
    }

    /**
     * Deletes a citizen using the deleteCitizen method from citizenDAO
     */
    public void deleteCitizen(int citizenID) throws Exception {
        citizenManager.deleteCitizen(citizenID);
    }

    /**
     * Edits a citizen using the editCitizen method in citizenDAO
     */
    public void editCitizen(Citizen citizen) throws Exception {
        citizenManager.editCitizen(citizen);
    }
}
