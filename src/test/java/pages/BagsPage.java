package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BagsPage extends BasePage{

    //Locators for elements on the page
    @FindBy(xpath="//h3[contains(text(),'Cabin Baggage Allowance')]")
    private WebElement titleCabinBaggageAllowance;

    private WebDriver driver = null;

    //Constructor to Initialize the Page Factory
    public BagsPage(WebDriver driver) throws Exception{
        super(driver);
        this.driver = driver;
        if(!isBagsPageDisplayed()) throw new Exception("Bags page not displayed");
    }

    //Actions to be performed in Bags Page
    public boolean isBagsPageDisplayed() {
        waitForElement(titleCabinBaggageAllowance, 20 );
        return titleCabinBaggageAllowance.isDisplayed();
    }
}
