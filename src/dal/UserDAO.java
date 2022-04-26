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

    private final DatabaseConnector connector = DatabaseConnector.getInstance();

    public UserDAO() throws IOException {

    }

    /**
     * Making a students list, connecting to the database and adding the results to our ArrayList.
     * @return a list of students or an empty list of students.
     */

    public List<User> getStudents() throws SQLServerException, SQLException {
        ArrayList<User> allStudents = new ArrayList<>();

        try (Connection connection = connector.getConnection()) {
            String sql = "SELECT * FROM Login WHERE userType ='Student';";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultset = preparedStatement.executeQuery();

            while (resultset.next()) {
                int loginID = resultset.getInt("LoginID");
                String username = resultset.getString("username");
                String password = resultset.getString("password");
                UserType userType = UserType.valueOf(resultset.getString("userType"));

                User student = new User(loginID, username, password, userType);
                allStudents.add(student);
            }
        } catch (SQLException sqlException) {
        }
        return allStudents;
    }

    /**
     * Making a teacher list, connecting to the database and adding the results to our ArrayList.
     * @return a list of teachers or an empty list of teachers.
     */
    public List<User> getTeachers() throws SQLServerException, SQLException {
        ArrayList<User> allTeachers = new ArrayList<>();

        try (Connection connection = connector.getConnection()) {
            String sql = "SELECT * FROM Login WHERE userType ='Teacher' ;";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultset = preparedStatement.executeQuery();
            while (resultset.next()) {
                int loginID = resultset.getInt("LoginID");
                String username = resultset.getString("username");
                String password = resultset.getString("password");
                UserType userType = UserType.valueOf(resultset.getString("userType"));

                User teacher = new User(loginID, username, password, userType);
                allTeachers.add(teacher);
            }
        } catch (SQLException sqlException) {
        }
        return allTeachers;
    }

    /**
     * Creating a new user, type student by inserting name, username, password and type of user.
     * @param name
     * @param username
     * @param password
     * @param userType
     * @return a user type student
     * @throws SQLException
     */
    public User createStudent (String name, String username, String password, UserType userType) throws SQLException {
        try (Connection connection = connector.getConnection()) {
            String sql = "INSERT INTO Login (name, username, password, userType) VALUES (?,?,?,?);";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, username);
                preparedStatement.setString(3, password);
                preparedStatement.setString(4, String.valueOf(userType.STUDENT));
                preparedStatement.execute();

                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                int id = 0;
                if (resultSet.next()) {
                    id = resultSet.getInt(1);
                }

                User student = new User(id, name, username, password, userType.STUDENT);
                return student;
            }
        } catch (SQLServerException throwables) {
        }
        return null;
    }

    /**
     * Creating a new user, type teacher by inserting name, username, password and type of user.
     * @param name
     * @param username
     * @param password
     * @param userType
     * @return a user type teacher
     * @throws SQLException
     */
    public User createTeacher (String name, String username, String password, UserType userType) throws SQLException {
        try (Connection connection = connector.getConnection()) {
            String sql = "INSERT INTO Login (name, username, password, userType) VALUES (?,?,?,?);";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, username);
                preparedStatement.setString(3, password);
                preparedStatement.setString(4, String.valueOf(userType.TEACHER));
                preparedStatement.execute();

                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                int id = 0;
                if (resultSet.next()) {
                    id = resultSet.getInt(1);
                }

                User student = new User(id, name, username, password, userType.TEACHER);
                return student;
            }
        } catch (SQLServerException throwables) {
        }
        return null;
    }

    /**
     * Deletes a student by taking the id and where the userType equals student
     * @param id
     */
    public void deleteStudent(int id, UserType userType) {
        try (Connection connection = connector.getConnection()) {
            String sql = "DELETE FROM Login WHERE LoginID =? AND userType =?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, String.valueOf(userType.STUDENT));
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes a teacher by taking the id and where the userType equals teacher
     * @param id
     */
    public void deleteTeacher(int id, UserType userType) {
        try (Connection connection = connector.getConnection()) {
            String sql = "DELETE FROM Login WHERE LoginID =? AND userType =?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, String.valueOf(userType.TEACHER));
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Edits a student
     * @param student
     * @throws Exception
     */
    public void editStudent(User student) throws Exception {
        try (Connection connection = connector.getConnection()) {
            String sql = "UPDATE Login SET name=? username=?, password=? WHERE LoginID=? AND userType='Student';";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getUsername());
            preparedStatement.setString(3, student.getPassword());
            preparedStatement.setInt(4, student.getId());
            preparedStatement.setString(5, String.valueOf(student.getUsertype()));
            if (preparedStatement.executeUpdate() != 1) {
                throw new Exception("Could not edit student");
            }
        }
    }

    /**
     * Edits a teacher
     * @param teacher
     * @throws Exception
     */
    public void editTeacher(User teacher) throws Exception {
        try (Connection connection = connector.getConnection()) {
            String sql = "UPDATE Login SET name=? username=?, password=? WHERE LoginID=? AND userType='Student';";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, teacher.getName());
            preparedStatement.setString(2, teacher.getUsername());
            preparedStatement.setString(3, teacher.getPassword());
            preparedStatement.setInt(4, teacher.getId());
            preparedStatement.setString(5, String.valueOf(teacher.getUsertype()));
            if (preparedStatement.executeUpdate() != 1) {
                throw new Exception("Could not edit teacher");
            }
        }
    }

    /**
     * This method gets a login from the database and check if it is a student, teacher or administrator
     * @param user
     * @param pass
     * @return
     */
    public User login(String user, String pass) {
        String sql = "SELECT * FROM Login WHERE username =? AND password =?;";
        try(Connection connection = connector.getConnection()){
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, user);
            st.setString(2, pass);
            ResultSet rs = st.executeQuery();
            if (rs.next()){
                int id = rs.getInt("LoginID");
                String name = rs.getString("name");
                String username = rs.getString("username");
                String password = rs.getString("password");
                UserType userType = UserType.valueOf(rs.getString("userType"));
                if (userType == UserType.STUDENT){
                    return new User(id, name, username, password, userType);
                } else if (userType == UserType.TEACHER){
                    return new User(id, name, username, password, userType);
                } else if (userType == UserType.ADMINISTRATOR) {
                    return new User(id, name, username, password, userType);
                }else {
                    return null;
                }
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws IOException, SQLException {
        UserDAO dao = new UserDAO();
        dao.deleteStudent(3, UserType.STUDENT);
        System.out.println(dao.getStudents());
    }
}
