package selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_3_WebBrowser_WebElement_API_Command_2 {
	WebDriver driver;
	String geckodriver = "/Users/geotech/Downloads/Setup-Mac/geckodriver";

	By mail = By.id("mail");
	By under18 = By.id("under_18");
	By education = By.id("edu");
	By passwordDisabled = By.id("disable_password");
	By bioDisabled = By.id("bio");
	By developmentCheckBox = By.id("development");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", geckodriver);
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	public boolean checkIsDisplayed(By by) {
		WebElement element = driver.findElement(by);
		if (element.isDisplayed()) {
			System.out.println(by + " is Displayed");
			return true;
		} else {
			System.out.println(by + " is not Displayed");
			return false;
		}
	}

	public boolean checkIsEnabled(By by) {
		WebElement element = driver.findElement(by);
		if (element.isEnabled()) {
			System.out.println(by + " is Enabled");
			return true;
		} else {
			System.out.println(by + " is disabled");
			return false;
		}
	}
	
	public void checkToCheckBox(By by) {
		WebElement element = driver.findElement(by);
		if (!element.isSelected()) {
			element.click();
		}
	}
	
	public void unCheckToCheckBox(By by) {
		WebElement element = driver.findElement(by);
		if (element.isSelected()) {
			element.click();
		}
	}

	public boolean checkIsSelected(By by) {
		WebElement element = driver.findElement(by);
		if (element.isSelected()) {
			System.out.println(by + " is Selected");
			return true;
		} else {
			System.out.println(by + " is not Selected");
			return false;
		}
	}
	
	@Test
	public void TC_01_checkElementsIsDisplayed() throws InterruptedException {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		if (checkIsDisplayed(mail)) {
			driver.findElement(mail).sendKeys("Automation Testing");
		}

		if (checkIsDisplayed(under18)) {
			driver.findElement(under18).click();
			Assert.assertTrue(driver.findElement(under18).isSelected());
		}

		if (checkIsDisplayed(education)) {
			driver.findElement(education).sendKeys("Automation Testing");
		}
	}

	@Test
	public void TC_02_checkElementsIsEnabled() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		checkIsEnabled(mail);
		checkIsEnabled(under18);
		checkIsEnabled(bioDisabled);
		checkIsEnabled(developmentCheckBox);
		checkIsEnabled(education);
		checkIsEnabled(passwordDisabled);
	}

	@Test
	public void TC_03_checkElementsIsSelected() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		checkToCheckBox(under18);
		checkToCheckBox(developmentCheckBox);
		
		Assert.assertTrue(checkIsSelected(under18));
		Assert.assertTrue(checkIsSelected(developmentCheckBox));
		
		unCheckToCheckBox(developmentCheckBox);
		
		Assert.assertFalse(checkIsSelected(developmentCheckBox));

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}