@SmokeTest @US_005
Feature:System Settings add room functionality

  Background:
    Given Admin user navigates to NHS website 'http://www.techtorialacademy.link/'
    When The correct username and password is entered
    And Login successfully by validating title 'NHS patients' and url 'http://www.techtorialacademy.link/app'

  @TC_13
  Scenario: TC_13 Verify the user can add a room with the room name
    When the user clicks the System Settings button on the left side of the page
    Then validate the room was added in the room table

  @TC_14
  Scenario: TC_14 Verify the user can see the rooms table in ascending order
    When the user clicks the System Settings button on the left side of the page
    Then validate the rooms are displayed in a table in 'ascending' order

  @TC_15
  Scenario: TC_15 Verify the user should be able to delete the created room
    When the user clicks the System Settings button on the left side of the page
    Then validate the user can delete selected room