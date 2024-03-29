package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/letskodeit/features"},
        glue = {"step_definitions/letskodeit"},
//        tags = "@smoke",
        monochrome = true,
        plugin = {"pretty", "html:target/cucumber-html-report.html"}
)
public class LetsKodeItRunner {
}
