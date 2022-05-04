package bll;

import be.HealthCondition;
import be.School;
import be.SubCategory;
import dal.HealthConditionsDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class HealthConditionsManager {

    private HealthConditionsDAO healthConditionsDAO;

    public HealthConditionsManager() throws IOException {
        this.healthConditionsDAO = new HealthConditionsDAO();
    }

    public List<HealthCondition> getHealthConditions() throws SQLException {
        return healthConditionsDAO.getHealthConditions();
    }

    public List<SubCategory> getSubCategories(int categoryId) throws SQLException {
        return healthConditionsDAO.getSubCategories(categoryId);
    }
}
