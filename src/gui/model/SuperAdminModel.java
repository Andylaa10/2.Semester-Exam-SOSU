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

    public List<School> getSchools() throws SQLException {
        return superAdminManager.getSchools();
    }

    public List<User> getAssignedTeachers(int schoolId) throws SQLException{
        return superAdminManager.getAssignedTeachers(schoolId);
    }

    public List<User> getAssignedAdmins(int schoolId) throws SQLException{
        return superAdminManager.getAssignedAdmins(schoolId);
    }

    public List<User> getAssignedStudents(int schoolId) throws SQLException{
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

    public List<User> getAdminsOnSchool(int schoolId) throws SQLException {
        return superAdminManager.getAdminsOnSchool(schoolId);
    }

    public List<Citizen> getAssignedCitizen(int schoolId){
        return superAdminManager.getAssignedCitizen(schoolId);
    }
    public List<Case> getAssignedCases(int schoolId){
        return superAdminManager.getAssignedCases(schoolId);
    }

    public void addAdminToSchool(int loginId, int schoolId) {
        superAdminManager.addAdminToSchool(loginId, schoolId);
    }

    public void deleteAdminFromSchool(int userId, int schoolId) {
        superAdminManager.deleteAdminFromSchool(userId, schoolId);
    }

}
