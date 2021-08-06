Feature: Categories Feature

  As a user I want to perform CRUD operation for Categories

  Scenario:  Create new categories and verify if the categories is added
    When      I create new categories by providing a name & id
    Then      I verify that the categories is created with name


  Scenario: Update categories name and verify if the categories name is updated
    When    I update a existing categories using categoriesID
    Then    I verify that the categories name is updated


  Scenario: Delete categories and verify if the categories is deleted
    When    I delete categories using categoriesID
    Then    I verify that the categories with categoriesID is deleted
