package rahulshettyacademy.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.TestComponents.Retry;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckOutPage;
import rahulshettyacademy.pageobjects.OrderConfirmPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest {
	
	
	@Test(groups= {"ErrorHandling"},retryAnalyzer=Retry.class)
	public void LoginErrorValidation() {
			//Login
			landingPage.loginApplication("shruti.mittu@gmail.com", "Montreal@512");
			Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
	}

	
	/*
	 * @Test public void ProductErrorValidation() throws IOException,
	 * InterruptedException {
	 * 
	 * String prodName = "ADIDAS ORIGINAL"; //Login ProductCatalogue
	 * productCatalogue = landingPage.loginApplication("shruti.mittu@gmail.com",
	 * "Montreal@5120"); //Catalog Page //Select product from catalog and add to
	 * cart CartPage cartPage = productCatalogue.addProductToCart(prodName); //Cart
	 * Page //Click on Cart icon cartPage.goToCartPage(); //Verify if item exists on
	 * Cart page Assert.assertTrue(cartPage.IsProductDisplayedInCart(prodName)); }
	 */
	
	
}
	
