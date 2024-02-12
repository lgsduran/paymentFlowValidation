package pt.accenture.paymentFlowValidation.base;

import static java.lang.String.format;
import static org.apache.commons.lang3.StringUtils.endsWithIgnoreCase;
import static org.openqa.selenium.PageLoadStrategy.NONE;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SharedDriver {
	private WebDriver driver;
	private final String isLinux = System.getProperty("os.name");
	
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

		System.out.println(format("Current OS is: '%s'", isLinux));
		if(endsWithIgnoreCase(isLinux, "linux"))
			options.setBinary("/usr/bin/google-chrome");

		options.setPageLoadStrategy(NONE);
		driver = new ChromeDriver(options);
		driver.manage().deleteAllCookies();
		return driver;
	}

}
