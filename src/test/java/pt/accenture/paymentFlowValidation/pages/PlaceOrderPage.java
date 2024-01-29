package pt.accenture.paymentFlowValidation.pages;

import static java.lang.String.format;
import static java.lang.String.valueOf;
import static java.time.Duration.ofSeconds;
import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.id;
import static org.openqa.selenium.By.xpath;
import static org.openqa.selenium.support.ui.ExpectedConditions.alertIsPresent;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfElementsToBe;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;

import pt.accenture.paymentFlowValidation.selenium.SeleniumFunctions;

public class PlaceOrderPage {

	private WebDriver driver;
	private SeleniumFunctions sf;

	public PlaceOrderPage(WebDriver driver) {
		this.driver = driver;
		sf = new SeleniumFunctions(this.driver);
	}

	public void clickOnPlaceOrder() {
		sf.getWait()
			.withTimeout(ofSeconds(20))
			.until(numberOfElementsToBe(cssSelector("#tbodyid tr"), 1));
		sf.getElement(cssSelector("#page-wrapper button")).click();
	}

	public void insertName(String name) {
		sf.getWait().until(visibilityOf(sf.getElement(id("name")))).sendKeys(name);
	}

	public void insertCountry(String country) {
		sf.getElement(id("country")).sendKeys(country);
	}

	public void insertCity(String city) {
		sf.getElement(id("city")).sendKeys(city);
	}

	public void insertCreditCard(Long card) {
		sf.getElement(id("card")).sendKeys(Long.toString(card));
	}

	public void insertMonth(int month) {
		sf.getElement(id("month")).sendKeys(valueOf(month));
	}

	public void insertYear(int year) {
		sf.getElement(id("year")).sendKeys(valueOf(year));
	}

	public void submitForm() {
		sf.getWait().until(elementToBeClickable(xpath(format("//*[text()='%s']", "Purchase")))).click();
	}

	public String alertTextValidation() {
		Alert alert = sf.getWait().until(alertIsPresent());
		var text = alert.getText().trim();
		alert.accept();
		return text;
	}

}
