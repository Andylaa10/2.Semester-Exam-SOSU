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


    /**
     * Overloaded Constructor id, coping, motivation, resources, roles, habits, educationAndJob, lifeStory, network, healthInformation, equipmentAids and homeLayout
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

    /**
     * Gets the id
     * @return
     */
    public int getId() {
        return id;
    }


    /**
     * Gets the coping
     * @return
     */
    public String getCoping() {
        return coping;
    }

    /**
     * Set the coping
     * @param coping
     */
    public void setCoping(String coping) {
        this.coping = coping;
    }

    /**
     * Gets the motivation
     * @return
     */
    public String getMotivation() {
        return motivation;
    }

    /**
     * Sets the motivation
     * @param motivation
     */
    public void setMotivation(String motivation) {
        this.motivation = motivation;
    }

    /**
     * Gets the resources
     * @return
     */
    public String getResources() {
        return resources;
    }

    /**
     * Sets the resources
     * @param resources
     */
    public void setResources(String resources) {
        this.resources = resources;
    }

    /**
     * Gets the roles
     * @return
     */
    public String getRoles() {
        return roles;
    }

    /**
     * Sets the roles
     * @param roles
     */
    public void setRoles(String roles) {
        this.roles = roles;
    }

    /**
     * Gets the habits
     * @return
     */
    public String getHabits() {
        return habits;
    }

    /**
     * Sets the habits
     * @param habits
     */
    public void setHabits(String habits) {
        this.habits = habits;
    }

    /**
     * Gets the educationAndJob
     * @return
     */
    public String getEducationAndJob() {
        return educationAndJob;
    }

    /**
     * Sets the educationAndJob
     * @param educationAndJob
     */
    public void setEducationAndJob(String educationAndJob) {
        this.educationAndJob = educationAndJob;
    }

    /**
     * Gets the lifeStory
     * @return
     */
    public String getLifeStory() {
        return lifeStory;
    }

    /**
     * Sets the lifeStory
     * @param lifeStory
     */
    public void setLifeStory(String lifeStory) {
        this.lifeStory = lifeStory;
    }

    /**
     * Gets the network
     * @return
     */
    public String getNetwork() {
        return network;
    }

    /**
     * Sets the network
     * @param network
     */
    public void setNetwork(String network) {
        this.network = network;
    }

    /**
     * Gets the healthInformation
     * @return
     */
    public String getHealthInformation() {
        return healthInformation;
    }

    /**
     * Sets the healthInformation
     * @param healthInformation
     */
    public void setHealthInformation(String healthInformation) {
        this.healthInformation = healthInformation;
    }

    /**
     * Gets the equipmentAids
     * @return
     */
    public String getEquipmentAids() {
        return equipmentAids;
    }

    /**
     * Sets the equipmentAids
     * @param equipmentAids
     */
    public void setEquipmentAids(String equipmentAids) {
        this.equipmentAids = equipmentAids;
    }

    /**
     * Gets the homeLayout
     * @return
     */
    public String getHomeLayout() {
        return homeLayout;
    }

    /**
     * Sets the homeLayout
     * @param homeLayout
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
