package testscripts;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import elementRepository.HomePage;
import elementRepository.RegisterPage;
import genericlibrary.CommonUtility;
import genericlibrary.DataUtility;

public class TC01_TestRegisterTest {
	//ghj
	
	@Test(groups = "Integration",dataProvider = "Register")
	public void registerTestCase(String firstName, String lastName, String email,String password, String confirmPassword) throws FileNotFoundException {
		
		//UserName = Testengg111@gmial.com
		//password = Engg@123
		DataUtility data_Utility = new DataUtility();
		CommonUtility common_Utility = new CommonUtility();
		
		String modEmail=common_Utility.getRandomNumber()+email;
		
		//Step 1 : launch the browser
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(data_Utility.getDataFromProperties("url"));
				
		Assert.assertEquals(driver.getTitle(), "Demo Web Shop");
		Reporter.log("Demo Web Shop Page Displayed..");
		HomePage home_Page = new HomePage(driver);
		
		//Step 3: Navigating to Register Page
		home_Page.getRegisterLink().click();
				
		Assert.assertEquals(driver.getTitle(), "Demo Web Shop. Register");
		Reporter.log("Register Page Displayed..");
		
		//Step 5: entering valid the inputs 
		RegisterPage register_Page=new RegisterPage(driver);
		register_Page.getMaleRadioButton().click();
		register_Page.getFirstNameTextFeild().sendKeys(firstName);
		register_Page.getLastNameTextFeild().sendKeys(lastName);
		register_Page.getEmailTextFeild().sendKeys(modEmail);
		register_Page.getPasswordTextFeild().sendKeys(password);
		register_Page.getConfirmPasswordTextFeild().sendKeys(confirmPassword);
		register_Page.getRegisterButton().click();
		
		//Step 6: fetching the register sucessfull message
		String expText=register_Page.getSuccessfullMessage().getText();
			
		Assert.assertEquals(expText, "Your registration completed","Register Test Case Fail");
		Reporter.log("Register Test Case Pass");
		
		//Step 8: Perform Logout
		home_Page.getLogoutLink().click();
		
		//Step 9: Close the browser
		driver.close();	
		
	}
	
	@DataProvider(name = "Register")
	public Object[][] dataSupply() throws EncryptedDocumentException, IOException
	{
		return DataUtility.getAllDataFromExcel("Sheet1");
	}
	
}