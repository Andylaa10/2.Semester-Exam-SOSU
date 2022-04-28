package bll;

import be.SuperAdmin;
import be.User;
import be.enums.UserType;
import dal.SuperAdminDAO;


import java.io.IOException;
import java.sql.SQLException;

public class SuperAdminManager {

    private SuperAdminDAO superAdminDAO;

    public SuperAdminManager() throws IOException {
        superAdminDAO = new SuperAdminDAO();
    }

    public SuperAdmin createSuperAdmin(String username, String password) throws SQLException {
        return superAdminDAO.createSuperAdmin(username, password);
    }

    public void deleteSuperAdmin(int id){
        superAdminDAO.deleteSuperAdmin(id);
    }

    public void editSuperAdmin(SuperAdmin superAdmin) throws Exception {
        superAdminDAO.editSuperAdmin(superAdmin);
    }

    public SuperAdmin superAdminLogin(String username, String password){
        return superAdminDAO.superAdminLogin(username, password);
    }

}
