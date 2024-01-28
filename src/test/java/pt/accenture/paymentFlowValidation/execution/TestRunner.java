package pt.accenture.paymentFlowValidation.execution;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/resources/features",
		glue = "pt.accenture.paymentFlowValidation.steps",
		tags =  "@order"
		)
public class TestRunner {

}
