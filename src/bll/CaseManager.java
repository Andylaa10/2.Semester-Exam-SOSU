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
     * @return
     * @throws SQLException
     */
    public List<Case> getCases() throws SQLException {
        return caseDAO.getCases();
    }

    /**
     * Creates a case using the createCase method from caseDAO
     * @param name
     * @param info
     * @return
     * @throws SQLException
     */
    public Case createCase(String name, String info) throws SQLException {
        return caseDAO.createCase(name, info);
    }

    /**
     * Deletes a case using the deleteCase method from caseDAO
     * @param id
     * @throws Exception
     */
    public void deleteCase(int id) throws Exception {
        caseDAO.deleteCase(id);
    }

    /**
     * Edits a case using the editCase method in caseDAO
     * @param aCase
     * @throws Exception
     */
    public void editCase(Case aCase) throws Exception {
        caseDAO.editCase(aCase);
    }


}
