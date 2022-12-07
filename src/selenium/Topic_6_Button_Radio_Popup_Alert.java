package selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_6_Button_Radio_Popup_Alert {
	WebDriver driver;
	WebDriverWait waitExplicit;
	Alert alert;
	JavascriptExecutor jE;
	String geckodriver = "/Users/geotech/Downloads/Setup-Mac/geckodriver";

	// Data test
	String FahasaUsrName = "Your User Name";
	String FahasaPassword = "Your Password";

	// Elements
	By logoFahasa = By.xpath("//div[@title='FAHASA.COM']");
	By tabLoginFahasa = By.linkText("Đăng nhập");
	By txtUsrNameFahasa = By.id("login_username");
	By txtPwdFahasa = By.id("login_password");
	By btnLoginFahasa = By.xpath("//form//button[@title='Đăng nhập']");

	By titleKendo = By.xpath("//h1[@class='kd-title']");
	By cbDualZoneACKendo = By.xpath("//label[text()='Dual-zone air conditioning']//preceding-sibling::input");
	By rdoPetro = By.xpath("//label[text()='2.0 Petrol, 147kW']//preceding-sibling::input");

	By rdoSummer = By.xpath("//label[text()=' Summer ']/preceding-sibling::div/input");
	By cbChecked = By.xpath("//label[text()='Checked']/preceding-sibling::div/input");
	By cbIndeterminate = By.xpath("//label[text()='Indeterminate']/preceding-sibling::div/input");

	By titleAutomationFC = By.xpath("//h1[text()='SELENIUM WEBDRIVER API']");
	By btnClickForJSAlert = By.xpath("//button[text()='Click for JS Alert']");
	By resultClickForJSAlert = By.xpath("//p[contains(text(),'You clicked an alert successfully')]");

	By btnClickForJSConfirmAlert = By.xpath("//button[text()='Click for JS Confirm']");
	By resultClickForJSConfirmAlert = By.xpath("//p[contains(text(),'You clicked: Cancel')]");

	By btnClickForJSPromptAlert = By.xpath("//button[text()='Click for JS Prompt']");
	By resultClickForJSPromptAlert = By.xpath("//p[contains(text(),'You entered: liam')]");

	By authenticateSuccess = By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", geckodriver);
		driver = new FirefoxDriver();
		jE = (JavascriptExecutor) driver;
		waitExplicit = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test(enabled = true)
	public void TC_01_Button() {
		driver.get("https://www.fahasa.com/customer/account/create");
		Assert.assertTrue(Check_isDisplayed(logoFahasa));

		// Switch to login tab
		Click_BuildIn(tabLoginFahasa);

		// Verify login button is disabled
		Assert.assertFalse(Check_IsEnabled(btnLoginFahasa));

		// Verify login button is enabled
		driver.findElement(txtUsrNameFahasa).sendKeys(FahasaUsrName);
		driver.findElement(txtPwdFahasa).sendKeys(FahasaPassword);
		Assert.assertTrue(Check_IsEnabled(btnLoginFahasa));
	}

	@Test(enabled = true)
	public void TC_02_Default_CheckBox_Or_Radio_Button() {
		// CheckBox
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		Assert.assertTrue(Check_isDisplayed(titleKendo));
		Click_JS(cbDualZoneACKendo);
		Assert.assertTrue(Check_IsSelected(cbDualZoneACKendo));
		Click_JS(cbDualZoneACKendo);
		Assert.assertFalse(Check_IsSelected(cbDualZoneACKendo));

		// Radio Button
		driver.get("http://demos.telerik.com/kendo-ui/styling/radios");
		Assert.assertTrue(Check_isDisplayed(titleKendo));
		Click_JS(rdoPetro);
		Assert.assertTrue(Check_IsSelected(rdoPetro));
	}

	@Test(enabled = true)
	public void TC_03_and_04_Custom_CheckBox_Or_Radio_Button() {
		// Radio button
		driver.get("https://material.angular.io/components/radio/examples");
		Click_BuildIn(rdoSummer);
		Assert.assertTrue(Check_IsSelected(rdoSummer));

		// CheckBox
		driver.get("https://material.angular.io/components/checkbox/examples");
		Click_BuildIn(cbChecked);
		Assert.assertTrue(Check_IsSelected(cbChecked));
		Click_BuildIn(cbIndeterminate);
		Assert.assertTrue(Check_IsSelected(cbIndeterminate));
	}

	@Test(enabled = true)
	public void TC_05_Accept_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		Assert.assertTrue(Check_isDisplayed(titleAutomationFC));

		Click_BuildIn(btnClickForJSAlert);
		alert = driver.switchTo().alert();
		String Msg = alert.getText();
		Assert.assertEquals(Msg, "I am a JS Alert");
		alert.accept();
		Assert.assertTrue(Check_isDisplayed(resultClickForJSAlert));
	}

	@Test(enabled = true)
	public void TC_06_Confirm_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		Assert.assertTrue(Check_isDisplayed(titleAutomationFC));

		Click_BuildIn(btnClickForJSConfirmAlert);
		alert = driver.switchTo().alert();
		String Msg = alert.getText();
		Assert.assertEquals(Msg, "I am a JS Confirm");
		alert.dismiss();
		Assert.assertTrue(Check_isDisplayed(resultClickForJSConfirmAlert));
	}

	@Test(enabled = true)
	public void TC_07_Prompt_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		Assert.assertTrue(Check_isDisplayed(titleAutomationFC));

		Click_BuildIn(btnClickForJSPromptAlert);
		alert = driver.switchTo().alert();
		String Msg = alert.getText();
		Assert.assertEquals(Msg, "I am a JS prompt");
		alert.sendKeys("liam");
		alert.accept();
		Assert.assertTrue(Check_isDisplayed(resultClickForJSPromptAlert));
	}

	@Test(enabled = true)
	public void TC_08_Authenticate_Alert() {
		String url = "http://the-internet.herokuapp.com/basic_auth";
		String UsrName = "admin";
		String Password = "admin";
		Handle_Url_Authenticate_Alert(url, UsrName, Password);
		driver.get(url);
		Assert.assertTrue(Check_isDisplayed(authenticateSuccess));
	}

	public String Handle_Url_Authenticate_Alert(String url, String usrName, String pwd) {
		String[] output = url.split("//");
		String finalUrl = output[0] + "//" + usrName + ":" + pwd + "@" + output[1];
		return finalUrl;
	}

	public boolean Check_isDisplayed(By by) {
		WebElement element = driver.findElement(by);
		if (element.isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean Check_IsEnabled(By by) {
		WebElement element = driver.findElement(by);
		if (element.isEnabled()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean Check_IsSelected(By by) {
		WebElement element = driver.findElement(by);
		if (element.isSelected()) {
			return true;
		} else {
			return false;
		}
	}

	public void Click_BuildIn(By by) {
		WebElement element = driver.findElement(by);
		element.click();
	}

	public void Click_JS(By by) {
		WebElement element = driver.findElement(by);
		jE.executeScript("arguments[0].click();", element);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}