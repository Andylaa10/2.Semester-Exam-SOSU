package dal;

import be.GeneralInformation;
import be.HealthCondition.HealthConditionSubCategoryText;
import be.ObservationNote;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import dal.db.DatabaseConnector;

import java.io.IOException;
import java.sql.*;

public class ObservationNoteDAO {

    private DatabaseConnector databaseConnector = DatabaseConnector.getInstance();


    public ObservationNoteDAO() throws IOException {
    }

    public ObservationNote getObservationNote(int citizenId) throws Exception {
        try (Connection connection = databaseConnector.getConnection()) {
            String sql = "SELECT * FROM ObservationNote WHERE citizenId =?;";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, citizenId);;
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("observationNoteID");
                int citId = resultSet.getInt("citizenId");
                String date = resultSet.getString("date");
                String note = resultSet.getString("note");

                return new ObservationNote(id, citId, date, note);
            }
        }
        return null;
    }

    public ObservationNote createObservationNote(int citizenId, String date, String note) throws SQLException {
        try (Connection connection = databaseConnector.getConnection()) {
            String sql = "INSERT INTO ObservationNote (citizenId, date, note) VALUES (?,?,?);";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setInt(1, citizenId);
                preparedStatement.setString(2, date);
                preparedStatement.setString(3, note);

                preparedStatement.execute();
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                int id = 0;
                if (resultSet.next()) {
                    id = resultSet.getInt(1);
                }

                return new ObservationNote(id, citizenId, date, note);
            }
        } catch (SQLServerException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public void editObservationNote(ObservationNote observationNote) throws Exception {
        try (Connection connection = databaseConnector.getConnection()) {
            String sql = "UPDATE ObservationNote SET date = ?, note = ? WHERE observationNoteID = ? ;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, observationNote.getDate());
            preparedStatement.setString(2, observationNote.getNote());
            preparedStatement.setInt(3, observationNote.getId());

            preparedStatement.executeUpdate();
            if (preparedStatement.executeUpdate() != 1) {
                throw new Exception("Could not edit observation note");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void deleteObservationNote(int id) throws Exception {
        try (Connection connection = databaseConnector.getConnection()) {
            String sql = "DELETE FROM ObservationNote WHERE ObservationNoteID = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            if (preparedStatement.executeUpdate() != 1) {
                throw new Exception();
            }
        } catch (SQLException throwables) {
            throw new SQLException();
        }
    }


    public static void main(String[] args) throws Exception {
        ObservationNoteDAO observationNoteDAO = new ObservationNoteDAO();
        observationNoteDAO.deleteObservationNote(1);
        System.out.println(observationNoteDAO.getObservationNote(5));
    }

}
