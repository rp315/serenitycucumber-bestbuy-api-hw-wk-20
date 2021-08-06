package com.bestbuyapi.cucumber.steps;

import com.bestbuyapi.bestbuyapiInfo.ProductsSteps;
import com.bestbuyapi.consants.Path;
import com.bestbuyapi.utils.TestUtils;
import cucumber.api.PendingException;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;
import static org.junit.Assert.assertThat;

public class ProductStepdefs {

    static String name = null;
    static String productId = null;
    static ValidatableResponse response = null;

    @Before
    public void setUp() {
        RestAssured.basePath = Path.PRODUCTS;
    }

    @Steps
    ProductsSteps productsSteps;

    @When("^User sends a get request, they must get back a valid status code 200$")
    public void userSendsAGetRequestTheyMustGetBackAValidStatusCode() {
        productsSteps.allProducts().log().all().statusCode(200);
    }

    @When("^I create a new product by providing the information name\"([^\"]*)\",type\"([^\"]*)\",price\"([^\"]*)\",upc\"([^\"]*)\",shipping\"([^\"]*)\",description\"([^\"]*)\",manufacturer\"([^\"]*)\",model\"([^\"]*)\",url\"([^\"]*)\",image\"([^\"]*)\"$")
    public void iCreateANewProductByProvidingTheInformationNameTypePriceUpcShippingDescriptionManufacturerModelUrlImage(String _name, String type, String price, String upc,
                                                                                                                        String shipping, String description, String manufacturer,
                                                                                                                        String model, String url, String image) {

        name = _name + TestUtils.getRandomValue();
        response = productsSteps.createProduct(name, type, Double.parseDouble(price), upc, Integer.parseInt(shipping), description, manufacturer, model, url, image);


    }

    @Then("^I verify that the product with name\"([^\"]*)\" is created$")
    public void iVerifyThatTheProductWithNameIsCreated(String _name) {

        HashMap<String,Object> productInfo=response.statusCode(201)
                .extract()
                .body()
                .jsonPath().get();
        System.out.println(productInfo);
        assertThat(productInfo,hasValue(name));
        System.out.println(name);

    }

    @When("^I update a product by providing the information id\"([^\"]*)\",name\"([^\"]*)\",type\"([^\"]*)\",price\"([^\"]*)\",upc\"([^\"]*)\",shipping\"([^\"]*)\",description\"([^\"]*)\",manufacturer\"([^\"]*)\",model\"([^\"]*)\",url\"([^\"]*)\",image\"([^\"]*)\"$")
    public void iUpdateAProductByProvidingTheInformationIdNameTypePriceUpcShippingDescriptionManufacturerModelUrlImage(String id,String _name, String type, String price, String upc,
                                                                                                                       String shipping, String description, String manufacturer,
                                                                                                                       String model, String url, String image) {
        name = _name + TestUtils.getRandomValue();
        response = productsSteps.updateProducts(Integer.parseInt(id),name, type, Double.parseDouble(price), upc, Integer.parseInt(shipping), description, manufacturer, model, url, image);

    }

    @Then("^I verify that the product with name\"([^\"]*)\" is updated$")
    public void iVerifyThatTheProductWithNameIsUpdated(String _name)  {

        HashMap<String,Object> productInfo=response.statusCode(200)
                .extract()
                .body()
                .jsonPath().get();
        System.out.println(productInfo);
        assertThat(productInfo,hasValue(name));
        System.out.println(name);
    }

    @When("^I delete a product by providing the information id (\\d+)$")
    public void iDeleteAProductByProvidingTheInformationId(int id) {
        response=productsSteps.deleteProducts(id);
    }

    @Then("^I verify that the product with id (\\d+) is deleted$")
    public void iVerifyThatTheProductWithIdIsDeleted(int id) {
        response.statusCode(200);
        productsSteps.getSingleProductsWithId(id).statusCode(404);
    }
}
