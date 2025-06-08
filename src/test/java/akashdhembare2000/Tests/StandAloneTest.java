package akashdhembare2000.Tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class StandAloneTest {
    public static void main(String[] args) {
        WebDriver driver= new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://rahulshettyacademy.com/client/");

        String productName="ADIDAS ORIGINAL";

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.findElement(By.id("userEmail")).sendKeys("akash.dhembare2000@gmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("Akash@2000");

        driver.findElement(By.id("login")).click();

        WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(5));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
        // Grab all the products into the list
        List<WebElement> products= driver.findElements(By.cssSelector(".mb-3"));

        // Iterating through the list of products using Java Streams and finding the element with matching text
        WebElement prod = products.stream().filter(product->
                product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
        // product.findElement(By.cssSelector("b") -> Limiting the scope of the driver to products only
        // findFirst() -> If there are multiple elements with the same name then only return the first one
        // orElse(null) ->If its not returning anything then return null and store it in prod

        // Clicking on Add to Cart button
        prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();



        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container"))); // We are waiting until Added to cart success message is visible
        //ng-animating
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating")))); // We are waiting to disappear the animation

        driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

        // Validate if item added by us is same as actual item present in the cart
        List<WebElement> addedToCartItems = driver.findElements(By.xpath("//div[@class='cartSection']/h3"));
        Boolean match=addedToCartItems.stream().anyMatch(item->item.getText().equalsIgnoreCase(productName));
        System.out.println(match);
        Assert.assertTrue(match);

        // filter() -> If you want to get WebElement out of the condition.
        // anyMatch() -> If you just want to make sure condition matches.

        // Proceed to checkout
        driver.findElement(By.xpath("//button[text()='Checkout']")).click();

//        driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("ind");
//
//        List<WebElement> autoSuggestCountries = driver.findElements(By.xpath("//section/button[contains(@class,'ta-item')]"));
//
//        for(WebElement country : autoSuggestCountries){
//            if (country.getText().equals("India")){
//                country.click();
//            }
//        }

        // OR


        Actions actions= new Actions(driver);
        actions.sendKeys(driver.findElement(By.xpath("//input[@placeholder='Select Country']")),"India").build().perform();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section/button[contains(@class,'ta-item')]")));

        driver.findElement(By.xpath("(//button[contains(@class, 'ta-item')])[2]")).click();

        // Place order
        driver.findElement(By.cssSelector(".action__submit")).click();

        String confirmMsg=driver.findElement(By.cssSelector(".hero-primary")).getText();
        Assert.assertTrue(confirmMsg.equalsIgnoreCase("Thankyou for the order."));

        driver.quit();




    }
}
