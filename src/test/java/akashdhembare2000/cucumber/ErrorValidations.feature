@tag
  Feature: Error Validation
    I want to use this template for my feature file

  @ErrorValidation
  Scenario Outline: Positive test of Submitting the order
    Given I landed on Ecommerce Page
    When Logged in with username <name> and password <password>
    Then "Incorrect email or password." message is displayed


    Examples:
    | name                         | password   |
    | akash.dhembre2000@gmail.com  | Akas@2000  |