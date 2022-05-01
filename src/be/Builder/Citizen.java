package be.Builder;

// https://www.youtube.com/watch?v=xy6FUwftz1Q&ab_channel=PhilipStarritt
public class Citizen {
    private int id;
    private String firstName;
    private String lastName;
    private PersonalDetails personalDetails;
    private GeneralInformation generalInformation;


    public Citizen(Builder builder) {
        this.id = builder.id;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.personalDetails = builder.personalDetails;
        this.generalInformation = builder.generalInformation;
    }

    public static class Builder{

        private int id;
        private String firstName;
        private String lastName;
        private PersonalDetails personalDetails;
        private GeneralInformation generalInformation;

        public Builder id(final int id){
            this.id = id;
            return this;
        }

        public Builder firstName(final String firstName){
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(final String lastName){
            this.lastName = lastName;
            return this;
        }

        public Builder personalDetails(final PersonalDetails personalDetails){
            this.personalDetails = personalDetails;
            return this;
        }

        public Builder generalInformation(final GeneralInformation generalInformation){
            this.generalInformation = generalInformation;
            return this;
        }

        public Citizen build(){
            return new Citizen(this);
        }
    }
}
