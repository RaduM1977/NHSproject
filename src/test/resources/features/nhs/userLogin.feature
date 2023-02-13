@SmokeTest
Feature: Sign-in functionality

  Scenario: TC_01 Registered user - login successfully
    Given User navigates to NHS website
    When The correct username and password is entered
    Then Login should be successful