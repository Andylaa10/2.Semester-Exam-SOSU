package gui.Facade;

import be.*;
import be.FunctionalAbilities.FunctionalAbilitySubCategoryText;
import be.FunctionalAbilities.FunctionalAbility;
import be.HealthCondition.HealthCondition;
import be.HealthCondition.HealthConditionSubCategory;
import be.HealthCondition.HealthConditionSubCategoryText;
import be.enums.UserType;
import com.microsoft.sqlserver.jdbc.SQLServerException;
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


    public DataModelFacade() throws IOException, SQLException {
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
     * Get a list of students using the getStudents method from userModel
     */
    public List<User> getStudents() throws SQLException {
        return userModel.getStudents();
    }

    public User getHashedPassword(String userName, String password, int schoolId) throws SQLException {
        return userModel.getHashedPassword(userName, password, schoolId);
    }

    /**
     * Get a list of teachers using the getTeachers method from userModel
     */
    public List<User> getTeachers() throws SQLException {
        return userModel.getTeachers();
    }

    /**
     * Get a list of admins using the getAdmins method from userModel
     */
    public List<User> getAdmins() throws SQLException {
        return userModel.getAdmins();
    }

    public List<HealthCondition> getHealthConditions() throws SQLException {
        return healthConditionsModel.getHealthConditions();
    }

    public List<HealthConditionSubCategory> getSubCategories(int categoryId) throws SQLException {
        return healthConditionsModel.getSubCategories(categoryId);
    }

    /**
     * Creates a student using the createStudent method from userModel
     */
    public User createStudent(String firstName, String lastName, String username, String password, UserType userType, int schoolId) throws SQLException {
        return userModel.createStudent(firstName, lastName, username, password, userType, schoolId);
    }

    /**
     * Creates a teacher using the createTeacher method from userModel
     */
    public User createTeacher(String firstName, String lastName, String username, String password, UserType userType, int schoolId) throws SQLException {
        return userModel.createTeacher(firstName, lastName, username, password, userType, schoolId);
    }

    /**
     * Creates an admin using the createAdmin method from userModel
     */
    public User createAdmin(String firstName, String lastName, String username, String password, UserType userType, int schoolId) throws SQLException {
        return userModel.createAdmin(firstName, lastName, username, password, userType, schoolId);
    }

    /**
     * Create a super admin using the createSuperAdmin method from superAdminModel
     */
    public SuperAdmin createSuperAdmin(String username, String password) throws SQLException {
        return superAdminModel.createSuperAdmin(username, password);
    }

    /**
     * Deletes a student using the deleteStudent method from userModel
     */
    public void deleteStudent(int id, UserType userType) throws SQLException {
        userModel.deleteStudent(id, userType);
    }

    /**
     * Deletes a teacher using the deleteTeacher method from userModel
     */
    public void deleteTeacher(int id, UserType userType) throws SQLException {
        userModel.deleteTeacher(id, userType);
    }

    /**
     * Deletes an admin using the deleteAdmin method from userModel
     */
    public void deleteAdmin(int id, UserType userType) throws SQLException {
        userModel.deleteAdmin(id, userType);
    }

    /**
     * Deletes a super admin using the deleteSuperAdmin method from superAdminModel
     */
    public void deleteSuperAdmin(int id) throws SQLException {
        superAdminModel.deleteSuperAdmin(id);
    }

    /**
     * Edits a student using the editStudent method from userModel
     */
    public void editStudent(User student) throws Exception {
        userModel.editStudent(student);
    }

    /**
     * Edits a teacher using the editTeacher method from userModel
     */
    public void editTeacher(User teacher) throws Exception {
        userModel.editTeacher(teacher);
    }

    /**
     * Edits an admin using the editAdmin method from userModel
     */
    public void editAdmin(User admin) throws Exception {
        userModel.editAdmin(admin);
    }

    /**
     * Edits a super admin using the editSuperAdmin method from superAdminModel
     */
    public void editSuperAdmin(SuperAdmin superAdmin) throws Exception {
        superAdminModel.editSuperAdmin(superAdmin);
    }

    /**
     * Gets the user userLogin using the userLogin method from userModel
     */
    public User userLogin(String username, String password, int schoolId){
        return userModel.userLogin(username, password, schoolId);
    }

    /**
     * Gets the super admin login using the superAdminLogin method from superAdminModel
     */
    public SuperAdmin superAdminLogin(String username, String password) throws SQLException {
        return superAdminModel.superAdminLogin(username, password);
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

    /**
     * Creates a school using the createSchool method from superAdminModel
     */
    public School createSchool(String schoolName) throws SQLException {
        return superAdminModel.createSchool(schoolName);
    }

    /**
     * Deletes a school using the deleteSchool method from superAdminModel
     */
    public void deleteSchool(int id) throws SQLException {
        superAdminModel.deleteSchool(id);
    }

    /**
     * Edits a school using the editSchool method from superAdminModel
     */
    public void editSchool(School school) throws Exception {
        superAdminModel.editSchool(school);
    }

    /**
     * Get a list of case using the getCases method from caseModel
     */
    public List<Case> getCases() throws SQLException {
        return caseModel.getCases();
    }

    /**
     * Creates a case using the createCase method from caseModel
     */
    public Case createCase(String name, String info, int schoolId) throws SQLException {
        return caseModel.createCase(name, info, schoolId);
    }

    /**
     * Deletes a case using the deleteCase method from caseModel
     */
    public void deleteCase(int id) throws Exception {
        caseModel.deleteCase(id);
    }

    /**
     * Edits a case using the editCase method in caseModel
     */
    public void editCase(Case aCase) throws Exception {
        caseModel.editCase(aCase);
    }

    public void assignCaseToCitizen(int caseId, int citizenId){
        caseModel.assignCaseToCitizen(caseId, citizenId);
    }

    public void deleteCaseFromCitizen(int caseId, int citizenId){
        caseModel.deleteCaseFromCitizen(caseId, citizenId);
    }

    public List<Case> getCasesOnCitizen(int citizenId) throws SQLException {
        return caseModel.getCasesOnCitizen(citizenId);
    }
    public Case getCaseOnCitizen(int citizenId, int casesId) throws SQLException {
        return caseModel.getCaseOnCitizen(citizenId, casesId);
    }

    /**
     * Get a list of citizen using the getCitizens method from citizenModel
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

    /**
     * Creates a case using the createCitizen method from citizenModel
     */
    public Citizen createCitizen(String firstname, String lastName, String SSN, String address, String sex, int schoolId) throws SQLException {
        return citizenModel.createCitizen(firstname, lastName, SSN, address, sex, schoolId);
    }

    /**
     * Deletes a citizen using the deleteCitizen method from citizenModel
     */
    public void deleteCitizen(int citizenID) throws Exception {
        citizenModel.deleteCitizen(citizenID);
    }

    /**
     * Edits a citizen using the editCitizen method in citizenModel
     */
    public void editCitizen(Citizen citizen) throws Exception {
        citizenModel.editCitizen(citizen);
    }

    public List<User> getAdminsOnSchool(int schoolId) throws SQLException {
        return superAdminModel.getAdminsOnSchool(schoolId);
    }

    public List<Citizen> getAssignedCitizen(int schoolId){
        return superAdminModel.getAssignedCitizen(schoolId);
    }
    public List<Case> getAssignedCases(int schoolId){
        return superAdminModel.getAssignedCases(schoolId);
    }

    public void addAdminToSchool(int loginId, int schoolId) {
        superAdminModel.addAdminToSchool(loginId, schoolId);
    }

    public void deleteAdminFromSchool(int userId, int schoolId) {
        superAdminModel.deleteAdminFromSchool(userId, schoolId);
    }

    public SuperAdmin getHashedPasswordSuperAdmin(String username, String password) throws SQLException {
        return superAdminModel.getHashedPasswordSuperAdmin(username, password);
    }


    /**
     * Get a list of generalInformation using the getgeneralInformations method from generalInformationManager
     */
    public List<GeneralInformation> getGeneralInformation() throws SQLException {
        return generalInformationModel.getGeneralInformation();
    }

    /**
     * Gets a list of generalInformation that is assigned to citizen, using the getGeneralInformationsOnCitizen
     * method from generalInformationManager
     */
    public GeneralInformation getGeneralInformationOnCitizen(int citizenID) throws SQLException {
        return generalInformationModel.getGeneralInformationOnCitizen(citizenID);
    }


    /**
     * Creates  generalInformation using the createGeneralInformation method from generalInformationManager
     */
    public GeneralInformation createGeneralInformation(int citizenId, String coping, String motivation, String resources, String roles,
                                                       String habits, String educationAndJob, String lifestory,
                                                       String network, String healthInformation, String equipmentAids,
                                                       String homeLayout) throws SQLException {
        return generalInformationModel.createGeneralInformation(citizenId, coping, motivation, resources, roles, habits,
                educationAndJob, lifestory, network, healthInformation, equipmentAids, homeLayout);
    }

    /**
     * Deletes generalInformation using the deleteGeneralInformation method from generalInformationManager
     */
    public void deleteGeneralInformation(int id) throws Exception {
        generalInformationModel.deleteGeneralInformation(id);
    }

    /**
     * Edits generalInformation using the editGeneralInformation method in generalInformationManager
     */
    public void editGeneralInformation(GeneralInformation generalInformation) throws Exception {
        generalInformationModel.editGeneralInformation(generalInformation);
    }

    public HealthConditionSubCategoryText getTextOnSubCategory(int citizenId, int subCategoryId) throws SQLServerException {
        return healthConditionsModel.getTextOnSubCategory(citizenId, subCategoryId);
    }

    public List<HealthConditionSubCategoryText> getHCInfoOnSubCategories(int citizenId){
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

    public List<FunctionalAbility> getFunctionalAbilities() throws SQLException {
        return functionalAbilitiesModel.getFunctionalAbilities();
    }

    public FunctionalAbilitySubCategoryText getInfoOnSubCategory(int citizenId, int functionalAbilitySubCategoryId) throws SQLServerException {
        return functionalAbilitiesModel.getInfoOnSubCategory(citizenId, functionalAbilitySubCategoryId);
    }

    public List<FunctionalAbilitySubCategoryText> getFAInfoOnSubCategories(int citizenId) throws SQLServerException{
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

    public List<Citizen> searchCitizen(String query, int schoolId) throws SQLException {
        List<Citizen> searchResults = null;

        searchResults = citizenModel.searchCitizen(query, schoolId);
        citizensToBeViewed.clear();
        citizensToBeViewed.addAll(searchResults);

        return searchResults;
    }

}
