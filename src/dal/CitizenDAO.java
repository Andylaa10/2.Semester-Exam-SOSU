package dal;

import be.Citizen;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import dal.db.DatabaseConnector;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CitizenDAO {

    private final DatabaseConnector connector = DatabaseConnector.getInstance();

    /**
     * Constructor
     */
    public CitizenDAO() throws IOException {
    }

    /**
     * Making a citizens list, connecting to the database and adding the results to our ArrayList.
     *
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
                String sex = resultset.getString("sex");
                int schoolId = resultset.getInt("schoolId");

                Citizen citizen = new Citizen(id, firstName, lastName, ssn, sex, schoolId);
                allCitizens.add(citizen);
            }

        } catch (SQLServerException throwables) {
            throw new SQLException();
        }
        return allCitizens;
    }

    /**
     * Gets a list of citizens assigned to a school
     */
    public List<Citizen> getCitizenAndSchool(int schoolId) throws SQLException {
        ArrayList<Citizen> allCitizens = new ArrayList<>();

        try (Connection connection = connector.getConnection()) {
            String sql = "SELECT * FROM Citizen WHERE schoolId = ?;";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, schoolId);
            ResultSet resultset = preparedStatement.executeQuery();

            while (resultset.next()) {
                int id = resultset.getInt("citizenID");
                String firstName = resultset.getString("firstName");
                String lastName = resultset.getString("lastName");
                String ssn = resultset.getString("SSN");
                String sex = resultset.getString("sex");
                int schoolID = resultset.getInt("schoolId");

                Citizen citizen = new Citizen(id, firstName, lastName, ssn, sex, schoolID);
                allCitizens.add(citizen);
            }

        } catch (SQLServerException throwables) {
            throw new SQLException();
        }
        return allCitizens;
    }

    /**
     * Gets all the info on a citizen and returns that citizen
     */
    public Citizen getInfoOnCitizen(int citizenId) throws SQLException {
        try (Connection connection = connector.getConnection()) {
            String sql = "SELECT * FROM Citizen WHERE citizenID = ?;";

            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, citizenId);
            preparedStatement.execute();
            ResultSet resultset = preparedStatement.executeQuery();
            while (resultset.next()) {
                int id = resultset.getInt("citizenID");
                String firstName = resultset.getString("firstName");
                String lastName = resultset.getString("lastName");
                String ssn = resultset.getString("SSN");
                String sex = resultset.getString("sex");

                Citizen citizen = new Citizen(id, firstName, lastName, ssn, sex);
                return citizen;
            }

        } catch (SQLServerException throwables) {
            throw new SQLException();
        }
        return null;
    }


    /**
     * Creates a citizen, by inserting firstName, lastName, SSN, address and sex into the Citizen table
     */
    public Citizen createCitizen(String firstName, String lastName, String SSN, String sex, int schoolId) throws SQLException {
        try (Connection connection = connector.getConnection()) {
            String sql = "INSERT INTO Citizen (firstName, lastName , SSN, sex, schoolId) VALUES (?,?,?,?,?);";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, firstName);
                preparedStatement.setString(2, lastName);
                preparedStatement.setString(3, SSN);
                preparedStatement.setString(4, sex);
                preparedStatement.setInt(5, schoolId);
                preparedStatement.execute();
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                int id = 0;
                if (resultSet.next()) {
                    id = resultSet.getInt(1);
                }

                Citizen citizen = new Citizen(id, firstName, lastName, SSN, sex, schoolId);
                return citizen;
            }
        } catch (SQLServerException throwables) {
            throw new SQLException();
        }
    }

    /**
     * Deletes a citizen from the database by taken the selected citizen ID
     */
    public void deleteCitizen(int citizenID) throws SQLException {
        try (Connection connection = connector.getConnection()) {
            String sql = "DELETE FROM Citizen WHERE CitizenID =?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, citizenID);
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throw new SQLException();
        }
    }

    /**
     * Edit citizen information by getting the selected citizen ID.
     */
    public void editCitizen(Citizen citizen) throws SQLException {
        try (Connection connection = connector.getConnection()) {
            String sql = "UPDATE Citizen " +
                    "SET firstName=?, lastName=?, SSN=?, sex=? " +
                    "WHERE citizenID=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, citizen.getFirstName());
            preparedStatement.setString(2, citizen.getLastName());
            preparedStatement.setString(3, citizen.getSSN());
            preparedStatement.setString(4, citizen.getSex());
            preparedStatement.setInt(5, citizen.getId());
            preparedStatement.executeUpdate();
            if (preparedStatement.executeUpdate() != 1) {
                throw new SQLException();
            }
        }
    }


    /**
     * Main method used for testing this DAO class
     */
    public static void main(String[] args) throws Exception {
        CitizenDAO citizenDAO = new CitizenDAO();
        citizenDAO.createCitizen("Andy Lam", "Nguyen", "040100-1111", "Male", 1);
        citizenDAO.createCitizen("Kristian", "Hollænder", "140396-2222",  "Male", 1);
        citizenDAO.createCitizen("Marcus", "Iversen", "271100-3333",  "Male", 1);
        citizenDAO.createCitizen("Lise", "Billeschou", "170901-4444",  "Female", 1);
        citizenDAO.createCitizen("Agnes", "Mynte", "140598-5555",  "Other", 2);
        citizenDAO.createCitizen("Peter", "Stegger", "260869-6666",  "Male", 2);
        citizenDAO.createCitizen("Trine", "Thomsen", "190974-7777",  "Female", 2);
        citizenDAO.createCitizen("Jeppe", "Led", "221067-8888", "Male", 2);
        System.out.println(citizenDAO.getCitizens());

    }


}
