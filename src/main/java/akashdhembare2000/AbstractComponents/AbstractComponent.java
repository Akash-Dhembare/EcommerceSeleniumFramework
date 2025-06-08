package akashdhembare2000.AbstractComponents;

import akashdhembare2000.pageObjects.CartPage;
import akashdhembare2000.pageObjects.OrderPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AbstractComponent {
    WebDriver driver;
    public AbstractComponent(WebDriver driver) {
        this.driver= driver; // Giving driver scope to local WebDriver
        PageFactory.initElements(driver, this);
    }
    // We will write reusable code here
    // This will be parent class

    // driver.findElement(By.cssSelector("[routerlink*='cart']")).click();


    // driver.findElement(By.cssSelector("[routerlink*='cart']")) -> WebElement
    // By.cssSelector("[routerlink*='cart']") -> By Locator - Return Type is "By"
    @FindBy (css = "[routerlink*='cart']")
    WebElement cartHeader;

    @FindBy (css = "[routerlink*='myorders']")
    WebElement orderHeader;



    public void waitForElementToAppear(By findBy){
           WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(5));
           wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
    }

    public void waitForWebElementElementToAppear(WebElement element){
        WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(element));
    }


    public void waitForElementToDisappear(WebElement ele){
        WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.invisibilityOf(ele));
    }

    public CartPage goToCartPage(){ // We are giving it here, because cart is common in pages
        cartHeader.click();

        CartPage cartPage= new CartPage(driver);
        return cartPage;
    }

    public OrderPage goToOrderPage(){ // We are giving it here, because cart is common in pages
        orderHeader.click();

        OrderPage orderPage= new OrderPage(driver);
        return orderPage;
    }
}
