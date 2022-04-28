package gui.Facade;

import be.School;
import be.SuperAdmin;
import be.User;
import be.enums.UserType;
import dal.SuperAdminDAO;
import gui.model.CitizenModel;
import gui.model.SuperAdminModel;
import gui.model.UserModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class DataModelFacade {

    private CitizenModel citizenModel;
    private UserModel userModel;
    private SuperAdminModel superAdminModel;

    public DataModelFacade() throws IOException {
        citizenModel = new CitizenModel();
        userModel = new UserModel();
        superAdminModel = new SuperAdminModel();
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

    public SuperAdmin createSuperAdmin(String username, String password) throws SQLException {
        return superAdminModel.createSuperAdmin(username, password);
    }

    /**
     * Deletes a student using the deleteStudent method from userModel
     * @param id
     * @param userType
     */
    public void deleteStudent(int id, UserType userType){
        userModel.deleteStudent(id, userType);
    }

    /**
     * Deletes a teacher using the deleteTeacher method from userModel
     * @param id
     * @param userType
     */
    public void deleteTeacher(int id, UserType userType){
        userModel.deleteTeacher(id, userType);
    }

    /**
     * Deletes an admin using the deleteAdmin method from userModel
     * @param id
     * @param userType
     */
    public void deleteAdmin(int id, UserType userType){
        userModel.deleteAdmin(id, userType);
    }

    public void deleteSuperAdmin(int id) {
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

    public SuperAdmin superAdminLogin(String username, String password) throws SQLException {
        return superAdminModel.superAdminLogin(username, password);
    }

    public School createSchool(String schoolName) throws SQLException {
        return superAdminModel.createSchool(schoolName);
    }

    public void deleteSchool(int schoolID) {
        superAdminModel.deleteSchool(schoolID);
    }

    public void editSchool(School school) throws Exception {
        superAdminModel.editSchool(school);
    }


}
