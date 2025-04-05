package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class OrderConfirmPage extends AbstractComponent {
	
	WebDriver driver;
	public OrderConfirmPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".hero-primary")
	WebElement confirmMsgEle;	
		
	By confirmMsgLocator = By.cssSelector(".hero-primary");
	
	public String getConfirmnMsg() throws InterruptedException {
		WaitForElementToAppear(confirmMsgLocator);
		Thread.sleep(2000);
		String confirmMsg = confirmMsgEle.getText();
		return confirmMsg;
	}
}
