package pt.accenture.paymentFlowValidation.selenium;

import static java.time.Duration.ofSeconds;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumFunctions {

	private WebDriver driver;
	private WebDriverWait wait;

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

	public void waitForPage() {
		getWait().withTimeout(ofSeconds(10))
				.pollingEvery(ofSeconds(2))
				.ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class)
				.until(new WaitForPage(getWait()));		
	}

	class WaitForPage implements ExpectedCondition<Boolean> {

		private WebDriverWait wait;

		public WaitForPage(WebDriverWait wait) {
			this.wait = wait;
		}

		@Override
		public Boolean apply(WebDriver driver) {
			// TODO Auto-generated method stub

			ExpectedCondition<Boolean> jQueryLoad = null, jsLoad = null;

			jQueryLoad = new ExpectedCondition<Boolean>() {
				@Override
				public Boolean apply(WebDriver driver) {
					try {
						return ((Long) ((JavascriptExecutor) driver).executeScript("return jQuery.active") == 0);
					} catch (Exception e) {
						// no jQuery present
						return true;
					}
				}
			};

			jsLoad = new ExpectedCondition<Boolean>() {
				@Override
				public Boolean apply(WebDriver driver) {
					return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
							.equals("complete");
				}
			};

			return wait.until(jQueryLoad) && wait.until(jsLoad);

		}
	}
}
