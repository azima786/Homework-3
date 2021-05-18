package Homework2;

import java.io.FileReader;
import java.io.IOException;
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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HW3 {
	
	WebDriver driver; 
String browser;

@BeforeClass
public void readHW() {
	
	//Input Stream, Buffer Reader, Scanner, File Reader - Class that can help us read any file
	
	try{
		
		FileReader inputs = new FileReader("src\\main\\java\\Homework2\\config.properties");
		Properties property = new Properties();
property.load(inputs);
	System.out.println(property.getProperty("browser"));
	property.getProperty("browser");
	
	}
	
	catch(IOException e){
		e.printStackTrace();
	}
	}

@BeforeMethod
public void start() {
	if(browser.equalsIgnoreCase("chrome")) {
		System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
	driver = new ChromeDriver();}
		
	else if (browser==("FireFox")) 
	{System.setProperty("webdriver.gecko.driver", "drivers\\geckodriver.exe");
		driver = new FirefoxDriver();}
}

@Test
public void loginTest() {
	System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
	driver = new ChromeDriver();

	driver.get("https://techfios.com/billing/?ng=login/");
	
	String login = "demo@techfios.com";
	String password = "abc123";
	
	Assert.assertEquals(driver.getTitle(), "Login - iBilling", "Incorrect!");
	
	By Username = By.xpath("//input[@id=\"username\"]");
	By Password = By.xpath("//input[@id=\"password\"]");
	By Login = By.xpath("//button[@name=\"login\"]");
	
	driver.findElement(Username).sendKeys(login);
	driver.findElement(Password).sendKeys(password);
	driver.findElement(Login).click();
	
	Assert.assertEquals(driver.getTitle(), "Dashboard- iBilling", "Wrong Page!!!!");
	
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
	
	String Name = "Jim Bryan";
	String CompanyName = "Google";
		
	Random rdn = new Random();
	int number = rdn.nextInt(999);
	
	String email = "abc@techfios.com";
	String phone = "205-7965";
	String address = "121 Miguels Rd";
	String city = "Plano";
	String state = "TX";
	String zip = "75056";
	
	driver.findElement(Customers).click();
	
	WaitforElement(driver, 5, AddCustomer);
	driver.findElement(AddCustomer).click();

	WaitforElement(driver, 5, FullName);
	driver.findElement(FullName).sendKeys(Name + number);
String name = Name + number;	
	
	Select sel = new Select(driver.findElement(Company));
	sel.selectByVisibleText(CompanyName);

	driver.findElement(Email).sendKeys(number + email);
driver.findElement(Phone).sendKeys (phone + number);
driver.findElement(Address).sendKeys(address);
driver.findElement(City).sendKeys(city);
driver.findElement(State).sendKeys(state);
driver.findElement(Zip).sendKeys(zip);
driver.findElement(Submit).click();

driver.navigate().back();

WaitforElement(driver, 5, ListCustomers);
driver.findElement(ListCustomers).click();

WebElement find = driver.findElement(By.xpath("//table/tbody/tr/td[3]/a[contains(text(),\"Jim\")]"));
WaitforElement(driver, 5, ListCustomers);

Assert.assertEquals(find.getText(), name, "Is not displayed");

}


private void WaitforElement(WebDriver driver2, int TimeInSeconds, By locator) {
	WebDriverWait wait = new WebDriverWait(driver, TimeInSeconds);
	wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	
}

private void teardown() {
driver.quit();
driver.close();
}

}