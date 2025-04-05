package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {
	
	WebDriver driver;
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".cartSection h3")
	List<WebElement> ProductTitles;
	
	@FindBy(css=".totalRow button")
	WebElement checkOutBtn;
	
	By AddedProdLocator = By.cssSelector(".cartSection h3");
	
	public List<WebElement> getAddedProductList() {
		WaitForElementToAppear(AddedProdLocator);
		return ProductTitles;
	}
	
	public boolean IsProductDisplayedInCart(String productName) {
		WaitForWebElementListToAppear(ProductTitles);
		Boolean match = ProductTitles.stream().anyMatch(prod -> prod.getText().equalsIgnoreCase(productName));		
		return match;
	}
	
	public CheckOutPage goToCheckOut() {
		checkOutBtn.click();
		CheckOutPage paymentPage = new CheckOutPage(driver);
		return paymentPage;
	}
}
