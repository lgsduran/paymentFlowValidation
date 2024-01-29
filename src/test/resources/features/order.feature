#Author: Luiz Duran <lgsduran@gmail.com>
@order
Feature: Purchase confirmation
  
  As a user
  I want to buy some products
  In order to validate the order payment flow

  Background: 
    Given that I navigate to the index page
    Then the page is displayed

  @positive
  Scenario Outline: Place an order with "<cardholder>" credit card
    Given that I go to the "<section>" section
    When I find the "<product>"
    And I add it to cart
    And I go to cart page
    And I click on the Place Order button
    And I fill the name with "<name>"
    And I fill the country with "<country>"
    And I fill the city with "<city>"
    And I fill the Credit Card <card>
    And I fill the month with <month>
    And I fill the year with <year>
    And I submit the form
    Then the Order Id is displayed
    And the amount paid is equal the expected
    And The displayed name is equal to the mocked information

    Examples: 
      | cardholder | section | product     | name          | country       | city         | card             | month | year |
      | AMEX       | Laptops | MacBook Pro | John Doe      | Portugal      | Lisbon       |  375567668884617 |    02 | 2030 |
      | VISA       | Laptops | MacBook Pro | Percy Clayton | United States | Jacksonville | 4411732769254916 |     4 | 2029 |

  @negative
  Scenario Outline: Place an order without credit card
    Given that I go to the "<section>" section
    When I find the "<product>"
    And I add it to cart
    And I go to cart page
    And I click on the Place Order button
    And I submit the form
    Then the credit card information is needed to complete the order

    Examples: 
      | section | product     |
      | Laptops | MacBook Pro |