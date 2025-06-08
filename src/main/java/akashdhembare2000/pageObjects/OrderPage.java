package akashdhembare2000.pageObjects;

import akashdhembare2000.AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrderPage extends AbstractComponent {
    WebDriver driver;

    public OrderPage(WebDriver driver){
        super(driver); // Every child should give the life of driver to parent, so we are defining it in every child class
        // initialization
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }




    // Page Factory
    @FindBy(css = ".totalRow button")
    WebElement checkoutEle;

    @FindBy(css = "tr td:nth-child(3)")
    List<WebElement> productNames;

    public Boolean VerifyOrderDisplay(String productName){
        Boolean match=productNames.stream().anyMatch(product->product.getText().equalsIgnoreCase(productName));
        return match;
    }

    public CheckoutPage goToCheckout(){
        checkoutEle.click();

        return new CheckoutPage(driver);
    }





}
