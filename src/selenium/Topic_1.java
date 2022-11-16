package selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_1{
	WebDriver driver;
	String geckodriver = "/Users/geotech/Downloads/Setup-Mac/geckodriver";
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", geckodriver );
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://demo.guru99.com/v4/");
	}

	@Test
	public void TC_01_ValidateCurrentUrl() {
		String currentPageUrl = driver.getCurrentUrl();
		Assert.assertEquals(currentPageUrl, "https://demo.guru99.com/v4/");
	}
	
	@Test
	public void TC_02_ValidatePageTitle() {
		String currentPageTile = driver.getTitle();
		Assert.assertEquals(currentPageTile, "Guru99 Bank Home Page");
	}
	
	@Test
	public void TC_03_LoginFormisDisplayed() { 
		Assert.assertTrue(driver.findElement(By.xpath("//form[@name='frmLogin']")).isDisplayed());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}