package pt.accenture.paymentFlowValidation.pages;

import static java.lang.String.format;
import static org.openqa.selenium.By.xpath;
import static org.openqa.selenium.support.ui.ExpectedConditions.alertIsPresent;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;

import pt.accenture.paymentFlowValidation.selenium.SeleniumFunctions;

public class CartPage {

	private WebDriver driver;
	private SeleniumFunctions sf;

	public CartPage(WebDriver driver) {
		this.driver = driver;
		sf = new SeleniumFunctions(this.driver);
	}

	public void addToCart() {
		sf.getWait()
		.until(elementToBeClickable(xpath(format("//*[text()='%s']", "Add to cart"))))
		.click();
	}

	public void goToCartPage() {
		Alert alert = sf.getWait().until(alertIsPresent());
		alert.accept();
		sf.waitForPage();
		sf.getElement(xpath(format("//*[text()='%s']", "Cart"))).click();
	}
}
