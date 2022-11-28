package selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.json.Json;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_4_TextBox_TextArea_Part_2{
	WebDriver driver;
	Actions action;
	String geckodriver = "/Users/geotech/Downloads/Setup-Mac/geckodriver";
	
	//Data test
	String employeeID;
	String Username = "Admin";
	String Password = "admin123";
	String FirstName = "Gracy";
	String LastName = "Ngo";
	String UserNameCreateEmployee = "gracyngo";
	String PasswordCreateEmployee = "Gpp3FprM.2dLXr7G";
	String ConfirmPasswordCreateEmployee = "Gpp3FprM.2dLXr7G";
	
	
	//Elements	
	By txtUsername = By.name("username");
	By txtPassword = By.name("password");
	By btnLogin = By.xpath("//button[text()=' Login ']");
	By btnPim = By.linkText("PIM");
	By dashboard = By.xpath("//h6[text()='Dashboard']");
	By pimHeader = By.xpath("//h6[text()='PIM']");
	By addEmployeeHeader = By.xpath("//h6[text()='Add Employee']");
	By btnAddEmployee = By.linkText("Add Employee");
	By txtFirstName = By.name("firstName");
	By txtLastName = By.name("lastName");
	By txtEmployeeID = By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input");
	By checkboxCreateLoginDetails = By.xpath("//input[@type='checkbox']/following-sibling::span");
	By txtUserNameCreateEmployee = By.xpath("//label[text()='Username']/parent::div/following-sibling::div/input");
	By txtPasswordCreateEmployee = By.xpath("//label[text()='Password']/parent::div/following-sibling::div/input");
	By txtConfirmPasswordCreateEmployee = By.xpath("//label[text()='Confirm Password']/parent::div/following-sibling::div/input");
	By btnSave = By.xpath("//button[text()=' Save ']");
	By hdPersonalDetails = By.xpath("//h6[text()='Personal Details']");
	By btnLogout = By.linkText("Logout");
	By hdLogin = By.xpath("//h5[text()='Login']");
	By btnMyInfo = By.linkText("My Info");
			
	boolean CheckboxIsChecked(By by) {
		WebElement element = driver.findElement(by);
		if(element.isSelected()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", geckodriver );
		driver = new FirefoxDriver();
		action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		
		//Login
		driver.findElement(txtUsername).sendKeys(Username);
		driver.findElement(txtPassword).sendKeys(Password);
		driver.findElement(btnLogin).click();
		
		//Verify dashboard page is displayed
		Assert.assertTrue(driver.findElement(dashboard).isDisplayed());
		
	}

	@Test
	public void TC_01_AddEmployee() throws InterruptedException {
		driver.findElement(btnPim).click();
		//Verify Pim page is displayed
		Assert.assertTrue(driver.findElement(pimHeader).isDisplayed());
		driver.findElement(btnAddEmployee).click();
		Assert.assertTrue(driver.findElement(addEmployeeHeader).isDisplayed());
		driver.findElement(txtFirstName).sendKeys(FirstName);
		driver.findElement(txtLastName).sendKeys(LastName);
		employeeID = driver.findElement(txtEmployeeID).getAttribute("value");
		System.out.println("employeeID: " + employeeID);
		Thread.sleep(10000);
		
		driver.findElement(checkboxCreateLoginDetails).click();
		driver.findElement(txtUserNameCreateEmployee).sendKeys(UserNameCreateEmployee);
		driver.findElement(txtPasswordCreateEmployee).sendKeys(PasswordCreateEmployee);
		driver.findElement(txtConfirmPasswordCreateEmployee).sendKeys(ConfirmPasswordCreateEmployee);
		driver.findElement(btnSave).click();
		Thread.sleep(10000);
		
		Assert.assertTrue(driver.findElement(hdPersonalDetails).isDisplayed());
		Assert.assertEquals(driver.findElement(txtFirstName).getAttribute("value"), FirstName);
		Assert.assertEquals(driver.findElement(txtLastName).getAttribute("value"), LastName);
		Assert.assertEquals(driver.findElement(txtEmployeeID).getAttribute("value"),employeeID);
		
		//Logout
		driver.findElement(By.xpath("//img/following-sibling::p")).click();
		driver.findElement(btnLogout).click();
		Thread.sleep(10000);
		
		//Login
		Assert.assertTrue(driver.findElement(hdLogin).isDisplayed());
		driver.findElement(txtUsername).sendKeys(UserNameCreateEmployee);
		driver.findElement(txtPassword).sendKeys(PasswordCreateEmployee);
		driver.findElement(btnLogin).click();
		Thread.sleep(10000);
		Assert.assertTrue(driver.findElement(dashboard).isDisplayed());
		
		//Verify my info after login successfully
		driver.findElement(btnMyInfo).click();
		Thread.sleep(10000);
		Assert.assertTrue(driver.findElement(hdPersonalDetails).isDisplayed());
		Assert.assertEquals(driver.findElement(txtFirstName).getAttribute("value"), FirstName);
		Assert.assertEquals(driver.findElement(txtLastName).getAttribute("value"), LastName);
		Assert.assertEquals(driver.findElement(txtEmployeeID).getAttribute("value"),employeeID);
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}