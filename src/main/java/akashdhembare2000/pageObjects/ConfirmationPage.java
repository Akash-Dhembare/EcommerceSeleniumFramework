package akashdhembare2000.pageObjects;

import akashdhembare2000.AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmationPage extends AbstractComponent {
    WebDriver driver;

    public ConfirmationPage(WebDriver driver){
        super(driver); // Every child should give the life of driver to parent, so we are defining it in every child class
        // initialization
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css =".hero-primary")
    WebElement confirmationMessage;

    public String getConfirmationMessage(){
        return confirmationMessage.getText();
    }
}
