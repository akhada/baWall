Feature: Verify BaWall Scenarios

  Scenario: Verify user is able to log-out
    When I login with valid credentials
    And I select the project "TEST"
    Then I should be able to logout

  Scenario: Verify standard text input scenario
    When I login with valid credentials
    And I select the project "TEST"
    And I search the status for story number "1"

    Then I should be able to assign card to "Anay"
    And I should be able the change the state of the story to "Close Issue"

  #wip - because validation part is yet to be implemented
#  Scenario: Verify Login scenarios
#    When I try to login without any credentials
#    Then I should be asked to fill in mandatory values
#
#    When I login with invalid credentials
#    Then I should not be logged-in


