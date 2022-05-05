package gui.model;

import be.GeneralInformation;
import bll.GeneralInformationManager;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class GeneralInformationModel {

    private GeneralInformationManager generalInformationManager;

    public GeneralInformationModel() throws IOException {
        generalInformationManager = new GeneralInformationManager();
    }

    /**
     * Get a list of generalInformation using the getgeneralInformations method from generalInformationManager
     * @return
     * @throws SQLException
     */
    public List<GeneralInformation> getGeneralInformation() throws SQLException {
        return generalInformationManager.getGeneralInformation();
    }


    /**
     * Creates  generalInformation using the createGeneralInformation method from generalInformationManager
     * @param coping
     * @param motivation
     * @param resources
     * @param roles
     * @param habits
     * @param educationAndJob
     * @param lifestory
     * @param network
     * @param healthInformation
     * @param equipmentAids
     * @param homeLayout
     * @return
     * @throws SQLException
     */
    public GeneralInformation createGeneralInformation(String coping, String motivation, String resources, String roles,
                                                       String habits, String educationAndJob, String lifestory,
                                                       String network, String healthInformation, String equipmentAids,
                                                       String homeLayout) throws SQLException {
        return generalInformationManager.createGeneralInformation(coping, motivation, resources, roles, habits,
                educationAndJob, lifestory, network, healthInformation, equipmentAids, homeLayout);
    }

    /**
     * Deletes generalInformation using the deleteGeneralInformation method from generalInformationManager
     * @param id
     * @throws Exception
     */
    public void deleteGeneralInformation(int id) throws Exception {
        generalInformationManager.deleteGeneralInformation(id);
    }

    /**
     * Edits generalInformation using the editGeneralInformation method in generalInformationManager
     * @param generalInformation
     * @throws Exception
     */
    public void editGeneralInformation(GeneralInformation generalInformation) throws Exception {
        generalInformationManager.editGeneralInformation(generalInformation);
    }
}

