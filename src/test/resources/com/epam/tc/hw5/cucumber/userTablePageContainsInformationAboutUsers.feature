Feature: User Table page contains information about users
  The User Table page contains table with information about users.
  The table contains 4 columns: number, type, user, description.
  Number - number of the row with information of the user.
  Type - role of the user (Admin, User, Manager).
  User - username of the user.
  Description - additional information: image, description text, vip status.

  Scenario Outline: Numbers, usernames and descriptions on User Table Page test

    Given I open JDI GitHub site
    And I login as user "Roman Iovlev"

    When I click on "Service" button in Header
    And I click on "User Table" button in Service dropdown

    Then "User Table" page should be opened
    And 6 Number Type Dropdowns should be displayed on Users Table on User Table Page
    And 6 Usernames should be displayed on Users Table on User Table Page
    And 6 Description texts under images should be displayed on Users Table on User Table Page
    And 6 checkboxes should be displayed on Users Table on User Table Page
    And  User table should contain '<Number>' number, '<User>' user, '<Description>' description

    Examples:
      | Number | User             | Description                      |
      | 1      | Roman            | Wolverine                        |
      | 2      | Sergey Ivan      | Spider Man                       |
      | 3      | Vladzimir        | Punisher                         |
      | 4      | Helen Bennett    | Captain America some description |
      | 5      | Yoshi Tannamuri  | Cyclope some description         |
      | 6      | Giovanni Rovelli | Hulksome description             |


  Scenario Outline: Droplist values on User Table Page test

    Given I open JDI GitHub site
    And I login as user "Roman Iovlev"

    When I click on "Service" button in Header
    And I click on "User Table" button in Service dropdown

    Then droplist should contain '<Dropdown Values>' values in column Type for user 'Roman'
    Examples:
      | Dropdown Values |
      | Admin           |
      | User            |
      | Manager         |
