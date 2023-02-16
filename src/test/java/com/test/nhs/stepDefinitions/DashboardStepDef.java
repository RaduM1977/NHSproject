package com.test.nhs.stepDefinitions;

import com.test.nhs.pages.DashboardPage;
import com.test.nhs.pages.LoginPage;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import utils.DriverHelper;

import java.util.Arrays;
import java.util.List;

public class DashboardStepDef {

    WebDriver driver = DriverHelper.getDriver();

    DashboardPage dashboardPage = new DashboardPage(driver);

    @Then("validate the number of cards on the dashboard is {int}")
    public void validate_the_number_of_cards_on_the_dashboard_is(int number) {

        Assert.assertEquals(number,dashboardPage.getNumberOfCards());
//        System.out.println(dashboardPage.getCardsDisplayedNumbers());
//        System.out.println(dashboardPage.getCardsDisplayedMessage());
    }

    @Then("validate the display a positive number each")
    public void validate_the_display_a_positive_number_each() {
        for(int number:dashboardPage.getCardsDisplayedNumbers()){
            Assert.assertTrue(number>=0);
        }
    }

    @Then("validate the message on each card")
    public void validate_the_message_on_each_card() {
        List<String> expectedList = Arrays.asList("Patients with rooms","Patients waiting","Free rooms");
      Assert.assertEquals(expectedList,dashboardPage.getCardsDisplayedMessage());
    }
}
