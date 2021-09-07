Feature: Fill form on the Different Elements page
  Different Elements page contains form with checkboxes, radio buttons and dropdown list.
  Also, there is log section where logs appear with information about changes
  in elements in the form.

  Scenario: Select checkboxes, radio button and option in dropdown list.
    Given I open JDI GitHub site
    And I login as user "Roman Iovlev"
    And I click on "Service" button in Header
    And I click on "Different Elements" button in Service dropdown

    When I select checkbox "Water" in the row with checkboxes on Different Elements page
    And I select checkbox "Wind" in the row with checkboxes on Different Elements page
    And I select radio button "Selen" in the row with radio buttons on Different Elements page
    And I select option "Yellow" in the dropdown list on Different Elements page

    Then The log row for checkbox "Water" and checkbox status "true" should be in logs panel on Different Elements page
    And The log row for checkbox "Wind" and checkbox status "true" should be in logs panel on Different Elements page
    And The log row for radio button with value "Selen" should be in logs panel on Different Elements page
    And The log row for dropdown option with value "Yellow" should be in logs panel on Different Elements page


