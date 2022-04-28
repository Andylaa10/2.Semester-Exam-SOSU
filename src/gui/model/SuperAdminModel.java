package gui.model;

import be.School;
import be.SuperAdmin;
import bll.SuperAdminManager;
import dal.SuperAdminDAO;

import java.io.IOException;
import java.sql.SQLException;

public class SuperAdminModel {

    private SuperAdminManager superAdminManager;

    public SuperAdminModel() throws IOException {
        superAdminManager = new SuperAdminManager();
    }


    public SuperAdmin createSuperAdmin(String username, String password) throws SQLException {
        return superAdminManager.createSuperAdmin(username, password);
    }

    public void deleteSuperAdmin(int id){
        superAdminManager.deleteSuperAdmin(id);
    }

    public void editSuperAdmin(SuperAdmin superAdmin) throws Exception {
        superAdminManager.editSuperAdmin(superAdmin);
    }

    public SuperAdmin superAdminLogin(String username, String password) throws SQLException {
        return superAdminManager.superAdminLogin(username, password);
    }

    public School createSchool(String schoolName) throws SQLException {
        return superAdminManager.createSchool(schoolName);
    }

    public void deleteSchool(int schoolID) {
        superAdminManager.deleteSchool(schoolID);
    }

    public void editSchool(School school) throws Exception {
        superAdminManager.editSchool(school);
    }



}
