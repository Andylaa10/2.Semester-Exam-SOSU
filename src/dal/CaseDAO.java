package dal;

import be.Case;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import dal.db.DatabaseConnector;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CaseDAO {

    private DatabaseConnector databaseConnector = DatabaseConnector.getInstance();

    public CaseDAO() throws IOException {

    }

    /**
     * Making a cases list, connecting to the database and adding the results to our ArrayList.
     * @return a list of cases or an empty list of cases.
     */
    public List<Case> getCases() throws SQLException {
        ArrayList<Case> allCases = new ArrayList<>();

        try (Connection connection = databaseConnector.getConnection()) {
            String sql = "SELECT * FROM Cases;";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultset = preparedStatement.executeQuery();
            while (resultset.next()) {
                int id = resultset.getInt("casesID");
                String name = resultset.getString("name");
                String date = resultset.getString("date");
                String info = resultset.getString("info");

                Case aCase = new Case(id, name, date, info);
                allCases.add(aCase);
            }
        } catch (SQLException sqlException) {
            throw new SQLException();
        }
        return allCases;
    }

    /**
     * Creates a case, by inserting name, date and info
     * @param name
     * @param date
     * @param info
     * @return a new case
     */
    public Case createCase (String name, String date, String info) throws SQLException {
        try (Connection connection = databaseConnector.getConnection()) {
            String sql = "INSERT INTO Cases (name, date , info) VALUES (?,?,?);";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, date);
                preparedStatement.setString(3, info);
                preparedStatement.execute();
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                int id = 0;
                if (resultSet.next()) {
                    id = resultSet.getInt(1);
                }

                Case aCase = new Case(id, name, date, info);
                return aCase;
            }
        } catch (SQLServerException throwables) {
            throw new SQLException();
        }
    }

    /**
     * Delete a case, by taking the id
     * @param id
     */
    public void deleteCase(int id) throws Exception {
        try (Connection connection = databaseConnector.getConnection()) {
            String sql = "DELETE FROM Cases WHERE casesID =?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            if (preparedStatement.executeUpdate() != 1) {
                throw new Exception("Could not delete case");
            }
        } catch (SQLException throwables) {
            throw new SQLException("could not delete a case");
        }
    }

    /**
     * Changes the name of a case if a match is found
     * @param aCase
     */
    public void editCase(Case aCase) throws Exception {
        try (Connection connection = databaseConnector.getConnection()) {
            String sql = "UPDATE Cases SET name=?, date=?, info=? WHERE casesID=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, aCase.getName());
            preparedStatement.setString(2, aCase.getDate());
            preparedStatement.setString(3, aCase.getInfo());
            preparedStatement.executeUpdate();
            if (preparedStatement.executeUpdate() != 1) {
                throw new Exception("Could not edit case");
            }
        } catch (SQLException e) {
            throw new SQLException();
        }
    }


    public static void main(String[] args) throws Exception {
        CaseDAO caseDAO = new CaseDAO();
        caseDAO.deleteCase(2);
        //caseDAO.createCase("Brækket ben", "28-04-2022", "Hjælp med at indtaste informationer");
        System.out.println(caseDAO.getCases());
    }
}
