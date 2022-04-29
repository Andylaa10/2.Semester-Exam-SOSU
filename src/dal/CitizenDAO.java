package dal;

import be.Citizen;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import dal.db.DatabaseConnector;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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




}
