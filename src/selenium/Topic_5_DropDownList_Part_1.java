package selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_5_DropDownList_Part_1 {
	WebDriver driver;
	String geckodriver = "/Users/geotech/Downloads/Setup-Mac/geckodriver";

	// Data test(nopcommerce)
	String FirstName = "Tina";
	String LastName = "Dang";
	String Email = "tinadang@gmail.com";
	String Company = "Automation";
	String Password = "Gpp3FprM.2dLXr7G";
	String ConfirmPassword = "Gpp3FprM.2dLXr7G";
	String RegistrationCompleted = "Your registration completed";

	// Elements(nopcommerce)
	By HeaderLogo = By.xpath("//div[@class='header-logo']/a/img");
	By btnRegister = By.linkText("Register");
	By btnMyAccount = By.linkText("My account");
	By headerRegisterPage = By.xpath("//h1[text()='Register']");
	By radioMale = By.id("gender-male");
	By radioFemale = By.id("gender-female");
	By DoBDay = By.xpath("//select[@name='DateOfBirthDay']");
	By DoBDaySelected = By.xpath("//select[@name='DateOfBirthDay']//option[@selected='']");
	By DoBMonth = By.xpath("//select[@name='DateOfBirthMonth']");
	By DoBMonthSelected = By.xpath("//select[@name='DateOfBirthMonth']//option[@selected='']");
	By DoBYear = By.xpath("//select[@name='DateOfBirthYear']");
	By DoBYearSelected = By.xpath("//select[@name='DateOfBirthYear']//option[@selected='']");
	By txtFirstName = By.id("FirstName");
	By txtLastName = By.id("LastName");
	By txtEmail = By.id("Email");
	By txtCompany = By.id("Company");
	By checkboxNewsletter = By.id("Newsletter");
	By txtPassword = By.id("Password");
	By txtConfirmPassword = By.id("ConfirmPassword");
	By btnRegisterButton = By.id("register-button");
	By registerPageTitle = By.xpath("//h1[text()='Register']");
	By registerResult = By.xpath("//div[@class='result']");
	By myaccountPageTitle = By.xpath("//h1[text()='My account - Customer info']");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", geckodriver);
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	boolean CheckboxIsChecked(By by) {
		WebElement element = driver.findElement(by);
		if (element.isSelected()) {
			return true;
		} else {
			return false;
		}
	}

	public String getTextFromSelectedOption(By by, By _by) {
		WebElement element = driver.findElement(by);
		WebElement _element = driver.findElement(_by);
		element.click();
		return _element.getText();
	}

	@Test
	public void TC_01_Demo_nopcommerce() {
		driver.get("https://demo.nopcommerce.com/register");
		Assert.assertTrue(driver.findElement(HeaderLogo).isDisplayed());
		driver.findElement(btnRegister).click();
		Assert.assertTrue(driver.findElement(headerRegisterPage).isDisplayed());

		driver.findElement(radioFemale).click();
		driver.findElement(txtFirstName).sendKeys(FirstName);
		driver.findElement(txtLastName).sendKeys(LastName);

		// Verify Day has 32 items
		Select drpDateOfBirthDay = new Select(driver.findElement(DoBDay));
		drpDateOfBirthDay.selectByVisibleText("25");
		Assert.assertEquals(drpDateOfBirthDay.getFirstSelectedOption().getText(), "25");
		Assert.assertEquals(drpDateOfBirthDay.getOptions().size(), 32);

		// Verify month has 13 items
		Select drpDateOfBirthMonth = new Select(driver.findElement(DoBMonth));
		drpDateOfBirthMonth.selectByVisibleText("November");
		Assert.assertEquals(drpDateOfBirthMonth.getFirstSelectedOption().getText(), "November");
		Assert.assertEquals(drpDateOfBirthMonth.getOptions().size(), 13);

		// Verify year has 112 items
		Select drpDateOfBirthYear = new Select(driver.findElement(DoBYear));
		drpDateOfBirthYear.selectByVisibleText("1933");
		Assert.assertEquals(drpDateOfBirthYear.getFirstSelectedOption().getText(), "1933");
		Assert.assertEquals(drpDateOfBirthYear.getOptions().size(), 112);

		driver.findElement(txtEmail).sendKeys(Email);
		driver.findElement(txtCompany).sendKeys(Company);
		if (CheckboxIsChecked(checkboxNewsletter)) {
			System.out.println("Newsletter: " + "is checked");
		} else {
			driver.findElement(checkboxNewsletter).click();
		}

		driver.findElement(txtPassword).sendKeys(Password);
		driver.findElement(txtConfirmPassword).sendKeys(ConfirmPassword);
		driver.findElement(btnRegisterButton).click();

		// Verify Registration Completed
		Assert.assertEquals(driver.findElement(registerPageTitle).getText(), "Register");
		Assert.assertEquals(driver.findElement(registerResult).getText(), RegistrationCompleted);

		// Verify my account
		driver.findElement(btnMyAccount).click();
		Assert.assertTrue(driver.findElement(myaccountPageTitle).isDisplayed());
		Assert.assertEquals(driver.findElement(txtFirstName).getAttribute("value"), FirstName);
		Assert.assertEquals(driver.findElement(txtLastName).getAttribute("value"), LastName);
		Assert.assertEquals(getTextFromSelectedOption(DoBDay, DoBDaySelected), "25");
		Assert.assertEquals(getTextFromSelectedOption(DoBMonth, DoBMonthSelected), "November");
		Assert.assertEquals(getTextFromSelectedOption(DoBYear, DoBYearSelected), "1933");
		Assert.assertEquals(driver.findElement(txtEmail).getAttribute("value"), Email);
		Assert.assertEquals(driver.findElement(txtCompany).getAttribute("value"), Company);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}