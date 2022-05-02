package gui.model;

import be.School;
import be.SuperAdmin;
import bll.SuperAdminManager;
import dal.SuperAdminDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class SuperAdminModel {

    private SuperAdminManager superAdminManager;

    /**
     * Constructor
     * @throws IOException
     */
    public SuperAdminModel() throws IOException {
        superAdminManager = new SuperAdminManager();
    }

    /**
     * Creates a superAdmin using the createSuperAdmin method from superAdminManager
     * @param username
     * @param password
     * @return
     * @throws SQLException
     */
    public SuperAdmin createSuperAdmin(String username, String password) throws SQLException {
        return superAdminManager.createSuperAdmin(username, password);
    }

    /**
     * Deletes a super admin using the deleteSuperAdmin method from superAdminManager
     * @param id
     */
    public void deleteSuperAdmin(int id) throws SQLException {
        superAdminManager.deleteSuperAdmin(id);
    }

    /**
     * Edits a super admin using the editSuperAdmin method from superAdminManager
     * @param superAdmin
     * @throws Exception
     */
    public void editSuperAdmin(SuperAdmin superAdmin) throws Exception {
        superAdminManager.editSuperAdmin(superAdmin);
    }

    /**
     * Gets the super admin login using superAdmin method from superAdminManager
     * @param username
     * @param password
     * @return
     * @throws SQLException
     */
    public SuperAdmin superAdminLogin(String username, String password) throws SQLException {
        return superAdminManager.superAdminLogin(username, password);
    }

    public List<School> getSchools() throws SQLException {
        return superAdminManager.getSchools();
    }

        /**
         * Creates a school using the createSchool method from superAdminManager
         * @param schoolName
         * @return
         * @throws SQLException
         */
    public School createSchool(String schoolName) throws SQLException {
        return superAdminManager.createSchool(schoolName);
    }

    /**
     * Deletes a school using the deleteSchool method from superAdminManager
     * @param schoolID
     */
    public void deleteSchool(int schoolID) throws SQLException {
        superAdminManager.deleteSchool(schoolID);
    }

    /**
     * Edits a school using the editSchool method from superAdminManager
     * @param school
     * @throws Exception
     */
    public void editSchool(School school) throws Exception {
        superAdminManager.editSchool(school);
    }



}
