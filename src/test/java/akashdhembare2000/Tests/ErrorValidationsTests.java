package akashdhembare2000.Tests;

import akashdhembare2000.TestComponents.BaseTest;
import akashdhembare2000.TestComponents.Retry;
import akashdhembare2000.pageObjects.CartPage;
import akashdhembare2000.pageObjects.ProductCatelogue;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class ErrorValidationsTests extends BaseTest {

   @Test(groups = {"ErrorHandling"}, retryAnalyzer = Retry.class)
   public void LoginErrorValidation() throws IOException {

        String productName="ADIDAS ORIGINAL";
        landingPage.loginApplication("tejasb199@gmail.com", "Tejas@1999"); // Invalid U/P
        Assert.assertEquals("Incorrect emal or password.",landingPage.getErrorMessage());

    }

    @Test
    public void ProductErrorValidation() throws IOException {

        String productName="ADIDAS ORIGINAL";

        ProductCatelogue productCatelogue=landingPage.loginApplication("akash.dhembare2000@gmail.com", "Akash@2000");

        List<WebElement> products = productCatelogue.getProductList();
        productCatelogue.addProductToCart(productName);
        CartPage cartPage= productCatelogue.goToCartPage();

        Boolean match = cartPage.VerifyProductDisplay("ADIDAS ORIGINAL 123");
        Assert.assertFalse(match);


    }
}
