package rahulshettyacademy.stepDefinitions;

import java.io.IOException;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckOutPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.OrderConfirmPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class StepDefinitionImpl extends BaseTest {

	public LandingPage landingPage;
	ProductCatalogue productCatalogue;
	CartPage cartPage;
	CheckOutPage checkOutPage;
	OrderConfirmPage orderConfirmPage;

	@Given("I landed on Ecommerce page")
	public void I_landed_on_Ecommerce_page() throws IOException {

		landingPage = LaunchApplication();
	}

	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_username_and_password(String username, String password) {

		productCatalogue = landingPage.loginApplication(username, password);
	}

	@When("^I add the product (.+) to Cart$")
	public void i_add_product_to_Cart(String productName) {

		cartPage = productCatalogue.addProductToCart(productName);
	}

	@When("^Checkout (.+) and submit the order$")
	public void checkout_submit_order(String productName) throws InterruptedException {

		cartPage.goToCartPage();
		Assert.assertTrue(cartPage.IsProductDisplayedInCart(productName));
		checkOutPage = cartPage.goToCheckOut();
		checkOutPage.searchCountry("india");
		checkOutPage.selectSearchedCountry();
		orderConfirmPage = checkOutPage.ClickPlaceOrderBtn();
	}

	@Then("{string} message is displayed on Confirmation page")
	public void message_displayed_confirmationPage(String string) throws InterruptedException {
		
		String confirmMsg = orderConfirmPage.getConfirmnMsg();
		Assert.assertTrue(confirmMsg.equalsIgnoreCase(string));	
		driver.quit();
	}
	
	@Then("^\"([^\"]*)\" message is displayed")
	public void error_message_displayed_login_page(String strArg) throws InterruptedException {
		
		Assert.assertEquals(strArg, landingPage.getErrorMessage());
		driver.quit();
	}

}
