package ca.mcgill.ecse428.project.features;
import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin = "pretty", 
		features = "src/test/resources",
		glue = "ca.mcgill.ecse428.project.features")
public class CucumberTestsRunner {
}
