package dal;

import be.Citizen;
import be.GeneralInformation;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import dal.db.DatabaseConnector;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CitizenDAO {

    private final DatabaseConnector connector = DatabaseConnector.getInstance();

    public CitizenDAO() throws IOException {
    }

    /**
     * Making a citizens list, connecting to the database and adding the results to our ArrayList.
     * @return a list of citizens or an empty list of citizens.
     */
    public List<Citizen> getCitizens() throws SQLException {
        ArrayList<Citizen> allCitizens = new ArrayList<>();

        try (Connection connection = connector.getConnection()) {
            String sql = "SELECT * FROM Citizen;";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultset = preparedStatement.executeQuery();
            while (resultset.next()) {
                int id = resultset.getInt("citizenID");
                String firstName = resultset.getString("firstName");
                String lastName = resultset.getString("lastName");
                String ssn = resultset.getString("SSN");
                String address = resultset.getString("address");
                String sex = resultset.getString("sex");

                Citizen citizen = new Citizen(id, firstName, lastName, ssn, address, sex);
                allCitizens.add(citizen);
            }

        } catch (SQLServerException throwables) {
            throwables.printStackTrace();
        }
        return allCitizens;
    }

    /**
     * Gets all the info on a citizen and returns that citizen
     */
    public Citizen getInfoOnCitizen(int citizenId) throws SQLException {
        try (Connection connection = connector.getConnection()) {
            String sql = "SELECT * FROM Citizen WHERE citizenID = ?;";

            PreparedStatement preparedStatement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, citizenId);
            preparedStatement.execute();
            ResultSet resultset = preparedStatement.executeQuery();
            while (resultset.next()) {
                int id = resultset.getInt("citizenID");
                String firstName = resultset.getString("firstName");
                String lastName = resultset.getString("lastName");
                String ssn = resultset.getString("SSN");
                String address = resultset.getString("address");
                String sex = resultset.getString("sex");

               Citizen citizen = new Citizen(id, firstName, lastName, ssn, address, sex);
                return citizen;
            }

        } catch (SQLServerException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }


    /**
     * Creates a citizen, by inserting firstName, lastName, SSN, address and sex into the Citizen table
     */
    public Citizen createCitizen(String firstName, String lastName, String SSN, String address, String sex) throws SQLException {
        try (Connection connection = connector.getConnection()) {
            String sql = "INSERT INTO Citizen (firstName, lastName , SSN, address, sex) VALUES (?,?,?,?,?);";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, firstName);
                preparedStatement.setString(2, lastName);
                preparedStatement.setString(3, SSN);
                preparedStatement.setString(4, address);
                preparedStatement.setString(5, sex);
                preparedStatement.execute();
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                int id = 0;
                if (resultSet.next()) {
                    id = resultSet.getInt(1);
                }

                Citizen citizen = new Citizen(id, firstName, lastName, SSN, address, sex);
                return citizen;
            }
        } catch (SQLServerException throwables) {
            throw new SQLException();
        }
    }

    /**
     * Deletes a citizen from the database by taken the selected citizen ID
     */
    public void deleteCitizen(int citizenID) {
        try (Connection connection = connector.getConnection()) {
            String sql = "DELETE FROM Citizen WHERE CitizenID =?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, citizenID);
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Edit citizen information by getting the selected citizen ID.
     */
    public void editCitizen(Citizen citizen) throws Exception {
        try (Connection connection = connector.getConnection()) {
            String sql = "UPDATE Citizen SET firstName=?, lastName=?, SSN=?, address=?, sex=? WHERE citizenID=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, citizen.getFirstName());
            preparedStatement.setString(2, citizen.getLastName());
            preparedStatement.setString(3, citizen.getSSN());
            preparedStatement.setString(4, citizen.getAddress());
            preparedStatement.setString(5, citizen.getSex());
            preparedStatement.setInt(6, citizen.getId());
            preparedStatement.executeUpdate();
            if (preparedStatement.executeUpdate() != 1) {
                throw new Exception("Could not edit case");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * Main method used for testing this DAO class
     */
    public static void main(String[] args) throws Exception {
        CitizenDAO citizenDAO = new CitizenDAO();
        citizenDAO.createCitizen("Andy Lam", "Nguyen", "040100-1111", "Golfvej 8", "Male");
        citizenDAO.createCitizen("Kristian", "Hollænder", "140396-2222", "Hollændervej 4", "Male");
        citizenDAO.createCitizen("Marcus", "Iversen", "271100-3333", "Skolegade 8", "Male");
        citizenDAO.createCitizen("Lise", "Billeschou", "170901-4444", "Skolegade 8", "Female");
        citizenDAO.createCitizen("Agnes", "Mynte", "140598-5555", "Transvej 2", "Other");
        citizenDAO.createCitizen("Peter", "Stegger", "260869-6666", "Hackervej 69", "Male");
        citizenDAO.createCitizen("Trine", "Thomsen", "190974-7777", "ITO Vej 12", "Female");
        citizenDAO.createCitizen("Jeppe", "Led", "221067-8888", "Designvej 96", "Male");

        System.out.println(citizenDAO.getCitizens());
    }


}
