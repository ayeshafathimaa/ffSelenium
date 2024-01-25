package genericlibrary;

import java.awt.dnd.DropTarget;
import java.io.File;
import java.io.IOException;
import java.sql.Driver;
import java.util.Random;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.Select;

public class CommonUtility {
	
	Select s;
	
	public int getRandomNumber()
	{
		Random r = new Random();
		return r.nextInt(1000);
	}
		
	public void toTakeScreenShot(WebDriver driver,String name)
	{
		String path = FrameWorkConstants.screenshot_Path+name+".png";
		TakesScreenshot ts=(TakesScreenshot)driver;
		File src=ts.getScreenshotAs(OutputType.FILE);
		File trg=new File(path);
		try {
			FileHandler.copy(src, trg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void acceptAlertPopup(WebDriver driver)
	{
		driver.switchTo().alert().accept();
	}
	
	public void dissmissAlertPopup(WebDriver driver)
	{
		driver.switchTo().alert().dismiss();
	}
	
	public void selectDropDownByIndex(WebElement dropDown, int indexNum)
	{
		s = new Select(dropDown);
		s.selectByIndex(indexNum);
	}
	
	public void selectDropDownByVisibleText(WebElement dropDown, String text)
	{
		s = new Select(dropDown);
		s.selectByVisibleText(text);
	}
	
	public void selectDropDownByValue(WebElement dropDown, String value)
	{
		s = new Select(dropDown);
		s.selectByValue(value);
	}
	
	
	

}
