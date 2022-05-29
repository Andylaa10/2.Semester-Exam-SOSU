package be.HealthCondition;

public class HealthConditionSubCategory {

    private int id;
    private String subCategoryName;
    private int healthConditionId;
    private int condition;

    public HealthConditionSubCategory(int id, String subCategoryName) {
        this.id = id;
        this.subCategoryName = subCategoryName;
    }

    public int getId() {
        return id;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public int getHealthConditionId() {
        return healthConditionId;
    }

    public int getCondition(HealthConditionSubCategoryText subCategoryText) {
        condition = subCategoryText.getCondition();
        return condition;
    }
}
