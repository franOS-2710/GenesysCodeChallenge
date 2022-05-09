package pages;

import core.TestConfig;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class HomePage extends BasePage{

    //Locators for elements on the page
    @FindBy(xpath="//button[@class='cookie-popup-with-overlay__button']")
    private WebElement btnAgreeCookies;

    @FindBy(xpath = "//fsw-input-button[@uniqueid='departure']")
    private WebElement btnDeparture;

    @FindBy(xpath = "//calendar[@class='datepicker__calendar datepicker__calendar--left']/div[@class='calendar__month-name']")
    private WebElement datePickerMonthTitle;

    @FindBy(xpath = "//div[@class='datepicker__arrow-wrap']")
    private WebElement datePickerNextButton;

    @FindBy(xpath = "//ry-counter[@data-ref='passengers-picker__adults']//div[@data-ref='counter.counter__increment']")
    private WebElement btnIncrementAdult;

    @FindBy(xpath="//button/span[contains(text(),'Search')]")
    private WebElement btnSearch;

    private WebDriver driver = null;

    //Constructor to Initialize the Page Factory
    public HomePage(WebDriver driver) throws Exception{
        super(driver);
        this.driver = driver;
        driver.navigate().to(TestConfig.getProperty("appBaseURL"));
        if(!isHomepageDisplayed()) throw new Exception("Homepage not displayed");
    }

    //Actions to be performed in Home Page
    public boolean isHomepageDisplayed() {
        waitForElement(btnDeparture, 30 );
        return btnDeparture.isDisplayed();
    }

    public void acceptCookies() throws Exception {
        if(waitForAlert(2)) {
            Alert alert = driver.switchTo().alert();
            alert.accept();
        }
        if(waitForElement(btnAgreeCookies, 1)) {
            btnAgreeCookies.click();
        }
    }

    public void SetInfoToFindFlight(String departureCountry, String departureAirport, String destinationCountry, String destinationAirport, String departureDate, String returnDate ) throws Exception{
        btnDeparture.click();
        waitForElement(driver.findElement(By.xpath("//span[contains(text(),'"+departureCountry+"')]/parent::div[@role='button']")), 20);
        driver.findElement(By.xpath("//span[contains(text(),'"+departureCountry+"')]/parent::div[@role='button']")).click();
        waitForElement(driver.findElement(By.xpath("//span[contains(text(),'"+departureAirport+"')]/ancestor::fsw-airport-item[@role='button']")), 20);
        driver.findElement(By.xpath("//span[contains(text(),'"+departureAirport+"')]/ancestor::fsw-airport-item[@role='button']")).click();
        waitForElement(driver.findElement(By.xpath("//span[contains(text(),'"+destinationCountry+"')]/parent::div[@role='button']")), 20);
        driver.findElement(By.xpath("//span[contains(text(),'"+destinationCountry+"')]/parent::div[@role='button']")).click();
        waitForElement(driver.findElement(By.xpath("//span[contains(text(),'"+destinationAirport+"')]/ancestor::fsw-airport-item[@role='button']")), 20);
        driver.findElement(By.xpath("//span[contains(text(),'"+destinationAirport+"')]/ancestor::fsw-airport-item[@role='button']")).click();
        HandleCalendars(departureDate);
        HandleCalendars(returnDate);
        waitForElement(btnIncrementAdult, 20);
        btnIncrementAdult.click();
        btnSearch.click();
    }

    public void HandleCalendars(String date) throws Exception {
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        String expectedMonth = localDate.getMonth().name().toUpperCase();
        String expectedYear = String.valueOf(localDate.getYear());
        String expectedDay = String.valueOf(localDate.getDayOfMonth());
        String currentMonthAndYear = datePickerMonthTitle.getText();
        String[] splitText = currentMonthAndYear.split(" ");
        String currentMonth = splitText[0];
        String currentYear = splitText[1];

        //Month Navigation
        while (!expectedMonth.equals(currentMonth.toUpperCase()) && expectedYear.equals(currentYear)) {
            datePickerNextButton.click();
            Thread.sleep(3000);
            currentMonthAndYear = datePickerMonthTitle.getText();
            splitText = currentMonthAndYear.split(" ");
            currentMonth = splitText[0];
            currentYear = splitText[1];
        }

        driver.findElement(By.xpath("//calendar[@class='datepicker__calendar datepicker__calendar--left']//div[@data-value='"+expectedDay+"']")).click();
    }
}
