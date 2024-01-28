package testscripts;

import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import elementRepository.DigitalDownloadsPage;
import elementRepository.HomePage;
import elementRepository.ShoppingCartPage;
import genericlibrary.BaseTest;
import genericlibrary.DataUtility;

@Listeners(genericlibrary.ListenersImpelmentaion.class)
public class TC02_AddToCartTest extends BaseTest{

	@Test(groups = "System")
	public void addToCartTestCase() throws InterruptedException, FileNotFoundException {
		//concat 
		//
		
		//Step 6 : To navigate to Digital downloads
				driver.findElement(By.partialLinkText("Digital downloads")).click();
				
				Assert.assertEquals(driver.getTitle(), "Demo Web Shop. Digital downloads");
				Reporter.log("Digital downloads page is displayed",true);
				
				//Step 8: Located all AddToCart buttons
				List<WebElement> allProducts=driver.findElements(By.xpath("//input[@value='Add to cart']"));
				
				WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
				
				//Step 9: To Add all the products to the cart
				for (WebElement product : allProducts) {
					product.click();
					Thread.sleep(500);
				}
				//
				//vcbn
				//dfgh
				//Step 10: To navigate to shopping cart
				driver.findElement(By.xpath("//span[text()='Shopping cart']")).click();
						
				Assert.assertEquals(driver.getTitle(), "Demo Web Shop. Shopping Cart");
				Reporter.log("Shopping Cart page displayed",true);
				
				List<WebElement> cartProducts=driver.findElements(By.xpath("//tr[@class='cart-item-row']"));
				
				//Step 12: Validate the number of products in shopping cart		
				Assert.assertEquals(allProducts.size(), cartProducts.size());
				Reporter.log("AddToCart Test Case Pass",true);
				common_Utility.toTakeScreenShot(driver, "addToCartTestCase");
				
				//Step 13 : Removing all the products from cart
				List<WebElement> allRemoveCartButtons = driver.findElements(By.xpath("//td[@class='remove-from-cart']//input[@type='checkbox']"));
				
				for(WebElement removeCartButton : allRemoveCartButtons)
				{
					removeCartButton.click();
				}
				
				driver.findElement(By.xpath("//input[@name='updatecart']")).click();
	}
}