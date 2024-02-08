package pt.accenture.paymentFlowValidation.base;

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
		options.addArguments
		(
		"--no-sandbox",
		"start-maximized", 
		"disable-infobars",
		"--disable-dev-shm-usage",
		"--headless=new"
		);
		options.setBinary("/usr/bin/google-chrome");
		options.setPageLoadStrategy(NONE);
		driver = new ChromeDriver(options);
		driver.manage().deleteAllCookies();
		return driver;
	}

}
