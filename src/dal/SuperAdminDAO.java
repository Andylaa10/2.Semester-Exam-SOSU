package dal;

import be.School;
import be.SuperAdmin;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import dal.db.DatabaseConnector;
import java.io.IOException;
import java.sql.*;

public class SuperAdminDAO {

    private final DatabaseConnector connector = DatabaseConnector.getInstance();


    public SuperAdminDAO() throws IOException {
    }

    public SuperAdmin createSuperAdmin (String username, String password) throws SQLException {
        try (Connection connection = connector.getConnection()) {
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
        }
        return null;
    }


    public void deleteSuperAdmin(int id) throws SQLException {
        try (Connection connection = connector.getConnection()) {
            String sql = "DELETE FROM SuperAdmin WHERE superAdminID =?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throw new SQLException();
        }
    }

    public void editSuperAdmin(SuperAdmin superAdmin) throws Exception {
        try (Connection connection = connector.getConnection()) {
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


    public SuperAdmin superAdminLogin(String user, String pass) throws SQLException {
        String sql = "SELECT * FROM SuperAdmin WHERE username =? AND password =?;";
        try(Connection connection = connector.getConnection()){
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

    public School createSchool(String schoolName) throws SQLException {
        try (Connection connection = connector.getConnection()) {
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
        }
        return null;
    }

    public void deleteSchool(int schoolID) throws SQLException {
        try (Connection connection = connector.getConnection()) {
            String sql = "DELETE FROM School WHERE schoolID =?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, schoolID);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throw new SQLException();
        }
    }

    public void editSchool(School school) throws Exception {
        try (Connection connection = connector.getConnection()) {
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
     * Main used for testing the DAO methods
     * @param args
     * @throws IOException
     * @throws SQLException
     */
    public static void main(String[] args) throws IOException, SQLException {
        SuperAdminDAO superAdminDAO = new SuperAdminDAO();
        superAdminDAO.createSchool("SOSU Esbjerg");
        //superAdminDAO.deleteSchool(1);
        //superAdminDAO.createSuperAdmin("superadmin", "superadmin");
        //superAdminDAO.deleteSuperAdmin(1);
    }

}
