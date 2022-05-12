package dal;

import be.*;
import be.enums.UserType;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import dal.db.DatabaseConnector;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SuperAdminDAO {

    private final DatabaseConnector databaseConnector = DatabaseConnector.getInstance();


    public SuperAdminDAO() throws IOException {
    }

    /**
     * Create a SuperAdmin by inserting into the SuperAdmin table.
     */
    public SuperAdmin createSuperAdmin (String username, String password) throws SQLException {
        try (Connection connection = databaseConnector.getConnection()) {
            String sql = "INSERT INTO SuperAdmin (username, password) VALUES (?,?);";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                preparedStatement.execute();

                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                int id = 0;
                if (resultSet.next()) {
                    id = resultSet.getInt(1);
                }

                SuperAdmin superAdmin = new SuperAdmin(id, username, password);
                return superAdmin;
            }
        } catch (SQLServerException throwables) {
            throw new SQLException();
        }
    }


    /**
     * Deletes a superAdmin.
     * This method is currently not in use.
     */
    public void deleteSuperAdmin(int id) throws SQLException {
        try (Connection connection = databaseConnector.getConnection()) {
            String sql = "DELETE FROM SuperAdmin WHERE superAdminID =?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throw new SQLException();
        }
    }

    /**
     * Edits a superAdmin.
     * This method is currently not in use.
     */
    public void editSuperAdmin(SuperAdmin superAdmin) throws Exception {
        try (Connection connection = databaseConnector.getConnection()) {
            String sql = "UPDATE SuperAdmin SET username=?, password=? WHERE superAdminID=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, superAdmin.getUsername());
            preparedStatement.setString(2, superAdmin.getPassword());
            preparedStatement.setInt(3, superAdmin.getId());
            preparedStatement.executeUpdate();
            if (preparedStatement.executeUpdate() != 1) {
                throw new Exception("Could not edit super admin");
            }
        }
    }


    /**
     * Makes the superAdmin able to login by checking if the login info is in the database
     */
    public SuperAdmin superAdminLogin(String user, String pass) throws SQLException {
        String sql = "SELECT * FROM SuperAdmin WHERE username =? AND password =?;";
        try(Connection connection = databaseConnector.getConnection()){
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, user);
            st.setString(2, pass);
            ResultSet rs = st.executeQuery();
            if (rs.next()){
                int id = rs.getInt("SuperAdminID");
                String username = rs.getString("username");
                String password = rs.getString("password");
                    return new SuperAdmin(id, username, password);
                } else {
                    return null;
                }
        } catch (SQLException sqlException) {
            throw new SQLException();
        }
    }

    /**
     * Makes a list of allSchools and returns it
     */
    public List<School> getSchools() throws SQLException {
        ArrayList<School> allSchools = new ArrayList<>();

        try (Connection connection = databaseConnector.getConnection()) {
            String sql = "SELECT * FROM School;";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultset = preparedStatement.executeQuery();
            while (resultset.next()) {
                int id = resultset.getInt("schoolID");
                String name = resultset.getString("name");

                School school = new School(id, name);
                allSchools.add(school);
            }
        } catch (SQLException sqlException) {
            throw new SQLException();
        }
        return allSchools;
    }


    /**
     * Making a students list, connecting to the database and adding the results to our ArrayList.
     * @return a list of assigned students or an empty list of students.
     */
    public List<User> getAssignedStudents(int schoolId) throws SQLException {
        ArrayList<User> allStudents = new ArrayList<>();

        try (Connection connection = databaseConnector.getConnection()) {
            String sql = "SELECT * FROM Login WHERE userType =? AND schoolId=?;";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, String.valueOf(UserType.STUDENT));
            preparedStatement.setInt(2, schoolId);
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
     * @return a list of assigned teachers or an empty list of teachers.
     */
    public List<User> getAssignedTeachers(int schoolId) throws SQLException {
        ArrayList<User> allTeachers = new ArrayList<>();
        try (Connection connection = databaseConnector.getConnection()) {
            String sql = "SELECT * FROM Login WHERE userType =? AND schoolId=? ;";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, String.valueOf(UserType.TEACHER));
            preparedStatement.setInt(2, schoolId);
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
     * @return a list of assigned admins or an empty list of admins.
     */
    public List<User> getAssignedAdmins(int schoolId) throws SQLException {
        ArrayList<User> allAdmins = new ArrayList<>();

        try (Connection connection = databaseConnector.getConnection()) {
            String sql = "SELECT * FROM Login WHERE userType =? AND schoolId=? ;";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, String.valueOf(UserType.ADMINISTRATOR));
            preparedStatement.setInt(2, schoolId);
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
     * Gets all the admins assigned to a school.
     * Inner Joins on the UserOnSchool table
     */
    public List<User> getAdminsOnSchool(int schoolId) throws SQLException {
        ArrayList<User> allAdmins = new ArrayList<>();
        try (Connection connection = databaseConnector.getConnection()) {
            String sql = "SELECT * FROM [Login] INNER JOIN School ON School.schoolID = [Login].schoolId WHERE [Login].userType=? AND [Login].schoolId =?;";

            PreparedStatement preparedStatement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, String.valueOf(UserType.ADMINISTRATOR));
            preparedStatement.setInt(2, schoolId);
            preparedStatement.execute();

            ResultSet resultset = preparedStatement.executeQuery();
            while (resultset.next()) {
                int id = resultset.getInt("loginID");
                String firstName = resultset.getString("firstName");
                String lastName = resultset.getString("lastName");
                String username = resultset.getString("username");
                UserType usertype = UserType.valueOf(resultset.getString("userType"));
                int schoolID = resultset.getInt("schoolId");

                User admin = new User(id, firstName, lastName, username, usertype, schoolID);
                allAdmins.add(admin);
            }

        } catch (SQLServerException throwables) {
            throwables.printStackTrace();
        }
        return allAdmins;
    }

    /**
     * Adds an admin to a school, using the UserOnSchool table.
     */
    public void addAdminToSchool(int loginId, int schoolId){
        String sql = "INSERT INTO [Login] (loginId, schoolId) VALUES (?,?);";
        try (Connection con = databaseConnector.getConnection();
             PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            st.setInt(1, loginId);
            st.setInt(2, schoolId);
            st.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Creates a school by inserting the name into the School table.
     */
    public School createSchool(String schoolName) throws SQLException {
        try (Connection connection = databaseConnector.getConnection()) {
            String sql = "INSERT INTO School (name) VALUES (?);";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, schoolName);
                preparedStatement.execute();

                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                int id = 0;
                if (resultSet.next()) {
                    id = resultSet.getInt(1);
                }

                School school = new School(id, schoolName);
                return school;
            }
        } catch (SQLServerException throwables) {
            throw new SQLException();
        }
    }

    /**
     * Deletes the selected school ID from the School table.
     */
    public void deleteSchool(int schoolID) throws SQLException {
        try (Connection connection = databaseConnector.getConnection()) {
            String sql = "DELETE FROM School WHERE schoolID =?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, schoolID);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throw new SQLException();
        }
    }

    /**
     * Edits the selected schools name using the selected schools ID.
     */
    public void editSchool(School school) throws Exception {
        try (Connection connection = databaseConnector.getConnection()) {
            String sql = "UPDATE School SET name=? WHERE schoolID=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, school.getSchoolName());
            preparedStatement.setInt(2, school.getId());
            if (preparedStatement.executeUpdate() != 1) {
                throw new Exception("Could not edit school name");
            }
        }
    }

    /**
     * Deletes an admin from the UserOnSchool table, so he is no longer assigned to that school.
     */
    public void deleteAdminFromSchool(int userId, int schoolId) {
        String sql = "DELETE FROM UserOnSchool WHERE loginId = ? AND schoolId = ?;";
        try (Connection con = databaseConnector.getConnection();
             PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            st.setInt(1, userId);
            st.setInt(2, schoolId);
            st.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Main used for testing the DAO methods in this class.
     */
    public static void main(String[] args) throws IOException, SQLException {
        SuperAdminDAO superAdminDAO = new SuperAdminDAO();
        superAdminDAO.createSchool("SOSU Esbjerg");
        superAdminDAO.createSchool("SOSU Bramming");
        System.out.println(superAdminDAO.getSchools());
        //System.out.println(superAdminDAO.getAdminsOnSchool(4));
        //System.out.println(superAdminDAO.getSchools());
        //superAdminDAO.deleteSchool(1);
        superAdminDAO.createSuperAdmin("superadmin", "1");
        //superAdminDAO.deleteSuperAdmin(1);
    }

}
