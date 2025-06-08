package akashdhembare2000;

import akashdhembare2000.pageObjects.*;
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

public class SubmitOrderTest {
    public static void main(String[] args) {
        WebDriver driver= new ChromeDriver();
        driver.manage().window().maximize();
        String productName="ADIDAS ORIGINAL";
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));


        LandingPage landingPage= new LandingPage(driver); // Sending driver to Login Page
        landingPage.goTo();
        ProductCatelogue productCatelogue=landingPage.loginApplication("akash.dhembare2000@gmail.com", "Akash@2000");


        List<WebElement> products = productCatelogue.getProductList();
        productCatelogue.addProductToCart(productName);
        CartPage cartPage= productCatelogue.goToCartPage();

        Boolean match = cartPage.VerifyProductDisplay(productName);
        Assert.assertTrue(match);

        // Proceed to checkout
        CheckoutPage checkoutPage=cartPage.goToCheckout();
        checkoutPage.selectCountry("India");
        ConfirmationPage confirmationPage = checkoutPage.submitOrder();

        String confirmMessage=confirmationPage.getConfirmationMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));

        driver.quit();




    }
}
