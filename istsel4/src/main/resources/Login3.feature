#Author: Sadakar Pochampalli
@LoginFeature
Feature: Login page validations

  Background: 
    Given User is on login page

  @Login1
  Scenario: Login to Orange HRM
    When User enters username "Admin"
    And User enters password "admin123"
    And User clicks on Login button
    Then User should navigate to Orange HRM home page

  @Login2
  Scenario: Login to Orange HRM
    When User enters username "1"
    And User enters password "1"
    And User clicks on Login button
    Then User should navigate to Orange HRM home page    
 