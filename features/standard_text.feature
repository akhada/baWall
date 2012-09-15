#Ideally - this feature will contains end-to-end scenarios for standard text as input but as of now adding login scenarios. later on i'll remove it
Feature: Login feature

  Scenario: Verify Login scenarios
    When I try to login without any credentials
    Then I should be asked to fill in mandatory values

    When I login with invalid credentials
    Then I should not be logged-in

    When I login with valid credentials
    Then I search the status for story number "1234"