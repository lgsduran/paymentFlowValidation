package pt.accenture.paymentFlowValidation.steps;

import static java.io.File.separator;
import static java.lang.String.format;
import static java.lang.System.getProperty;
import static java.time.LocalDateTime.now;
import static java.time.format.DateTimeFormatter.ofPattern;
import static org.apache.commons.io.FileUtils.copyFile;
import static org.apache.commons.lang3.StringUtils.replace;
import static org.openqa.selenium.OutputType.FILE;

import java.io.File;

import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

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
		var driver = this.context.getSharedDriver().getDriver();
		try {
			//if (scenario.isFailed())
				captureScreenshot(driver, scenario.getName());
		} finally {
			driver.close();
			driver.quit();
		}

	}

	private void captureScreenshot(WebDriver driver, String fileName) {
		try {
			var rootPath = getProperty("user.dir") + separator + "screenshot" + separator;
			var strDest = rootPath + fileName + "_" + now().format(ofPattern("yyyy-MM-dd-HH-mm-ss")) + ".png";
			var dest = replace(strDest, "\"", "");
			var screenshot = (TakesScreenshot) driver;
			var sourceFile = screenshot.getScreenshotAs(FILE);
			copyFile(sourceFile, new File(dest));
			System.out.println("Screenshot path: " + rootPath);

		} catch (Exception e) {
			System.out.println(format("Screenshot exception: ", e.getMessage()));
		}
	}

}
