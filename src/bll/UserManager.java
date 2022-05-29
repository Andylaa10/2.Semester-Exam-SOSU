package bll;

import be.User;
import be.enums.UserType;
import dal.UserDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class UserManager {

    private final UserDAO userDAO;

    public UserManager() throws IOException {
        userDAO = new UserDAO();
    }

    /**
     * Get a list of student using the getStudents method from userDAO
     */
    public List<User> getStudents() throws SQLException {
        return userDAO.getStudents();
    }

    public User getHashedPassword(String userName, String password, int schoolId) throws SQLException {
        return userDAO.getHashedPassword(userName, password, schoolId);
    }

    /**
     * Get a list of teachers using the getTeachers method from userDAO
     */
    public List<User> getTeachers() throws SQLException {
        return userDAO.getTeachers();
    }

    /**
     * Get a list of admins using the getAdmins method from userDAO
     */
    public List<User> getAdmins() throws SQLException {
        return userDAO.getAdmins();
    }

    public List<User> getUsernames() throws SQLException {
        return userDAO.getUsernames();
    }

    /**
     * Creates a student using the createStudent method in userDAO
     */
    public User createStudent(String firstName, String lastName, String username, String password, UserType userType, int schoolId) throws SQLException {
        return userDAO.createStudent(firstName, lastName, username, password, userType, schoolId);
    }

    /**
     * Creates a teacher using the createTeacher method from userDAO
     */
    public User createTeacher(String firstName, String lastName, String username, String password, UserType userType, int schoolId) throws SQLException {
        return userDAO.createTeacher(firstName, lastName, username, password, userType, schoolId);
    }

    /**
     * Creates an admin using the createAdmin method from userDAO
     */
    public User createAdmin(String firstName, String lastName, String username, String password, UserType userType, int schoolId) throws SQLException {
        return userDAO.createAdmin(firstName, lastName, username, password, userType, schoolId);
    }


    /**
     * Deletes a student using the deleteStudent method from userDAO
     */
    public void deleteStudent(int id, UserType userType) throws SQLException {
        userDAO.deleteStudent(id, userType);
    }

    /**
     * Deletes a teacher using the deleteTeacher method from userDAO
     */
    public void deleteTeacher(int id, UserType userType) throws SQLException {
        userDAO.deleteTeacher(id, userType);
    }

    /**
     * Deletes an admin using the deleteAdmin method from userDAO
     */
    public void deleteAdmin(int id, UserType userType) throws SQLException {
        userDAO.deleteAdmin(id, userType);
    }

    /**
     * Edits a student using the editStudent method from userDAO
     */
    public void editStudent(User student) throws Exception {
        userDAO.editStudent(student);
    }

    /**
     * Edits a teacher using the editTeacher method from userDAO
     */
    public void editTeacher(User teacher) throws Exception {
        userDAO.editTeacher(teacher);
    }

    /**
     * Edits an admin using the editAdmin method from userDAO
     */
    public void editAdmin(User admin) throws Exception {
        userDAO.editAdmin(admin);
    }

    /**
     * Gets the user userLogin using the userLogin method from userDAO
     */
    public User userLogin(String username, String password, int schoolId) throws SQLException {
        return userDAO.userLogin(username, password, schoolId);
    }

}
