package pt.accenture.paymentFlowValidation.selenium;

import static java.time.Duration.ofSeconds;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumFunctions {

	private WebDriver driver;
	private WebDriverWait wait;
	private WaitHelper waitHelper;

	/**
	 * @param driver
	 */
	public SeleniumFunctions(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * @return the wait
	 */
	public WebDriverWait getWait() {
		return (wait == null) ? wait = new WebDriverWait(this.driver, ofSeconds(10)) : wait;
	}
	
	public WebElement getElement(By by) {
		return this.driver.findElement(by);
	}
	
	public void navigateTo(String url) {
		this.driver.navigate().to(url);		
	}
	
	public WaitHelper getWaitHelper() {
		return (waitHelper == null) ? new WaitHelper(driver) : waitHelper;
	}

}
