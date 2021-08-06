package com.bestbuyapi.cucumber.steps;

import com.bestbuyapi.bestbuyapiInfo.CategoriesSteps;
import com.bestbuyapi.consants.Path;
import com.bestbuyapi.testBase.TestBase;
import com.bestbuyapi.utils.TestUtils;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;
import static org.junit.Assert.assertThat;

public class CategoryStepdefs {

   @Before
    public void setUp() {

        RestAssured.basePath = Path.CATEGORIES;
    }

    static String name = "Learning Ideas" + TestUtils.getRandomValue();
    static String categoriesId = "abcat008002"+TestUtils.getRandomNumber();
    static ValidatableResponse response = null;

    @Steps
    CategoriesSteps categoriesSteps;

    @When("^I create new categories by providing a name & id$")
    public void iCreateNewCategoriesByProvidingANameId() {
        response=categoriesSteps.createCategories(categoriesId,name);
    }

    @Then("^I verify that the categories is created with name$")
    public void iVerifyThatTheCategoriesIsCreatedWithName() {
        response.statusCode(201);
        HashMap<String,Object> categoryInfo =response.statusCode(201)
                .extract()
                .body()
                .jsonPath().get();
        System.out.println(categoryInfo);
        categoriesId=response.extract().path("id");
        assertThat(categoryInfo,hasValue(name));
        System.out.println(name);
    }

    @When("^I update a existing categories using categoriesID$")
    public void iUpdateAExistingCategoriesUsingCategoriesID() {
        response=categoriesSteps.updateCategories(categoriesId,name);
    }

    @Then("^I verify that the categories name is updated$")
    public void iVerifyThatTheCategoriesNameIsUpdated() {
        response.statusCode(200);
        HashMap<String,Object> categoryInfo =response.statusCode(201)
                .extract()
                .body()
                .jsonPath().get();
        System.out.println(categoryInfo);
        assertThat(categoryInfo,hasValue(name));
        System.out.println(name);

    }

    @When("^I delete categories using categoriesID$")
    public void iDeleteCategoriesUsingCategoriesID() {
        response=categoriesSteps.deleteCategories(categoriesId);
    }

    @Then("^I verify that the categories with categoriesID is deleted$")
    public void iVerifyThatTheCategoriesWithCategoriesIDIsDeleted() {
        response.statusCode(200);
        categoriesSteps.getSingleCategoriesWithId(categoriesId).statusCode(404);
    }
}
