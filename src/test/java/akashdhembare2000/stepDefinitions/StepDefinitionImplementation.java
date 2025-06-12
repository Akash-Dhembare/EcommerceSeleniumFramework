package akashdhembare2000.stepDefinitions;

import akashdhembare2000.TestComponents.BaseTest;
import akashdhembare2000.pageObjects.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;

public class StepDefinitionImplementation extends BaseTest {

    public LandingPage landingPage;
    public  ProductCatelogue productCatelogue;
    public ConfirmationPage confirmationPage;
    @Given("I landed on Ecommerce Page")
    public void I_landed_on_Ecommerce_Page() throws IOException {
        landingPage = launchApplication();
    }

    // Logged in with username <name> and password <password>
    @Given("^Logged in with username (.+) and password (.+)$") // We have given ^ at start, $ at end because we are dealing with RegEx here.
    public void Logged_in_with_username_and_password(String username, String password){
       productCatelogue = landingPage.loginApplication(username, password);
    }

    @When("^Add the product (.+) to Cart$")
    public void Add_the_product_to_Cart(String productName){
        List<WebElement> products= productCatelogue.getProductList();
        productCatelogue.addProductToCart(productName);
    }

    @And("^Checkout (.+) and submit the order$")
    public void Checkout_and_submit_order(String productName){
        CartPage cartPage= productCatelogue.goToCartPage();
        Boolean match= cartPage.VerifyProductDisplay(productName);
        Assert.assertTrue(match);
        CheckoutPage checkoutPage=cartPage.goToCheckout();
        checkoutPage.selectCountry("india");
        confirmationPage=checkoutPage.submitOrder();
    }

    @Then("Verify the confirmation message {string} is displayed on ConfirmationPage")
    public void message_displayed_confirmation_page(String string){
        String confirmMessage= confirmationPage.getConfirmationMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
        driver.close();
    }

    @Then("^\"([^\"]*)\" message is displayed")
    public void error_message_displayed(String str1){
        Assert.assertEquals(str1, landingPage.getErrorMessage());
        driver.close();
    }

}
