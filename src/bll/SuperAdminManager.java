package bll;

import be.School;
import be.SuperAdmin;
import dal.SuperAdminDAO;
import java.io.IOException;
import java.sql.SQLException;

public class SuperAdminManager {

    private SuperAdminDAO superAdminDAO;

    /**
     * Constructor
     * @throws IOException
     */
    public SuperAdminManager() throws IOException {
        superAdminDAO = new SuperAdminDAO();
    }

    /**
     * Creates a super admin using the createSuperAdmin method in superAdminDAO
     * @param username
     * @param password
     * @return
     * @throws SQLException
     */
    public SuperAdmin createSuperAdmin(String username, String password) throws SQLException {
        return superAdminDAO.createSuperAdmin(username, password);
    }

    /**
     * Deletes a super admin using the deleteSuperAdmin method in superAdminDAO
     * @param id
     */
    public void deleteSuperAdmin(int id){
        superAdminDAO.deleteSuperAdmin(id);
    }

    /**
     * Edits a super admin using the editSuperAdmin method in superAdminDAO
     * @param superAdmin
     * @throws Exception
     */
    public void editSuperAdmin(SuperAdmin superAdmin) throws Exception {
        superAdminDAO.editSuperAdmin(superAdmin);
    }

    /**
     * Gets the super admin Login using the superAdminLogin method from superAdminDAO
     * @param username
     * @param password
     * @return
     * @throws SQLException
     */
    public SuperAdmin superAdminLogin(String username, String password) throws SQLException {
        return superAdminDAO.superAdminLogin(username, password);
    }

    /**
     * Creates a school using the createSchool method in superAdminDAO
     * @param schoolName
     * @return
     * @throws SQLException
     */
    public School createSchool(String schoolName) throws SQLException {
        return superAdminDAO.createSchool(schoolName);
    }

    /**
     * Deletes a school using the deleteSchool method in superAdminDAO
     * @param schoolID
     */
    public void deleteSchool(int schoolID) {
        superAdminDAO.deleteSchool(schoolID);
    }

    /**
     * Edits a school using the editSchool method in superAdminDAO
     * @param school
     * @throws Exception
     */
    public void editSchool(School school) throws Exception {
        superAdminDAO.editSchool(school);
    }

}
