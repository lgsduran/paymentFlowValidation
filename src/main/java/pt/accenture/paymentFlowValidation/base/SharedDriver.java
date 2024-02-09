package pt.accenture.paymentFlowValidation.base;

import static org.openqa.selenium.PageLoadStrategy.NONE;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SharedDriver {
	private WebDriver driver;
	private final String strOsName = System.getProperty("os.name");
	
	public WebDriver getDriver() {
		if (driver == null)
			driver = createDriver();
		return driver;
	}

	private WebDriver createDriver() {
		WebDriverManager.chromedriver().setup();
		var options = new ChromeOptions();
		options.addArguments
		(
			"--no-sandbox",
			"start-maximized", 
			"disable-infobars",
			"--disable-dev-shm-usage",
			"--headless=new"
		);

		System.out.println(String.format("Current OS is: '%s'", strOsName));
		if(StringUtils.endsWithIgnoreCase(strOsName, "linux"))
			options.setBinary("/usr/bin/google-chrome");

		options.setPageLoadStrategy(NONE);
		driver = new ChromeDriver(options);
		driver.manage().deleteAllCookies();
		return driver;
	}

}
