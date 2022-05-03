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
    public Citizen createCitizen(String firstname, String lastName, String SSN, String address, String sex, String info) throws SQLException {
        return citizenManager.createCitizen(firstname, lastName, SSN, address, sex, info);
    }

    /**
     * Deletes a citizen using the deleteCitizen method from citizenDAO
     * @param id
     * @throws Exception
     */
    public void deleteCitizen(int id) throws Exception {
        citizenManager.deleteCitizen(id);
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
