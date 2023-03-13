package com.test.nhs.stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import utils.BrowserUtils;
import utils.DriverHelper;

import java.time.Duration;

public class Hook {

    WebDriver driver = DriverHelper.getDriver();

    @Before
    public void setup(){

        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }

    @After
    public void tearDown(Scenario scenario){
        BrowserUtils.getScreenShotCucumber(scenario,driver);
       //driver.quit();
    }
}
