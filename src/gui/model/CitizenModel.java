package gui.model;

import be.Citizen;
import bll.CitizenManager;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CitizenModel {

    private CitizenManager citizenManager;

    public CitizenModel() throws IOException {
        citizenManager = new CitizenManager();
    }

    /**
     * Get a list of citizen using the getCitizens method from citizenManager
     * @return
     * @throws SQLException
     */
    public List<Citizen> getCitizens() throws SQLException {
        return citizenManager.getCitizens();
    }
}
