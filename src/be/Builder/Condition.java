package be.Builder;

public enum Condition {
    NOT_RELEVANT(0), POTENTIAL(1), ACTUAL(2);

    private final int value;

    Condition(int newValue) {
        value = newValue;
    }

    public int getValue() { return value; }
}
