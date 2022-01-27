package cucumber.Options;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)

@CucumberOptions( 
		features = "src/test/java/features",
        glue = "stepDefinition",
        plugin = {
        		"pretty",
        		"json:target/jsonReports/cucumber-report.json",
        		"html:target/cucumber-report/cucumber.html"
		},
		stepNotifications = true)
		//tags="@addplace or @deleteplace")  
		// phases Compile -> Test -> Verify
public class TestRunner {
	
}