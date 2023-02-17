package com.test.nhs.stepDefinitions;

import com.test.nhs.pages.DashboardPage;
import com.test.nhs.pages.LoginPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import utils.BrowserUtils;
import utils.DriverHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DashboardStepDef {
        List<String> expectedMessages = new ArrayList<>();
    WebDriver driver = DriverHelper.getDriver();

    DashboardPage dashboardPage = new DashboardPage(driver);

    @Then("validate the number of cards on the dashboard is {int}")
    public void validate_the_number_of_cards_on_the_dashboard_is(int number) {

        Assert.assertEquals(number,dashboardPage.getNumberOfCards());
//        System.out.println(dashboardPage.getCardsDisplayedNumbers());
//        System.out.println(dashboardPage.getCardsDisplayedMessage());
       // System.out.println(dashboardPage.getCardsColors());
    }

    @Then("validate the display a positive number each")
    public void validate_the_display_a_positive_number_each() {
        for(int number:dashboardPage.getCardsDisplayedNumbers()){
            Assert.assertTrue(number>=0);
        }
    }

    @Then("The information cards show on the screen")
    public void the_the_information_cards_show_on_the_screen( DataTable dataTable) {

        List<String> allMessages = dataTable.asList();

        expectedMessages.add(allMessages.get(0));
        expectedMessages.add(allMessages.get(1));
        expectedMessages.add(allMessages.get(2));

    }
    @Then("validate the message on each card")
    public void validate_the_message_on_each_card() {
      Assert.assertEquals(expectedMessages,dashboardPage.getCardsDisplayedMessage());
    }

    @Then("validate the correct color on each card")
    public void validate_the_correct_color_on_each_card(DataTable dataTable) {

     List<String> expectedColor = dataTable.asList();
        for(int i =0;i<expectedColor.size();i++){
         Assert.assertEquals(expectedColor.get(i),dashboardPage.getCardsColors().get(i));
        }
    }
}
