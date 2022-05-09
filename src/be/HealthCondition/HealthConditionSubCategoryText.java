package be.HealthCondition;

import be.enums.ConditionEnum;

public class HealthConditionSubCategoryText {

    private int id;
    private int citizenId;
    private int categoryId;
    private String note;
    private ConditionEnum condition;

    public HealthConditionSubCategoryText(int id, int citizenId, int categoryId, String note, ConditionEnum condition) {
        this.id = id;
        this.citizenId = citizenId;
        this.categoryId = categoryId;
        this.note = note;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public ConditionEnum getCondition() {
        return condition;
    }

    public void setCondition(ConditionEnum condition) {
        this.condition = condition;
    }

    @Override
    public String toString() {
        return note;
    }
}
