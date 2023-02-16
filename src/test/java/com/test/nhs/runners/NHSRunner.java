package com.test.nhs.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/NHS",
        glue = "com/test/nhs/stepDefinitions",
        dryRun = false,
        tags = "@SmokeTest",
        //tags = "@US_002",
        plugin = {"pretty","html:target/uiReport.html","rerun:target/uiFailedTests.txt",
                "json:target/cucumber-reports/cucumber.json"}
)
public class NHSRunner {
}
