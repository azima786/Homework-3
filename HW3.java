package Homework2;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class HW3 {
	
WebDriver driver; 
String browser;
String CompanyName;
String Name;
String email;
String phone; 
String zip;
String address;
String state;
String city;

@BeforeTest
public void readHW() {
	Properties property = new Properties();
	
	//Input Stream, Buffer Reader, Scanner, File Reader - Class that can help us read any file
	
	try{
		
		InputStream inputs = new FileInputStream("data\\config.properties");
		
property.load(inputs);
	
	browser = property.getProperty("browser");
	CompanyName = property.getProperty("CompanyName");
	Name = property.getProperty("Name");
	email = property.getProperty("email");
	phone = property.getProperty("phone");
	zip = property.getProperty("zip");
	address = property.getProperty("address");
	state = property.getProperty("state");
	city = property.getProperty("city");
	}
	
	catch(IOException e){
		e.printStackTrace();
	}
	}

@BeforeClass
public void start() {
	if(browser.equalsIgnoreCase("chrome")) {
		System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
	driver = new ChromeDriver();}
		
	else if (browser.equalsIgnoreCase("FireFox")) 
	{System.setProperty("webdriver.gecko.driver", "drivers\\geckodriver.exe");
		driver = new FirefoxDriver();}
}

@BeforeMethod
public void startbrowser() {


	driver.get("https://techfios.com/billing/?ng=login/");
	driver.manage().deleteAllCookies();
	driver.manage().window().maximize();
	
	
	String login = "demo@techfios.com";
	String password = "abc123";
	
	Assert.assertEquals(driver.getTitle(), "Login - iBilling", "Incorrect!");
	
	By Username = By.xpath("//input[@id=\"username\"]");
	By Password = By.xpath("//input[@id=\"password\"]");
	By Login = By.xpath("//button[@name=\"login\"]");
	
	driver.findElement(Username).sendKeys(login);
	driver.findElement(Password).sendKeys(password);
	driver.findElement(Login).click();
	
	Assert.assertEquals(driver.getTitle(), "Dashboard- iBilling", "Wrong Page!!!!");}
	
@Test
public void Test() {
	//By Class
	By Customers = By.xpath("//span[contains(text(), \"Customers\")]");
	By AddCustomer = By.xpath("//a[contains(text(), \"Add Customer\")]");
	By FullName = By.xpath("//input[@id=\"account\"]");
	By Email = By.xpath("//input[@id=\"email\"]");
	By Phone = By.xpath("//input[@id=\"phone\"]");
	By Address = By.xpath("//input[@id=\"address\"]");
	By City = By.xpath("//input[@id=\"city\"]");
	By State = By.xpath("//input[@id=\"state\"]");
	By Zip = By.xpath("//input[@id=\"zip\"]");
	By Submit = By.xpath("//button[@id=\"submit\"]");
	By ListCustomers = By.xpath("//a[contains(text(),\"List Customers\")]");
	By Company = By.xpath("//select[@id=\"cid\"]");
	
	//Add Customer
	driver.findElement(Customers).click();
	WaitforElement(driver, 5, AddCustomer);
	driver.findElement(AddCustomer).click();

int number =	random();
	WaitforElement(driver, 5, FullName);
	driver.findElement(FullName).sendKeys(Name + number);
String name = Name + number;	
	
select(Company, CompanyName);

driver.findElement(Email).sendKeys(number + email);
driver.findElement(Phone).sendKeys(number + phone);
driver.findElement(Address).sendKeys(address);
driver.findElement(City).sendKeys(city);
driver.findElement(State).sendKeys(state);
driver.findElement(Zip).sendKeys(zip);
driver.findElement(Submit).click();

driver.navigate().back();

WaitforElement(driver, 10, ListCustomers);
driver.findElement(ListCustomers).click();

WebElement find = driver.findElement(By.xpath("//table/tbody/tr/td[3]/a[contains(text(),\"Jim\")]"));
WaitforElement(driver, 10, ListCustomers);

Assert.assertEquals(find.getText(), name, "Is not displayed");

}


private int random() {
	Random number = new Random();
	return number.nextInt(999);
	
}

private void WaitforElement(WebDriver driver2, int TimeInSeconds, By locator) {
	WebDriverWait wait = new WebDriverWait(driver, TimeInSeconds);
	wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	
}

private void select(By locator, String CompanyName) {
	Select sel = new Select(driver.findElement(locator));
	sel.selectByVisibleText(CompanyName);
}

@AfterTest
public void end() {
driver.close();
driver.quit();
}

}