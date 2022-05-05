package bll;

import be.GeneralInformation;
import dal.GeneralInformationDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class GeneralInformationManager {

    private GeneralInformationDAO generalInformationDAO;

    public GeneralInformationManager() throws IOException {
        generalInformationDAO = new GeneralInformationDAO();
    }

    /**
     * Get a list of generalInformation using the getgeneralInformations method from generalInformationDAO
     * @return
     * @throws SQLException
     */
    public List<GeneralInformation> getGeneralInformation() throws SQLException {
        return generalInformationDAO.getGeneralInformation();
    }


    /**
     * Creates  generalInformation using the createGeneralInformation method from generalInformationDAO
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
        return generalInformationDAO.createGeneralInformation(coping, motivation, resources, roles, habits,
                educationAndJob, lifestory, network, healthInformation, equipmentAids, homeLayout);
    }

    /**
     * Deletes generalInformation using the deleteGeneralInformation method from generalInformationDAO
     * @param id
     * @throws Exception
     */
    public void deleteGeneralInformation(int id) throws Exception {
        generalInformationDAO.deleteGeneralInformation(id);
    }

    /**
     * Edits generalInformation using the editGeneralInformation method in generalInformationDAO
     * @param generalInformation
     * @throws Exception
     */
    public void editGeneralInformation(GeneralInformation generalInformation) throws Exception {
        generalInformationDAO.editGeneralInformation(generalInformation);
    }
}