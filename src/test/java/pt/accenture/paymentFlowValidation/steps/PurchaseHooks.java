package pt.accenture.paymentFlowValidation.steps;


import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import pt.accenture.paymentFlowValidation.base.FlowContext;

public class PurchaseHooks {
	
	FlowContext context;

	/**
	 * @param context
	 */
	public PurchaseHooks(FlowContext context) {
		this.context = context;
	}


	@After()
	public void afterScenario(Scenario scenario) {
		boolean failed = scenario.isFailed();
		var driver = this.context.getSharedDriver().getDriver();
		if (failed) {
			System.out.println("print");
		}
		driver.close();
		driver.quit();
		

	}

}
