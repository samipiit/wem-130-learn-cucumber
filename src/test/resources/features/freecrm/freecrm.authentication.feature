Feature: Users should be able to authenticate to the application

  Scenario:
    Given user navigates to Free CRM application
    When user clicks Log In button in application header
    And user enters "sami@piit.us" in email input field
    And user enters "Test123!" in password input field
    And user clicks Submit button
    Then user is navigated to authenticated homepage
    And user full name "Sami Sheikh" is displayed in the header