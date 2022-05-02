package gui.model;

import be.User;
import be.enums.UserType;
import bll.UserManager;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class UserModel {

    private UserManager userManager;

    public UserModel() throws IOException {
        userManager = new UserManager();
    }

    /**
     * Get a list of student using the getStudents method from userManager
     * @return
     * @throws SQLException
     */
    public List<User> getStudents() throws SQLException {
        return userManager.getStudents();
    }

    /**
     * Get a list of teachers using the getTeachers method from userManager
     * @return
     * @throws SQLException
     */
    public List<User> getTeachers() throws SQLException {
        return userManager.getTeachers();
    }

    /**
     * Get a list of admins using the getAdmins method from userManager
     * @return
     * @throws SQLException
     */
    public List<User> getAdmins() throws SQLException {
        return userManager.getAdmins();
    }

    /**
     * Creates a student using the createStudent method from userManager
     * @param firstName
     * @param lastName
     * @param username
     * @param password
     * @param userType
     * @return
     * @throws SQLException
     */
    public User createStudent(String firstName, String lastName, String username, String password, UserType userType) throws SQLException {
        return userManager.createStudent(firstName, lastName, username, password, userType);
    }

    /**
     * Creates a teacher using the createTeacher method from userManager
     * @param firstName
     * @param lastName
     * @param username
     * @param password
     * @param userType
     * @return
     * @throws SQLException
     */
    public User createTeacher(String firstName, String lastName, String username, String password, UserType userType) throws SQLException {
        return userManager.createTeacher(firstName, lastName, username, password, userType);
    }

    /**
     * Creates an admin using the createAdmin method from userManager
     * @param firstName
     * @param lastName
     * @param username
     * @param password
     * @param userType
     * @return
     * @throws SQLException
     */
    public User createAdmin(String firstName, String lastName, String username, String password, UserType userType) throws SQLException {
        return userManager.createAdmin(firstName, lastName, username, password, userType);
    }

    /**
     * Deletes a student using the deleteStudent method from userManager
     * @param id
     * @param userType
     */
    public void deleteStudent(int id, UserType userType) throws SQLException {
        userManager.deleteStudent(id, userType);
    }

    /**
     * Deletes a teacher using the deleteTeacher method from userManager
     * @param id
     * @param userType
     */
    public void deleteTeacher(int id, UserType userType) throws SQLException {
        userManager.deleteTeacher(id, userType);
    }

    /**
     * Deletes an admin using the deleteAdmin method from userManager
     * @param id
     * @param userType
     */
    public void deleteAdmin(int id, UserType userType) throws SQLException {
        userManager.deleteAdmin(id, userType);
    }

    /**
     * Edits a student using the editStudent method from userManager
     * @param student
     * @throws Exception
     */
    public void editStudent(User student) throws Exception {
        userManager.editStudent(student);
    }

    /**
     * Edits a teacher using the editTeacher method from userManager
     * @param teacher
     * @throws Exception
     */
    public void editTeacher(User teacher) throws Exception {
        userManager.editTeacher(teacher);
    }

    /**
     * Edits an admin using the editAdmin method from userManager
     * @param admin
     * @throws Exception
     */
    public void editAdmin(User admin) throws Exception {
        userManager.editAdmin(admin);
    }

    /**
     * Gets the user userLogin using the userLogin method from userManager
     * @param username
     * @param password
     * @return
     */
    public User userLogin(String username, String password){
        return userManager.userLogin(username, password);
    }
}
