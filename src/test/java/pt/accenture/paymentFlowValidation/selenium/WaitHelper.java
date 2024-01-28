package pt.accenture.paymentFlowValidation.selenium;

import static java.time.Duration.ofSeconds;
import static java.time.LocalDateTime.now;
import static org.junit.Assert.fail;

import java.util.NoSuchElementException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

public class WaitHelper {
	
	private Wait<WebDriver> wait;
	
	public WaitHelper(WebDriver driver) {
		this.wait = new FluentWait<WebDriver>(driver).withTimeout(ofSeconds(10))
				.pollingEvery(ofSeconds(2))
				.ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class);
	}
	
	public boolean waitFor() {

		ExpectedCondition<Boolean> jQueryLoad = null, jsLoad = null;

		try {
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

		} catch (TimeoutException e) {
			fail(now() + " Time out");
		}

		return wait.until(jQueryLoad) && wait.until(jsLoad);
	}

}
