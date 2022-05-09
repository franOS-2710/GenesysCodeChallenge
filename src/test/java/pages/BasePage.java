package pages;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.NoSuchElementException;

public class BasePage {

    protected WebDriver driver = null;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    protected boolean waitForElement(WebElement ele, long timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
            wait.ignoring(NoSuchElementException.class)
                    .pollingEvery(Duration.ofMillis(500))
                    .until(ExpectedConditions.elementToBeClickable(ele));
            return true;
        }catch(Exception e) {}
        return false;
    }

    protected boolean waitForAlert(long timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
            wait.ignoring(NoSuchElementException.class)
                    .pollingEvery(Duration.ofMillis(500))
                    .until(ExpectedConditions.alertIsPresent());
            return true;
        }catch(Exception e) {}
        return false;
    }

    protected void moveToElement(WebDriver driver, WebElement ele) {
        Actions act = new Actions(driver);
        act.moveToElement(ele).perform();
    }

    protected void FillinData(WebElement field, String value) throws Exception{
        waitForElement(field, 20 );
        field.sendKeys(value);
    }

    public boolean isAlertPresent() {

        boolean presentFlag = false;

        try {

            // Check the presence of alert
            Alert alert = driver.switchTo().alert();
            // Alert present; set the flag
            presentFlag = true;
            // if present consume the alert
            alert.dismiss();
            //( Now, click on ok or cancel button )

        } catch (NoAlertPresentException ex) {
            // Alert not present
            ex.printStackTrace();
        }

        return presentFlag;
    }
}