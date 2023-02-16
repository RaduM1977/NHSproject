@SmokeTest @US_001
Feature: Sign-in functionality

  Background:
    Given Admin user navigates to NHS website 'http://www.techtorialacademy.link/'

  @TC_01
  Scenario: TC_01 Registered user - login successfully
    When The correct username and password is entered
    Then Login successfully by validating title 'NHS Patient' and url 'http://www.techtorialacademy.link/app'

   @TC_02
   Scenario: TC_02 Register user - login unsuccessfully with incorrect username and password
     When the incorrect username and password is entered
     Then login unsuccessfully by validating the title  'LoginPage' and url 'http://www.techtorialacademy.link/'

  @TC_03
   Scenario Outline: TC_03 Register user - login unsuccessfully with blank username and password
     When the blank '<username>' and,or '<password>' is entered
     Then login unsuccessfully by validating the title  'LoginPage' and url 'http://www.techtorialacademy.link/'
     Examples:
     |username|password|
     |        |admin   |
     |admin   |        |
     |        |        |
