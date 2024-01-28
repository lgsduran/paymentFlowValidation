package pt.accenture.paymentFlowValidation.pages;

import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.id;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import pt.accenture.paymentFlowValidation.selenium.SeleniumFunctions;

public class CatalogPage {

	private WebDriver driver;
	private SeleniumFunctions sf;

	public CatalogPage(WebDriver driver) {
		this.driver = driver;
		sf = new SeleniumFunctions(this.driver);
	}

	public void selectProduct(String product) {
		sf.getWaitHelper().waitFor();
		WebElement body = sf.getElement(id("tbodyid"));
		List<WebElement> articles = body.findElements(cssSelector("h4"));
		for (WebElement article : articles) {
			if (article.getText().trim().equalsIgnoreCase(product)) {
				article.click();
				break;
			}
		}
	}

}
