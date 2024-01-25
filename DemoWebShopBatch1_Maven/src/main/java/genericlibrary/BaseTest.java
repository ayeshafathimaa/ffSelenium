package genericlibrary;

import java.io.FileNotFoundException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import elementRepository.HomePage;
import elementRepository.LoginPage;

public class BaseTest {
	
	//
	//
	// new changes done 
	//
	//
	public DataUtility data_Utility = new DataUtility();
	public CommonUtility common_Utility = new CommonUtility();
	public WebDriver driver ;
	public static WebDriver listenersDriver;
	public HomePage home_Page ;
	
	
	@Parameters("Browser")
	@BeforeClass(alwaysRun = true)
	public void launchbrowser(@Optional("Chrome")String browserName) throws FileNotFoundException
	{
		if(browserName.equals("Chrome"))
		{
			driver = new ChromeDriver();
		}
		else if (browserName.equals("Edge")) {
			driver = new EdgeDriver();
		}
		else
			System.out.println("Enter valid browser name");
		
		listenersDriver = driver;
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(data_Utility.getDataFromProperties("url"));
		
	}
	@BeforeMethod(alwaysRun = true)
	public void login() throws FileNotFoundException
	{
		home_Page = new HomePage(driver);
		home_Page.getLoginLink().click();
		LoginPage login_Page = new LoginPage(driver);
		login_Page.getEmailTextFeild().sendKeys(data_Utility.getDataFromProperties("email"));
		login_Page.getPasswordTextFeild().sendKeys(data_Utility.getDataFromProperties("password"));
		login_Page.getLoginButton().click();
	}
	@AfterMethod(alwaysRun = true)
	public void logOut()
	{
		
		home_Page.getLogoutLink().click();
	}
	@AfterClass(alwaysRun = true)
	public void closeBrowser()
	{
		driver.close();
	}

}
