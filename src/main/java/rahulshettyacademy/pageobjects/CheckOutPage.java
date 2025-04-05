package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class CheckOutPage extends AbstractComponent {
	
	WebDriver driver;
	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".action__submit")
	WebElement placeOrderBtn;
	
	@FindBy(css=".ta-results button:last-of-type")
	WebElement countryEle;
	
	@FindBy(xpath="//input[@placeholder='Select Country']")
	WebElement country;
	
	By placeOrdrLocator = By.cssSelector(".action__submit");
	By countrySrchResults = By.cssSelector(".ta-results");
	//By countryLocator = By.xpath("//input[@placeholder='Select Country']");
	
	public WebElement searchCountry(String countryNm) {
		Actions a = new Actions(driver);
		a.sendKeys(country,countryNm).build().perform();
		return countryEle;
	}
	
	public void selectSearchedCountry() {
		WaitForElementToAppear(countrySrchResults);
		countryEle.click();
	}	
	
	public OrderConfirmPage ClickPlaceOrderBtn() {
		
		ScrollDownPage();
		WaitForElemntToBeClickable(placeOrdrLocator);
		//Click on Place Order
		placeOrderBtn.click();
		OrderConfirmPage orderConfirmPage = new OrderConfirmPage(driver);
		return orderConfirmPage;
	}	
}
