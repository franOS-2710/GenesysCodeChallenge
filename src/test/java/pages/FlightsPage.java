package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FlightsPage extends BasePage{

    //Locators for elements on the page
    @FindBy(xpath="//journey-container[@data-ref='outbound']//button[contains(text(),'Select')]")
    private WebElement btnSelectDepartureFlight;

    @FindBy(xpath = "//journey-container[@data-ref='inbound']//button[contains(text(),'Select')]")
    private WebElement btnSelectReturnFlight;

    @FindBy(xpath = "//h2[contains(text(),'Regular')]/parent::div//button")
    private WebElement btnRegular;

    @FindBy(xpath = "//h3[contains(text(),'Log in to')]")
    private WebElement titleLogInToMyRyanair;

    @FindBy(xpath = "//h3[contains(text(),'Passengers')]")
    private WebElement titlePassengers;

    @FindBy(xpath = "//span[contains(text(),'Log in later')]")
    private WebElement linkLoginLater;

    @FindBy(xpath = "//span[contains(text(),'Passenger 1')]/ancestor::div[@class='passenger']//div[@class='dropdown b2']")
    private WebElement btnTitlePassenger1;

    @FindBy(xpath = "//div[@class='dropdown-item__label b2']")
    private WebElement ddlTitlePassenger;

    @FindBy(xpath = "//input[@name='form.passengers.ADT-0.name']")
    private WebElement txtFirstNamePassenger1;

    @FindBy(xpath = "//input[@name='form.passengers.ADT-0.surname']")
    private WebElement txtLastNamePassenger1;

    @FindBy(xpath = "//span[contains(text(),'Passenger 2')]/ancestor::div[@class='passenger']//div[@class='dropdown b2']")
    private WebElement btnTitlePassenger2;

    @FindBy(xpath = "//input[@name='form.passengers.ADT-1.name']")
    private WebElement txtFirstNamePassenger2;

    @FindBy(xpath = "//input[@name='form.passengers.ADT-1.surname']")
    private WebElement txtLastNamePassenger2;

    @FindBy(xpath = "//button[contains(text(),'Continue')]")
    private WebElement btnContinue;

    private WebDriver driver = null;

    //Constructor to Initialize the Page Factory
    public FlightsPage(WebDriver driver) throws Exception{
        super(driver);
        this.driver = driver;
        if(!isFlightsPageDisplayed()) throw new Exception("Flights page not displayed");
    }

    //Actions to be performed in Flights Page
    public boolean isFlightsPageDisplayed() {
        waitForElement(btnSelectDepartureFlight, 30 );
        return btnSelectDepartureFlight.isDisplayed();
    }

    public void ClickSelectDepartureFlight() throws Exception{
        waitForElement(btnSelectDepartureFlight, 20);
        btnSelectDepartureFlight.click();
    }

    public void ClickSelectReturnFlight() throws Exception{
        waitForElement(btnSelectReturnFlight, 20);
        btnSelectReturnFlight.click();
    }

    public void ClickLoginLater() throws Exception{
        waitForElement(linkLoginLater, 20);
        linkLoginLater.click();
    }

    public void ClickContinue() throws Exception{
        waitForElement(btnContinue, 20);
        btnContinue.click();
        Thread.sleep(1000);
    }

    public void SelectRegularPlan() throws Exception{
        waitForElement(btnRegular, 20);
        btnRegular.click();
    }

    public void CheckIfSectionIsDisplayed(WebElement element) throws Exception{
        if(!element.isDisplayed())
        {
            throw new Exception("Element not displayed");
        }
    }

    public boolean CheckIfSectionIsActive(WebElement element, boolean shouldBe) throws Exception{
        waitForElement(element, 20 );

        if(!element.isEnabled())
        {
            shouldBe = false;
            throw new Exception("Element '"+element.toString()+"' not active");
        }
        else
        {
            return shouldBe = true;
        }
    }

    public void CheckIfSectionsAreDisplayed(WebElement element) throws Exception{//Fazer um for aqui
        waitForElement(element, 20 );
        if(!element.isDisplayed())
        {
            throw new Exception("Element not displayed");
        }
    }

    public void FillinDropdown(WebElement dropdownClick, String title) throws Exception{
        dropdownClick.click();
        waitForElement(driver.findElement(By.xpath("//div[text()='"+title+"']")), 20);
        driver.findElement(By.xpath("//div[text()='"+title+"']")).click();
    }

    public void SetInfoPassenger1(String title, String Name, String Surname) throws Exception{
        FillinDropdown(btnTitlePassenger1, title);
        FillinData(txtFirstNamePassenger1, Name);
        FillinData(txtLastNamePassenger1, Surname);
    }

    public void SetInfoPassenger2(String title, String Name, String Surname) throws Exception{
        FillinDropdown(btnTitlePassenger2, title);
        FillinData(txtFirstNamePassenger2, Name);
        FillinData(txtLastNamePassenger2, Surname);
    }

    public void SelectFlights(String passengerTitle1, String NameP1, String SurnameP1, String passengerTitle2,String NameP2, String SurnameP2) throws Exception{
        ClickSelectDepartureFlight();
        ClickSelectReturnFlight();
        SelectRegularPlan();
        Thread.sleep(5000);
        CheckIfSectionIsDisplayed(titleLogInToMyRyanair);
        CheckIfSectionIsDisplayed(titlePassengers);
        CheckIfSectionIsActive(titlePassengers, false);
        ClickLoginLater();
        Thread.sleep(5000);
        CheckIfSectionIsActive(titlePassengers, true);
        SetInfoPassenger1(passengerTitle1, NameP1, SurnameP1);
        SetInfoPassenger2(passengerTitle2, NameP2, SurnameP2);
        ClickContinue();
    }
}
