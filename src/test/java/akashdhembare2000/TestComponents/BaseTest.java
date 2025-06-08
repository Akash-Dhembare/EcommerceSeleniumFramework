package akashdhembare2000.TestComponents;

import akashdhembare2000.pageObjects.LandingPage;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class BaseTest {

    public WebDriver driver;
    public LandingPage landingPage;

    public WebDriver initializeDriver() throws IOException {

        FileInputStream fileInputStream= new FileInputStream(System.getProperty("user.dir")+"/src/main/java/akashdhembare2000/Resources/GlobalData.properties");
        Properties prop= new Properties();
        prop.load(fileInputStream);

        // Use of Java ternary operator
                                 // Condition                            // First Argument                 //Second Argument
                                                                     // if Condition is true           // if condition is false
        String browserName=  System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");


        if(browserName.contains("chrome")){
            ChromeOptions options= new ChromeOptions();
            if(browserName.contains("headless")) {
                options.addArguments("headless");
            }

            driver= new ChromeDriver(options);
            driver.manage().window().setSize(new Dimension(1440, 900)); // Help to run test cases in fullscreen in headless mode

        } else if (browserName.equalsIgnoreCase("edge")) {
            EdgeOptions edgeOptions= new EdgeOptions();
            if(browserName.contains("headless")) {
                edgeOptions.addArguments("headless");
            }

            driver= new EdgeDriver(edgeOptions);
            driver.manage().window().setSize(new Dimension(1440, 900));

        } else if (browserName.equalsIgnoreCase("firefox")){
            FirefoxOptions options= new FirefoxOptions();
            if(browserName.contains("headless")) {
                options.addArguments("headless");
            }
           driver= new FirefoxDriver(options);
           driver.manage().window().setSize(new Dimension(1440, 900));

        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));


        return driver;
    }

    public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
        TakesScreenshot ts= (TakesScreenshot) driver;
        File source=ts.getScreenshotAs(OutputType.FILE);

        File file= new File(System.getProperty("user.dir")+"//reports//"+testCaseName+".png");
        FileUtils.copyFile(source, file);

        return System.getProperty("user.dir")+"//reports//"+testCaseName+".png";
    }

    public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
        // Read JSON to String
        String jsonContent= FileUtils.readFileToString(new File(filePath));

        // String to HashMap - Jackson DataBind
        ObjectMapper mapper= new ObjectMapper();
        List<HashMap<String, String>> data= mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
        });

        return data;

        //{map, map}

    }

    @BeforeMethod(alwaysRun = true)
    public LandingPage launchApplication() throws IOException {
        driver=initializeDriver();
        landingPage= new LandingPage(driver);
        landingPage.goTo();

        return landingPage;
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        driver.quit();
    }


}
