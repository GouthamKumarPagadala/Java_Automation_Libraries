package manager.test;

import org.junit.jupiter.api.Test;

import manager.DriverManager;

public class WebdriverManagerTest {

	
	@Test
	public void TestOne() {
		DriverManager Driver = new DriverManager("Firefox");
		
		//Navigate to a URL
        Driver.getDriver().get("http://toolsqa.com");
        Driver.getDriver().quit();
	}
}

