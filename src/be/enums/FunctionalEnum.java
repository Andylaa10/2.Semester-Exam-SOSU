package be.enums;

public enum FunctionalEnum {
    /**
     * These values are based on functional abilities
     */
    NO_LIMIT(0), LIGHT_LIMIT(1),
    MODERATE_LIMIT(2), SEVERE_LIMIT(3),
    TOTAL_LIMIT(4), NOT_RELEVANT(9);

    private final int value;

    FunctionalEnum(int newValue) {
        value = newValue;
    }

    public int getValue() {
        return value;
    }
}
