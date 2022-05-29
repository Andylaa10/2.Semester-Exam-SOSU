package bll;

import be.GeneralInformation;
import dal.GeneralInformationDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class GeneralInformationManager {

    private final GeneralInformationDAO generalInformationDAO;

    /**
     * Constructor
     */
    public GeneralInformationManager() throws IOException {
        generalInformationDAO = new GeneralInformationDAO();
    }

    /**
     * Get a list of generalInformation using the getgeneralInformations method from generalInformationDAO
     */
    public List<GeneralInformation> getGeneralInformation() throws SQLException {
        return generalInformationDAO.getGeneralInformation();
    }

    /**
     * Gets generalInformation that is assigned to citizen, using the getGeneralInformationsOnCitizen
     * method from generalInformationDAO
     */
    public GeneralInformation getGeneralInformationOnCitizen(int citizenID) throws SQLException {
        return generalInformationDAO.getGeneralInformationOnCitizen(citizenID);
    }


    /**
     * Creates generalInformation using the createGeneralInformation method from generalInformationDAO
     */
    public GeneralInformation createGeneralInformation(int citizenId, String coping, String motivation, String resources, String roles,
                                                       String habits, String educationAndJob, String lifestory,
                                                       String network, String healthInformation, String equipmentAids,
                                                       String homeLayout) throws SQLException {
        return generalInformationDAO.createGeneralInformation(citizenId, coping, motivation, resources, roles, habits,
                educationAndJob, lifestory, network, healthInformation, equipmentAids, homeLayout);
    }

    /**
     * Deletes generalInformation using the deleteGeneralInformation method from generalInformationDAO
     */
    public void deleteGeneralInformation(int id) throws Exception {
        generalInformationDAO.deleteGeneralInformation(id);
    }

    /**
     * Edits generalInformation using the editGeneralInformation method in generalInformationDAO
     */
    public void editGeneralInformation(GeneralInformation generalInformation) throws Exception {
        generalInformationDAO.editGeneralInformation(generalInformation);
    }

}
