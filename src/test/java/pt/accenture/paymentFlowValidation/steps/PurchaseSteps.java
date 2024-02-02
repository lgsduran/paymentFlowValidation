package pt.accenture.paymentFlowValidation.steps;

import static org.apache.commons.lang3.StringUtils.substringBetween;
import static org.junit.Assert.assertTrue;

import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pt.accenture.paymentFlowValidation.context.FlowContext;
import pt.accenture.paymentFlowValidation.exception.ProductException;
import pt.accenture.paymentFlowValidation.order.Product;
import pt.accenture.paymentFlowValidation.pages.CartPage;
import pt.accenture.paymentFlowValidation.pages.CatalogPage;
import pt.accenture.paymentFlowValidation.pages.IndexPage;
import pt.accenture.paymentFlowValidation.pages.PlaceOrderPage;

public class PurchaseSteps {

	FlowContext context;

	CartPage cartPage;
	CatalogPage catalogPage;
	IndexPage indexPage;
	PlaceOrderPage placeOrderPage;

	private Product product = new Product();

	private SoftAssertions softAssertions = new SoftAssertions();

	public PurchaseSteps(FlowContext context) {
		this.context = context;
		cartPage = this.context.getPageObjectManager().getCartPage();
		catalogPage = this.context.getPageObjectManager().getCatalogPage();
		indexPage = this.context.getPageObjectManager().getIndexPage();
		placeOrderPage = this.context.getPageObjectManager().getPlaceOrderPage();
	}

	@Given("that I navigate to the index page")
	public void that_i_navigate_to_the_index_page() {
		this.context.getSharedDriver().getDriver().get("https://demoblaze.com/");
	}

	@Then("the page is displayed")
	public void the_page_is_displayed() {
		assertTrue(indexPage.isStorePageOnLine());
	}

	@Given("that I go to the {string} section")
	public void that_i_go_to_the_section(String section) {
		indexPage.goToSection(section);
	}

	@When("I find the {string}")
	public void i_find_the(String product) throws ProductException {
		catalogPage.selectProduct(product);
	}

	@And("I add it to cart")
	public void i_add_it_to_cart() {
		cartPage.addToCart();
	}

	@And("I go to cart page")
	public void i_go_to_cart_page() {
		cartPage.goToCartPage();
	}

	@And("I click on the Place Order button")
	public void i_click_on_the_place_order_button() {
		placeOrderPage.clickOnPlaceOrder();
	}

	@And("I fill the name with {string}")
	public void i_fill_the_name_with(String name) {
		placeOrderPage.insertName(name);
		product.setClient(name);
	}

	@And("I fill the country with {string}")
	public void i_fill_the_country_with(String country) {
		placeOrderPage.insertCountry(country);
	}

	@And("I fill the city with {string}")
	public void i_fill_the_city_with(String city) {
		placeOrderPage.insertCity(city);
	}

	@And("I fill the Credit Card {long}")
	public void i_fill_the_credit_card(long card) {
		placeOrderPage.insertCreditCard(card);
	}

	@And("I fill the month with {int}")
	public void i_fill_the_month_with(int month) {
		placeOrderPage.insertMonth(month);
	}

	@And("I fill the year with {int}")
	public void i_fill_the_year_with(int year) {
		placeOrderPage.insertYear(year);
	}

	@And("I submit the form")
	public void i_submit_the_form() {
		placeOrderPage.submitForm();
	}

	@Then("the Order Id is displayed")
	public void the_order_id_is_displayed() {
		var text = this.context.getSharedDriver()
				.getDriver().findElement(By.cssSelector(".sweet-alert p"))
				.getText();
		product.setPurchase(text);
		String id = substringBetween(text, "Id:", "Amount:").trim();
		softAssertions.assertThat(id).isNotEmpty();
	}

	@And("the amount paid is equal the expected")
	public void the_amount_paid_is_equal_the_expected() {
		String amount = product.getPurchase();
		String resultAmount = substringBetween(amount, "Amount:", "USD").trim();
		softAssertions.assertThat(resultAmount).isEqualTo(1100);
	}

	@And("The displayed name is equal to the mocked information")
	public void the_displayed_name_is_equal_to_the_mocked_information() {
		var client = product.getPurchase();
		var resultClient = substringBetween(client, "Name:", "Date").trim();
		softAssertions.assertThat(product.getClient()).isEqualToIgnoringCase(resultClient);
	}

	@Then("the credit card information is needed to complete the order")
	public void the_credit_card_information_is_needed_to_complete_the_order() {
			var alertText = placeOrderPage.alertTextValidation();
			softAssertions.assertThat(alertText).isBlank();
	}

}
