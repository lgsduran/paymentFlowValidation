package pt.accenture.paymentFlowValidation.context;

import pt.accenture.paymentFlowValidation.base.SharedDriver;
import pt.accenture.paymentFlowValidation.manager.PageObjectManager;

public class FlowContext {

	private SharedDriver sharedDriver;
	private PageObjectManager pageObjectManager;
	
	public FlowContext() {
		pageObjectManager = new PageObjectManager(getSharedDriver().getDriver());
	}

	/**
	 * @return the driver
	 */
	public SharedDriver getSharedDriver() {
		return (sharedDriver == null) ? sharedDriver = new SharedDriver() : sharedDriver;
	}

	/**
	 * @return the pageObjectManager
	 */
	public PageObjectManager getPageObjectManager() {
		return pageObjectManager;
	}
}
