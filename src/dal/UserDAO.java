package dal;

import be.User;
import be.enums.UserType;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import dal.db.DatabaseConnector;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    private final DatabaseConnector databaseConnector = DatabaseConnector.getInstance();

    public UserDAO() throws IOException {
    }

    /**
     * Making a students list, connecting to the database and adding the results to our ArrayList.
     * @return a list of students or an empty list of students.
     */
    public List<User> getStudents() throws SQLException {
        ArrayList<User> allStudents = new ArrayList<>();

        try (Connection connection = databaseConnector.getConnection()) {
            String sql = "SELECT * FROM Login WHERE userType =?;";

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
     * @return a list of teachers or an empty list of teachers.
     */
    public List<User> getTeachers() throws SQLException {
        ArrayList<User> allTeachers = new ArrayList<>();
        try (Connection connection = databaseConnector.getConnection()) {
            String sql = "SELECT * FROM Login WHERE userType =? ;";

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
     * @return a list of admins or an empty list of admins.
     */
    public List<User> getAdmins() throws SQLException {
        ArrayList<User> allAdmins = new ArrayList<>();

        try (Connection connection = databaseConnector.getConnection()) {
            String sql = "SELECT * FROM Login WHERE userType =? ;";

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
     * Creating a new student, by inserting first name, last name, username, password and type of user.
     */
    public User createStudent (String firstname, String lastName, String username, String password, UserType userType) throws SQLException {
        try (Connection connection = databaseConnector.getConnection()) {
            String sql = "INSERT INTO Login (firstName, lastName, username, password, userType) VALUES (?,?,?,?,?);";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, firstname);
                preparedStatement.setString(2, lastName);
                preparedStatement.setString(3, username);
                preparedStatement.setString(4, password);
                preparedStatement.setString(5, String.valueOf(userType.STUDENT));
                preparedStatement.execute();

                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                int id = 0;
                if (resultSet.next()) {
                    id = resultSet.getInt(1);
                }

                User student = new User(id, firstname, lastName, username, password, userType.STUDENT);
                return student;
            }
        } catch (SQLServerException throwables) {
            throw new SQLException();
        }
    }

    /**
     * Creating a new user, type teacher by inserting first name, last name, username, password and type of user.
     */
    public User createTeacher (String firstName, String lastName, String username, String password, UserType userType) throws SQLException {
        try (Connection connection = databaseConnector.getConnection()) {
            String sql = "INSERT INTO Login (firstName, lastName, username, password, userType) VALUES (?,?,?,?,?);";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, firstName);
                preparedStatement.setString(2, lastName);
                preparedStatement.setString(3, username);
                preparedStatement.setString(4, password);
                preparedStatement.setString(5, String.valueOf(userType.TEACHER));
                preparedStatement.execute();

                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                int id = 0;
                if (resultSet.next()) {
                    id = resultSet.getInt(1);
                }

                User teacher = new User(id, firstName, lastName, username, password, userType.TEACHER);
                return teacher;
            }
        } catch (SQLServerException throwables) {
            throw new SQLException();
        }
    }

    /**
     * Creating a new admin, type teacher by inserting first name, last name, username, password and type of user.
     */
    public User createAdmin (String firstName, String lastName, String username, String password, UserType userType) throws SQLException {
        try (Connection connection = databaseConnector.getConnection()) {
            String sql = "INSERT INTO Login (firstName, lastName, username, password, userType) VALUES (?,?,?,?,?);";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, firstName);
                preparedStatement.setString(2, lastName);
                preparedStatement.setString(3, username);
                preparedStatement.setString(4, password);
                preparedStatement.setString(5, String.valueOf(userType.ADMINISTRATOR));
                preparedStatement.execute();

                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                int id = 0;
                if (resultSet.next()) {
                    id = resultSet.getInt(1);
                }

                User admin = new User(id, firstName, lastName, username, password, userType.ADMINISTRATOR);
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
            String sql = "DELETE FROM Login WHERE LoginID =? AND userType =?;";
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
            String sql = "DELETE FROM Login WHERE LoginID =? AND userType =?;";
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
            String sql = "DELETE FROM Login WHERE LoginID =? AND userType =?;";

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
    public void editStudent(User student) throws Exception {
        try (Connection connection = databaseConnector.getConnection()) {
            String sql = "UPDATE Login SET firstName=?, lastName=?, username=?, password=? WHERE LoginID=? AND userType=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, student.getFirstName());
            preparedStatement.setString(2, student.getLastName());
            preparedStatement.setString(3, student.getUsername());
            preparedStatement.setString(4, student.getPassword());
            preparedStatement.setInt(5, student.getId());
            preparedStatement.setString(6, String.valueOf(UserType.STUDENT));
            if (preparedStatement.executeUpdate() != 1) {
                new Exception("Could not edit student").printStackTrace();
            }
        }
    }

    /**
     * Edits a teacher
     */
    public void editTeacher(User teacher) throws Exception {
        try (Connection connection = databaseConnector.getConnection()) {
            String sql = "UPDATE Login SET firstName=?, lastName=?, username=?, password=? WHERE LoginID=? AND userType=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, teacher.getFirstName());
            preparedStatement.setString(2, teacher.getLastName());
            preparedStatement.setString(3, teacher.getUsername());
            preparedStatement.setString(4, teacher.getPassword());
            preparedStatement.setInt(5, teacher.getId());
            preparedStatement.setString(6, String.valueOf(UserType.TEACHER));
            if (preparedStatement.executeUpdate() != 1) {
                throw new Exception("Could not edit teacher");
            }
        }
    }

    /**
     * Edits an admin
     */
    public void editAdmin(User admin) throws Exception {
        try (Connection connection = databaseConnector.getConnection()) {
            String sql = "UPDATE Login SET firstName=?, lastName=?, username=?, password=? WHERE LoginID=? AND userType=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, admin.getFirstName());
            preparedStatement.setString(2, admin.getLastName());
            preparedStatement.setString(3, admin.getUsername());
            preparedStatement.setString(4, admin.getPassword());
            preparedStatement.setInt(5, admin.getId());
            preparedStatement.setString(6, String.valueOf(UserType.ADMINISTRATOR));
            preparedStatement.executeUpdate();
            if (preparedStatement.executeUpdate() != 1) {
                throw new Exception();
            }
        }
    }

    /**
     * This method gets a userLogin from the database and check if it is a student, teacher or administrator
     */
    public User userLogin(String user, String pass) {
        String sql = "SELECT * FROM Login WHERE username =? AND password =?;";
        try(Connection connection = databaseConnector.getConnection()){
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, user);
            st.setString(2, pass);
            ResultSet rs = st.executeQuery();
            if (rs.next()){
                int id = rs.getInt("LoginID");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String username = rs.getString("username");
                String password = rs.getString("password");
                UserType userType = UserType.valueOf(rs.getString("userType"));
                if (userType == UserType.STUDENT){
                    return new User(id, firstName, lastName, username, password, userType);
                } else if (userType == UserType.TEACHER){
                    return new User(id, firstName, lastName, username, password, userType);
                } else if (userType == UserType.ADMINISTRATOR) {
                    return new User(id, firstName, lastName, username, password, userType);
                }else {
                    return null;
                }
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }

    /**
     * Main method used for testing this DAO class.
     */
    public static void main(String[] args) throws Exception {
        UserDAO dao = new UserDAO();
        dao.createAdmin("John", "Johnson", "Admin", "1", UserType.ADMINISTRATOR);
        dao.createTeacher("Kim", "Larsen", "Teacher", "1", UserType.TEACHER);
        dao.createStudent("andy", "lam", "Student", "1", UserType.STUDENT);
        dao.createAdmin("John", "Johnson", "John", "1", UserType.ADMINISTRATOR);
        dao.createTeacher("Kim", "Larsen", "Kim", "1", UserType.TEACHER);
        dao.createStudent("Kristian", "Hollænder", "kris",  "1", UserType.STUDENT);
        System.out.println(dao.getAdmins());
        System.out.println(dao.getStudents());
        System.out.println(dao.getTeachers());
    }
}
