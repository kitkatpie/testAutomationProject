#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@contractorRequestQueue
Feature: Contractor request queue
  I want to use this feature file to validate the full contractor request queue flow

	Background: Navigate to contractor request queue
		Given user navigates to STAR QA platform
		When user goes to contractor request queue page
		Then user is on contractor request queue page

  @requestQueueTableDisplay
  Scenario: Validate request queue table has required elements and expected requests
    Given I want to write a step with precondition
    And some other precondition
    When I complete action
    And some other action
    And yet another action
    Then I validate the outcomes
    And check more outcomes

  @updateStatus
  Scenario: Edit status of contractor request
  	Given user is on contractor request queue page
  	When user updates status for request
  	Then request status is updated
