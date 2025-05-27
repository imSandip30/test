#Author: Sadakar Pochampalli
@LoginFeature
Feature: Login page validations

  Background: 
    Given User is on login page

  @LoginB
  Scenario: Login to Orange HRM
    When User enters username "Admin"
    And User enters password "admin123"
    And User clicks on Login button
    Then User should navigate to Orange HRM home page

  @LoginC
  Scenario: Login to Orange HRM
    When User enters username "B"
    And User enters password "B"
    And User clicks on Login button
    Then User should navigate to Orange HRM home page    
 