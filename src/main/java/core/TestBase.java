package core;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import java.io.File;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

public class TestBase {

    private WebDriver driver;
    private DriverFactory driverFactory;

    @BeforeSuite(alwaysRun=true)
    public void initSuite(){
        TestConfig.loadProperties();
    }

    @BeforeClass(alwaysRun=true)
    public void initDriver()  {
        driverFactory = new DriverFactory();
        if(System.getenv("runtype").equals("local")){
            driver = driverFactory.getDriver();
        }else if(System.getenv("runtype").equals("remote")){
            driver = driverFactory.getRemoteWebDriver();
        }else if(System.getenv("runtype").equals("docker")){
            driver = driverFactory.getRemoteWebDriverDocker(System.getenv("hub"));
        }
        //driver.navigate().to(TestConfig.getProperty("appBaseURL"));
    }

    @AfterClass(alwaysRun=true)
    public void tearDown(){
        if(driver!=null){
            driver.quit();
        }
    }

    public WebDriver driver(){
        return driver;
    }

    @DataProvider(name="getData")
    public Object[][] getData(Method testCase) throws Exception {
        File testDataLocation = new File("src/main/resources/testdata");
        List<Map<String,String>> extractedData = null;
        String envName  =  TestConfig.getEnv();
        JSONDataProvider testData = new JSONDataProvider(testDataLocation+"/data."+envName+".json");
        extractedData = testData.getAllData(testCase.getName());

        Object[][] dataObject = new Object[extractedData.size()][1];
        int count =0;

        for(Map<String,String> map :  extractedData) {
            dataObject[count][0] = map;
            count++;
        }

        return dataObject;
    }
}
