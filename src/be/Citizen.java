package be;

import be.enums.HealthCondition;
import be.enums.LimitationValue;
import be.enums.Personal;

public class Citizen {
    private int id;
    private String name;
    private int age;
    private LimitationValue limitValue;
    private HealthCondition health;
    private Personal personal;

    public Citizen(int id, String name, int age, LimitationValue limitValue, HealthCondition health, Personal personal) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.limitValue = limitValue;
        this.health = health;
        this.personal = personal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public LimitationValue getLimitValue() {
        return limitValue;
    }

    public void setLimitValue(LimitationValue limitValue) {
        this.limitValue = limitValue;
    }

    public HealthCondition getHealth() {
        return health;
    }

    public void setHealth(HealthCondition health) {
        this.health = health;
    }

    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }
}
