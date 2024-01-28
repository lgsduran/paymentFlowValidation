package pt.accenture.paymentFlowValidation.pages;

import static java.lang.String.format;
import static org.openqa.selenium.By.id;
import static org.openqa.selenium.By.xpath;

import org.openqa.selenium.WebDriver;

import pt.accenture.paymentFlowValidation.selenium.SeleniumFunctions;

public class IndexPage {

	private WebDriver driver;
	private SeleniumFunctions sf;

	public IndexPage(WebDriver driver) {
		this.driver = driver;
		sf = new SeleniumFunctions(this.driver);
	}

	public boolean isStorePageOnLine() {
		sf.getWaitHelper().waitFor();
		return sf.getElement(id("nava")).isDisplayed();		
	}

	public void goToSection(String section) {
		sf.getWaitHelper().waitFor();
		sf.getElement(xpath(format("//*[text()='%s']", section))).click();
	}

}
