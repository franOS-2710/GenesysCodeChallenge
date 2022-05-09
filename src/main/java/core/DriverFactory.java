package core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.ImmutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URL;
import java.time.Duration;

public class DriverFactory {

    private WebDriver driver;
    private RemoteWebDriver remoteWebDriver;

    //getDriver to run locally
    public WebDriver getDriver() {
        try{
            String browser = TestConfig.getBrowser();
            if(browser.equalsIgnoreCase("chrome")){
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
            }else if(browser.equalsIgnoreCase("firefox")){
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
            }
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Long.parseLong(TestConfig.getProperty("pageLoadTimeOut"))));
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Long.parseLong(TestConfig.getProperty("implicitWait"))));
            driver.manage().window().maximize();
        }catch (Exception e){
            e.printStackTrace();
        }return driver;
    }

    //getDriver to run on docker
    public WebDriver getRemoteWebDriverDocker(String hub_ip){
        try{
            String browser = TestConfig.getBrowser();
            ImmutableCapabilities capabilities = null;
            URL url = new URL("http://"+hub_ip+":4444");

            if(browser.equalsIgnoreCase("chrome")){
                WebDriverManager.chromedriver().setup();
                capabilities = new ImmutableCapabilities("browserName", "chrome");
            }else if(browser.equalsIgnoreCase("firefox")){
                WebDriverManager.firefoxdriver().setup();
                capabilities = new ImmutableCapabilities("browserName", "firefox");
            }
            remoteWebDriver = new RemoteWebDriver(url,capabilities);
            remoteWebDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Long.parseLong(TestConfig.getProperty("pageLoadTimeOut"))));
            remoteWebDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Long.parseLong(TestConfig.getProperty("implicitWait"))));
        }catch (Exception e){
            e.printStackTrace();
        }
        return remoteWebDriver;
    }

    //getDriver to run on remotely
    public WebDriver getRemoteWebDriver(){
        try{
            String browser = TestConfig.getBrowser();
            ImmutableCapabilities capabilities = null;
            URL url = new URL("http://127.0.0.1:4444");
            if(browser.equalsIgnoreCase("chrome")){
                capabilities = new ImmutableCapabilities("browserName", "chrome");
            }else if(browser.equalsIgnoreCase("firefox")){
                capabilities = new ImmutableCapabilities("browserName", "firefox");
            }
            remoteWebDriver = new RemoteWebDriver(url,capabilities);
            remoteWebDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Long.parseLong(TestConfig.getProperty("pageLoadTimeOut"))));
            remoteWebDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Long.parseLong(TestConfig.getProperty("implicitWait"))));
        }catch (Exception e){
            e.printStackTrace();
        }
        return remoteWebDriver;
    }
}
