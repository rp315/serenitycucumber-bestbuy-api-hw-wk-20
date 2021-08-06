Feature: Services Feature

  As a user I want to perform CRUD operation for Services

  Scenario: Create new services and verify if the services is added
    When      I create new services by providing a name
    Then      I verify that the services is created with name


  Scenario: Update services name and verify if the services name is updated
    When    I update a existing services using servicesID
    Then    I verify that the services name is updated


  Scenario: Delete services and verify if the services is deleted
    When      I delete services using servicesID
    Then      I verify that the services with servicesID is deleted
