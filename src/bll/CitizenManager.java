package bll;


import be.Case;
import be.Citizen;
import dal.CaseDAO;
import dal.CitizenDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CitizenManager {

    private CitizenDAO citizenDAO;

    public CitizenManager() throws IOException {
        citizenDAO = new CitizenDAO();
    }

    /**
     * Get a list of citizen using the getCitizens method from citizenDAO
     * @return
     * @throws SQLException
     */
    public List<Citizen> getCitizens() throws SQLException {
        return citizenDAO.getCitizens();
    }

}
