@tag
  Feature: Purchase order for Ecommerce
    I want to use this template for my feature file


    Background:
      Given I landed on Ecommerce Page

  @Regression
  Scenario Outline: Positive test of Submitting the order
    Given Logged in with username <name> and password <password>
    When Add the product <productName> to Cart
    And Checkout <productName> and submit the order
    Then Verify the confirmation message "Thankyou for the order." is displayed on ConfirmationPage


    Examples:
    | name                         | password   | productName |
    | akash.dhembare2000@gmail.com | Akash@2000 | ZARA COAT 3 |