@SearchRestaurants
Feature: Use the website to find restaurants So that I can order food
  As a hungry customer
  I want to be able to find restaurants in my area

  Background: User logs in to Just Eat Website
    Given I navigate to "http://www.just-eat.co.uk/"
    Then I should see page title starts with "Order takeaway online"

  Scenario: Search for restaurants in an area
    Given I want food in "AR51 1AA"
    When I search for restaurants
    Then I should see some restaurants in "AR51 1AA"

  @VerifyIncorrectPostalCode
  Scenario Outline: Search for restaurants in invalid postal code
    Given I want food in "<invalidpostalcode>"
    When I search for restaurants
    Then I should see an error message "<ErrorMessage>"
  
    Examples: 
      | invalidpostalcode | ErrorMessage                        |
      |          12763342 | Please enter a full, valid postcode |
      | dgasdj3762        | Please enter a full, valid postcode |
      | %&&&GHH@          | Please enter a full, valid postcode |

   @VerifyLoginAndHelpLinks
    Scenario: Login and Help link should be displayed
    Given I land on homepage
    When user is not logged in
    Then I should see Login option
    And I should see Help option 