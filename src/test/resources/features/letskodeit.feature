Feature: Lets Kode It Practice Page

  Background:
    Given user navigates to application
    And user clicks practice button
    And user switches to newly opened tab

  Scenario Outline: User should be able to select car manufacturer radio button
    When user clicks "<manufacturer>" radio button
    Then "<expectedManufacturer>" radio button is selected

    Examples:
    | manufacturer  | expectedManufacturer |
    | BMW           | BMW                  |
    | Mercedes Benz | Mercedes Benz        |
    | Honda         | Honda                |

  @smoke
  Scenario Outline: User should be able to select car manufacturer dropdown option
    When user selects "<manufacturer>" from manufacturer dropdown
    Then "<manufacturer>" is selected from manufacturer dropdown

    Examples:
      | manufacturer  |
      | BMW           |
      | Benz          |
      | Honda         |