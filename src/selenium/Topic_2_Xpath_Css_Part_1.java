package selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_2_Xpath_Css_Part_1 {
	WebDriver driver;
	String geckodriver = "/Users/geotech/Downloads/Setup-Mac/geckodriver";

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", geckodriver);
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://live.techpanda.org/");
	}

	@Test
	public void TC_01_LoginWithEmailAndPasswordEmpty() {
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		String requiredEntryEmail =	driver.findElement(By.xpath("//div[@id='advice-required-entry-email']")).getText();
		Assert.assertEquals(requiredEntryEmail, "This is a required field.");
		String requiredEntryPass = driver.findElement(By.xpath("//div[@id='advice-required-entry-pass']")).getText();
		Assert.assertEquals(requiredEntryPass, "This is a required field.");
	}

	@Test
	public void TC_02_LoginWithEmailInvalid() {
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("1234@123.123");
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		String validateEmail =	driver.findElement(By.xpath("//div[@id='advice-validate-email-email']")).getText();
		Assert.assertEquals(validateEmail, "Please enter a valid email address. For example johndoe@domain.com.");
		String requiredEntryPass = driver.findElement(By.xpath("//div[@id='advice-required-entry-pass']")).getText();
		Assert.assertEquals(requiredEntryPass, "This is a required field.");
	}

	@Test
	public void TC_03_LoginWithPasswordLessThan6Chars() {
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automation@gmail.com");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("12345");
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		String validatePassword = driver.findElement(By.xpath("//div[@id='advice-validate-password-pass']")).getText();
		Assert.assertEquals(validatePassword, "Please enter 6 or more characters without leading or trailing spaces.");
	}
	
	@Test
	public void TC_04_LoginWithPasswordIncorrect() {
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automation@gmail.com");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("1234556");
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		String errorMsg = driver.findElement(By.xpath("//li[@class='error-msg']")).getText();
		Assert.assertEquals(errorMsg, "Invalid login or password.");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}