@SmokeTest @US_003
Feature: Dashboard table information
  Background:
    Given Admin user navigates to NHS website 'http://www.techtorialacademy.link/'
    When The correct username and password is entered
    And Login successfully by validating title 'NHS patients' and url 'http://www.techtorialacademy.link/app'

  @TC_08
  Scenario: TC_08 Verify tables are displayed on dashboard
    Then validate the number of tables on the dashboard is 3
    And the tables are displayed on the page

  @TC_09
  Scenario: TC_09 Check the headers on Patients with rooms has the right information
    Then the information on the table header is
    |no.|
    |Patients with rooms|
    |Room|
    |Score|
    And validate the text of the header

    Scenario: TC_10 Check the score default is descending
      Then validate patients score default is 'descending'