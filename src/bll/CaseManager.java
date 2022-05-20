package bll;

import be.Case;
import dal.CaseDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CaseManager {

    private CaseDAO caseDAO;

    public CaseManager() throws IOException {
        caseDAO = new CaseDAO();
    }

    /**
     * Get a list of case using the getCases method from caseDAO
     */
    public List<Case> getCases() throws SQLException {
        return caseDAO.getCases();
    }

    /**
     * Creates a case using the createCase method from caseDAO
     */
    public Case createCase(String name, String info, int schoolId) throws SQLException {
        return caseDAO.createCase(name, info, schoolId);
    }

    /**
     * Deletes a case using the deleteCase method from caseDAO
     */
    public void deleteCase(int id) throws Exception {
        caseDAO.deleteCase(id);
    }

    /**
     * Edits a case using the editCase method in caseDAO
     */
    public void editCase(Case aCase) throws Exception {
        caseDAO.editCase(aCase);
    }

    /**
     * When a case and a citizen is selected, you can assign case to citizen, by using the method from caseDAO
     * @param caseId
     * @param citizenId
     */
    public void assignCaseToCitizen(int caseId, int citizenId) throws SQLException {
        caseDAO.assignCaseToCitizen(caseId, citizenId);
    }

    /**
     * When a case and a citizen is selected, you can delete a case from citizen, by using the method from caseDAO
     * @param caseId
     * @param citizenId
     */
    public void deleteCaseFromCitizen(int caseId, int citizenId) throws SQLException {
        caseDAO.deleteCaseFromCitizen(caseId, citizenId);
    }

    /**
     * Gets assigned cases on the selected citizen, by using the method from caseDAO
     * @param citizenId
     * @return
     * @throws SQLException
     */
    public List<Case> getCasesOnCitizen(int citizenId) throws SQLException {
        return caseDAO.getCasesOnCitizen(citizenId);
    }

    /**
     * Gets a selected case on the selected citizen, by using the method from caseDAO
     * @param citizenId
     * @param casesId
     * @return
     * @throws SQLException
     */
    public Case getCaseOnCitizen(int citizenId, int casesId) throws SQLException {
        return caseDAO.getCaseOnCitizen(citizenId, casesId);
    }


}
