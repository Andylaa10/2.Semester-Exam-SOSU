package be.HealthCondition;

public class HealthConditionSubCategoryText {

    private int id;
    private int citizenId;
    private int categoryId;
    private String professionalNote;
    private String currentLevelAssessment;
    private String expectedLevelAssessment;
    private int condition;

    public HealthConditionSubCategoryText(int id, int citizenId, int categoryId, String note, int condition) {
        this.id = id;
        this.citizenId = citizenId;
        this.categoryId = categoryId;
        this.professionalNote = note;
        this.condition = condition;
    }

    public HealthConditionSubCategoryText(int citizenId, int subCategoryId, String note, int conditionValue) {
        this.citizenId =  citizenId;
        this.categoryId = subCategoryId;
        this.professionalNote = note;
        this.condition = conditionValue;
    }

    public HealthConditionSubCategoryText(int id, int citId, int subId, String professionalNote, String currentLevelAssessment, String expectedLevelAssessment, int condition) {
        this.id = id;
        this.citizenId = citId;
        this.categoryId = subId;
        this.professionalNote = professionalNote;
        this.currentLevelAssessment = currentLevelAssessment;
        this.expectedLevelAssessment = expectedLevelAssessment;
        this.condition = condition;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCitizenId() {
        return citizenId;
    }

    public void setCitizenId(int citizenId) {
        this.citizenId = citizenId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getProfessionalNote() {
        return professionalNote;
    }

    public void setProfessionalNote(String professionalNote) {
        this.professionalNote = professionalNote;
    }

    public int getCondition() {
        return condition;
    }

    public void setCondition(int condition) {
        this.condition = condition;
    }

    @Override
    public String toString() {
        return professionalNote;
    }

    public String getCurrentLevelAssessment() {
        return currentLevelAssessment;
    }

    public void setCurrentLevelAssessment(String currentLevelAssessment) {
        this.currentLevelAssessment = currentLevelAssessment;
    }

    public String getExpectedLevelAssessment() {
        return expectedLevelAssessment;
    }

    public void setExpectedLevelAssessment(String expectedLevelAssessment) {
        this.expectedLevelAssessment = expectedLevelAssessment;
    }
}
