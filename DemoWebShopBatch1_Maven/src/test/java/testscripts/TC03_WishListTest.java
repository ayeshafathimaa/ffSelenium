package testscripts;

import java.io.FileNotFoundException;
import java.nio.file.spi.FileSystemProvider;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import genericlibrary.BaseTest;

public class TC03_WishListTest extends BaseTest {

	@Test(groups = "System")
	//
	//
	//
	public void wishListTestCase() throws FileNotFoundException{
		
		//Step 6: Books page is displayed
		driver.findElement(By.partialLinkText("Books")).click();
			
		Assert.assertEquals(driver.getTitle(), "Demo Web Shop. Books");
		Reporter.log("Books page is displayed",true);
		
		//Step 8: Clicking on Fiction EX product and adding the product to wishlist
		WebElement product = driver.findElement(By.linkText("Fiction EX"));
		String productName=product.getText();
		product.click();
		driver.findElement(By.xpath("//input[@value='Add to wishlist']")).click();
		
		
		//Step 9: Navigating Wish List Product
		driver.findElement(By.xpath("//span[text()='Wishlist']")).click();
		
		//Step 10 : Locating the product present in the Wish list page
		WebElement wishListProduct=driver.findElement(By.xpath("//a[text()='Fiction EX']"));
		
		//Step 11 : Validating the product added to wish list or not
		if (wishListProduct.getText().equals(productName)) {
			System.out.println("Wish List Test Case Pass..");
		} else
			System.out.println("Wish List Test Case Fail..");
		
		Assert.assertEquals(wishListProduct.getText().equals(productName), true);
		Reporter.log("Books page is displayed",true);
		
		//Step 12: Removing the product from the wish list
		List<WebElement> allRemoveCheckBox = driver.findElements(By.xpath("//td[@class='remove-from-cart']/input"));
		
		for (WebElement checkBox : allRemoveCheckBox) {
			checkBox.click();
		}
		driver.findElement(By.xpath("//input[@value='Update wishlist']")).click();
		
	}
}
