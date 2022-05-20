package bll.utilities;

import be.Citizen;

import java.util.ArrayList;
import java.util.List;

public class CitizenSearcher {

    /**
     * Method for searching an event with the searchCitizen button.
     */
    public List<Citizen> searchCitizen(List<Citizen> searchBase, String query) {
        List<Citizen> searchResult = new ArrayList<>();

        for (Citizen citizen : searchBase) {
            if (compareToCitizenFirstName(query, citizen) | compareToCitizenLastName(query, citizen) | compareToCitizenSSN(query, citizen)) {
                searchResult.add(citizen);
            }
        }
        return searchResult;
    }

    private boolean compareToCitizenFirstName(String query, Citizen citizen) {
        return citizen.getFirstName().toLowerCase().contains(query.toLowerCase());
    }

    private boolean compareToCitizenLastName(String query, Citizen citizen) {
        return citizen.getLastName().toLowerCase().contains(query.toLowerCase());
    }

    private boolean compareToCitizenSSN(String query, Citizen citizen) {
        return citizen.getSSN().toLowerCase().contains(query.toLowerCase());
    }


}
