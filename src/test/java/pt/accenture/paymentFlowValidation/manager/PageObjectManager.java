package pt.accenture.paymentFlowValidation.manager;

import org.openqa.selenium.WebDriver;

import pt.accenture.paymentFlowValidation.pages.CartPage;
import pt.accenture.paymentFlowValidation.pages.CatalogPage;
import pt.accenture.paymentFlowValidation.pages.IndexPage;
import pt.accenture.paymentFlowValidation.pages.PlaceOrderPage;

public class PageObjectManager {

	private WebDriver driver;
	private CartPage cartPage;
	private CatalogPage catalogPage;
	private IndexPage indexPage;
	private PlaceOrderPage placeOrderPage;

	/**
	 * @param driver
	 */
	public PageObjectManager(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * @return the cartPage
	 */
	public CartPage getCartPage() {
		return (cartPage == null) ? cartPage = new CartPage(driver) : cartPage;
	}

	/**
	 * @return the catalogPage
	 */
	public CatalogPage getCatalogPage() {
		return (catalogPage == null) ? catalogPage = new CatalogPage(driver) : catalogPage;
	}

	/**
	 * @return the indexPage
	 */
	public IndexPage getIndexPage() {
		return (indexPage == null) ? indexPage = new IndexPage(driver) : indexPage;
	}

	/**
	 * @return the placeOrderPage
	 */
	public PlaceOrderPage getPlaceOrderPage() {
		return (placeOrderPage == null) ? placeOrderPage = new PlaceOrderPage(driver) : placeOrderPage;
	}

}
