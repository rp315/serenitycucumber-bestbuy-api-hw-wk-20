Feature: Testing different request on resource product of best buy application

  Scenario: check if product resource of the best buy application can be accessed by users
    When User sends a get request, they must get back a valid status code 200

  Scenario Outline: Create new products and verify product added
    When I create a new product by providing the information name"<name>",type"<type>",price"<price>",upc"<upc>",shipping"<shipping>",description"<description>",manufacturer"<manufacturer>",model"<model>",url"<url>",image"<image>"
    Then I verify that the product with name"<name>" is created
    Examples:
      | name                | type        | price | upc     | shipping | description                  | manufacturer | model | url                                                                                                 | image                                                                 |
      | Choco Lava biscuits | Cookies     | 5.99  | 0413334 | 1        | Chocolaty and Testy ; 5-pack | CadBury      | 1     | http://www.bestbuy.com/site/macvities biscuit-4-pack/43900.p?id=1059384074145&skuId=43900&cmp=RMXCC | http://img.bbystatic.com/BestBuy_US/images/products/4390/43900_sa.jpg |
      | Choco biscuits      | Short bread | 4.99  | 0413334 | 2        | Chocolaty and Testy ; 4-pack | CadBury      | 1     | http://www.bestbuy.com/site/macvities biscuit-4-pack/43900.p?id=1059384074145&skuId=43900&cmp=RMXCC | http://img.bbystatic.com/BestBuy_US/images/products/4390/43900_sa.jpg |

  Scenario Outline: Update products and verify product updated
    When I update a product by providing the information id"<id>",name"<name>",type"<type>",price"<price>",upc"<upc>",shipping"<shipping>",description"<description>",manufacturer"<manufacturer>",model"<model>",url"<url>",image"<image>"
    Then I verify that the product with name"<name>" is updated
    Examples:
      | id    | name                | type        | price | upc     | shipping | description                  | manufacturer | model | url                                                                                                 | image                                                                 |
      | 43900 | Choco Lava biscuits | Cookies     | 5.99  | 0413334 | 1        | Chocolaty and Testy ; 5-pack | CadBury      | 1     | http://www.bestbuy.com/site/macvities biscuit-4-pack/43900.p?id=1059384074145&skuId=43900&cmp=RMXCC | http://img.bbystatic.com/BestBuy_US/images/products/4390/43900_sa.jpg |
      | 48530 | Choco biscuits      | Short bread | 4.99  | 0413334 | 2        | Chocolaty and Testy ; 4-pack | CadBury      | 1     | http://www.bestbuy.com/site/macvities biscuit-4-pack/43900.p?id=1059384074145&skuId=43900&cmp=RMXCC | http://img.bbystatic.com/BestBuy_US/images/products/4390/43900_sa.jpg |

    Scenario: Delete product and verify product deleted
      When I delete a product by providing the information id 127687
      Then I verify that the product with id 127687 is deleted