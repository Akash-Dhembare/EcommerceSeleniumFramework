package akashdhembare2000.cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/akashdhembare2000/cucumber", glue = "akashdhembare2000.stepDefinitions",
monochrome = true, tags = "@Regression",  plugin = {"html:target/cucumber.html"})
public class TestNGRunner extends AbstractTestNGCucumberTests {

}
