package be;

import be.enums.FunctionalEnum;

public class FunctionalAbilities {

    private int id;
    private FunctionalEnum abilityNow;
    private FunctionalEnum abilityExpected;

    public FunctionalAbilities(int id, FunctionalEnum abilityNow, FunctionalEnum abilityExpected) {
        this.id = id;
        this.abilityNow = abilityNow;
        this.abilityExpected = abilityExpected;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public FunctionalEnum getAbilityNow() {
        return abilityNow;
    }

    public void setAbilityNow(FunctionalEnum abilityNow) {
        this.abilityNow = abilityNow;
    }

    public FunctionalEnum getAbilityExpected() {
        return abilityExpected;
    }

    public void setAbilityExpected(FunctionalEnum abilityExpected) {
        this.abilityExpected = abilityExpected;
    }
}
