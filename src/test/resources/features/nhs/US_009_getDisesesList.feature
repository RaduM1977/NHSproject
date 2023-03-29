@SmokeTest @US_009
Feature: Diseases list functionality with API

  Background:
    Given Admin user navigates to NHS website 'http://www.techtorialacademy.link/'
    When The correct username and password is entered
      |admin|
      |admin|

  @TC_24
  Scenario:TC_24 Verify the list of diseases with API
    When the user clicks the System Settings button on the left side of the page
    And the get request for 'http://www.techtorialacademy.link/app/getdiseases/' is made
    Then validate the user should see the same list of diseases and score in UI and in API
    |diseases|
#    And validate the user should see the same score for the diseases
    And  appropriate status code should be displayed
      |200|