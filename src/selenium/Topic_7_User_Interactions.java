package selenium;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_7_User_Interactions {
	WebDriver driver;
	WebDriverWait waitExplicit;
	Alert alert;
	Actions act;
	JavascriptExecutor jE;
	String geckodriver = "/Users/geotech/Downloads/Setup-Mac/geckodriver";

	// Elements
	By txtAge = By.id("age");
	By tooltip = By.xpath("//div[@role='tooltip']");

	By tabKids = By.xpath("//div[@class='desktop-navLink']/a[text()='Kids']");
	By itemToys = By.xpath("//a[text()='Toys']");
	By toysStoreDir = By.xpath("//span[contains(text(), 'Toys Store')]");
	By fahasaMenu = By.xpath("//span[@class='icon_menu']/parent::div");

	By btnDoubleClick = By.xpath("//button[text()='Double-Click Me!']");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", geckodriver);
		driver = new FirefoxDriver();
		jE = (JavascriptExecutor) driver;
		act = new Actions(driver);
		waitExplicit = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test(enabled = true)
	public void TC_01_HoverToElement_Tooltip() {
		driver.get("https://automationfc.github.io/jquery-tooltip/");
		Assert.assertEquals(driver.getTitle(), "jQuery UI Tooltip - Default functionality");

		act.moveToElement(driver.findElement(txtAge)).perform();
		Assert.assertTrue(Check_isDisplayed(tooltip));
	}

	@Test(enabled = true)
	public void TC_02_HoverToElement_myntra() throws InterruptedException {
		driver.get("http://www.myntra.com/");
		Assert.assertEquals(driver.getTitle(), "Online Shopping for Women, Men, Kids Fashion & Lifestyle - Myntra");

		act.moveToElement(driver.findElement(tabKids)).perform();
		Thread.sleep(3000);
		Click_BuildIn(itemToys);
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.myntra.com/toys");
		Assert.assertTrue(Check_isDisplayed(toysStoreDir));
	}

	@Test(enabled = true)
	public void TC_03_ClickAndHold() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		List<WebElement> listArr = driver.findElements(By.xpath("//ol/li"));
		act.clickAndHold(listArr.get(0)).moveToElement(listArr.get(3)).release().perform();
		List<WebElement> listArrSelected = driver.findElements(By.xpath("//ol/li[contains(@class,'ui-selected')]"));
		Assert.assertEquals(listArrSelected.size(), 4);

		driver.navigate().refresh();
		List<WebElement> listAnotherArr = driver.findElements(By.xpath("//ol/li"));
		act.keyDown(Keys.COMMAND).click(listAnotherArr.get(0)).perform();
		act.keyDown(Keys.COMMAND).click(listAnotherArr.get(2)).perform();
		act.keyDown(Keys.COMMAND).click(listAnotherArr.get(5)).perform();
		act.keyDown(Keys.COMMAND).click(listAnotherArr.get(8)).perform();
		act.keyDown(Keys.COMMAND).click(listAnotherArr.get(10)).perform();
		List<WebElement> listAnotherArrSelected = driver
				.findElements(By.xpath("//ol/li[contains(@class,'ui-selected')]"));
		Assert.assertEquals(listAnotherArrSelected.size(), 5);
	}

	@Test(enabled = true)
	public void TC_04_DoubleClick() {
		driver.get("http://seleniumlearn.com/double-click");
		jE.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(btnDoubleClick));
		act.doubleClick(driver.findElement(btnDoubleClick)).perform();
		alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "The Button was double-clicked.");
	}

	@Test(enabled = true)
	public void TC_05_DragAndDrop() {
		driver.get("https://automationfc.github.io/kendo-drag-drop/");
		act.dragAndDrop(driver.findElement(By.id("draggable")), driver.findElement(By.id("droptarget"))).perform();
		Assert.assertEquals(driver.findElement(By.id("droptarget")).getText(), "You did great!");
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