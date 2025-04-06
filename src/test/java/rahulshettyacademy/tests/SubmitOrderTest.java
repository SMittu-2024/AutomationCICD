package rahulshettyacademy.tests;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.AssertJUnit;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.OrderConfirmPage;
import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.pageobjects.CheckOutPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {

	String prodName;
	
	@Test(dataProvider="getData",groups= {"Purchase"})
	public void submitOrderTest(HashMap<String,String> input) throws IOException, InterruptedException{

		String country = "India";
		String ConfirmMessage =  "THANKYOU FOR THE ORDER."; 
				
		//Login
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));

		//Catalog Page
		prodName = input.get("product");
		//Select product from catalog and add to cart	
		CartPage cartPage = productCatalogue.addProductToCart(prodName);		

		//Cart Page
		//Click on Cart icon
		cartPage.goToCartPage();		
		//Verify if item exists on Cart page
		Assert.assertTrue(cartPage.IsProductDisplayedInCart(prodName));		
		//Click CheckOut
		CheckOutPage checkOutPage = cartPage.goToCheckOut();
		
		//CheckOut page
		//Enter Country
		checkOutPage.searchCountry(country);
		//Select a country from searched results
		checkOutPage.selectSearchedCountry();
		//Click Place Order button
		OrderConfirmPage orderConfirmPage = checkOutPage.ClickPlaceOrderBtn();		
		//Verify confirmation message	
		String confirmMsg = orderConfirmPage.getConfirmnMsg();
		Assert.assertTrue(confirmMsg.equalsIgnoreCase(ConfirmMessage));	
		
	}
	
	@Test(dependsOnMethods= {"submitOrderTest"})
	public void OrderHistoryTest() {
		//Verify if product is listed in Order History page
		ProductCatalogue productCatalogue = landingPage.loginApplication("shruti.mittu@gmail.com", "Montreal@5120");
		OrderPage orderPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(orderPage.VerifyOrderDisplay(prodName));
	}
	
	
	@DataProvider
	public Object[][] getData() throws IOException {
		
		//String path = System.getProperty("user.dir")+"\\src\\test\\java\\rahulshettyacademy\\data\\PurchaseOrder.json";
		
		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\rahulshettyacademy\\data\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	
	/*@DataProvider
	public Object[][] getData() {
		return new Object[][] {{"shruti.mittu@gmail.com", "Montreal@5120",  "ZARA COAT 3" },{"shruti.mittu@gmail.com", "Montreal@5120", "ADIDAS ORIGINAL"}};
	}*/
	/*HashMap<String,String> map = new HashMap<String,String>();
	map.put("email", "shruti.mittu@gmail.com");
	map.put("password", "Montreal@5120");
	map.put("product", "ZARA COAT 3");
	
	HashMap<String,String> map1 = new HashMap<String,String>();
	map1.put("email", "shruti.mittu@gmail.com");
	map1.put("password", "Montreal@5120");
	map1.put("product", "ADIDAS ORIGINAL");*/
}
