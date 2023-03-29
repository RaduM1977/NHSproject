@SmokeTest @US_011
Feature: Patients list functionality with API

  Background:
    Given Admin user navigates to NHS website 'http://www.techtorialacademy.link/'
    When The correct username and password is entered
      |admin|
      |admin|

  @TC_26
  Scenario:TC_26 Verify the list of patients with API
#    When the user clicks the System Settings button on the left side of the page
    When the get request for 'http://www.techtorialacademy.link/app/getpatients' is made
    Then validate the user should see the same list of patients in UI and in API
    And appropriate status code should be displayed
      |200|