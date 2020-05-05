package manager;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverManager {

	private WebDriver driver;

	Logger log = Logger.getLogger("WebdriverManager");

	public DriverManager(String browserName) {
		
		switch(browserName) {
		case "Chrome":
			WebDriverManager.chromedriver().setup();
		    System.setProperty("webdriver.chrome.silentOutput", "true");
	        this.driver = new ChromeDriver();
	        log.debug("Launched Chrome");
			break;
		case "Firefox":
			WebDriverManager.firefoxdriver().setup();
			String logText = ".//Logs//Firefox//"+new SimpleDateFormat("ddMMyyyyhhmmss").format(new Date())+".txt";
			new File(".//Logs//Firefox").mkdirs();
			new File(logText);
		    System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,logText);
	        this.driver = new FirefoxDriver();
			break;
		}
		 
       
 
        
	}
	
	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}
	
	
}
