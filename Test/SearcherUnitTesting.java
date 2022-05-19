import be.Citizen;
import bll.utilities.CitizenSearcher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class SearcherUnitTesting {

    @DisplayName("Test user search on user name")
    @Test
    void userSearch(){
        //Triple A pattern
        //Arrange - set up our test object etc.
        //Make a list of Users
        List<Citizen> searchData = new ArrayList<>();
        //Make an instance of EventSearcher
        CitizenSearcher searcher = new CitizenSearcher();

        //Making data to insert into searchData list
        Citizen citizen1 = new Citizen(1, "John", "Johnson", "121200-4673", "Allé vej 32", "male");
        Citizen citizen2 = new Citizen(2, "Jonas", "Johansen", "020301-1243", "Allé vej 32", "male");
        Citizen citizen3 = new Citizen(3, "Jena", "Andersen", "0602-1522", "Bulgursvej 124", "female");
        Citizen citizen4 = new Citizen(4, "Andy", "Lam", "230301-2577", "Spangsbjergs allé 2", "male");
        Citizen citizen5 = new Citizen(5, "Kristian", "Iversen", "171198-2221", "Håndsprit vej 1", "male");
        //Added data
        searchData.add(citizen1);
        searchData.add(citizen2);
        searchData.add(citizen3);
        searchData.add(citizen4);
        searchData.add(citizen5);
        // Act - do the actual cal or method run
        int expected = 2;
        int actual = searcher.searchCitizen(searchData, "jo").size();
        // Assert - check if actual value is equal to expected value
        Assertions.assertEquals(expected, actual);
    }

    @DisplayName("Test event search on citizen ssn")
    @Test
    void locationSearch(){
        //Triple A pattern
        //Arrange - set up our test object etc.
        //Make a list of Events
        List<Citizen> searchData = new ArrayList<>();
        //Make an instance of EventSearcher
        CitizenSearcher searcher = new CitizenSearcher();
        //Making data to insert into searchData list
        Citizen citizen1 = new Citizen(1, "John", "Johnson", "121200-4673", "Allé vej 32", "male");
        Citizen citizen2 = new Citizen(2, "Jonas", "Johansen", "020301-1243", "Allé vej 32", "male");
        Citizen citizen3 = new Citizen(3, "Jena", "Andersen", "0602-1522", "Bulgursvej 124", "female");
        Citizen citizen4 = new Citizen(4, "Andy", "Lam", "230301-2577", "Spangsbjergs allé 2", "male");
        Citizen citizen5 = new Citizen(5, "Kristian", "Iversen", "171198-2221", "Håndsprit vej 1", "male");
        //Added data
        searchData.add(citizen1);
        searchData.add(citizen2);
        searchData.add(citizen3);
        searchData.add(citizen4);
        searchData.add(citizen5);
        // Act - do the actual cal or method run
        int expected = 1;
        int actual = searcher.searchCitizen(searchData, "1522").size();
        // Assert - check if actual value is equal to expected value
        Assertions.assertEquals(expected, actual);
    }

}
