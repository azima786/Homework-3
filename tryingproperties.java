package Homework2;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;


public class tryingproperties {
	WebDriver driver;
	String browser;

	@Test
public void readconfig() throws IOException {
	
	try {Properties p = new Properties();
	
	String path = "data\\config.properties";
	FileInputStream fl = new FileInputStream(path);
p.load(fl);	
p.getProperty("browser");}

	catch (IOException e) {e.printStackTrace();}

if (browser==("chrome"))
	{System.setProperty("WebDriver.chrome.driver", "drivers\\chromedriver.exe");
	driver = new ChromeDriver();
	
	}
	
else if (browser==("firefox"))
{System.setProperty("WebDriver.gecko.driver", "drivers\\geckodriver.exe");
driver = new FirefoxDriver();}
	
}}
