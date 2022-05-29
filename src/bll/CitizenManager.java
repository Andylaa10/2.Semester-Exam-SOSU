package bll;

import be.Citizen;
import bll.utilities.CitizenSearcher;
import dal.CitizenDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CitizenManager {

    private final CitizenDAO citizenDAO;
    private final CitizenSearcher citizenSearcher;

    public CitizenManager() throws IOException {
        citizenDAO = new CitizenDAO();
        citizenSearcher = new CitizenSearcher();
    }

    /**
     * Get a list of citizen using the getCitizens method from citizenDAO
     */
    public List<Citizen> getCitizens() throws SQLException {
        return citizenDAO.getCitizens();
    }

    /**
     * Gets all the citizens, that is assigned to school, by using the method from citizenDAO
     */
    public List<Citizen> getCitizensAndSchool(int schoolId) throws SQLException {
        return citizenDAO.getCitizenAndSchool(schoolId);
    }

    /**
     * Gets all the general information on a selected citizen, by using the method from citizenDAO
     */
    public Citizen getInfoOnCitizen(int citizenId) throws SQLException {
        return citizenDAO.getInfoOnCitizen(citizenId);
    }

    /**
     * Creates a case using the createCitizen method from citizenDAO
     */
    public Citizen createCitizen(String firstname, String lastName, String SSN, String sex, int schoolId) throws SQLException {
        return citizenDAO.createCitizen(firstname, lastName, SSN, sex, schoolId);
    }

    /**
     * Deletes a citizen using the deleteCitizen method from citizenDAO
     */
    public void deleteCitizen(int citizenID) throws Exception {
        citizenDAO.deleteCitizen(citizenID);
    }

    /**
     * Edits a citizen using the editCitizen method in citizenDAO
     */
    public void editCitizen(Citizen citizen) throws Exception {
        citizenDAO.editCitizen(citizen);
    }

    /**
     * Gets a list of citizen based on the query, by using the method from citizenDAO
     */
    public List<Citizen> searchCitizen(String query, int schoolId) throws SQLException {
        List<Citizen> allCitizens = getCitizensAndSchool(schoolId);
        List<Citizen> searchResult = citizenSearcher.searchCitizen(allCitizens, query);
        return searchResult;
    }

}
