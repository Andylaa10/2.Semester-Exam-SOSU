package bll;

import be.*;
import dal.SuperAdminDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class SuperAdminManager {

    private final SuperAdminDAO superAdminDAO;

    /**
     * Constructor
     */
    public SuperAdminManager() throws IOException {
        superAdminDAO = new SuperAdminDAO();
    }

    /**
     * Creates a super admin using the createSuperAdmin method in superAdminDAO
     */
    public SuperAdmin createSuperAdmin(String username, String password) throws SQLException {
        return superAdminDAO.createSuperAdmin(username, password);
    }

    /**
     * Deletes a super admin using the deleteSuperAdmin method in superAdminDAO
     */
    public void deleteSuperAdmin(int id) throws SQLException {
        superAdminDAO.deleteSuperAdmin(id);
    }

    /**
     * Edits a super admin using the editSuperAdmin method in superAdminDAO
     */
    public void editSuperAdmin(SuperAdmin superAdmin) throws Exception {
        superAdminDAO.editSuperAdmin(superAdmin);
    }

    /**
     * Gets the super admin Login using the superAdminLogin method from superAdminDAO
     */
    public SuperAdmin superAdminLogin(String username, String password) throws SQLException {
        return superAdminDAO.superAdminLogin(username, password);
    }

    /**
     * Gets a list of schools, by using the method from superAdminDAO
     */
    public List<School> getSchools() throws SQLException {
        return superAdminDAO.getSchools();
    }

    /**
     * Gets a list of teachers assigned on a school, by using the method from superAdminDAO
     */
    public List<User> getAssignedTeachers(int schoolId) throws SQLException {
        return superAdminDAO.getAssignedTeachers(schoolId);
    }

    /**
     * Gets a list of admins assigned on a school, by using the method from superAdminDAO
     */
    public List<User> getAssignedAdmins(int schoolId) throws SQLException {
        return superAdminDAO.getAssignedAdmins(schoolId);
    }

    /**
     * Gets a list of student assigned on a school, by using the method from superAdminDAO
     */
    public List<User> getAssignedStudents(int schoolId) throws SQLException {
        return superAdminDAO.getAssignedStudents(schoolId);
    }

    /**
     * Creates a school using the createSchool method in superAdminDAO
     */
    public School createSchool(String schoolName) throws SQLException {
        return superAdminDAO.createSchool(schoolName);
    }

    /**
     * Deletes a school using the deleteSchool method in superAdminDAO
     */
    public void deleteSchool(int schoolID) throws SQLException {
        superAdminDAO.deleteSchool(schoolID);
    }

    /**
     * Edits a school using the editSchool method in superAdminDAO
     */
    public void editSchool(School school) throws Exception {
        superAdminDAO.editSchool(school);
    }

    /**
     * Gets a list of admins assigned to a school, by using the method from superAdminDAO
     */
    public List<User> getAdminsOnSchool(int schoolId) throws SQLException {
        return superAdminDAO.getAdminsOnSchool(schoolId);
    }

    /**
     * Gets a list of citizens assigned to a school, by using the method from superAdminDAO
     */
    public List<Citizen> getAssignedCitizen(int schoolId) throws SQLException {
        return superAdminDAO.getAssignedCitizen(schoolId);
    }

    /**
     * Gets a list of cases assigned to a school, by using the method from superAdminDAO
     */
    public List<Case> getAssignedCases(int schoolId) throws SQLException {
        return superAdminDAO.getAssignedCases(schoolId);
    }

    /**
     * Add admin to a school, by using the method from superAdminDAO
     */
    public void addAdminToSchool(int loginId, int schoolId) throws SQLException {
        superAdminDAO.addAdminToSchool(loginId, schoolId);
    }

    /**
     * Deletes an admin from a school, by using the method from superAdminDAO
     */
    public void deleteAdminFromSchool(int userId, int schoolId) throws SQLException {
        superAdminDAO.deleteAdminFromSchool(userId, schoolId);
    }

    /**
     * Gets a superAdmin with username and a hashed password
     */
    public SuperAdmin getHashedPasswordSuperAdmin(String username, String password) throws SQLException {
        return superAdminDAO.getHashedPasswordSuperAdmin(username, password);
    }

}
