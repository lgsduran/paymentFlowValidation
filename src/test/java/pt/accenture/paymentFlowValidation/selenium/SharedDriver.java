package pt.accenture.paymentFlowValidation.selenium;

import static org.openqa.selenium.PageLoadStrategy.NONE;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SharedDriver {
	private WebDriver driver;

	public WebDriver getDriver() {
		if (driver == null)
			driver = createDriver();
		return driver;
	}

	private WebDriver createDriver() {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized", "disable-infobars");
		//options.addArguments("--headless=new");
		options.setPageLoadStrategy(NONE);
		driver = new ChromeDriver(options);
		driver.manage().deleteAllCookies();
		return driver;
	}

}
