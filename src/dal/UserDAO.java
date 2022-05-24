package dal;

import be.User;
import be.enums.UserType;
import bll.utilities.BCrypt.BCrypt;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import dal.db.DatabaseConnector;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    private final DatabaseConnector databaseConnector = DatabaseConnector.getInstance();


    /**
     * Constructor
     * @throws IOException
     */
    public UserDAO() throws IOException {
    }

    /**
     * Making a students list, connecting to the database and adding the results to our ArrayList.
     *
     * @return a list of students or an empty list of students.
     */
    public List<User> getStudents() throws SQLException {
        ArrayList<User> allStudents = new ArrayList<>();

        try (Connection connection = databaseConnector.getConnection()) {
            String sql = "SELECT * " +
                    "FROM Login " +
                    "WHERE userType =?;";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, String.valueOf(UserType.STUDENT));
            ResultSet resultset = preparedStatement.executeQuery();

            while (resultset.next()) {
                int loginID = resultset.getInt("LoginID");
                String firstName = resultset.getString("firstName");
                String lastName = resultset.getString("lastName");
                String username = resultset.getString("username");
                String password = resultset.getString("password");
                UserType userType = UserType.valueOf(resultset.getString("userType"));

                User student = new User(loginID, firstName, lastName, username, password, userType);
                allStudents.add(student);
            }
        } catch (SQLException sqlException) {
            throw new SQLException();
        }
        return allStudents;
    }

    /**
     * Making a teacher list, connecting to the database and adding the results to our ArrayList.
     *
     * @return a list of teachers or an empty list of teachers.
     */
    public List<User> getTeachers() throws SQLException {
        ArrayList<User> allTeachers = new ArrayList<>();
        try (Connection connection = databaseConnector.getConnection()) {
            String sql = "SELECT * " +
                    "FROM Login " +
                    "WHERE userType =? ;";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, String.valueOf(UserType.TEACHER));
            ResultSet resultset = preparedStatement.executeQuery();
            while (resultset.next()) {
                int loginID = resultset.getInt("LoginID");
                String firstName = resultset.getString("firstName");
                String lastName = resultset.getString("lastName");
                String username = resultset.getString("username");
                String password = resultset.getString("password");
                UserType userType = UserType.valueOf(resultset.getString("userType"));

                User teacher = new User(loginID, firstName, lastName, username, password, userType);
                allTeachers.add(teacher);
            }
        } catch (SQLException sqlException) {
            throw new SQLException();
        }
        return allTeachers;
    }

    /**
     * Making an admin list, connecting to the database and adding the results to our ArrayList.
     *
     * @return a list of admins or an empty list of admins.
     */
    public List<User> getAdmins() throws SQLException {
        ArrayList<User> allAdmins = new ArrayList<>();

        try (Connection connection = databaseConnector.getConnection()) {
            String sql = "SELECT * " +
                    "FROM Login " +
                    "WHERE userType =? ;";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, String.valueOf(UserType.ADMINISTRATOR));
            ResultSet resultset = preparedStatement.executeQuery();
            while (resultset.next()) {
                int loginID = resultset.getInt("LoginID");
                String firstName = resultset.getString("firstName");
                String lastName = resultset.getString("lastName");
                String username = resultset.getString("username");
                String password = resultset.getString("password");
                UserType userType = UserType.valueOf(resultset.getString("userType"));

                User admin = new User(loginID, firstName, lastName, username, password, userType);
                allAdmins.add(admin);
            }
        } catch (SQLException sqlException) {
            throw new SQLException();
        }
        return allAdmins;
    }

    /**
     * Gets a list of usernames in the database
     * @return
     * @throws SQLException
     */
    public List<User> getUsernames() throws SQLException {
        ArrayList<User> allUsernames = new ArrayList<>();

        try (Connection connection = databaseConnector.getConnection()) {
            String sql = "SELECT loginID, [username] FROM [Login];";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultset = preparedStatement.executeQuery();
            while (resultset.next()) {
                int loginID = resultset.getInt("loginID");
                String username = resultset.getString("username");

                User user = new User(loginID, username);
                allUsernames.add(user);
            }
        } catch (SQLException sqlException) {
            throw new SQLException();
        }
        return allUsernames;
    }

    /**
     * Creating a new student, by inserting first name, last name, username, password and type of user.
     */
    public User createStudent(String firstname, String lastName, String username, String password, UserType userType, int schoolId) throws SQLException {
        try (Connection connection = databaseConnector.getConnection()) {
            String sql = "INSERT INTO Login (firstName, lastName, username, password, userType, schoolId) VALUES (?,?,?,?,?,?);";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, firstname);
                preparedStatement.setString(2, lastName);
                preparedStatement.setString(3, username);
                preparedStatement.setString(4, password);
                preparedStatement.setString(5, String.valueOf(userType.STUDENT));
                preparedStatement.setInt(6, schoolId);
                preparedStatement.execute();

                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                int id = 0;
                if (resultSet.next()) {
                    id = resultSet.getInt(1);
                }

                User student = new User(id, firstname, lastName, username, password, userType.STUDENT, schoolId);
                return student;
            }
        } catch (SQLServerException throwables) {
            throw new SQLException();
        }
    }

    /**
     * Creating a new user, type teacher by inserting first name, last name, username, password and type of user.
     */
    public User createTeacher(String firstName, String lastName, String username, String password, UserType userType, int schoolId) throws SQLException {
        try (Connection connection = databaseConnector.getConnection()) {
            String sql = "INSERT INTO Login (firstName, lastName, username, password, userType, schoolId) VALUES (?,?,?,?,?,?);";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, firstName);
                preparedStatement.setString(2, lastName);
                preparedStatement.setString(3, username);
                preparedStatement.setString(4, password);
                preparedStatement.setString(5, String.valueOf(userType.TEACHER));
                preparedStatement.setInt(6, schoolId);
                preparedStatement.execute();

                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                int id = 0;
                if (resultSet.next()) {
                    id = resultSet.getInt(1);
                }

                User teacher = new User(id, firstName, lastName, username, password, userType.TEACHER, schoolId);
                return teacher;
            }
        } catch (SQLServerException throwables) {
            throw new SQLException();
        }
    }

    /**
     * Creating a new admin, type teacher by inserting first name, last name, username, password and type of user.
     */
    public User createAdmin(String firstName, String lastName, String username, String password, UserType userType, int schoolId) throws SQLException {
        try (Connection connection = databaseConnector.getConnection()) {
            String sql = "INSERT INTO Login (firstName, lastName, username, password, userType, schoolId) VALUES (?,?,?,?,?,?);";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, firstName);
                preparedStatement.setString(2, lastName);
                preparedStatement.setString(3, username);
                preparedStatement.setString(4, password);
                preparedStatement.setString(5, String.valueOf(userType.ADMINISTRATOR));
                preparedStatement.setInt(6, schoolId);
                preparedStatement.execute();

                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                int id = 0;
                if (resultSet.next()) {
                    id = resultSet.getInt(1);
                }

                User admin = new User(id, firstName, lastName, username, password, userType.ADMINISTRATOR, schoolId);
                return admin;
            }
        } catch (SQLServerException throwables) {
            throw new SQLException();
        }
    }

    /**
     * Deletes a student by taking the id and where the userType equals student
     */
    public void deleteStudent(int id, UserType userType) throws SQLException {
        try (Connection connection = databaseConnector.getConnection()) {
            String sql = "DELETE FROM Login " +
                    "WHERE LoginID =? AND userType =?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, String.valueOf(userType.STUDENT));
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throw new SQLException();
        }
    }

    /**
     * Deletes a teacher by taking the id and where the userType equals teacher
     */
    public void deleteTeacher(int id, UserType userType) throws SQLException {
        try (Connection connection = databaseConnector.getConnection()) {
            String sql = "DELETE FROM Login " +
                    "WHERE LoginID =? AND userType =?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, String.valueOf(userType.TEACHER));
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throw new SQLException();
        }
    }

    /**
     * Deletes an admin by taking the id and where the userType equals admin
     */
    public void deleteAdmin(int id, UserType userType) throws SQLException {
        try (Connection connection = databaseConnector.getConnection()) {
            String sql = "DELETE FROM Login " +
                    "WHERE LoginID =? AND userType =?;";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, String.valueOf(userType.ADMINISTRATOR));
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throw new SQLException();
        }
    }

    /**
     * Edits the selected students ID.
     */
    public void editStudent(User student) throws SQLException {
        try (Connection connection = databaseConnector.getConnection()) {
            String sql = "UPDATE Login " +
                    "SET firstName=?, lastName=?, username=?, password=? " +
                    "WHERE LoginID=? AND userType=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, student.getFirstName());
            preparedStatement.setString(2, student.getLastName());
            preparedStatement.setString(3, student.getUsername());
            preparedStatement.setString(4, student.getPassword());
            preparedStatement.setInt(5, student.getId());
            preparedStatement.setString(6, String.valueOf(UserType.STUDENT));
            if (preparedStatement.executeUpdate() != 1) {
                throw new SQLException();
            }
        }
    }

    /**
     * Edits a teacher
     */
    public void editTeacher(User teacher) throws SQLException {
        try (Connection connection = databaseConnector.getConnection()) {
            String sql = "UPDATE Login " +
                    "SET firstName=?, lastName=?, username=?, password=? " +
                    "WHERE LoginID=? AND userType=?;";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, teacher.getFirstName());
            preparedStatement.setString(2, teacher.getLastName());
            preparedStatement.setString(3, teacher.getUsername());
            preparedStatement.setString(4, teacher.getPassword());
            preparedStatement.setInt(5, teacher.getId());
            preparedStatement.setString(6, String.valueOf(UserType.TEACHER));
            if (preparedStatement.executeUpdate() != 1) {
                throw new SQLException();
            }
        }
    }

    /**
     * Edits an admin
     */
    public void editAdmin(User admin) throws SQLException {
        try (Connection connection = databaseConnector.getConnection()) {
            String sql = "UPDATE Login " +
                    "SET firstName=?, lastName=?, username=?, password=? " +
                    "WHERE LoginID=? AND userType=?;";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, admin.getFirstName());
            preparedStatement.setString(2, admin.getLastName());
            preparedStatement.setString(3, admin.getUsername());
            preparedStatement.setString(4, admin.getPassword());
            preparedStatement.setInt(5, admin.getId());
            preparedStatement.setString(6, String.valueOf(UserType.ADMINISTRATOR));
            preparedStatement.executeUpdate();
            if (preparedStatement.executeUpdate() != 1) {
                throw new SQLException();
            }
        }
    }

    /**
     * This method gets a userLogin from the database and check if it is a student, teacher or administrator
     */
    public User userLogin(String user, String pass, int schoolId) throws SQLException {
        String sql = "SELECT * " +
                "FROM Login " +
                "WHERE username =? AND password =? AND schoolId=?;";
        try (Connection connection = databaseConnector.getConnection()) {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, user);
            st.setString(2, pass);
            st.setInt(3, schoolId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("LoginID");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String username = rs.getString("username");
                String password = rs.getString("password");
                UserType userType = UserType.valueOf(rs.getString("userType"));
                int schoolID = rs.getInt("schoolId");
                if (userType == UserType.STUDENT) {
                    return new User(id, firstName, lastName, username, password, userType, schoolID);
                } else if (userType == UserType.TEACHER) {
                    return new User(id, firstName, lastName, username, password, userType, schoolID);
                } else if (userType == UserType.ADMINISTRATOR) {
                    return new User(id, firstName, lastName, username, password, userType, schoolID);
                } else {
                    return null;
                }
            }
        } catch (SQLException sqlException) {
            throw new SQLException();
        }
        return null;
    }

    /**
     * Making a students list, connecting to the database and adding the results to our ArrayList.
     *
     * @return a list of students or an empty list of students.
     */
    public User getHashedPassword(String userName, String password, int schoolId) throws SQLException {

        try (Connection connection = databaseConnector.getConnection()) {
            String sql = "SELECT * " +
                    "FROM Login " +
                    "WHERE userName =? AND schoolId =? ;";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userName);
            preparedStatement.setInt(2, schoolId);
            ResultSet resultset = preparedStatement.executeQuery();

            while (resultset.next()) {
                int id = resultset.getInt("loginID");
                String firstName = resultset.getString("firstName");
                String lastName = resultset.getString("lastName");
                String username = resultset.getString("username");
                UserType userType = UserType.valueOf(resultset.getString("userType"));
                String hashedPassword = resultset.getString("password");


                if (BCrypt.checkpw(password, hashedPassword)) {
                    User user = new User(id, firstName, lastName, username, userType, schoolId);
                    return user;
                } else {
                    System.out.println("VERY SAD");
                }

            }
        } catch (SQLException sqlException) {
            throw new SQLException();
        }
        return null;
    }

    /**
     * Main method used for testing this DAO class.
     */
    public static void main(String[] args) throws Exception {
        UserDAO dao = new UserDAO();
        System.out.println(dao.getUsernames());
        //dao.getHashedPassword("BCryptCheck", "hej123", 2);
        //dao.createAdmin("John", "Johnson", "Admin", "1", UserType.ADMINISTRATOR, 1);
        //dao.createTeacher("Kim", "Larsen", "Teacher", "1", UserType.TEACHER,1);
        //dao.createStudent("andy", "lam", "Student", "1", UserType.STUDENT,1);
        //dao.createAdmin("Test", "Testen", "Admin2", "1", UserType.ADMINISTRATOR,2);
        //dao.createTeacher("Kim", "Larsen", "Teacher2", "1", UserType.TEACHER,2);
        //dao.createStudent("Kristian", "Hollænder", "Student2",  "1", UserType.STUDENT, 2);
        //System.out.println(dao.getAdmins());
        //System.out.println(dao.getStudents());
        //System.out.println(dao.getTeachers());
    }
}
