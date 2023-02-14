package com.test.nhs.stepDefinitions;

import com.test.nhs.pages.LoginPage;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import utils.ConfigReader;
import utils.DriverHelper;

public class LoginInStepDef {

    WebDriver driver = DriverHelper.getDriver();

    LoginPage loginPage = new LoginPage(driver);

    @Given("User navigates to NHS website")
    public void user_navigates_to_nhs_website() {
        driver.get(ConfigReader.readProperty("NHS_url"));
    }
    @When("The correct username and password is entered")
    public void the_correct_username_and_password_is_entered() {
       loginPage.doLogin(ConfigReader.readProperty("NHS_Admin_username"),ConfigReader.readProperty("NHS_Admin_Password"));
    }
    @Then("Login should be successful")
    public void login_should_be_successful() {
        Assert.assertEquals("http://www.techtorialacademy.link/app",driver.getCurrentUrl());
        Assert.assertEquals("NHS Patient",driver.getTitle());
    }
}
