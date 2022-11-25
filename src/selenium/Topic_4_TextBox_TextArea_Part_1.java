package selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_4_TextBox_TextArea_Part_1{
	WebDriver driver;
	String geckodriver = "/Users/geotech/Downloads/Setup-Mac/geckodriver";
	
	//Data test
	String customerID;
	String userName = "mngr455858";
	String pwd = "enEsyja";
	String name = "Darllen Queyeiro";
	String dateOfBirth = "1998-10-09";
	String address = "843 Pleasure Street";
	String city = "Milwaukee";
	String state = "Wisconsin";
	String pin = "958624";
	String phone = "4146893511";
	String email = "dqueyeiroi01@blinklist.com";
	String password = "111222";
	
	String newAddress = "15 Drewry Avenue";
	String newCity = "Elizabeth";
	String newState = "New Jersey";
	String newPin = "658234";
	String newPhone = "9086646701";
	String newEmail = "sdoth01@cbsnews.com";
	
	
	//Elements
	By txtUserId = By.xpath("//input[@name='uid']");
	By txtPassword = By.xpath("//input[@name='password']");
	By btnLogin = By.xpath("//input[@name='btnLogin']");
	By marquee = By.xpath("//marquee[@class='heading3']");
	By btnNewCustomer = By.linkText("New Customer");
	By btnEditCustomer = By.linkText("Edit Customer");
	By txtName = By.name("name");
	By dobDateOfBirth = By.name("dob");
	By txtAreaAddress = By.name("addr");
	By txtCity = By.name("city");
	By txtState = By.name("state");
	By txtPin = By.name("pinno");
	By txtPhoneNumber = By.name("telephoneno");
	By txtEmail = By.name("emailid");
	By txtPwd = By.name("password");
	By btnSubmit = By.name("sub");
	By btnSubmitEditCustomer = By.name("AccSubmit");
	
	By customerNameRow = By.xpath("//td[text()='Customer Name']/following-sibling::td");
	By dobRow = By.xpath("//td[text()='Birthdate']/following-sibling::td");
	By addressRow = By.xpath("//td[text()='Address']/following-sibling::td");
	By cityRow = By.xpath("//td[text()='City']/following-sibling::td");
	By stateRow = By.xpath("//td[text()='State']/following-sibling::td");
	By pinRow = By.xpath("//td[text()='Pin']/following-sibling::td");
	By phoneRow = By.xpath("//td[text()='Mobile No.']/following-sibling::td");
	By emailRow = By.xpath("//td[text()='Email']/following-sibling::td");
	By customerIDRow = By.xpath("//td[text()='Customer ID']/following-sibling::td");
	
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", geckodriver );
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://demo.guru99.com/v4/");
		
		//Login
		driver.findElement(txtUserId).sendKeys(userName);
		driver.findElement(txtPassword).sendKeys(pwd);
		driver.findElement(btnLogin).click();
		
		//Verify HomePage displayed
		Assert.assertEquals(driver.findElement(marquee).getText(), "Welcome To Manager's Page of Guru99 Bank");
	}

	@Test
	public void TC_01_NewCustomer() {
		//Open New Customer Page
		driver.findElement(btnNewCustomer).click();
		
		//Input valid value
		driver.findElement(txtName).sendKeys(name);
		driver.findElement(dobDateOfBirth).sendKeys(dateOfBirth);
		driver.findElement(txtAreaAddress).sendKeys(address);
		driver.findElement(txtCity).sendKeys(city);
		driver.findElement(txtState).sendKeys(state);
		driver.findElement(txtPin).sendKeys(pin);
		driver.findElement(txtPhoneNumber).sendKeys(phone);
		driver.findElement(txtEmail).sendKeys(email);
		driver.findElement(txtPassword).sendKeys(password);
		
		//submit
		driver.findElement(btnSubmit).click();
		
		//Verify Create new customer successfully
		Assert.assertEquals(driver.findElement(By.xpath("//table[@id='customer']//p[@class='heading3']")).getText(),"Customer Registered Successfully!!!");
		Assert.assertEquals(driver.findElement(customerNameRow).getText(),name);
		Assert.assertEquals(driver.findElement(dobRow).getText(),dateOfBirth);
		Assert.assertEquals(driver.findElement(addressRow).getText(),address);
		Assert.assertEquals(driver.findElement(cityRow).getText(),city);
		Assert.assertEquals(driver.findElement(stateRow).getText(),state);
		Assert.assertEquals(driver.findElement(pinRow).getText(),pin);
		Assert.assertEquals(driver.findElement(phoneRow).getText(),phone);
		Assert.assertEquals(driver.findElement(emailRow).getText(),email);
		
		//Get Customer Id
		customerID = driver.findElement(customerIDRow).getText();
	}
	
	@Test
	public void TC_02_EditCustomer() {
		driver.findElement(btnEditCustomer).click();
		driver.findElement(By.name("cusid")).sendKeys(customerID);
		driver.findElement(btnSubmitEditCustomer).click();
		
		//Verify new Customer show correct
		Assert.assertEquals(driver.findElement(customerNameRow).getAttribute("value"),name);
		Assert.assertEquals(driver.findElement(dobRow).getAttribute("value"),dateOfBirth);
		Assert.assertEquals(driver.findElement(addressRow).getText(),address);
		Assert.assertEquals(driver.findElement(cityRow).getAttribute("value"),city);
		Assert.assertEquals(driver.findElement(stateRow).getAttribute("value"),state);
		Assert.assertEquals(driver.findElement(pinRow).getAttribute("value"),pin);
		Assert.assertEquals(driver.findElement(phoneRow).getAttribute("value"),phone);
		Assert.assertEquals(driver.findElement(emailRow).getAttribute("value"),email);
		
		//Edit Customer
		driver.findElement(txtAreaAddress).clear();
		driver.findElement(txtAreaAddress).sendKeys(newAddress);
		driver.findElement(txtCity).clear();
		driver.findElement(txtCity).sendKeys(newCity);
		driver.findElement(txtState).clear();
		driver.findElement(txtState).sendKeys(newState);
		driver.findElement(txtPin).clear();
		driver.findElement(txtPin).sendKeys(newPin);
		driver.findElement(txtPhoneNumber).clear();
		driver.findElement(txtPhoneNumber).sendKeys(newPhone);
		driver.findElement(txtEmail).clear();
		driver.findElement(txtEmail).sendKeys(newEmail);
		driver.findElement(btnSubmitEditCustomer).click();
		
		//Verify Edit Customer
		Assert.assertEquals(driver.findElement(By.xpath("//table[@id='customer']//p[@class='heading3']")).getText(),"Customer details updated Successfully!!!");
		Assert.assertEquals(driver.findElement(customerNameRow).getText(),name);
		Assert.assertEquals(driver.findElement(dobRow).getText(),dateOfBirth);
		Assert.assertEquals(driver.findElement(addressRow).getText(),newAddress);
		Assert.assertEquals(driver.findElement(cityRow).getText(),newCity);
		Assert.assertEquals(driver.findElement(stateRow).getText(),newState);
		Assert.assertEquals(driver.findElement(pinRow).getText(),newPin);
		Assert.assertEquals(driver.findElement(phoneRow).getText(),newPhone);
		Assert.assertEquals(driver.findElement(emailRow).getText(),newEmail);
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}