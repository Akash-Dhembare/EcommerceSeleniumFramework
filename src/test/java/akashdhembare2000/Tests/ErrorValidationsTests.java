package akashdhembare2000.Tests;

import akashdhembare2000.TestComponents.BaseTest;
import akashdhembare2000.pageObjects.CartPage;
import akashdhembare2000.pageObjects.CheckoutPage;
import akashdhembare2000.pageObjects.ConfirmationPage;
import akashdhembare2000.pageObjects.ProductCatelogue;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class ErrorValidations extends BaseTest {

   @Test
   public void SubmitOrder() throws IOException {

        String productName="ADIDAS ORIGINAL";
        landingPage.loginApplication("akah.dhembare2000@gmail.com", "Akas@2000"); // Invalid U/P
        Assert.assertEquals("Incorrect email or password.",landingPage.getErrorMessage());

    }
}
