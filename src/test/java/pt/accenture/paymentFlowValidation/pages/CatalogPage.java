package pt.accenture.paymentFlowValidation.pages;

import static java.lang.String.format;
import static org.apache.commons.lang3.StringUtils.equalsIgnoreCase;
import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.id;

import java.util.List;
import java.util.Optional;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import pt.accenture.paymentFlowValidation.exception.ProductException;
import pt.accenture.paymentFlowValidation.selenium.SeleniumFunctions;

public class CatalogPage {

	private WebDriver driver;
	private SeleniumFunctions sf;

	public CatalogPage(WebDriver driver) {
		this.driver = driver;
		sf = new SeleniumFunctions(this.driver);
	}

	public void selectProduct(String product) throws ProductException {
		sf.waitForPage();
		WebElement body = sf.getElement(id("tbodyid"));
		List<WebElement> articles = body.findElements(cssSelector("h4"));

		Optional<WebElement> articleFiltered = articles
				.stream()
				.filter(article -> equalsIgnoreCase(article.getText().trim(), product))
				.findFirst();

		if (articleFiltered.isEmpty()) {
			try {
				sf.getElement(cssSelector("#next2")).click();
				this.selectProduct(product);				
			} catch (StaleElementReferenceException ser) {
				throw new ProductException(format("Product %s was not found!", product));	
			}		
		}

		articleFiltered.get().click();

	}

}
