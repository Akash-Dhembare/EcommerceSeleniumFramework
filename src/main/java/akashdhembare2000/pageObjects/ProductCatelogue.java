package akashdhembare2000.pageObjects;

import akashdhembare2000.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCatelogue extends AbstractComponent {
    WebDriver driver;

    public ProductCatelogue(WebDriver driver){
        super(driver); // Every child should give the life of driver to parent, so we are defining it in every child class
        // initialization
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }


   // List<WebElement> products= driver.findElements(By.cssSelector(".mb-3"));

    // Page Factory
    @FindBy(css = ".mb-3")
    List<WebElement> products;

    @FindBy(css = ".ng-animating")
    WebElement spinner;


    // Page Factory is for driver.findElement construction only.
    By productsBy=By.cssSelector(".mb-3");
    By addToCart=By.cssSelector(".card-body button:last-of-type");
    By toastMessage = By.cssSelector("#toast-container");

    public List<WebElement> getProductList(){
        waitForElementToAppear(productsBy);
        return products;
    }

    public WebElement getProductByName(String productName){
        // Iterating through the list of products using Java Streams and finding the element with matching text
        WebElement prod = getProductList().stream().filter(product->
                product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);

        return prod;
    }

    public void addProductToCart(String productName){
        // Clicking on Add to Cart button
        WebElement prod=getProductByName(productName);
        prod.findElement(addToCart).click();
        waitForElementToAppear(toastMessage);
        waitForElementToDisappear(spinner);
    }

}
