package be;

public class GeneralInformation {
    private int id;
    private int citizenId;
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


    /**
     * Overloaded Constructor
     */
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

    public GeneralInformation(int id, int citizenId, String coping, String motivation, String resources, String roles, String habits, String educationAndJob, String lifeStory, String network, String healthInformation, String equipmentAids, String homeLayout) {
        this.id = id;
        this.citizenId = citizenId;
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

    public void setId(int id) {
        this.id = id;
    }

    public int getCitizenId() {
        return citizenId;
    }

    public void setCitizenId(int citizenId) {
        this.citizenId = citizenId;
    }

    /**
     * Gets the id
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the coping
     */
    public String getCoping() {
        return coping;
    }

    /**
     * Set the coping
     */
    public void setCoping(String coping) {
        this.coping = coping;
    }

    /**
     * Gets the motivation
     */
    public String getMotivation() {
        return motivation;
    }

    /**
     * Sets the motivation
     */
    public void setMotivation(String motivation) {
        this.motivation = motivation;
    }

    /**
     * Gets the resources
     */
    public String getResources() {
        return resources;
    }

    /**
     * Sets the resources
     */
    public void setResources(String resources) {
        this.resources = resources;
    }

    /**
     * Gets the roles
     */
    public String getRoles() {
        return roles;
    }

    /**
     * Sets the roles
     */
    public void setRoles(String roles) {
        this.roles = roles;
    }

    /**
     * Gets the habits
     */
    public String getHabits() {
        return habits;
    }

    /**
     * Sets the habits
     */
    public void setHabits(String habits) {
        this.habits = habits;
    }

    /**
     * Gets the educationAndJob
     */
    public String getEducationAndJob() {
        return educationAndJob;
    }

    /**
     * Sets the educationAndJob
     */
    public void setEducationAndJob(String educationAndJob) {
        this.educationAndJob = educationAndJob;
    }

    /**
     * Gets the lifeStory
     */
    public String getLifeStory() {
        return lifeStory;
    }

    /**
     * Sets the lifeStory
     */
    public void setLifeStory(String lifeStory) {
        this.lifeStory = lifeStory;
    }

    /**
     * Gets the network
     */
    public String getNetwork() {
        return network;
    }

    /**
     * Sets the network
     */
    public void setNetwork(String network) {
        this.network = network;
    }

    /**
     * Gets the healthInformation
     */
    public String getHealthInformation() {
        return healthInformation;
    }

    /**
     * Sets the healthInformation
     */
    public void setHealthInformation(String healthInformation) {
        this.healthInformation = healthInformation;
    }

    /**
     * Gets the equipmentAids
     */
    public String getEquipmentAids() {
        return equipmentAids;
    }

    /**
     * Sets the equipmentAids
     */
    public void setEquipmentAids(String equipmentAids) {
        this.equipmentAids = equipmentAids;
    }

    /**
     * Gets the homeLayout
     */
    public String getHomeLayout() {
        return homeLayout;
    }

    /**
     * Sets the homeLayout
     */
    public void setHomeLayout(String homeLayout) {
        this.homeLayout = homeLayout;
    }

    @Override
    public String toString() {
        return "GeneralInformation{" +
                "id=" + id +
                ", coping='" + coping + '\'' +
                ", motivation='" + motivation + '\'' +
                ", resources='" + resources + '\'' +
                ", roles='" + roles + '\'' +
                ", habits='" + habits + '\'' +
                ", educationAndJob='" + educationAndJob + '\'' +
                ", lifeStory='" + lifeStory + '\'' +
                ", network='" + network + '\'' +
                ", healthInformation='" + healthInformation + '\'' +
                ", equipmentAids='" + equipmentAids + '\'' +
                ", homeLayout='" + homeLayout + '\'' +
                '}';
    }
}
