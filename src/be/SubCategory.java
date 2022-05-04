package be;

public class SubCategory {
    private int id;
    private String subCategoryName;
    private int healthConditionId;

    public SubCategory(int id, String subCategoryName) {
        this.id = id;
        this.subCategoryName = subCategoryName;
    }

    public SubCategory(int id, String subCategoryName, int healthConditionId) {
        this.id = id;
        this.subCategoryName = subCategoryName;
        this.healthConditionId = healthConditionId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    public int getHealthConditionId() {
        return healthConditionId;
    }

    public void setHealthConditionId(int healthConditionId) {
        this.healthConditionId = healthConditionId;
    }
}
