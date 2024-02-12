package pt.accenture.paymentFlowValidation.runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions
		(
			features = "src/test/resources/features",
			glue = {"pt.accenture.paymentFlowValidation.hooks", "pt.accenture.paymentFlowValidation.steps"},
			tags =  "@order",
			plugin = {
				"json:target/cucumber.json", "pretty",
				"html:target/cucumber-reports" 
			}
		)
public class TestRunner {

}
