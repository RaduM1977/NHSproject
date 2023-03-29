package com.test.nhs.stepDefinitions;

import com.test.nhs.pages.ApiPage;
import com.test.nhs.pages.DashboardPage;
import com.test.nhs.pages.SystemSettingsPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import utils.DriverHelper;

import java.util.*;

public class ApiStepDef {

    WebDriver driver = DriverHelper.getDriver();
    SystemSettingsPage systemSettingsPage = new SystemSettingsPage(driver);
    DashboardPage dashboardPage = new DashboardPage(driver);
    ApiPage apiPage = new ApiPage();


    Response response;

    @When("the get request for {string} is made")
    public void the_get_request_for_is_made(String Url) {
      response = apiPage.getResponse(Url);

    }

    @Then("validate the user should see the same list of diseases and score in UI and in API")
    public void validate_the_user_should_see_the_same_list_of_in_ui_and_in_api(DataTable dataTable) {

        //assert the diseases(keys in API response)
        String tableName =dataTable.asList().get(0);
        List<String> actualNamesList = systemSettingsPage.systemSettingsList(tableName);
        List<String> expectedNamesList = apiPage.keysList();
        Assert.assertEquals(expectedNamesList,actualNamesList);

        //assert the score(values in API response )
        List<Object> actualScoreList = systemSettingsPage.scoreList();
        List<Object> expectedScoreList = apiPage.valueList();

        //assert as list
        Assert.assertEquals(expectedScoreList,actualScoreList);

        //assert as maps
        Assert.assertEquals(apiPage.deserializeResponseMap(),systemSettingsPage.diseasesScoreMap(tableName));

    }

    @Then("validate the user should see the same list of rooms in UI and in API")
    public void validate_the_user_should_see_the_same_list_of_in_ui_and_in_api() {
        Map<String,Object> actualValues = dashboardPage.roomsMap();
        Map<String,Object> expectedValue  = apiPage.deserializeResponseMap();
        Assert.assertEquals(expectedValue,actualValues);
    }

    @Then("validate the user should see the same list of patients in UI and in API")
    public void validate_the_user_should_see_the_same_list_of_patients_in_ui_and_in_api() {
       List<Map<String,String>> actualValueList = dashboardPage.patientsInfo();
        //System.out.println(actualValueList);

       List<Map<String,String>> expectedValueList = apiPage.deserializeResponseListOfMaps();
       // System.out.println(expectedValueList);

       Assert.assertEquals(expectedValueList,actualValueList);
    }

    @Then("appropriate status code should be displayed")
    public void appropriate_status_code_should_be_displayed(DataTable dataTable) {
            int expectedStatusCode = Integer.parseInt(dataTable.asList().get(0));
            int actualStatusCode = apiPage.getStatusCode();
        Assert.assertEquals(expectedStatusCode,actualStatusCode);
    }

}
