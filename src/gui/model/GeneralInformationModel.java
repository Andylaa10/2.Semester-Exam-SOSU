package gui.model;

import be.GeneralInformation;
import bll.GeneralInformationManager;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class GeneralInformationModel {

    private final GeneralInformationManager generalInformationManager;

    public GeneralInformationModel() throws IOException {
        generalInformationManager = new GeneralInformationManager();
    }

    /**
     * Get a list of generalInformation using the getgeneralInformations method from generalInformationManager
     */
    public List<GeneralInformation> getGeneralInformation() throws SQLException {
        return generalInformationManager.getGeneralInformation();
    }

    /**
     * Gets a list of generalInformation that is assigned to citizen, using the getGeneralInformationsOnCitizen
     * method from generalInformationManager
     */
    public GeneralInformation getGeneralInformationOnCitizen(int citizenID) throws SQLException {
        return generalInformationManager.getGeneralInformationOnCitizen(citizenID);
    }


    /**
     * Creates  generalInformation using the createGeneralInformation method from generalInformationManager
     */
    public GeneralInformation createGeneralInformation(int citizenId, String coping, String motivation, String resources, String roles,
                                                       String habits, String educationAndJob, String lifestory,
                                                       String network, String healthInformation, String equipmentAids,
                                                       String homeLayout) throws SQLException {
        return generalInformationManager.createGeneralInformation(citizenId, coping, motivation, resources, roles, habits,
                educationAndJob, lifestory, network, healthInformation, equipmentAids, homeLayout);
    }

    /**
     * Deletes generalInformation using the deleteGeneralInformation method from generalInformationManager
     */
    public void deleteGeneralInformation(int id) throws Exception {
        generalInformationManager.deleteGeneralInformation(id);
    }

    /**
     * Edits generalInformation using the editGeneralInformation method in generalInformationManager
     */
    public void editGeneralInformation(GeneralInformation generalInformation) throws Exception {
        generalInformationManager.editGeneralInformation(generalInformation);
    }
}

