#Author: aruldinakaran.kanniappan@marksandspencer.com
#Feature: User to validate the Get Order service for apiPOC
#Scenario: Validate the various response from the service
@api
Feature: User to validate the Get Order service for apiPOC

  Scenario: User to validate the Service Response
    Given To accept response in JSON format
    When I perform GET request
    Then I validate service reponse


  Scenario: User to validate the Size of the Response
    Given To accept response in JSON format
    When I perform GET request
    Then I validate size of the reponse

  Scenario: User to validate the content of the Response
    Given To accept response in JSON format
    When I perform GET request
    Then I validate content of the reponse