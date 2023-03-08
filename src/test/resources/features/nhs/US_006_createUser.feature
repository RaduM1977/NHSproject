@SmokeTest @US_006
Feature:Create User functionality

  @TC_16
  Scenario: TC_16 Verify a new user can be created
    Given Admin user navigates to NHS website 'http://www.techtorialacademy.link/'
    When The correct username and password is entered
      |admin|
      |admin|
    And the user clicks the System Settings button on the left side of the page
    When we create a new user
    |User name| John Doe|
    |Password|john@123 |
    Then  validate the message 'User succesfully created'

  @TC_17
  Scenario: TC_17 Verify the login with the new user
    Given Admin user navigates to NHS website 'http://www.techtorialacademy.link/'
    When The correct username and password is entered
      |John Doe|
      |john@123|
    And Login successfully by validating title 'NHS patients' and url 'http://www.techtorialacademy.link/app'
    Then validate the number of tables on the dashboard is 3
    And the tables are displayed on the page
    Then the information on the table header is
      |no.|
      |Patients with rooms|
      |Room|
      |Score|
    And validate the text of the header
    Then The information cards show on the screen
      |Patients with rooms|
      |Patients waiting   |
      |Free rooms         |
    And validate the message on each card
    And validate the correct color on each card
      |rgb(51, 122, 183)|
      |rgb(217, 83, 79)|
      |rgb(232, 145, 6)|