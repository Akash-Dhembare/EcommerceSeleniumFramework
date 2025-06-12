package akashdhembare2000.pageObjects;

import akashdhembare2000.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage extends AbstractComponent {
    WebDriver driver;

    public CheckoutPage(WebDriver driver){
        super(driver); // Every child should give the life of driver to parent, so we are defining it in every child class
        // initialization
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }




    // Page Factory
    @FindBy(css = "[placeholder='Select Country']")
    private WebElement country;

    @FindBy(css = ".action__submit")
    private WebElement submit;

    @FindBy(xpath= "(//button[contains(@class,'ta-item')])[2]")
    private WebElement selectCountry;

    By results= By.cssSelector(".ta-results");

    public void selectCountry(String countryName){
        Actions actions= new Actions(driver);
        actions.sendKeys(country,countryName).build().perform();
        waitForElementToAppear(results);
        selectCountry.click();
    }

    public ConfirmationPage submitOrder(){
        submit.click();

        return new ConfirmationPage(driver);
    }

}
