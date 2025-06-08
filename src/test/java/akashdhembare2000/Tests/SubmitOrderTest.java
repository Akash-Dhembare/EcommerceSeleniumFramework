package akashdhembare2000.Tests;

import akashdhembare2000.TestComponents.BaseTest;
import akashdhembare2000.pageObjects.*;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SubmitOrderTest extends BaseTest {

    String productName="ADIDAS ORIGINAL";
   @Test(dataProvider = "getData", groups = "Purchase")
   public void SubmitOrder(HashMap<String, String> input) throws IOException {

        ProductCatelogue productCatelogue=landingPage.loginApplication(input.get("email"), input.get("password"));

        List<WebElement> products = productCatelogue.getProductList();
        productCatelogue.addProductToCart(input.get("productName"));
        CartPage cartPage= productCatelogue.goToCartPage();

        Boolean match = cartPage.VerifyProductDisplay(input.get("productName"));
        Assert.assertTrue(match);

        // Proceed to checkout
        CheckoutPage checkoutPage=cartPage.goToCheckout();
        checkoutPage.selectCountry("India");
        ConfirmationPage confirmationPage = checkoutPage.submitOrder();

        String confirmMessage=confirmationPage.getConfirmationMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
    }

    // To Verify ADIDAS ORIGINAL is displaying in orders page
    @Test (dependsOnMethods = {"SubmitOrder"})
    public void OrderHistoryTest(){
        ProductCatelogue productCatelogue=landingPage.loginApplication("akash.dhembare2000@gmail.com", "Akash@2000");
        OrderPage orderPage=productCatelogue.goToOrderPage();
        Assert.assertTrue(orderPage.VerifyOrderDisplay(productName));
    }

//    @DataProvider
//    public Object[][] getData(){
//
//        HashMap<String, String> map= new HashMap<String, String>();
//        map.put("email", "akash.dhembare2000@gmail.com");
//        map.put("password", "Akash@2000");
//        map.put("productName", "ADIDAS ORIGINAL");
//
//        HashMap<String, String> map1= new HashMap<String, String>();
//        map1.put("email", "tejasb1999@gmail.com");
//        map1.put("password", "Tejas@1999");
//        map1.put("productName", "ZARA COAT 3");
//
//       return new Object[][] {{map}, {map1}};
//      // return new Object[][] {{"akash.dhembare2000@gmail.com", "Akash@2000", "ADIDAS ORIGINAL"}, {"tejasb1999@gmail.com", "Tejas@1999", "ZARA COAT 3"}};
//    }





    @DataProvider
    public Object[][] getData() throws IOException {

       List<HashMap<String, String>> data=getJsonDataToMap(System.getProperty("user.dir") + "/src/test/java/akashdhembare2000/data/PurchaseOrder.json");
       return new Object[][] {{data.get(0)}, {data.get(1)}};
    }
}
