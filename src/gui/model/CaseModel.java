package gui.model;

import be.Case;
import bll.CaseManager;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CaseModel {

    private final CaseManager caseManager;

    public CaseModel() throws IOException {
        caseManager = new CaseManager();
    }

    /**
     * Get a list of case using the getCases method from caseManager

     */
    public List<Case> getCases() throws SQLException {
        return caseManager.getCases();
    }

    /**
     * Creates a case using the createCase method from caseManager
     */
    public Case createCase(String name, String info, int schoolId) throws SQLException {
        return caseManager.createCase(name, info, schoolId);
    }

    /**
     * Deletes a case using the deleteCase method from caseManager

     */
    public void deleteCase(int id) throws Exception {
         caseManager.deleteCase(id);
    }

    /**
     * Edits a case using the editCase method in caseManager

     */
    public void editCase(Case aCase) throws Exception {
        caseManager.editCase(aCase);
    }

    /**
     * Assigns case to a citizen using the assignCaseToCitizen method from the caseManager.
     * @param caseId
     * @param citizenId
     */
    public void assignCaseToCitizen(int caseId, int citizenId){
        caseManager.assignCaseToCitizen(caseId, citizenId);
    }

    /**
     * Deletes a case from a citizen using the deleteCaseFromCitizen method from the caseManager
     * @param caseId
     * @param citizenId
     */
    public void deleteCaseFromCitizen(int caseId, int citizenId){
        caseManager.deleteCaseFromCitizen(caseId, citizenId);
    }

    /**
     * Gets a list of cases assigned to a citizen using the getCasesOnCitizen method from caseManager.
     * @param citizenId
     * @return
     * @throws SQLException
     */
    public List<Case> getCasesOnCitizen(int citizenId) throws SQLException {
        return caseManager.getCasesOnCitizen(citizenId);
    }

    /**
     * Gets a single case assigned to a citizen using the getCaseOnCitizen method from the caseManager.
     * @param citizenId
     * @param casesId
     * @return
     * @throws SQLException
     */
    public Case getCaseOnCitizen(int citizenId, int casesId) throws SQLException {
        return caseManager.getCaseOnCitizen(citizenId, casesId);
    }
}
