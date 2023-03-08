package com.test.nhs.stepDefinitions;

import com.test.nhs.pages.AddPatientPage;
import com.test.nhs.pages.DashboardPage;
import com.test.nhs.pages.PatientPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import utils.DriverHelper;

import java.util.List;
import java.util.Map;

public class AddPatientStepDef {

    WebDriver driver = DriverHelper.getDriver();
    DashboardPage dashboardPage = new DashboardPage(driver);
    AddPatientPage addPatientPage = new AddPatientPage(driver);

    PatientPage patientPage = new PatientPage(driver);



    @Given("the user clicks the add patient button on the left side of the page")
    public void the_user_clicks_the_add_patient_button_on_the_left_side_of_the_page() {
        dashboardPage.doAddPatientButton(driver);
    }
    @Then("the user should be able to see add patient details like")
    public void the_user_should_be_able_to_see_add_patient_details_like(DataTable dataTable) {
        Assert.assertTrue(addPatientPage.isDisplayed());

        List<String> expectedFields = dataTable.asList();
        List<String> actualFields = addPatientPage.addPatientDisplayedList();
        Assert.assertEquals(expectedFields,actualFields);
    }

    @When("the user create a patient")
    public void the_user_create_a_patient(DataTable dataTable) {
        Map<String,String> inputMap = dataTable.asMap();

        System.out.println(inputMap);
        addPatientPage.addPatient(inputMap);

    }

    @Then("the user should be able to see the patient is {string} to the dashboard page under {string} tab")
    public void the_user_should_be_able_to_see_the_patient_is_to_the_dashboard_page_under_tab(String isAdded, String tableHeader) {
        Assert.assertEquals(isAdded,dashboardPage.isPatientAdded(driver,tableHeader));
        patientPage.deletePatient();
    }

    @Then("the user should not be able to create a patient and should see the message {string}")
    public void the_user_should_not_be_able_to_create_a_patient_and_should_see_the_message(String expectedMessage) {
       Assert.assertEquals(expectedMessage,addPatientPage.getInvalidDataMessage());
       Assert.assertTrue(addPatientPage.isRequiredAttribute());
    }

}
