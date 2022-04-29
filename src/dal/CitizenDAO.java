package dal;

import be.Citizen;
import be.User;
import be.enums.UserType;
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
        ArrayList<Citizen> allCustomers = new ArrayList<>();

        try (Connection connection = connector.getConnection()) {
            String sql = "SELECT * FROM Citizen;";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultset = preparedStatement.executeQuery();
            while (resultset.next()) {
                int customerID = resultset.getInt("CustomerID");
                String firstName = resultset.getString("Fname");
                String lastName = resultset.getString("Lname");
                String phoneNumber = resultset.getString("PhoneNumber");
                String email = resultset.getString("Email");
                String study = resultset.getString("Study");
                String note = resultset.getString("Note");

                Customer customer = new Customer(customerID, firstName, lastName, phoneNumber, email, study, note);
                allCustomers.add(customer);
            }
        } catch (SQLServerException throwables) {
            throwables.printStackTrace();
        }
        return allCustomers;
    }

}
