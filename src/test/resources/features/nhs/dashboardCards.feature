@SmokeTest @US_002
Feature: Dashboard cards information

 Background:
    Given Admin user navigates to NHS website 'http://www.techtorialacademy.link/'
    When The correct username and password is entered

 @TC_04
 Scenario: TC_04 Verify the number of cards on dashboard
    Then validate the number of cards on the dashboard is 3

 @TC_05
 Scenario:TC_05 Check the cards display positive numbers
    Then validate the display a positive number each

 @TC_06
 Scenario: TC_05 Check each card display correct information
    Then validate the message on each card