package be.enums;

public enum ConditionEnum {
    /**
     * These values are based on functional abilities
     */
    NOT_RELEVANT(0), POTENTIAL(1),
    RELEVANT(2);

    private final int value;

    ConditionEnum(int newValue) {
        value = newValue;
    }

    public int getValue() {
        return value;
    }
}
