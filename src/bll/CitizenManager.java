package bll;

import be.Citizen;
import dal.CitizenDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CitizenManager {

    private CitizenDAO citizenDAO;

    public CitizenManager() throws IOException {
        citizenDAO = new CitizenDAO();
    }

    /**
     * Get a list of citizen using the getCitizens method from citizenDAO
     * @return
     * @throws SQLException
     */
    public List<Citizen> getCitizens() throws SQLException {
        return citizenDAO.getCitizens();
    }

    /**
     * Get a list of cases assigned to citizen, using the getCitizensOnCase method from citizenDAO
     * @param caseId
     * @return
     * @throws SQLException
     */
    public List<Citizen> getCitizensOnCases(int caseId) throws SQLException {
        return citizenDAO.getCitizensOnCase(caseId);
    }

    /**
     * Creates a case using the createCitizen method from citizenDAO
     * @param firstname
     * @param lastName
     * @param SSN
     * @param address
     * @param sex
     * @return
     * @throws SQLException
     */
    public Citizen createCitizen(String firstname, String lastName, String SSN, String address, String sex) throws SQLException {
        return citizenDAO.createCitizen(firstname, lastName, SSN, address, sex);
    }

    /**
     * Deletes a citizen using the deleteCitizen method from citizenDAO
     * @param id
     * @throws Exception
     */
    public void deleteCitizen(int id) throws Exception {
        citizenDAO.deleteCitizen(id);
    }

    /**
     * Edits a citizen using the editCitizen method in citizenDAO
     * @param citizen
     * @throws Exception
     */
    public void editCitizen(Citizen citizen) throws Exception {
        citizenDAO.editCitizen(citizen);
    }

}
