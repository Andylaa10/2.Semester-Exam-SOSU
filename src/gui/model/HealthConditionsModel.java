package gui.model;

import be.HealthCondition;
import be.SubCategory;
import bll.HealthConditionsManager;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class HealthConditionsModel {

    private HealthConditionsManager healthConditionsManager;

    public HealthConditionsModel() throws IOException {
        this.healthConditionsManager = new HealthConditionsManager();
    }

    public List<HealthCondition> getHealthConditions() throws SQLException {
        return healthConditionsManager.getHealthConditions();
    }

    public List<SubCategory> getSubCategories(int categoryId) throws SQLException {
        return healthConditionsManager.getSubCategories(categoryId);
    }
}
