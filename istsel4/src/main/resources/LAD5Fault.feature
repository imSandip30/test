Feature: Dynamic Table Verification
  As a tester
  I want to verify the dynamic table on the page
  So that I can ensure data is displayed correctly

  Scenario: Verify dynamic table presence and content
    Given I am on the page with the LAD5 fault table
    And    the LAD5 fault table should contain the message "Alfreds Futterkiste"