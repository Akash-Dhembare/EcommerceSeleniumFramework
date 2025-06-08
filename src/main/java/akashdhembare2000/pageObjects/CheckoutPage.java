package akashdhembare2000.pageObjects;

import akashdhembare2000.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends AbstractComponent {
    WebDriver driver;

    public CartPage(WebDriver driver){
        super(driver); // Every child should give the life of driver to parent, so we are defining it in every child class
        // initialization
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }




    // Page Factory
    @FindBy(css = ".totalRow button")
    WebElement checkoutEle;

    @FindBy(css = ".cartSection h3")
    List<WebElement> cartProducts;

    public Boolean VerifyProductDisplay(String productName){
        Boolean match=cartProducts.stream().anyMatch(item->item.getText().equalsIgnoreCase(productName));
        return match;
    }

    public CartPage goToCheckout(){
        checkoutEle.click();
        CartPage cartPage=new CartPage(driver);
        return cartPage;

    }





}
