package gui.model;

import be.Case;
import bll.CaseManager;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CaseModel {

    private CaseManager caseManager;

    public CaseModel() throws IOException {
        caseManager = new CaseManager();
    }

    /**
     * Get a list of case using the getCases method from caseManager
     * @return
     * @throws SQLException
     */
    public List<Case> getCases() throws SQLException {
        return caseManager.getCases();
    }

    /**
     * Creates a case using the createCase method from caseManager
     * @param name
     * @param date
     * @param info
     * @return
     * @throws SQLException
     */
    public Case createCase(String name, String date, String info) throws SQLException {
        return caseManager.createCase(name, date, info);
    }

    /**
     * Deletes a case using the deleteCase method from caseManager
     * @param id
     * @throws Exception
     */
    public void deleteCase(int id) throws Exception {
         caseManager.deleteCase(id);
    }

    /**
     * Edits a case using the editCase method in caseManager
     * @param aCase
     * @throws Exception
     */
    public void editCase(Case aCase) throws Exception {
        caseManager.editCase(aCase);
    }
}
