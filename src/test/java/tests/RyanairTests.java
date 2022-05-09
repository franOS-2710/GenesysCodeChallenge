package tests;

import core.TestBase;
import org.testng.annotations.Test;
import pages.BagsPage;
import pages.ChooseYourSeatPage;
import pages.FlightsPage;
import pages.HomePage;
import java.util.Map;

public class RyanairTests extends TestBase{

    HomePage home = null;
    FlightsPage flights = null;
    ChooseYourSeatPage seats = null;
    BagsPage bags = null;

    @Test(dataProvider="getData")
    public void BookAFlightTest(Map<String,String> data) throws Exception {

        home = new HomePage(driver());
        home.acceptCookies();
        home.isHomepageDisplayed();
        home.SetInfoToFindFlight(data.get("departureCountry"),data.get("departureAirport"),data.get("destinationCountry"),data.get("destinationAirport"),data.get("departureDate"),data.get("returnDate"));//"Portugal", "Lisbon","Spain","Barcelona","20-05-2022", "15-06-2022"

        flights = new FlightsPage(driver());
        flights.isFlightsPageDisplayed();
        flights.SelectFlights(data.get("passengerTitle1"), data.get("NameP1"), data.get("SurnameP1"), data.get("passengerTitle2"), data.get("NameP2"), data.get("SurnameP2"));//"Mrs","Franciele", "Silva", "Mr","Andre", "Silva"

        seats = new ChooseYourSeatPage(driver());
        seats.isChooseYourSeatPageDisplayed();
        seats.SelectYourSeats();

        bags = new BagsPage(driver());
        bags.isBagsPageDisplayed();
    }
}
