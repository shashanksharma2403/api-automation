package Runners;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/java/resources"},
        plugin = {"pretty"},
        glue = {"StepDefinations"}
        )
public class TestRunner {
}
