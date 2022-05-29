package be.HealthCondition;

public class HealthConditionSubCategoryText {

    private int id;
    private int citizenId;
    private int categoryId;
    private int healthConditionId;
    private String professionalNote;
    private String currentLevelAssessment;
    private String expectedLevelAssessment;
    private int condition;

    public HealthConditionSubCategoryText(int id, int citId, int subId, String professionalNote, String currentLevelAssessment, String expectedLevelAssessment, int condition) {
        this.id = id;
        this.citizenId = citId;
        this.categoryId = subId;
        this.professionalNote = professionalNote;
        this.currentLevelAssessment = currentLevelAssessment;
        this.expectedLevelAssessment = expectedLevelAssessment;
        this.condition = condition;
    }

    public HealthConditionSubCategoryText(int id, int citId, int subCatId, String professionalNote, String currentLevelAssessment, String expectedLevelAssessment, int condition, int healthConditionId) {
        this.id = id;
        this.citizenId = citId;
        this.categoryId = subCatId;
        this.professionalNote = professionalNote;
        this.currentLevelAssessment = currentLevelAssessment;
        this.expectedLevelAssessment = expectedLevelAssessment;
        this.condition = condition;
        this.healthConditionId = healthConditionId;
    }

    public HealthConditionSubCategoryText(int citizenId, int subCategoryId, String professionalNote, String currentLevelAssessment, String expectedLevelAssessment, int conditionValue) {
        this.citizenId = citizenId;
        this.categoryId = subCategoryId;
        this.professionalNote = professionalNote;
        this.currentLevelAssessment = currentLevelAssessment;
        this.expectedLevelAssessment = expectedLevelAssessment;
        this.condition = conditionValue;
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


    public int getHealthConditionId() {
        return healthConditionId;
    }


    public String getProfessionalNote() {
        return professionalNote;
    }


    public String getCurrentLevelAssessment() {
        return currentLevelAssessment;
    }


    public String getExpectedLevelAssessment() {
        return expectedLevelAssessment;
    }


    public int getCondition() {
        return condition;
    }

    @Override
    public String toString() {
        return "HealthConditionSubCategoryText{" +
                "id=" + id +
                ", citizenId=" + citizenId +
                ", categoryId=" + categoryId +
                ", healthConditionId=" + healthConditionId +
                ", professionalNote='" + professionalNote + '\'' +
                ", currentLevelAssessment='" + currentLevelAssessment + '\'' +
                ", expectedLevelAssessment='" + expectedLevelAssessment + '\'' +
                ", condition=" + condition +
                '}';
    }
}
