@SmokeTest @US_007
Feature: Patient waiting search functionality

  Background:
    Given Admin user navigates to NHS website 'http://www.techtorialacademy.link/'
    When The correct username and password is entered
      |admin|
      |admin|
#    And Login successfully by validating title 'NHS patients' and url 'http://www.techtorialacademy.link/app'

  @TC_18
  Scenario: TC_18 Verify the new user is searchable by first name
    Given the user clicks the add patient button on the left side of the page
    When the user create a patient
      | First Name    | John       |
      | Last Name     | Doe        |
      | Hospital No.  | 101        |
      | Date of Birth | 12/03/2021 |
      | Gender        | Female     |
      | Disease       | Acne       |

    Then validate the search functionality of the 'Patients waiting' table
      |Info|John|

  @TC_19
  Scenario: TC_19 Verify the new user is searchable by last name
    Given the user clicks the add patient button on the left side of the page
    When the user create a patient
      | First Name    | John       |
      | Last Name     | Doe        |
      | Hospital No.  | 101        |
      | Date of Birth | 12/03/2021 |
      | Gender        | Female     |
      | Disease       | Acne       |

    Then validate the search functionality of the 'Patients waiting' table
      |Info|Doe|

  @TC_20
  Scenario: TC_20 Verify the new user is searchable by full name
    Given the user clicks the add patient button on the left side of the page
    When the user create a patient
      | First Name    | John       |
      | Last Name     | Doe        |
      | Hospital No.  | 101        |
      | Date of Birth | 12/03/2021 |
      | Gender        | Female     |
      | Disease       | Acne       |

    Then validate the search functionality of the 'Patients waiting' table
      |Info|John Doe|

  @TC_21
  Scenario: TC_21 Verify the new user is searchable by hospital no. name
    Given the user clicks the add patient button on the left side of the page
    When the user create a patient
      | First Name    | John       |
      | Last Name     | Doe        |
      | Hospital No.  | 101        |
      | Date of Birth | 12/03/2021 |
      | Gender        | Female     |
      | Disease       | Acne       |

    Then validate the search functionality of the 'Patients waiting' table
      |Info|101|