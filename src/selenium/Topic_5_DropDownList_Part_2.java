package selenium;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Topic_5_DropDownList_Part_2 {
	WebDriver driver;
	WebDriverWait waitExplicit;
	Actions actions;
	JavascriptExecutor jE;
	String geckodriver = "/Users/geotech/Downloads/Setup-Mac/geckodriver";

	// Data test

	// Elements
	By parentDropdownNumberMenuJquery = By.xpath("//span[@id='number-button']");
	By allItemsNumberMenuJquery = By.xpath("//ul[@id='number-menu']/li[@class='ui-menu-item']/div");
	By parentDropdownSpeedMenuJquery = By.xpath("//span[@id='speed-button']");
	By allItemsSpeedMenuJquery = By.xpath("//ul[@id='speed-menu']/li[@class='ui-menu-item']/div");
	
	By parentDropdownSelectFriendReact = By.xpath("//div[@data-reactroot='']");
	By allItemsSelectFriendReact = By.xpath("//div[@class='item']/span");
	
	By parentDropdownSelectItemVue = By.xpath("//div[@class='btn-group']");
	By allItemsSelectItemVue = By.xpath("//ul[@class='dropdown-menu']/li/a");
	
	By cookiesPannel = By.xpath("//div[@class='cookies']");
	By btnAccept = By.xpath("//button[text()='CHẤP NHẬN']");
	//By parentDropdownSelectProduct = By.xpath("//div[@class='dropdown']");
	By parentDropdownSelectProduct = By.xpath("//div[@class='dropdown']/button[@id='selectize-input']");
	By allItemsSelectProduct = By.xpath("//button[@id='selectize-input']/following-sibling::div/a");
	By selectProvince = By.xpath("//select[@id='province']");
	By selectRegistrationFee = By.xpath("//select[@id='registration_fee']");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", geckodriver);
		driver = new FirefoxDriver();
		actions = new Actions(driver);
		jE = (JavascriptExecutor)driver;
		waitExplicit = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Demo_jqueryui() throws InterruptedException {
		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
		clickToOpenDropdownList(parentDropdownNumberMenuJquery, allItemsNumberMenuJquery, "19");
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='19']")).isDisplayed());
		Thread.sleep(3000);
		clickToOpenDropdownList(parentDropdownNumberMenuJquery, allItemsNumberMenuJquery, "15");
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='15']")).isDisplayed());
		Thread.sleep(3000);
		
		clickToOpenDropdownList(parentDropdownSpeedMenuJquery, allItemsSpeedMenuJquery, "Slower");
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='speed-button']/span[@class='ui-selectmenu-text' and text()='Slower']")).isDisplayed());
		Thread.sleep(3000);
		clickToOpenDropdownList(parentDropdownSpeedMenuJquery, allItemsSpeedMenuJquery, "Faster");
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='speed-button']/span[@class='ui-selectmenu-text' and text()='Faster']")).isDisplayed());
		Thread.sleep(3000);
	}
	
	@Test
	public void TC_02_Demo_react() throws InterruptedException {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		clickToOpenDropdownList(parentDropdownSelectFriendReact, allItemsSelectFriendReact, "Matt");
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Matt']")).isDisplayed());
		Thread.sleep(3000);
		clickToOpenDropdownList(parentDropdownSelectFriendReact, allItemsSelectFriendReact, "Stevie Feliciano");
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Stevie Feliciano']")).isDisplayed());
		Thread.sleep(3000);
	}
	
	@Test
	public void TC_03_Demo_Vue() throws InterruptedException {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		clickToOpenDropdownList(parentDropdownSelectItemVue, allItemsSelectItemVue, "First Option");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='btn-group']/li")).getText(), "First Option");
		Thread.sleep(3000);
		clickToOpenDropdownList(parentDropdownSelectItemVue, allItemsSelectItemVue, "Second Option");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='btn-group']/li")).getText(), "Second Option");
		Thread.sleep(3000);
		clickToOpenDropdownList(parentDropdownSelectItemVue, allItemsSelectItemVue, "Third Option");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='btn-group']/li")).getText(), "Third Option");
		Thread.sleep(3000);
	}
	
	@Test
	public void TC_04_Honda() throws InterruptedException{
		driver.get("https://www.honda.com.vn/o-to/du-toan-chi-phi");
		if(checkElementIsDisplayed(cookiesPannel)) {
			driver.findElement(btnAccept).click();
		}
		clickToOpenDropdownList(parentDropdownSelectProduct, allItemsSelectProduct, "CR-V E");
		Assert.assertEquals(driver.findElement(By.xpath("//button[text()='CR-V E']")).getText(), "CR-V E");
		clickToOpenDropdownList(parentDropdownSelectProduct, allItemsSelectProduct, "CITY G");
		Assert.assertEquals(driver.findElement(By.xpath("//button[text()='CITY G']")).getText(), "CITY G");
		clickToOpenDropdownList(parentDropdownSelectProduct, allItemsSelectProduct, "CR-V LSE (Đen Ánh)");
		Assert.assertEquals(driver.findElement(By.xpath("//button[text()='CR-V LSE (Đen Ánh)']")).getText(), "CR-V LSE (Đen Ánh)");
		
		Select drpProvince = new Select(driver.findElement(selectProvince));
		jE.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(selectProvince));
		drpProvince.selectByVisibleText("Cần Thơ");
		Assert.assertEquals(drpProvince.getFirstSelectedOption().getText(),"Cần Thơ");
		
		Select dropSelectRegistrationFee = new Select(driver.findElement(selectRegistrationFee));
		jE.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(selectProvince));
		dropSelectRegistrationFee.selectByVisibleText("Khu vực II");
		Assert.assertEquals(dropSelectRegistrationFee.getFirstSelectedOption().getText(),"Khu vực II");
	}
	
	public boolean checkElementIsDisplayed(By by) {
		WebElement element = driver.findElement(by);
		if(element.isDisplayed())
		{
			return true;
		}
		else {
			return false;
		}
	}
	
	public void clickToOpenDropdownList(By parentLocator, By allItems, String expectedItem)
			throws InterruptedException {
		WebElement parentDropdown = driver.findElement(parentLocator);
		jE.executeScript("arguments[0].click();",parentDropdown);
		waitExplicit.until(ExpectedConditions.presenceOfAllElementsLocatedBy(allItems));
		List<WebElement> allItemsInDropdown = driver.findElements(allItems);
		System.out.println("All items: " + allItemsInDropdown.size());
		for (WebElement item : allItemsInDropdown) {
			String actualText = item.getText();
			System.out.println("actualText = " + actualText);
			if (actualText.equals(expectedItem)) {
				Thread.sleep(5000);
				item.click();
				break;
			}
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}