package gui.model;

import be.Citizen;
import bll.CitizenManager;
import bll.utilities.CitizenSearcher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CitizenModel {

    private final CitizenManager citizenManager;
    private ObservableList<Citizen> citizensToBeViewed;


    public CitizenModel() throws IOException, SQLException {
        citizenManager = new CitizenManager();
        citizensToBeViewed = FXCollections.observableArrayList();
        citizensToBeViewed.addAll(citizenManager.getCitizens());
    }

    /**
     * Get a list of citizen using the getCitizens method from citizenManager
     */
    public List<Citizen> getCitizens() throws SQLException {
        return citizenManager.getCitizens();
    }

    /**
     * Gets a list of citizens on a specific school using the getCitizensAndSchool method from citizenManager.
     *
     * @param schoolId
     * @return
     * @throws SQLException
     */
    public List<Citizen> getCitizensAndSchool(int schoolId) throws SQLException {
        return citizenManager.getCitizensAndSchool(schoolId);
    }

    /**
     * Gets info on a specific citizen using the getInfoOnCitizen method from citizenManager.
     *
     * @param citizenId
     * @return
     * @throws SQLException
     */
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

    /**
     * Returns a list of citizens from search, using the searchCitizen method from citizenManager.
     *
     * @param query
     * @param schoolId
     * @return
     * @throws SQLException
     */
    public List<Citizen> searchCitizen(String query, int schoolId) throws SQLException {
        List<Citizen> searchResults = null;

        searchResults = citizenManager.searchCitizen(query, schoolId);
        citizensToBeViewed.clear();
        citizensToBeViewed.addAll(searchResults);

        return searchResults;
    }

}
