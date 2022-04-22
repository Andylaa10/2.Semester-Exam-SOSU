package be.enums;

public enum LimitationValue {
    NoLimit(0), LightLimit(1), ModerateLimit(2), SevereLimit(3), TotalLimit(4), NotRelevant(9);
    private final int value;

    private LimitationValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }


}
