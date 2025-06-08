package akashdhembare2000.pageObjects;

import akashdhembare2000.AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends AbstractComponent {
    WebDriver driver;

    public LandingPage(WebDriver driver){
        // Sending Driver from child to parent
        super(driver); // With super() we can send driver to parent i.e., AbstractComponent
        // In Abstract component we will create a constructor for handling super()

        // initialization
        this.driver=driver;

        PageFactory.initElements(driver, this); // This method will execute first and it is responsible for finding elements by @FindBy
    }


 //   WebElement userEmail=driver.findElement(By.id("userEmail"));

    // Page Factory - Design Pattern
    @FindBy(id = "userEmail")
    WebElement userEmail;

    @FindBy(id = "userPassword")
    WebElement password;

    @FindBy(id = "login")
    WebElement submit;

    @FindBy(css = "[class*='flyInOut']")
    WebElement errorMessage;

    public String getErrorMessage(){
        waitForWebElementElementToAppear(errorMessage);
        return errorMessage.getText();
    }

    public void goTo(){
        driver.get("https://rahulshettyacademy.com/client/");
    }
    // Action Methods
    public ProductCatelogue loginApplication(String email, String pass){
        // Page Object should not hold any data
        // Page object should only focus on Elements and Actions
        // If you want to send the data, send it through the testcase (Send data through arguments)
        userEmail.sendKeys(email);
        password.sendKeys(pass);
        submit.click();

        ProductCatelogue productCatelogue= new ProductCatelogue(driver);
        return productCatelogue;
    }
}
