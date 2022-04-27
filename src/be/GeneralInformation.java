package be;

public class GeneralInformation {
    private int id;
    private String coping;
    private String motivation;
    private String resources;
    private String roles;
    private String habits;
    private String educationAndJob;
    private String lifeStory;
    private String network;
    private String healthInformation;
    private String equipmentAids;
    private String homeLayout;

    public GeneralInformation() {
    }

    public GeneralInformation(int id, String coping, String motivation, String resources, String roles, String habits, String educationAndJob, String lifeStory, String network, String healthInformation, String equipmentAids, String homeLayout) {
        this.id = id;
        this.coping = coping;
        this.motivation = motivation;
        this.resources = resources;
        this.roles = roles;
        this.habits = habits;
        this.educationAndJob = educationAndJob;
        this.lifeStory = lifeStory;
        this.network = network;
        this.healthInformation = healthInformation;
        this.equipmentAids = equipmentAids;
        this.homeLayout = homeLayout;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCoping() {
        return coping;
    }

    public void setCoping(String coping) {
        this.coping = coping;
    }

    public String getMotivation() {
        return motivation;
    }

    public void setMotivation(String motivation) {
        this.motivation = motivation;
    }

    public String getResources() {
        return resources;
    }

    public void setResources(String resources) {
        this.resources = resources;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getHabits() {
        return habits;
    }

    public void setHabits(String habits) {
        this.habits = habits;
    }

    public String getEducationAndJob() {
        return educationAndJob;
    }

    public void setEducationAndJob(String educationAndJob) {
        this.educationAndJob = educationAndJob;
    }

    public String getLifeStory() {
        return lifeStory;
    }

    public void setLifeStory(String lifeStory) {
        this.lifeStory = lifeStory;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public String getHealthInformation() {
        return healthInformation;
    }

    public void setHealthInformation(String healthInformation) {
        this.healthInformation = healthInformation;
    }

    public String getEquipmentAids() {
        return equipmentAids;
    }

    public void setEquipmentAids(String equipmentAids) {
        this.equipmentAids = equipmentAids;
    }

    public String getHomeLayout() {
        return homeLayout;
    }

    public void setHomeLayout(String homeLayout) {
        this.homeLayout = homeLayout;
    }
}
