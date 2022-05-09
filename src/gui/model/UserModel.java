package gui.model;

import be.User;
import be.enums.UserType;
import bll.UserManager;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class UserModel {

    private final UserManager userManager;

    public UserModel() throws IOException {
        userManager = new UserManager();
    }

    /**
     * Get a list of student using the getStudents method from userManager
     */
    public List<User> getStudents() {
        return userManager.getStudents();
    }

    /**
     * Get a list of teachers using the getTeachers method from userManager
     */
    public List<User> getTeachers() {
        return userManager.getTeachers();
    }

    /**
     * Get a list of admins using the getAdmins method from userManager
     */
    public List<User> getAdmins() {
        return userManager.getAdmins();
    }

    /**
     * Creates a student using the createStudent method from userManager
     */
    public User createStudent(String firstName, String lastName, String username, String password, UserType userType) throws SQLException {
        return userManager.createStudent(firstName, lastName, username, password, userType);
    }

    /**
     * Creates a teacher using the createTeacher method from userManager
     */
    public User createTeacher(String firstName, String lastName, String username, String password, UserType userType) throws SQLException {
        return userManager.createTeacher(firstName, lastName, username, password, userType);
    }

    /**
     * Creates an admin using the createAdmin method from userManager
     */
    public User createAdmin(String firstName, String lastName, String username, String password, UserType userType) throws SQLException {
        return userManager.createAdmin(firstName, lastName, username, password, userType);
    }

    /**
     * Deletes a student using the deleteStudent method from userManager
     */
    public void deleteStudent(int id, UserType userType) throws SQLException {
        userManager.deleteStudent(id, userType);
    }

    /**
     * Deletes a teacher using the deleteTeacher method from userManager
     */
    public void deleteTeacher(int id, UserType userType) throws SQLException {
        userManager.deleteTeacher(id, userType);
    }

    /**
     * Deletes an admin using the deleteAdmin method from userManager
     */
    public void deleteAdmin(int id, UserType userType) throws SQLException {
        userManager.deleteAdmin(id, userType);
    }

    /**
     * Edits a student using the editStudent method from userManager
     */
    public void editStudent(User student) throws Exception {
        userManager.editStudent(student);
    }

    /**
     * Edits a teacher using the editTeacher method from userManager
     */
    public void editTeacher(User teacher) throws Exception {
        userManager.editTeacher(teacher);
    }

    /**
     * Edits an admin using the editAdmin method from userManager
     */
    public void editAdmin(User admin) throws Exception {
        userManager.editAdmin(admin);
    }

    /**
     * Gets the user userLogin using the userLogin method from userManager
     */
    public User userLogin(String username, String password){
        return userManager.userLogin(username, password);
    }
}
