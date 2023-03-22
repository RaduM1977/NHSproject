@SmokeTest @US_004
Feature: Add Patient functionality

  Background:
    Given Admin user navigates to NHS website 'http://www.techtorialacademy.link/'
    When The correct username and password is entered
      |admin|
      |admin|
    And Login successfully by validating title 'NHS patients' and url 'http://www.techtorialacademy.link/app'

  @TC_10
  Scenario: TC_10 Verify that the fields to addPatient are required and displayed
    Given the user clicks the add patient button on the left side of the page
    Then the user should be able to see add patient details like
      | First Name    |
      | Last Name     |
      | Hospital no.  |
      | Date of birth |
      | Sex           |
      | Male          |
      | Female        |
    When the user create a patient
      | First Name    | John       |
      | Last Name     | Doe        |
      | Hospital No.  | 101        |
      | Date of Birth | 12/03/2021 |
      | Gender        | Female     |
      | Disease       | Acne       |

    Then the user should be able to see the patient is 'added' to the dashboard page under 'Patients waiting' table
    |User info|101 John Doe 2|

  @TC_11
  Scenario:TC_11 Check the duplicate patient can not be added, the hospital no. is unique
    Given the user clicks the add patient button on the left side of the page
    When the user create a patient
      | First Name    | John       |
      | Last Name     | Doe        |
      | Hospital No.  | 101        |
      | Date of Birth | 12/03/2021 |
      | Gender        | Female     |
      | Disease       | Acne       |
    Given the user clicks the add patient button on the left side of the page
    When the user create a patient
      | First Name    | John       |
      | Last Name     | Doe        |
      | Hospital No.  | 101        |
      | Date of Birth | 12/03/2021 |
      | Gender        | Female     |
      | Disease       | Acne       |
    Then the user should be able to see the patient is 'not added' to the dashboard page under 'Patients waiting' table
      |User info|101 John Doe 2|

  @TC_12
  Scenario:TC_12 Check required fields for adding a patient are preset and the DOB field is date type format
    Given the user clicks the add patient button on the left side of the page
    When the user create a patient
      | First Name    | John       |
      | Last Name     | Doe        |
      | Hospital No.  | 101        |
      | Date of Birth | 0940395305 |
      | Gender        | Female     |
      | Disease       | Acne       |
    Then the user should not be able to create a patient and should see the message 'The date is not valid.'