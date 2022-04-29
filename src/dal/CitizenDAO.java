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
                int generalInfoID = resultset.getInt("generalInfoId");
                int functionalAbilityID = resultset.getInt("functionalAbilityId");
                int loginID = resultset.getInt("loginId");
                int functionalLevelID = resultset.getInt("functionalLevelId");
                int schoolID = resultset.getInt("schoolId");

                Citizen citizen = new Citizen(id, firstName, lastName, ssn, address, sex, generalInfoID, functionalAbilityID, loginID, functionalLevelID, schoolID);
                allCitizens.add(citizen);
            }

        } catch (SQLServerException throwables) {
            throw new SQLException();
        }
        return allCitizens;
    }


    //TODO GeneralInfo
    public Citizen createCitizen(String firstName, String lastName, String SSN, String address, String sex) throws SQLException {
        try(Connection connection = connector.getConnection()) {
            String sql = "INSERT INTO Citizen (firstName, lastName, SSN, address, sex) VALUES (?,?,?,?,?);";
            try(PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1,firstName);
                preparedStatement.setString(2,lastName);
                preparedStatement.setString(3,SSN);
                preparedStatement.setString(4,address);
                preparedStatement.setString(5,sex);

                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                int id = 0;
                if (resultSet.next()){
                    id = resultSet.getInt(1);
                }
                Citizen citizen = new Citizen(id, firstName, lastName, SSN, address, sex);
                return citizen;
            }

        } catch (SQLServerException throwables) {
            throw new SQLException();
        }
    }

    public static void main(String[] args) throws Exception {
        CitizenDAO citizenDAO = new CitizenDAO();
        //citizenDAO.createCitizen("Henrik", "Henriksen", "120300-2319", "Hulemands Allé 12", "Male");
        System.out.println(citizenDAO.getCitizens());
    }


}
