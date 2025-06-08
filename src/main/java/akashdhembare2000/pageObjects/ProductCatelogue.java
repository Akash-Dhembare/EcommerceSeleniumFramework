package akashdhembare2000.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage {
    WebDriver driver;

    public LandingPage(WebDriver driver){
        // initialization
        this.driver=driver;

        PageFactory.initElements(driver, this);
    }


 //   WebElement userEmail=driver.findElement(By.id("userEmail"));

    // Page Factory
    @FindBy(id = "userEmail")
    WebElement userEmail;

    @FindBy(id = "userPassword")
    WebElement password;

    @FindBy(id = "login")
    WebElement submit;

    public void goTo(){
        driver.get("https://rahulshettyacademy.com/client/");
    }
    // Action Methods
    public void loginApplication(String email, String pass){
        // Page Object should not hold any data
        // Page object should only focus on Elements and Actions
        // If you want to send the data, send it through the testcase (Send data through arguments)
        userEmail.sendKeys(email);
        password.sendKeys(pass);
        submit.click();
    }
}
