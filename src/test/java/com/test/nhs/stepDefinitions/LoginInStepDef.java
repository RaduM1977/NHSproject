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

    @Given("Admin user navigates to NHS website {string}")
    public void user_navigates_to_nhs_website(String url) {
        driver.get(ConfigReader.readProperty("NHS_url"));
    }
    @When("The correct username and password is entered")
    public void the_correct_username_and_password_is_entered() {
       loginPage.doLogin(ConfigReader.readProperty("NHS_Admin_username"),ConfigReader.readProperty("NHS_Admin_Password"));
    }
    @Then("Login successfully by validating title {string} and url {string}")
    public void login_should_be_successful(String title,String url) {
        Assert.assertEquals(url,driver.getCurrentUrl());
        Assert.assertEquals(title,driver.getTitle());
    }

    @When("the incorrect username and password is entered")
    public void the_incorrect_username_and_password_is_entered() {
        loginPage.doLogin(ConfigReader.readProperty("NHS_wrong_username"),ConfigReader.readProperty("NHS_wrong_Password"));
    }
    @Then("login unsuccessfully by validating the title  {string} and url {string}")
    public void login_unsuccessfully_by_validating_the_title_and_url(String title, String url) {
        Assert.assertEquals(url,driver.getCurrentUrl());
        Assert.assertEquals(title,driver.getTitle());
    }

    @When("the blank {string} and,or {string} is entered")
    public void the_blank_and_or_is_entered(String username, String password) {
       loginPage.doLogin(username,password);
    }




}
