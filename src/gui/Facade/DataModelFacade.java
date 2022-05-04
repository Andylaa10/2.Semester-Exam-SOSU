package gui.Facade;

import be.*;
import be.enums.UserType;
import gui.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class DataModelFacade {

    private CitizenModel citizenModel;
    private UserModel userModel;
    private SuperAdminModel superAdminModel;
    private CaseModel caseModel;
    private HealthConditionsModel healthConditionsModel;

    public DataModelFacade() throws IOException {
        citizenModel = new CitizenModel();
        userModel = new UserModel();
        superAdminModel = new SuperAdminModel();
        caseModel = new CaseModel();
        healthConditionsModel = new HealthConditionsModel();
    }

    /**
     * Get a list of students using the getStudents method from userModel
     * @return
     * @throws SQLException
     */
    public List<User> getStudents() throws SQLException {
        return userModel.getStudents();
    }

    /**
     * Get a list of teachers using the getTeachers method from userModel
     * @return
     * @throws SQLException
     */
    public List<User> getTeachers() throws SQLException {
        return userModel.getTeachers();
    }

    /**
     * Get a list of admins using the getAdmins method from userModel
     * @return
     * @throws SQLException
     */
    public List<User> getAdmins() throws SQLException {
        return userModel.getAdmins();
    }

    public List<HealthCondition> getHealthConditions() throws SQLException {
        return healthConditionsModel.getHealthConditions();
    }

    public List<SubCategory> getSubCategories(int categoryId) throws SQLException {
        return healthConditionsModel.getSubCategories(categoryId);
    }

    /**
     * Creates a student using the createStudent method from userModel
     * @param firstName
     * @param lastName
     * @param username
     * @param password
     * @param userType
     * @return
     * @throws SQLException
     */
    public User createStudent(String firstName, String lastName, String username, String password, UserType userType) throws SQLException {
        return userModel.createStudent(firstName, lastName, username, password, userType);
    }

    /**
     * Creates a teacher using the createTeacher method from userModel
     * @param firstName
     * @param lastName
     * @param username
     * @param password
     * @param userType
     * @return
     * @throws SQLException
     */
    public User createTeacher(String firstName, String lastName, String username, String password, UserType userType) throws SQLException {
        return userModel.createTeacher(firstName, lastName, username, password, userType);
    }

    /**
     * Creates an admin using the createAdmin method from userModel
     * @param firstName
     * @param lastName
     * @param username
     * @param password
     * @param userType
     * @return
     * @throws SQLException
     */
    public User createAdmin(String firstName, String lastName, String username, String password, UserType userType) throws SQLException {
        return userModel.createAdmin(firstName, lastName, username, password, userType);
    }

    /**
     * Create a super admin using the createSuperAdmin method from superAdminModel
     * @param username
     * @param password
     * @return
     * @throws SQLException
     */
    public SuperAdmin createSuperAdmin(String username, String password) throws SQLException {
        return superAdminModel.createSuperAdmin(username, password);
    }

    /**
     * Deletes a student using the deleteStudent method from userModel
     * @param id
     * @param userType
     */
    public void deleteStudent(int id, UserType userType) throws SQLException {
        userModel.deleteStudent(id, userType);
    }

    /**
     * Deletes a teacher using the deleteTeacher method from userModel
     * @param id
     * @param userType
     */
    public void deleteTeacher(int id, UserType userType) throws SQLException {
        userModel.deleteTeacher(id, userType);
    }

    /**
     * Deletes an admin using the deleteAdmin method from userModel
     * @param id
     * @param userType
     */
    public void deleteAdmin(int id, UserType userType) throws SQLException {
        userModel.deleteAdmin(id, userType);
    }

    /**
     * Deletes a super admin using the deleteSuperAdmin method from superAdminModel
     * @param id
     */
    public void deleteSuperAdmin(int id) throws SQLException {
        superAdminModel.deleteSuperAdmin(id);
    }

    /**
     * Edits a student using the editStudent method from userModel
     * @param student
     * @throws Exception
     */
    public void editStudent(User student) throws Exception {
        userModel.editStudent(student);
    }

    /**
     * Edits a teacher using the editTeacher method from userModel
     * @param teacher
     * @throws Exception
     */
    public void editTeacher(User teacher) throws Exception {
        userModel.editTeacher(teacher);
    }

    /**
     * Edits an admin using the editAdmin method from userModel
     * @param admin
     * @throws Exception
     */
    public void editAdmin(User admin) throws Exception {
        userModel.editAdmin(admin);
    }

    /**
     * Edits a super admin using the editSuperAdmin method from superAdminModel
     * @param superAdmin
     * @throws Exception
     */
    public void editSuperAdmin(SuperAdmin superAdmin) throws Exception {
        superAdminModel.editSuperAdmin(superAdmin);
    }

    /**
     * Gets the user userLogin using the userLogin method from userModel
     * @param username
     * @param password
     * @return
     */
    public User userLogin(String username, String password){
        return userModel.userLogin(username, password);
    }

    /**
     * Gets the super admin login using the superAdminLogin method from superAdminModel
     * @param username
     * @param password
     * @return
     * @throws SQLException
     */
    public SuperAdmin superAdminLogin(String username, String password) throws SQLException {
        return superAdminModel.superAdminLogin(username, password);
    }

    public List<School> getSchools() throws SQLException {
        return superAdminModel.getSchools();
    }

    /**
     * Creates a school using the createSchool method from superAdminModel
     * @param schoolName
     * @return
     * @throws SQLException
     */
    public School createSchool(String schoolName) throws SQLException {
        return superAdminModel.createSchool(schoolName);
    }

    /**
     * Deletes a school using the deleteSchool method from superAdminModel
     * @param id
     */
    public void deleteSchool(int id) throws SQLException {
        superAdminModel.deleteSchool(id);
    }

    /**
     * Edits a school using the editSchool method from superAdminModel
     * @param school
     * @throws Exception
     */
    public void editSchool(School school) throws Exception {
        superAdminModel.editSchool(school);
    }

    /**
     * Get a list of case using the getCases method from caseModel
     * @return
     * @throws SQLException
     */
    public List<Case> getCases() throws SQLException {
        return caseModel.getCases();
    }

    /**
     * Creates a case using the createCase method from caseModel
     * @param name
     * @param info
     * @return
     * @throws SQLException
     */
    public Case createCase(String name, String info) throws SQLException {
        return caseModel.createCase(name, info);
    }

    /**
     * Deletes a case using the deleteCase method from caseModel
     * @param id
     * @throws Exception
     */
    public void deleteCase(int id) throws Exception {
        caseModel.deleteCase(id);
    }

    /**
     * Edits a case using the editCase method in caseModel
     * @param aCase
     * @throws Exception
     */
    public void editCase(Case aCase) throws Exception {
        caseModel.editCase(aCase);
    }

    public void assignCaseToCitizen(int caseId, int citizenId){
        caseModel.assignCaseToCitizen(caseId, citizenId);
    }

    public void deleteCaseFromCitizen(int caseId, int citizenId){
        caseModel.deleteCaseFromCitizen(caseId, citizenId);
    }

    public List<Case> getCasesOnCitizen(int citizenId) throws SQLException {
        return caseModel.getCasesOnCitizen(citizenId);
    }

    /**
     * Get a list of citizen using the getCitizens method from citizenModel
     * @return
     * @throws SQLException
     */
    public List<Citizen> getCitizens() throws SQLException {
        return citizenModel.getCitizens();
    }

    /**
     * Get a list of cases assigned to citizen using the getCitizensOnCases method from citizenModel
     * @param caseId
     * @return
     * @throws SQLException
     */
    public List<Citizen> getCitizensOnCases(int caseId) throws SQLException {
        return citizenModel.getCitizensOnCases(caseId);
    }

    /**
     * Creates a case using the createCitizen method from citizenModel
     * @param firstname
     * @param lastName
     * @param SSN
     * @param address
     * @param sex
     * @return
     * @throws SQLException
     */
    public Citizen createCitizen(String firstname, String lastName, String SSN, String address, String sex, String info) throws SQLException {
        return citizenModel.createCitizen(firstname, lastName, SSN, address, sex, info);
    }

    /**
     * Deletes a citizen using the deleteCitizen method from citizenModel
     * @param id
     * @throws Exception
     */
    public void deleteCitizen(int id) throws Exception {
        citizenModel.deleteCitizen(id);
    }

    /**
     * Edits a citizen using the editCitizen method in citizenModel
     * @param citizen
     * @throws Exception
     */
    public void editCitizen(Citizen citizen) throws Exception {
        citizenModel.editCitizen(citizen);
    }

    public void addAdminToSchool(int loginId, int schoolId) {
        superAdminModel.addAdminToSchool(loginId, schoolId);
    }



}
