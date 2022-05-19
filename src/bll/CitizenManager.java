package bll;

import be.Citizen;
import bll.utilities.CitizenSearcher;
import dal.CitizenDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CitizenManager {

    private CitizenDAO citizenDAO;
    private CitizenSearcher citizenSearcher;

    public CitizenManager() throws IOException {
        citizenDAO = new CitizenDAO();
        citizenSearcher = new CitizenSearcher();
    }

    /**
     * Get a list of citizen using the getCitizens method from citizenDAO
     * @return
     * @throws SQLException
     */
    public List<Citizen> getCitizens() throws SQLException {
        return citizenDAO.getCitizens();
    }

    public Citizen getInfoOnCitizen(int citizenId) throws SQLException {
        return citizenDAO.getInfoOnCitizen(citizenId);
    }

    /**
     * Creates a case using the createCitizen method from citizenDAO
     */
    public Citizen createCitizen(String firstname, String lastName, String SSN, String address, String sex, int schoolId) throws SQLException {
        return citizenDAO.createCitizen(firstname, lastName, SSN, address, sex, schoolId);
    }

    /**
     * Deletes a citizen using the deleteCitizen method from citizenDAO
     * @param
     * @throws Exception
     */
    public void deleteCitizen(int citizenID) throws Exception {
        citizenDAO.deleteCitizen(citizenID);
    }

    /**
     * Edits a citizen using the editCitizen method in citizenDAO
     * @param citizen
     * @throws Exception
     */
    public void editCitizen(Citizen citizen) throws Exception {
        citizenDAO.editCitizen(citizen);
    }

    public List<Citizen> searchCitizen(String query) throws SQLException {
        List<Citizen> allCitizens = getCitizens();
        List<Citizen> searchResult = citizenSearcher.searchCitizen(allCitizens, query);
        return searchResult;
    }

}
