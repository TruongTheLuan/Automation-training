package selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_2_Xpath_Css_Part_2 {
	WebDriver driver;
	String geckodriver = "/Users/geotech/Downloads/Setup-Mac/geckodriver";

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", geckodriver);
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://alada.vn/");
	}

	@Test
	public void TC_01_RegisterWithEmptyData() {
		driver.findElement(By.xpath("//span[@class='box-item-login']/a")).click();
		if(driver.findElement(By.xpath("//input[@id='chkRight']")).isSelected()) {
			driver.findElement(By.xpath("//div[@class='field_btn']/button")).click();
		}
		else {
			driver.findElement(By.xpath("//input[@id='chkRight']")).click();
			driver.findElement(By.xpath("//div[@class='field_btn']/button")).click();
		}
		driver.findElement(By.xpath("//div[@class='field_btn']/button")).click();
		String txtFirstnameError = driver.findElement(By.xpath("//label[@id='txtFirstname-error']")).getText();
		Assert.assertEquals(txtFirstnameError, "Vui lòng nhập họ tên");
		
		String txtEmailError = driver.findElement(By.xpath("//label[@id='txtEmail-error']")).getText();
		Assert.assertEquals(txtEmailError, "Vui lòng nhập email");
		
		String txtCEmailError = driver.findElement(By.xpath("//label[@id='txtCEmail-error']")).getText();
		Assert.assertEquals(txtCEmailError, "Vui lòng nhập lại địa chỉ email");
		
		String txtPasswordError = driver.findElement(By.xpath("//label[@id='txtPassword-error']")).getText();
		Assert.assertEquals(txtPasswordError, "Vui lòng nhập mật khẩu");
		
		String txtCPasswordError = driver.findElement(By.xpath("//label[@id='txtCPassword-error']")).getText();
		Assert.assertEquals(txtCPasswordError, "Vui lòng nhập lại mật khẩu");
		
		String txtPhoneError = driver.findElement(By.xpath("//label[@id='txtPhone-error']")).getText();
		Assert.assertEquals(txtPhoneError, "Vui lòng nhập số điện thoại.");
	}
	
	@Test
	public void TC_02_RegisterWithInvalidEmail() {
		driver.navigate().refresh();
		driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("johny nguyen");
		driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("123@345@789");
		driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("123@345@789");
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("Gpp3FprM.2dLXr7G");
		driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("Gpp3FprM.2dLXr7G");
		driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("0987654321");
		if(driver.findElement(By.xpath("//input[@id='chkRight']")).isSelected()) {
			driver.findElement(By.xpath("//div[@class='field_btn']/button")).click();
		}
		else {
			driver.findElement(By.xpath("//input[@id='chkRight']")).click();
			driver.findElement(By.xpath("//div[@class='field_btn']/button")).click();
		}
		driver.findElement(By.xpath("//div[@class='field_btn']/button")).click();
		String txtEmailError = driver.findElement(By.xpath("//label[@id='txtEmail-error']")).getText();
		Assert.assertEquals(txtEmailError, "Vui lòng nhập email hợp lệ");
		
		String txtCEmailError = driver.findElement(By.xpath("//label[@id='txtCEmail-error']")).getText();
		Assert.assertEquals(txtCEmailError, "Email nhập lại không đúng");
	}

	@Test
	public void TC_03_RegisterWithIncorrectConfirmEmail() {
		driver.navigate().refresh();
		driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("johny nguyen");
		driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("johnynguyen@gmail.com");
		driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("123@345@789");
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("Gpp3FprM.2dLXr7G");
		driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("Gpp3FprM.2dLXr7G");
		driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("0987654321");
		if(driver.findElement(By.xpath("//input[@id='chkRight']")).isSelected()) {
			driver.findElement(By.xpath("//div[@class='field_btn']/button")).click();
		}
		else {
			driver.findElement(By.xpath("//input[@id='chkRight']")).click();
			driver.findElement(By.xpath("//div[@class='field_btn']/button")).click();
		}
		driver.findElement(By.xpath("//div[@class='field_btn']/button")).click();
		String txtCEmailError = driver.findElement(By.xpath("//label[@id='txtCEmail-error']")).getText();
		Assert.assertEquals(txtCEmailError, "Email nhập lại không đúng");
	}

	@Test
	public void TC_04_RegisterWithPasswordLessThan6Chars() {
		driver.navigate().refresh();
		driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("johny nguyen");
		driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("johnynguyen@gmail.com");
		driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("johnynguyen@gmail.com");
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("12345");
		driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("12345");
		driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("0987654321");
		if(driver.findElement(By.xpath("//input[@id='chkRight']")).isSelected()) {
			driver.findElement(By.xpath("//div[@class='field_btn']/button")).click();
		}
		else {
			driver.findElement(By.xpath("//input[@id='chkRight']")).click();
			driver.findElement(By.xpath("//div[@class='field_btn']/button")).click();
		}
		driver.findElement(By.xpath("//div[@class='field_btn']/button")).click();
		String txtPasswordError = driver.findElement(By.xpath("//label[@id='txtPassword-error']")).getText();
		Assert.assertEquals(txtPasswordError, "Mật khẩu phải có ít nhất 6 ký tự");
		
		String txtCPasswordError = driver.findElement(By.xpath("//label[@id='txtCPassword-error']")).getText();
		Assert.assertEquals(txtCPasswordError, "Mật khẩu phải có ít nhất 6 ký tự");
	}

	@Test
	public void TC_05_RegisterWithIncorrectConfirmPassword() {
		driver.navigate().refresh();
		driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("johny nguyen");
		driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("johnynguyen@gmail.com");
		driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("johnynguyen@gmail.com");
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("Gpp3FprM.2dLXr7G");
		driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("Gpp3FprM.2dLXr");
		driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("0987654321");
		if(driver.findElement(By.xpath("//input[@id='chkRight']")).isSelected()) {
			driver.findElement(By.xpath("//div[@class='field_btn']/button")).click();
		}
		else {
			driver.findElement(By.xpath("//input[@id='chkRight']")).click();
			driver.findElement(By.xpath("//div[@class='field_btn']/button")).click();
		}
		driver.findElement(By.xpath("//div[@class='field_btn']/button")).click();
		String txtCPasswordError = driver.findElement(By.xpath("//label[@id='txtCPassword-error']")).getText();
		Assert.assertEquals(txtCPasswordError, "Mật khẩu bạn nhập không khớp");
	}

	@Test
	public void TC_06_RegisterWithInvalidPhoneNumber() {
		driver.navigate().refresh();
		driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("johny nguyen");
		driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("johnynguyen@gmail.com");
		driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("johnynguyen@gmail.com");
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("Gpp3FprM.2dLXr7G");
		driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("Gpp3FprM.2dLXr7G");
		driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("0987654");
		if(driver.findElement(By.xpath("//input[@id='chkRight']")).isSelected()) {
			driver.findElement(By.xpath("//div[@class='field_btn']/button")).click();
		}
		else {
			driver.findElement(By.xpath("//input[@id='chkRight']")).click();
			driver.findElement(By.xpath("//div[@class='field_btn']/button")).click();
		}
		driver.findElement(By.xpath("//div[@class='field_btn']/button")).click();
		String txtPhoneError = driver.findElement(By.xpath("//label[@id='txtPhone-error']")).getText();
		Assert.assertEquals(txtPhoneError, "Số điện thoại phải từ 10-11 số.");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}