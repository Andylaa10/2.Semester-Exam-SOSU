package gui.Facade;

import be.*;
import be.FunctionalAbilities.FunctionalAbilitySubCategoryText;
import be.FunctionalAbilities.FunctionalAbility;
import be.HealthCondition.HealthCondition;
import be.HealthCondition.HealthConditionSubCategory;
import be.HealthCondition.HealthConditionSubCategoryText;
import be.enums.UserType;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import dal.db.DatabaseConnector;
import gui.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class DataModelFacade {

    private final CitizenModel citizenModel;
    private final UserModel userModel;
    private final SuperAdminModel superAdminModel;
    private final CaseModel caseModel;
    private final HealthConditionsModel healthConditionsModel;
    private final GeneralInformationModel generalInformationModel;
    private final FunctionalAbilitiesModel functionalAbilitiesModel;
    private final ObservationNoteModel observationNoteModel;

    private final ObservableList<Citizen> citizensToBeViewed;

    private static DataModelFacade instance = null;



    /**
     * Facade pattern that collects all methods from the model layer, put together to make it easier for coding in the
     * controller layer. You now only have to instantiate one model (dataModelFacade) instead of instantiating all 9
     * models.
     * @throws IOException
     * @throws SQLException
     */
    private DataModelFacade() throws IOException, SQLException {
        citizenModel = new CitizenModel();
        userModel = new UserModel();
        superAdminModel = new SuperAdminModel();
        caseModel = new CaseModel();
        healthConditionsModel = new HealthConditionsModel();
        generalInformationModel = new GeneralInformationModel();
        functionalAbilitiesModel = new FunctionalAbilitiesModel();
        observationNoteModel = new ObservationNoteModel();
        citizensToBeViewed = FXCollections.observableArrayList();
        citizensToBeViewed.addAll(citizenModel.getCitizens());
    }

    /**
     * Singleton pattern
     * @return DataModelFacade called instance
     */
    public static DataModelFacade getInstance() throws IOException, SQLException {
        if (instance == null)
            instance = new DataModelFacade();

        return instance;
    }


    /**
     * UserModel methods.
     */

    public List<User> getStudents() throws SQLException {
        return userModel.getStudents();
    }

    public User userLogin(String username, String password, int schoolId) throws SQLException {
        return userModel.userLogin(username, password, schoolId);
    }

    public List<User> getTeachers() throws SQLException {
        return userModel.getTeachers();
    }

    public List<User> getAdmins() throws SQLException {
        return userModel.getAdmins();
    }

    public List<User> getUsernames() throws SQLException {
        return userModel.getUsernames();
    }

    public User getHashedPassword(String userName, String password, int schoolId) throws SQLException {
        return userModel.getHashedPassword(userName, password, schoolId);
    }

    public User createStudent(String firstName, String lastName, String username, String password, UserType userType, int schoolId) throws SQLException {
        return userModel.createStudent(firstName, lastName, username, password, userType, schoolId);
    }


    public User createTeacher(String firstName, String lastName, String username, String password, UserType userType, int schoolId) throws SQLException {
        return userModel.createTeacher(firstName, lastName, username, password, userType, schoolId);
    }


    public User createAdmin(String firstName, String lastName, String username, String password, UserType userType, int schoolId) throws SQLException {
        return userModel.createAdmin(firstName, lastName, username, password, userType, schoolId);
    }

    public void deleteStudent(int id, UserType userType) throws SQLException {
        userModel.deleteStudent(id, userType);
    }

    public void deleteTeacher(int id, UserType userType) throws SQLException {
        userModel.deleteTeacher(id, userType);
    }


    public void deleteAdmin(int id, UserType userType) throws SQLException {
        userModel.deleteAdmin(id, userType);
    }


    public void deleteSuperAdmin(int id) throws SQLException {
        superAdminModel.deleteSuperAdmin(id);
    }


    public void editStudent(User student) throws Exception {
        userModel.editStudent(student);
    }


    public void editTeacher(User teacher) throws Exception {
        userModel.editTeacher(teacher);
    }


    public void editAdmin(User admin) throws Exception {
        userModel.editAdmin(admin);
    }


    /**
     * HealthConditionsModel methods.
     */

    public List<HealthCondition> getHealthConditions() throws SQLException {
        return healthConditionsModel.getHealthConditions();
    }

    public List<HealthConditionSubCategory> getSubCategories(int categoryId) throws SQLException {
        return healthConditionsModel.getSubCategories(categoryId);
    }


    /**
     * SuperAdminModel methods.
     */

    public List<User> getAdminsOnSchool(int schoolId) throws SQLException {
        return superAdminModel.getAdminsOnSchool(schoolId);
    }

    public List<Citizen> getAssignedCitizen(int schoolId) throws SQLException {
        return superAdminModel.getAssignedCitizen(schoolId);
    }

    public List<Case> getAssignedCases(int schoolId) throws SQLException {
        return superAdminModel.getAssignedCases(schoolId);
    }

    public SuperAdmin superAdminLogin(String username, String password) throws SQLException {
        return superAdminModel.superAdminLogin(username, password);
    }

    public SuperAdmin createSuperAdmin(String username, String password) throws SQLException {
        return superAdminModel.createSuperAdmin(username, password);
    }

    public void editSuperAdmin(SuperAdmin superAdmin) throws Exception {
        superAdminModel.editSuperAdmin(superAdmin);
    }


    public List<School> getSchools() throws SQLException {
        return superAdminModel.getSchools();
    }

    public List<User> getAssignedTeachers(int schoolId) throws SQLException{
        return superAdminModel.getAssignedTeachers(schoolId);
    }

    public List<User> getAssignedAdmins(int schoolId) throws SQLException{
        return superAdminModel.getAssignedAdmins(schoolId);
    }

    public List<User> getAssignedStudents(int schoolId) throws SQLException{
        return superAdminModel.getAssignedStudents(schoolId);
    }

    public School createSchool(String schoolName) throws SQLException {
        return superAdminModel.createSchool(schoolName);
    }


    public void deleteSchool(int id) throws SQLException {
        superAdminModel.deleteSchool(id);
    }


    public void editSchool(School school) throws Exception {
        superAdminModel.editSchool(school);
    }

    public void addAdminToSchool(int loginId, int schoolId) throws SQLException {
        superAdminModel.addAdminToSchool(loginId, schoolId);
    }

    public void deleteAdminFromSchool(int userId, int schoolId) throws SQLException {
        superAdminModel.deleteAdminFromSchool(userId, schoolId);
    }

    public SuperAdmin getHashedPasswordSuperAdmin(String username, String password) throws SQLException {
        return superAdminModel.getHashedPasswordSuperAdmin(username, password);
    }


    /**
     * CaseModel Methods
     */

    public List<Case> getCases() throws SQLException {
        return caseModel.getCases();
    }


    public Case createCase(String name, String info, int schoolId) throws SQLException {
        return caseModel.createCase(name, info, schoolId);
    }


    public void deleteCase(int id) throws Exception {
        caseModel.deleteCase(id);
    }


    public void editCase(Case aCase) throws Exception {
        caseModel.editCase(aCase);
    }

    public void assignCaseToCitizen(int caseId, int citizenId) throws SQLException {
        caseModel.assignCaseToCitizen(caseId, citizenId);
    }
    public void deleteCaseFromCitizen(int caseId, int citizenId) throws SQLException {
        caseModel.deleteCaseFromCitizen(caseId, citizenId);
    }

    public List<Case> getCasesOnCitizen(int citizenId) throws SQLException {
        return caseModel.getCasesOnCitizen(citizenId);
    }

    public Case getCaseOnCitizen(int citizenId, int casesId) throws SQLException {
        return caseModel.getCaseOnCitizen(citizenId, casesId);
    }


    /**
     * CitizenModel methods.
     */

    public List<Citizen> getCitizens() throws SQLException {
        return citizenModel.getCitizens();
    }

    public List<Citizen> getCitizensAndSchool(int schoolId) throws SQLException {
        return citizenModel.getCitizensAndSchool(schoolId);
    }

    public Citizen getInfoOnCitizen(int citizenId) throws SQLException {
        return citizenModel.getInfoOnCitizen(citizenId);
    }


    public Citizen createCitizen(String firstname, String lastName, String SSN, String address, String sex, int schoolId) throws SQLException {
        return citizenModel.createCitizen(firstname, lastName, SSN, address, sex, schoolId);
    }


    public void deleteCitizen(int citizenID) throws Exception {
        citizenModel.deleteCitizen(citizenID);
    }


    public void editCitizen(Citizen citizen) throws Exception {
        citizenModel.editCitizen(citizen);
    }

    public List<Citizen> searchCitizen(String query, int schoolId) throws SQLException {
        List<Citizen> searchResults = null;

        searchResults = citizenModel.searchCitizen(query, schoolId);
        citizensToBeViewed.clear();
        citizensToBeViewed.addAll(searchResults);

        return searchResults;
    }


    /**
     * GeneralInformationModel methods
     */

    public List<GeneralInformation> getGeneralInformation() throws SQLException {
        return generalInformationModel.getGeneralInformation();
    }


    public GeneralInformation getGeneralInformationOnCitizen(int citizenID) throws SQLException {
        return generalInformationModel.getGeneralInformationOnCitizen(citizenID);
    }



    public GeneralInformation createGeneralInformation(int citizenId, String coping, String motivation, String resources, String roles,
                                                       String habits, String educationAndJob, String lifestory,
                                                       String network, String healthInformation, String equipmentAids,
                                                       String homeLayout) throws SQLException {
        return generalInformationModel.createGeneralInformation(citizenId, coping, motivation, resources, roles, habits,
                educationAndJob, lifestory, network, healthInformation, equipmentAids, homeLayout);
    }


    public void deleteGeneralInformation(int id) throws Exception {
        generalInformationModel.deleteGeneralInformation(id);
    }


    public void editGeneralInformation(GeneralInformation generalInformation) throws Exception {
        generalInformationModel.editGeneralInformation(generalInformation);
    }

    public HealthConditionSubCategoryText getTextOnSubCategory(int citizenId, int subCategoryId) throws SQLException {
        return healthConditionsModel.getTextOnSubCategory(citizenId, subCategoryId);
    }

    public List<HealthConditionSubCategoryText> getHCInfoOnSubCategories(int citizenId) throws SQLException {
        return healthConditionsModel.getInfoOnSubCategories(citizenId);
    }

    public void insertIntoSubCategory(int citizenId, int subCategoryId, String professionalnote, String currentLevelAssessment, String expectedLevelAssessment, int condition) throws SQLException {
        healthConditionsModel.insertIntoSubCategory(citizenId, subCategoryId, professionalnote, currentLevelAssessment, expectedLevelAssessment, condition);
    }

    public void editSubcategory(HealthConditionSubCategoryText subCategoryText) throws Exception {
        healthConditionsModel.editSubcategory(subCategoryText);
    }

    public HealthConditionSubCategoryText getHealthConditionData(int citizenId, int subCategoryId) throws Exception{
        return healthConditionsModel.getHealthConditionData(citizenId, subCategoryId);
    }

    /**
     * FunctionalAbilityModel Methods
     */

    public List<FunctionalAbility> getFunctionalAbilities() throws SQLException {
        return functionalAbilitiesModel.getFunctionalAbilities();
    }

    public FunctionalAbilitySubCategoryText getInfoOnSubCategory(int citizenId, int functionalAbilitySubCategoryId) throws SQLException {
        return functionalAbilitiesModel.getInfoOnSubCategory(citizenId, functionalAbilitySubCategoryId);
    }

    public List<FunctionalAbilitySubCategoryText> getFAInfoOnSubCategories(int citizenId) throws SQLException {
        return functionalAbilitiesModel.getInfoOnSubCategories(citizenId);
    }

    public List<FunctionalAbilitySubCategoryText> getFunctionalAbilitySubCategories(int functionalAbilitySubCategoryId) throws SQLException{
        return functionalAbilitiesModel.getFunctionalAbilitySubCategories(functionalAbilitySubCategoryId);
    }

    public FunctionalAbility getSubcategoryData(int citizenId, int functionalAbilitySubCategoryId) throws SQLException {
        return functionalAbilitiesModel.getSubcategoryData(citizenId, functionalAbilitySubCategoryId);
    }

    public FunctionalAbility createFunctionalAbility(int citizenId, int functionalAbilitySubCategoryId, int abilityNow, int abilityExpected, String abilityNote, String citizenPerformance, String citizenMeaningOfPerformance, String abilityNoteCitizen) throws SQLException {
        return functionalAbilitiesModel.createFunctionalAbility(citizenId, functionalAbilitySubCategoryId, abilityNow, abilityExpected, abilityNote, citizenPerformance,citizenMeaningOfPerformance, abilityNoteCitizen);
    }

    public void editAbilities(FunctionalAbility functionalAbility) throws SQLException {
        functionalAbilitiesModel.editAbilities(functionalAbility);
    }

    public void deleteAbilities(int id) throws Exception {
        functionalAbilitiesModel.deleteAbilities(id);
    }

    /**
     * ObservationNoteModel methods.
     */

    public ObservationNote getObservationNote(int citizenId) throws Exception {
        return observationNoteModel.getObservationNote(citizenId);
    }

    public ObservationNote createObservationNote(int citizenId, String date, String note) throws SQLException {
        return observationNoteModel.createObservationNote(citizenId, date, note);
    }

    public void editObservationNote(ObservationNote observationNote) throws Exception {
        observationNoteModel.editObservationNote(observationNote);
    }

    public void deleteObservationNote(int id) throws Exception {
        observationNoteModel.deleteObservationNote(id);
    }

}
