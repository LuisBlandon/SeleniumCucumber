package runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;


// @RunWith(CukeSpace.class)
@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"json:target/cucumber-json-report"}, features = {"src/test/resources/features/"},
    glue = {"classpath:"}, tags = {"@Test"})
public class RunTest {
  }
