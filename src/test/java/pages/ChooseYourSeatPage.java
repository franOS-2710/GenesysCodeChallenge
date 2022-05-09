package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ChooseYourSeatPage extends BasePage{

    //Locators for elements on the page
    @FindBy(xpath="(//div[@class='seatmap']//button[not(contains(@class,'selected'))])[1]")
    private WebElement btnSelectSeat;

    @FindBy(xpath = "//button[contains(text(),'Next flight')]")
    private WebElement btnNextFlight;

    @FindBy(xpath = "//button[contains(text(),'Continue')]")
    private WebElement btnContinue;

    @FindBy(xpath = "//button[contains(text(),'No, thanks')]")
    private WebElement btnNoThanks;

    private WebDriver driver = null;

    //Constructor to Initialize the Page Factory
    public ChooseYourSeatPage(WebDriver driver) throws Exception{
        super(driver);
        this.driver = driver;
        if(!isChooseYourSeatPageDisplayed()) throw new Exception("Choose Your Seat page not displayed");
    }

    //Actions to be performed in Choose Your Seats Page
    public boolean isChooseYourSeatPageDisplayed() {
        waitForElement(btnSelectSeat, 30 );
        return btnSelectSeat.isDisplayed();
    }

    public void SelectSeat() throws Exception{
        waitForElement(btnSelectSeat, 20);
        moveToElement(driver, btnSelectSeat);
        btnSelectSeat.click();
    }

    public void ClickNextFlight() throws Exception{
        waitForElement(btnNextFlight, 20);
        btnNextFlight.click();
    }

    public void ClickContinue() throws Exception{
        waitForElement(btnContinue, 20);
        btnContinue.click();
    }

    public void ClickNoThanks() throws Exception{
        waitForElement(btnNoThanks, 20);
        btnNoThanks.click();
    }

    public void DismissModal() throws Exception{
        Thread.sleep(2000);
        if(waitForAlert(5)) {
            Alert alert = driver.switchTo().alert();
            alert.dismiss();
        }
        if(waitForElement(btnNoThanks, 2)) {
            btnNoThanks.click();
        }
    }

    public void SelectYourSeats() throws Exception{
        SelectSeat();
        SelectSeat();
        ClickNextFlight();
        Thread.sleep(3000);
        DismissModal();
        SelectSeat();
        SelectSeat();
        ClickContinue();
        DismissModal();
    }
}
