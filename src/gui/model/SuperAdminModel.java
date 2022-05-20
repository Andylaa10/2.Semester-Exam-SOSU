package gui.model;

import be.*;
import bll.SuperAdminManager;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class SuperAdminModel {

    private final SuperAdminManager superAdminManager;

    /**
     * Constructor
     */
    public SuperAdminModel() throws IOException {
        superAdminManager = new SuperAdminManager();
    }

    /**
     * Creates a superAdmin using the createSuperAdmin method from superAdminManager
     */
    public SuperAdmin createSuperAdmin(String username, String password) throws SQLException {
        return superAdminManager.createSuperAdmin(username, password);
    }

    /**
     * Deletes a super admin using the deleteSuperAdmin method from superAdminManager
     */
    public void deleteSuperAdmin(int id) throws SQLException {
        superAdminManager.deleteSuperAdmin(id);
    }

    /**
     * Edits a super admin using the editSuperAdmin method from superAdminManager
     */
    public void editSuperAdmin(SuperAdmin superAdmin) throws Exception {
        superAdminManager.editSuperAdmin(superAdmin);
    }

    /**
     * Gets the super admin login using superAdmin method from superAdminManager
     */
    public SuperAdmin superAdminLogin(String username, String password) throws SQLException {
        return superAdminManager.superAdminLogin(username, password);
    }

    /**
     * Gets a list of schools using the getSchools method from the superAdminManager.
     *
     * @return
     * @throws SQLException
     */
    public List<School> getSchools() throws SQLException {
        return superAdminManager.getSchools();
    }

    /**
     * Gets all assigned teachers at a school using the getAssignedTeachers method from the superAdminManager.
     *
     * @param schoolId
     * @return
     * @throws SQLException
     */
    public List<User> getAssignedTeachers(int schoolId) throws SQLException {
        return superAdminManager.getAssignedTeachers(schoolId);
    }

    /**
     * Gets all assigned admin at a school using the getAssignedAdmins method from the superAdminManager.
     *
     * @param schoolId
     * @return
     * @throws SQLException
     */
    public List<User> getAssignedAdmins(int schoolId) throws SQLException {
        return superAdminManager.getAssignedAdmins(schoolId);
    }

    /**
     * Gets all assigned students at a school using the getAssignedStudents method from the superAdminManager.
     *
     * @param schoolId
     * @return
     * @throws SQLException
     */
    public List<User> getAssignedStudents(int schoolId) throws SQLException {
        return superAdminManager.getAssignedStudents(schoolId);
    }

    /**
     * Creates a school using the createSchool method from superAdminManager
     */
    public School createSchool(String schoolName) throws SQLException {
        return superAdminManager.createSchool(schoolName);
    }

    /**
     * Deletes a school using the deleteSchool method from superAdminManager
     */
    public void deleteSchool(int schoolID) throws SQLException {
        superAdminManager.deleteSchool(schoolID);
    }

    /**
     * Edits a school using the editSchool method from superAdminManager
     */
    public void editSchool(School school) throws Exception {
        superAdminManager.editSchool(school);
    }

    /**
     * Gets a list of all admins on a school, using the getAdminsOnSchool method from the superAdminManager.
     *
     * @param schoolId
     * @return
     * @throws SQLException
     */
    public List<User> getAdminsOnSchool(int schoolId) throws SQLException {
        return superAdminManager.getAdminsOnSchool(schoolId);
    }

    /**
     * Gets a list of citizens assigned to a school, using the getAssignedCitizen method from the superAdminManager
     *
     * @param schoolId
     * @return
     */
    public List<Citizen> getAssignedCitizen(int schoolId) throws SQLException {
        return superAdminManager.getAssignedCitizen(schoolId);
    }

    /**
     * Gets a list of cases assigned to a school, using the getAssignedCases method from the superAdminManager.
     *
     * @param schoolId
     * @return
     */
    public List<Case> getAssignedCases(int schoolId) throws SQLException {
        return superAdminManager.getAssignedCases(schoolId);
    }

    /**
     * Adds admin to a school using the addAdminToSchool method from the superAdminManager.
     *
     * @param loginId
     * @param schoolId
     */
    public void addAdminToSchool(int loginId, int schoolId) throws SQLException {
        superAdminManager.addAdminToSchool(loginId, schoolId);
    }

    /**
     * Deletes admin from a school using the deleteAdminFromSchool method from the superAdminManager.
     *
     * @param userId
     * @param schoolId
     */
    public void deleteAdminFromSchool(int userId, int schoolId) throws SQLException {
        superAdminManager.deleteAdminFromSchool(userId, schoolId);
    }

    /**
     * Gets a superadmin login, with a hashed password using the getHashedPasswordSuperAdmin  method from the
     * superAdminManager.
     *
     * @param username
     * @param password
     * @return
     * @throws SQLException
     */
    public SuperAdmin getHashedPasswordSuperAdmin(String username, String password) throws SQLException {
        return superAdminManager.getHashedPasswordSuperAdmin(username, password);
    }

}
