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
                int functionalAbilityID = resultset.getInt("functionalAbilityId");
                int loginID = resultset.getInt("loginId");
                int schoolID = resultset.getInt("schoolId");

                Citizen citizen = new Citizen(id, firstName, lastName, ssn, address, sex, functionalAbilityID, loginID, schoolID);
                allCitizens.add(citizen);
            }

        } catch (SQLServerException throwables) {
            throwables.printStackTrace();
        }
        return allCitizens;
    }



    /**
     * Creates a citizen, by inserting firstName, lastName, SSN, address and sex
     * @param firstName
     * @param lastName
     * @param SSN
     * @param address
     * @param sex
     * @return
     * @throws SQLException
     */
    //TODO GeneralInfo
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
     * Deletes a citizen by taken the id
     * @param id
     * @throws Exception
     */
    public void deleteCitizen(int id) throws Exception {
        try (Connection connection = connector.getConnection()) {
            String sql = "DELETE FROM Citizen WHERE citizenID =?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            if (preparedStatement.executeUpdate() != 1) {
                throw new Exception();
            }
        } catch (SQLException throwables) {
            throw new SQLException();
        }
    }

    /**
     * Edit a citizen if a match is found
     * @param citizen
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
     * Read what cases a citizen is assigned to
     * @param caseId
     * @return
     * @throws SQLException
     */
    public List<Citizen> getCitizensOnCase(int caseId) throws SQLException {
        ArrayList<Citizen> allCitizens = new ArrayList<>();
        try (Connection connection = connector.getConnection()) {
            String sql = "SELECT Citizen.citizenID, firstName, lastName, SSN FROM Citizen INNER JOIN CasesOnCitizen ON CasesOnCitizen.citizenId = Citizen.citizenID WHERE CasesOnCitizen.casesId = ?;";

            PreparedStatement preparedStatement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, caseId);
            preparedStatement.execute();
            ResultSet resultset = preparedStatement.executeQuery();
            while (resultset.next()) {
                int id = resultset.getInt("citizenID");
                String firstName = resultset.getString("firstName");
                String lastName = resultset.getString("lastName");
                String ssn = resultset.getString("SSN");


                Citizen citizen = new Citizen(id, firstName, lastName, ssn);
                allCitizens.add(citizen);
            }

        } catch (SQLServerException throwables) {
            throw new SQLException();
        }
        return allCitizens;
    }

    public static void main(String[] args) throws Exception {
        CitizenDAO citizenDAO = new CitizenDAO();
        //citizenDAO.createCitizen("Joe", "Mama", "040119-2311", "Bjergberg Allé 205", "Male");
        citizenDAO.deleteCitizen(3);
        citizenDAO.deleteCitizen(4);
        System.out.println(citizenDAO.getCitizens());
    }


}
