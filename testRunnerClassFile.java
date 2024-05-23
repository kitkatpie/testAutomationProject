package Feature;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/Feature/requestQueuePage.feature", 
glue = "StepDefinition",monochrome=true,plugin= {"pretty", "html:target/cucumber.html"},
tags="@contractorRequestQueue")

//extending class so it uses class libraries to use test definition file using cucumber test class
public class TestRunnerClass extends AbstractTestNGCucumberTests {

}
