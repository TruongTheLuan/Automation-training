package selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_3_WebBrowser_WebElement_API_Command_1 {
	WebDriver driver;
	String geckodriver = "/Users/geotech/Downloads/Setup-Mac/geckodriver";
	By myAccount = By.xpath("//div[@class='footer']//a[@title='My Account']");
	By createMyAccount = By.xpath("//a[@title='Create an Account']");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", geckodriver);
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_getCurrentUrl() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(myAccount).click();
		String urlLoginPage = driver.getCurrentUrl();
		Assert.assertEquals(urlLoginPage,"http://live.techpanda.org/index.php/customer/account/login/");
		
		driver.findElement(createMyAccount).click();
		String urlRegisterPage = driver.getCurrentUrl();
		Assert.assertEquals(urlRegisterPage, "http://live.techpanda.org/index.php/customer/account/create/");
	}
	
	@Test
	public void TC_02_getTitle() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(myAccount).click();
		String titleLoginPage = driver.getTitle();
		Assert.assertEquals(titleLoginPage,"Customer Login");
		
		driver.findElement(createMyAccount).click();
		String titleRegisterPage = driver.getTitle();
		Assert.assertEquals(titleRegisterPage,"Create New Customer Account");
	}
	
	@Test
	public void TC_03_navigateFunction() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(myAccount).click();
		driver.findElement(createMyAccount).click();
		
		//Verify urlRegisterPage
		String urlRegisterPage = driver.getCurrentUrl();
		Assert.assertEquals(urlRegisterPage, "http://live.techpanda.org/index.php/customer/account/create/");
		
		//Verify urlLoginPage
		driver.navigate().back();
		String urlLoginPage = driver.getCurrentUrl();
		Assert.assertEquals(urlLoginPage,"http://live.techpanda.org/index.php/customer/account/login/");
		
		//Verify titleRegisterPage
		driver.navigate().forward();
		String titleRegisterPage = driver.getTitle();
		Assert.assertEquals(titleRegisterPage,"Create New Customer Account");
		
	}
	
	@Test
	public void TC_04_getPageSource() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(myAccount).click();
		Assert.assertTrue(driver.getPageSource().contains("Login or Create an Account"));
		
		driver.findElement(createMyAccount).click();
		Assert.assertTrue(driver.getPageSource().contains("Create an Account"));
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}