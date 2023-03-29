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
        //tags = "@US_003",
        //tags = "@US_002",
        //tags = "@US_004",
        //tags="@US_005",
        //tags="@US_006",
        //tags="@US_007",
        //tags="@TC_11",
        //tags="@TC_19",
        //tags = "@US_008",
        //tags ="@US_009",
        //tags="@US_010",
        //tags="@TC_25",
        //tags="@TC_26",
        plugin = {"pretty","html:target/uiReport.html","rerun:target/uiFailedTests.txt",
                "json:target/cucumber-reports/cucumber.json"}
)
public class NHSRunner {
}
