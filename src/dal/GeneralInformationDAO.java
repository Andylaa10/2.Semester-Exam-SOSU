package dal;

import be.GeneralInformation;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import dal.db.DatabaseConnector;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GeneralInformationDAO {

    private final DatabaseConnector databaseConnector = DatabaseConnector.getInstance();

    public GeneralInformationDAO() throws IOException {
    }

    /**
     * Making a citizens list, connecting to the database and adding the results to our ArrayList.
     *
     * @return a list of citizens or an empty list of citizens.
     */
    public List<GeneralInformation> getGeneralInformation() throws SQLException {
        ArrayList<GeneralInformation> allGeneralInformations = new ArrayList<>();

        try (Connection connection = databaseConnector.getConnection()) {
            String sql = "SELECT * FROM GeneralInformation;";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultset = preparedStatement.executeQuery();
            while (resultset.next()) {
                int id = resultset.getInt("generalInfoID");
                String coping = resultset.getString("coping");
                String motivation = resultset.getString("motivation");
                String resources = resultset.getString("resources");
                String roles = resultset.getString("roles");
                String habits = resultset.getString("habits");
                String educationAndJob = resultset.getString("educationAndJob");
                String lifestory = resultset.getString("lifestory");
                String network = resultset.getString("network");
                String healthInformation = resultset.getString("healthInformation");
                String equipmentAids = resultset.getString("equipmentAids");
                String homeLayout = resultset.getString("homeLayout");

                GeneralInformation generalInformation = new GeneralInformation(id, coping, motivation, resources, roles, habits,
                        educationAndJob, lifestory, network, healthInformation, equipmentAids, homeLayout);
                allGeneralInformations.add(generalInformation);
            }

        } catch (SQLServerException throwables) {
            throw new SQLException();
        }
        return allGeneralInformations;
    }

    public GeneralInformation getGeneralInformationOnCitizen(int citizenId) throws SQLException {
        try (Connection connection = databaseConnector.getConnection()) {
            String sql = "SELECT * FROM GeneralInformation WHERE citizenId =?;";

            PreparedStatement preparedStatement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, citizenId);
            preparedStatement.execute();
            ResultSet resultset = preparedStatement.executeQuery();
            while (resultset.next()) {
                int id = resultset.getInt("generalInfoID");
                String coping = resultset.getString("coping");
                String motivation = resultset.getString("motivation");
                String resources = resultset.getString("resources");
                String roles = resultset.getString("roles");
                String habits = resultset.getString("habits");
                String educationAndJob = resultset.getString("educationAndJob");
                String lifestory = resultset.getString("lifestory");
                String network = resultset.getString("network");
                String healthInformation = resultset.getString("healthInformation");
                String equipmentAids = resultset.getString("equipmentAids");
                String homeLayout = resultset.getString("homeLayout");


                GeneralInformation generalInformation = new GeneralInformation(id, coping, motivation, resources, roles, habits,
                        educationAndJob, lifestory, network, healthInformation, equipmentAids, homeLayout);
                return generalInformation;
            }

        } catch (SQLServerException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public GeneralInformation createGeneralInformation(int citizenId, String coping, String motivation, String resources, String roles,
                                                       String habits, String educationAndJob, String lifeStory,
                                                       String network, String healthInformation, String equipmentAids,
                                                       String homeLayout) throws SQLException {

        try (Connection connection = databaseConnector.getConnection()) {
            String sql = "INSERT INTO GeneralInformation (citizenId, coping, motivation, resources, roles, habits," +
                    "educationAndJob, lifestory, network, healthInformation, equipmentAids, homelayout) VALUES (?,?,?,?,?,?,?,?,?,?,?,?);";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setInt(1, citizenId);
                preparedStatement.setString(2, coping);
                preparedStatement.setString(3, motivation);
                preparedStatement.setString(4, resources);
                preparedStatement.setString(5, roles);
                preparedStatement.setString(6, habits);
                preparedStatement.setString(7, educationAndJob);
                preparedStatement.setString(8, lifeStory);
                preparedStatement.setString(9, network);
                preparedStatement.setString(10, healthInformation);
                preparedStatement.setString(11, equipmentAids);
                preparedStatement.setString(12, homeLayout);
                preparedStatement.execute();
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                int id = 0;
                if (resultSet.next()) {
                    id = resultSet.getInt(1);
                }

                GeneralInformation generalInformation = new GeneralInformation(id, citizenId, coping, motivation, resources,
                        roles, habits, educationAndJob, lifeStory, network, healthInformation, equipmentAids,
                        homeLayout);
                return generalInformation;
            }
        } catch (SQLServerException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public void editGeneralInformation(GeneralInformation generalInformation) throws Exception {
        try (Connection connection = databaseConnector.getConnection()) {
            String sql = "UPDATE GeneralInformation SET coping = ?, motivation = ?, resources = ?, roles = ?," +
                    " habits = ?,educationAndJob = ?, lifestory = ?, network = ?, healthInformation = ?," +
                    " equipmentAids = ?, homelayout = ? WHERE generalInfoID = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, generalInformation.getCoping());
            preparedStatement.setString(2, generalInformation.getMotivation());
            preparedStatement.setString(3, generalInformation.getResources());
            preparedStatement.setString(4, generalInformation.getRoles());
            preparedStatement.setString(5, generalInformation.getHabits());
            preparedStatement.setString(6, generalInformation.getEducationAndJob());
            preparedStatement.setString(7, generalInformation.getLifeStory());
            preparedStatement.setString(8, generalInformation.getNetwork());
            preparedStatement.setString(9, generalInformation.getHealthInformation());
            preparedStatement.setString(10, generalInformation.getEquipmentAids());
            preparedStatement.setString(11, generalInformation.getHomeLayout());
            preparedStatement.setInt(12, generalInformation.getId());

            preparedStatement.executeUpdate();
            if (preparedStatement.executeUpdate() != 1) {
                throw new Exception("Could not edit case");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteGeneralInformation(int id) throws Exception {
        try (Connection connection = databaseConnector.getConnection()) {
            String sql = "DELETE FROM GeneralInformation WHERE generalInfoID =?;";
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
        GeneralInformationDAO generalInformationDAO = new GeneralInformationDAO();
        //generalInformationDAO.createGeneralInformation("coping", "motivation", "resources",
        //        "roles", "habits", "educationAndJob", "lifeStory", "network",
        //        "healthInformation", "equipmentAids", "homeLayout");
        //generalInformationDAO.deleteGeneralInformation(1);
        //System.out.println(generalInformationDAO.getGeneralInformationOnCitizen(13));
        System.out.println(generalInformationDAO.getGeneralInformationOnCitizen(5));
    }
}
