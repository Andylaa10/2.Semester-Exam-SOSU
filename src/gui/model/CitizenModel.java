package gui.model;

import be.Citizen;
import bll.CitizenManager;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CitizenModel {

    private CitizenManager citizenManager;

    public CitizenModel() throws IOException {
        citizenManager = new CitizenManager();
    }

    /**
     * Get a list of citizen using the getCitizens method from citizenManager
     * @return
     * @throws SQLException
     */
    public List<Citizen> getCitizens() throws SQLException {
        return citizenManager.getCitizens();
    }

    public Citizen getInfoOnCitizen(int citizenId) throws SQLException {
        return citizenManager.getInfoOnCitizen(citizenId);
    }

    /**
     * Get a list of cases assigned to citizens using getCitizensOncases method from citizenManager
     * @param caseId
     * @return
     * @throws SQLException
     */
    public List<Citizen> getCitizensOnCases(int caseId) throws SQLException {
        return citizenManager.getCitizensOnCases(caseId);
    }

    /**
     * Creates a case using the createCitizen method from citizenManager
     * @param firstname
     * @param lastName
     * @param SSN
     * @param address
     * @param sex
     * @return
     * @throws SQLException
     */
    public Citizen createCitizen(String firstname, String lastName, String SSN, String address, String sex) throws SQLException {
        return citizenManager.createCitizen(firstname, lastName, SSN, address, sex);
    }

    /**
     * Deletes a citizen using the deleteCitizen method from citizenDAO
     * @throws Exception
     */
    public void deleteCitizen(int citizenID) throws Exception {
        citizenManager.deleteCitizen(citizenID);
    }

    /**
     * Edits a citizen using the editCitizen method in citizenDAO
     * @param citizen
     * @throws Exception
     */
    public void editCitizen(Citizen citizen) throws Exception {
        citizenManager.editCitizen(citizen);
    }
}
