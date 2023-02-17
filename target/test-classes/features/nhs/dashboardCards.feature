@SmokeTest @US_002
Feature: Dashboard cards information

 Background:
    Given Admin user navigates to NHS website 'http://www.techtorialacademy.link/'
    When The correct username and password is entered

 @TC_04
 Scenario: TC_04 Verify the number of cards on dashboard
      Then validate the number of cards on the dashboard is 3

 @TC_05
 Scenario:TC_05 Check the cards on dashboard display positive numbers
    Then validate the display a positive number each

 @TC_06
 Scenario: TC_05 Check each card on dashboard display correct information
     Then The information cards show on the screen
      |Patients with rooms|
      |Patients waiting   |
      |Free rooms         |
     And validate the message on each card

  @TC_07
  Scenario: TC_07 Verify each card on dashboard has the right color
    Then validate the correct color on each card
      |rgb(51, 122, 183)|
      |rgb(217, 83, 79)|
      |rgb(232, 145, 6)|