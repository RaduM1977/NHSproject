package com.test.nhs.stepDefinitions;

import com.test.nhs.pages.DashboardPage;
import com.test.nhs.pages.SystemSettingsPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.DriverHelper;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class SystemSettingsStepDef {

    WebDriver driver = DriverHelper.getDriver();
    DashboardPage dashboardPage = new DashboardPage(driver);
    SystemSettingsPage systemSettingsPage = new SystemSettingsPage(driver);

    @When("the user clicks the System Settings button on the left side of the page")
    public void the_user_clicks_the_system_settings_button_on_the_left_side_of_the_page() {
       dashboardPage.doSystemSettingsButton(driver);
    }
    @Then("validate the room was added in the room table")
    public void validate_the_room_was_added_in_the_room_table() {
        Assert.assertTrue(systemSettingsPage.addRoom(driver));
    }

    @Then("validate the rooms are displayed in a table in {string} order")
    public void validate_the_diseases_are_displayed_in_a_tables_in_order(String diseasesDefaultOrder) {
        System.out.println(systemSettingsPage.orderOfRooms(diseasesDefaultOrder));

        List<String> actualRoomsList = systemSettingsPage.orderOfRooms(diseasesDefaultOrder);
        List<String> expectedRoomsList = actualRoomsList;
        Collections.sort(expectedRoomsList);
        Assert.assertEquals(expectedRoomsList,actualRoomsList);
    }

    @Then("validate the user can delete selected room")
    public void validate_the_user_can_delete_selected_room() {
      Assert.assertTrue(systemSettingsPage.deleteRoom());
    }

    @When("we create a new user")
    public void we_create_a_new_user(DataTable dataTable) {
        Map<String,String> inputDataMap = dataTable.asMap();
        systemSettingsPage.addUser(inputDataMap.get("User name"),inputDataMap.get("Password"));
    }
    @Then("validate the message {string}")
    public void validate_the_message(String expectedMessage) {
        String actualMessage = systemSettingsPage.addUserMessage();
        Assert.assertEquals(expectedMessage,actualMessage);
    }

}
