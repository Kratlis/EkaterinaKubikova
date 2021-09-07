Feature: Change user information on User Table page
  The User Table page contains table with information about users.
  The table contains 4 columns: number, type, user, description.
  Number - number of the row with information of the user.
  Type - role of the user (Admin, User, Manager).
  User - username of the user.
  Description - additional information: image, description text, vip status.

  Scenario: Set the VIP status to user
    Given I open JDI GitHub site
    And I login as user "Roman Iovlev"
    And I click on "Service" button in Header
    And I click on "User Table" button in Service dropdown

    When I select 'vip' checkbox for "Sergey Ivan"

    Then 1 log row has "Vip: condition changed to true" text in log section
