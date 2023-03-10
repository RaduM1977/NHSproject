package com.test.nhs.stepDefinitions;

import com.test.nhs.pages.DashboardPage;
import com.test.nhs.pages.PatientPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.DriverHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DashboardStepDef {
    List<String> expectedMessages = new ArrayList<>();
    WebDriver driver = DriverHelper.getDriver();
    DashboardPage dashboardPage = new DashboardPage(driver);
    List<WebElement> actualTables = dashboardPage.isTablesDisplayed(driver);
    List<String> expectedHeader;

    PatientPage patientPage =  new PatientPage(driver);

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
        expectedMessages.addAll(allMessages);
//        expectedMessages.add(allMessages.get(0));
//        expectedMessages.add(allMessages.get(1));
//        expectedMessages.add(allMessages.get(2));

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

    @Then("validate the number of tables on the dashboard is {int}")
    public void validate_the_number_of_tables_on_the_dashboard_is(int expectedSize) {
       int actualSize =dashboardPage.isTablesDisplayed(driver).size();
       Assert.assertEquals(expectedSize,actualSize);
    }

    @Then("the tables are displayed on the page")
    public void the_tables_are_displayed_on_the_page() {
       //List<WebElement> actualTables = dashboardPage.checkTablesDisplayed(driver);
       for(WebElement table : actualTables){
           Assert.assertTrue(table.isDisplayed());
       }
    }

    @Then("the information on the table header is")
    public void the_information_on_the_table_header_is( DataTable dataTable) {
        this.expectedHeader = dataTable.asList();

    }
    @Then("validate the text of the header")
    public void validate_the_text_of_the_header() {
        List<String> actualHeader = dashboardPage.getPatientsWithRoomsHeaderText();
        Assert.assertEquals(expectedHeader,actualHeader);
    }

    @Then("validate patients score default is {string}")
    public void validate_patients_score_default_is_descending(String sortingType) {
        //assertion by using the aria-short attribute
      Assert.assertEquals(sortingType,dashboardPage.getScoreDefaultValue());

        //Assert by comparing the 2 lists
        List<String> expectedScoreColumValue = dashboardPage.getValuesOfScoreColumn();
        int expectedListSize= expectedScoreColumValue.size();
        for(int i = 0; i<expectedListSize;i++) {
            Collections.sort(expectedScoreColumValue);
            //Collections.reverse(expectedScoreColumValue);
            Assert.assertEquals(expectedScoreColumValue.get(expectedListSize-(i+1)), dashboardPage.getValuesOfScoreColumn().get(i));
        }
    }

    @Then("validate the search functionality of the {string} table")
    public void validate_the_search_functionality_of_the_table(String tableHeader,DataTable dataTable) {
        String expectedUserInfo = dataTable.asMap().get("Info").trim();

        Assert.assertTrue(dashboardPage.doSearchPatientWaiting(driver,tableHeader,expectedUserInfo,expectedUserInfo));

        patientPage.deletePatient(driver);

    }
}
