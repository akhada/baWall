#Ideally - this feature will contains end-to-end scenarios for standard text as input but as of now adding login scenarios. later on i'll remove it
Feature: Login feature

  Scenario: Verify Login scenarios
    When I try to login without any credentials
    Then I should be asked to fill in mandatory values

    When I login with valid credentials
    And I search the status for story number "1"

    Then I should be able to assign card to "Anay"
    And I should be able the change the state of the story to "Reopen Issue"
# step commented because logout functionality is not built on issue details screen. It works only on home page
#    And I should be able to logout

