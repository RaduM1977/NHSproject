@SmokeTest @US_010
Feature: Rooms list functionality with API

  Background:
    Given Admin user navigates to NHS website 'http://www.techtorialacademy.link/'
    When The correct username and password is entered
      |admin|
      |admin|

  @TC_25
  Scenario:TC_25 Verify the list of rooms with API
    When the user clicks the System Settings button on the left side of the page
    And the get request for 'http://www.techtorialacademy.link/app/getrooms/' is made
    Then validate the user should see the same list of 'rooms' in UI and in API
    And  appropriate status code should be displayed
      |200|