package rahulshettyacademy.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) {

		String prodName = "ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		driver.get("https://rahulshettyacademy.com/client");

		driver.findElement(By.id("userEmail")).sendKeys("shruti.mittu@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Montreal@5120");
		driver.findElement(By.id("login")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
	
		//Add Item to Cart
		WebElement fnlProd = products.stream().filter(prod->
		prod.findElement(By.cssSelector("b")).getText().equals("ZARA COAT 3")).findFirst().orElse(null);
		
		fnlProd.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		//Verify added to cart toast container
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));		
		
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		//Click on Cart
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		List<WebElement> addedProds = driver.findElements(By.cssSelector(".cartSection h3"));
		
		//Verify if item exists on Cart page
		Boolean match = addedProds.stream().anyMatch(prod -> prod.getText().equalsIgnoreCase(prodName));		
		Assert.assertTrue(match);
		//Click CheckOut
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		//Enter Country
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Select Country']")));
		//driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("India");
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.xpath("//input[@placeholder='Select Country']")), "India").build().perform();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		driver.findElement(By.cssSelector(".ta-results button:last-of-type")).click();
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
        // This  will scroll down the page by  1000 pixel vertical		
        js.executeScript("window.scrollBy(0,1000)");
        
		//Click on Place Order
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".action__submit")));
		
		driver.findElement(By.cssSelector(".action__submit")).click();
		
		String confirmMsg = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMsg.equalsIgnoreCase(confirmMsg));
		
		driver.close();	
		
	}

}
