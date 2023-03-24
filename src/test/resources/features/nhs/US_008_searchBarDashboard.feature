@SmokeTest @US_008
Feature: Patient waiting search functionality

  Background:
    Given Admin user navigates to NHS website 'http://www.techtorialacademy.link/'
    When The correct username and password is entered
      |admin|
      |admin|

    @TC_22
    Scenario: TC_22 Verify the free rooms table is searchable
      Given the user search in 'Free rooms'
      Then validate the search functionality of the 'Free rooms' table
        |Info|Room 10|

      @TC_23
      Scenario: TC_23 Verify the patients with rooms table is searchable
        Given the user search in 'patients in hospital'
        Then validate the search functionality of the 'Patients with rooms' table
          |Info|Logan|