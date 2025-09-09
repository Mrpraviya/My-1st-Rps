Feature: Add tasks
  As a user
  I want to add a new task
  So that I can track my work

  Scenario: Add a valid task
    Given the app is running
    When I add a task titled "Buy milk"
    Then I should see "Buy milk" in the list
