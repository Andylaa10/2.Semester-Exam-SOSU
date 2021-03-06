package dal;

import be.ObservationNote;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import dal.db.DatabaseConnector;
import java.io.IOException;
import java.sql.*;

public class ObservationNoteDAO {

    private DatabaseConnector databaseConnector = DatabaseConnector.getInstance();

    /**
     * Constructor
     * @throws IOException
     */
    public ObservationNoteDAO() throws IOException {
    }


    /**
     * Gets observation note on a selected citizen
     * @param citizenId
     * @return
     * @throws SQLException
     */
    public ObservationNote getObservationNote(int citizenId) throws SQLException {
        try (Connection connection = databaseConnector.getConnection()) {
            String sql = "SELECT * FROM ObservationNote WHERE citizenId =?;";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, citizenId);
            ;
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

    /**
     * Creates an observation on a selected citizen
     * @throws SQLException
     */
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
            throw new SQLException();
        }
    }

    /**
     * Edits observation note on a citizen
     * @param observationNote
     * @throws SQLException
     */
    public void editObservationNote(ObservationNote observationNote) throws SQLException {
        try (Connection connection = databaseConnector.getConnection()) {
            String sql = "UPDATE ObservationNote " +
                    "SET date = ?, note = ? " +
                    "WHERE citizenId = ? ;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, observationNote.getDate());
            preparedStatement.setString(2, observationNote.getNote());
            preparedStatement.setInt(3, observationNote.getCitizenId());

            preparedStatement.executeUpdate();
            if (preparedStatement.executeUpdate() != 1) {
                throw new SQLException();
            }
        }
    }


    /**
     * Deletes observations note on a citizen
     * @param id
     * @throws SQLException
     */
    public void deleteObservationNote(int id) throws SQLException {
        try (Connection connection = databaseConnector.getConnection()) {
            String sql = "DELETE FROM ObservationNote WHERE ObservationNoteID = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            if (preparedStatement.executeUpdate() != 1) {
                throw new SQLException();
            }
        }
    }


    public static void main(String[] args) throws Exception {
        ObservationNoteDAO observationNoteDAO = new ObservationNoteDAO();
        observationNoteDAO.createObservationNote(5, "12/12/2021", "TEst af create");
        System.out.println(observationNoteDAO.getObservationNote(5));
    }

}
