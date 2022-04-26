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
     * Creates a student using the createStudent method from userManager
     * @param name
     * @param username
     * @param password
     * @param userType
     * @return
     * @throws SQLException
     */
    public User createStudent(String name, String username, String password, UserType userType) throws SQLException {
        return userManager.createStudent(name, username, password, userType);
    }

    /**
     * Creates a teacher using the createTeacher method from userManager
     * @param name
     * @param username
     * @param password
     * @param userType
     * @return
     * @throws SQLException
     */
    public User createTeacher(String name, String username, String password, UserType userType) throws SQLException {
        return userManager.createTeacher(name, username, password, userType);
    }

    /**
     * Deletes a student using the deleteStudent method from userManager
     * @param id
     * @param userType
     */
    public void deleteStudent(int id, UserType userType){
        userManager.deleteStudent(id, userType);
    }

    /**
     * Deletes a teacher using the deleteTeacher method from userManager
     * @param id
     * @param userType
     */
    public void deleteTeacher(int id, UserType userType){
        userManager.deleteTeacher(id, userType);
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
     * Gets the user login using the login method from userManager
     * @param username
     * @param password
     * @return
     */
    public User login(String username, String password){
        return userManager.login(username, password);
    }
}
