package bll;

import be.User;
import be.enums.UserType;
import dal.UserDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class UserManager {

    private UserDAO userDAO;
    public UserManager() throws IOException {
        userDAO = new UserDAO();
    }

    /**
     * Get a list of student using the getStudents method from userDAO
     * @return
     * @throws SQLException
     */
    public List<User> getStudents() throws SQLException {
        return userDAO.getStudents();
    }

    /**
     * Get a list of teachers using the getTeachers method from userDAO
     * @return
     * @throws SQLException
     */
    public List<User> getTeachers() throws SQLException {
        return userDAO.getTeachers();
    }

    /**
     * Get a list of admins using the getAdmins method from userDAO
     * @return
     * @throws SQLException
     */
    public List<User> getAdmins() throws SQLException {
        return userDAO.getAdmins();
    }

    /**
     * Creates a student using the createStudent method from userDAO
     * @param name
     * @param username
     * @param password
     * @param userType
     * @return
     * @throws SQLException
     */
    public User createStudent(String name, String username, String password, UserType userType) throws SQLException {
        return userDAO.createStudent(name, username, password, userType);
    }

    /**
     * Creates a teacher using the createTeacher method from userDAO
     * @param name
     * @param username
     * @param password
     * @param userType
     * @return
     * @throws SQLException
     */
    public User createTeacher(String name, String username, String password, UserType userType) throws SQLException {
        return userDAO.createTeacher(name, username, password, userType);
    }

    /**
     * Creates an admin using the createAdmin method from userDAO
     * @param name
     * @param username
     * @param password
     * @param userType
     * @return
     * @throws SQLException
     */
    public User createAdmin(String name, String username, String password, UserType userType) throws SQLException {
        return userDAO.createAdmin(name, username, password, userType);
    }


    /**
     * Deletes a student using the deleteStudent method from userDAO
     * @param id
     * @param userType
     */
    public void deleteStudent(int id, UserType userType){
        userDAO.deleteStudent(id, userType);
    }

    /**
     * Deletes a teacher using the deleteTeacher method from userDAO
     * @param id
     * @param userType
     */
    public void deleteTeacher(int id, UserType userType){
        userDAO.deleteTeacher(id, userType);
    }

    /**
     * Deletes an admin using the deleteAdmin method from userDAO
     * @param id
     * @param userType
     */
    public void deleteAdmin(int id, UserType userType){
        userDAO.deleteAdmin(id, userType);
    }

    /**
     * Edits a student using the editStudent method from userDAO
     * @param student
     * @throws Exception
     */
    public void editStudent(User student) throws Exception {
        userDAO.editStudent(student);
    }

    /**
     * Edits a teacher using the editTeacher method from userDAO
     * @param teacher
     * @throws Exception
     */
    public void editTeacher(User teacher) throws Exception {
        userDAO.editTeacher(teacher);
    }

    /**
     * Edits an admin using the editAdmin method from userDAO
     * @param admin
     * @throws Exception
     */
    public void editAdmin(User admin) throws Exception {
        userDAO.editAdmin(admin);
    }

    /**
     * Gets the user login using the login method from userDAO
     * @param username
     * @param password
     * @return
     */
    public User login(String username, String password){
        return userDAO.login(username, password);
    }

}
