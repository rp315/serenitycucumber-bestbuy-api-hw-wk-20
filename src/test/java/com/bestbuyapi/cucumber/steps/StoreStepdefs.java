package com.bestbuyapi.cucumber.steps;

import com.bestbuyapi.bestbuyapiInfo.StoresSteps;
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

public class StoreStepdefs {

    @Before
    public void setUp() {

        RestAssured.basePath = Path.STORES;
    }

    static String name = "Tesco23" + TestUtils.getRandomValue();
    static String type = "Grocery" + TestUtils.getRandomValue();
    static String address = "12341 Reeds" + TestUtils.getRandomValue();
    static String address2 = "768" + TestUtils.getRandomValue();
    static String city = "London" + TestUtils.getRandomValue();
    static String state = "MN" + TestUtils.getRandomValue();
    static String zip = "456372" + TestUtils.getRandomValue();
    static double lat = 44.9674 + TestUtils.getRandomNumber();
    static double lng = -94.8712 + TestUtils.getRandomNumber();
    static String hours = "Mon: 10-9; Tue: 10-9; Wed: 10-9; Thurs: 10-9; Fri: 10-9; Sat: 10-9; Sun: 10-5";
    static int storesId = 0;
    static ValidatableResponse response = null;

    @Steps
    StoresSteps storesSteps;

    @When("^I create new stores by providing a name, type, address, address2, city, state, zip, lat, lng and hours get status code 201$")
    public void iCreateNewStoresByProvidingANameTypeAddressAddressCityStateZipLatLngAndHoursGetStatusCode() {
        response=storesSteps.createStoresData(name, type, address, address2, city, state, zip, lat, lng, hours);
    }


    @Then("^I verify that the stores with name is created$")
    public void iVerifyThatTheStoresWithNameIsCreated() {
        HashMap<String,Object> storeInfo=response.statusCode(201)
                .extract()
                .body()
                .jsonPath().get();
        System.out.println(storeInfo);
        storesId=response.extract().path("id");
        assertThat(storeInfo,hasValue(name));
        System.out.println(name);
    }

    @When("^I update a existing stores using storesID and get status code 200$")
    public void iUpdateAExistingStoresUsingStoresIDAndGetStatusCode() {
        response=storesSteps.updateStoresData(storesId, name, type, address, address2, city, state, zip, lat, lng, hours);
    }


    @Then("^I verify that the stores with name is updated$")
    public void iVerifyThatTheStoresWithNameIsUpdated() {
        HashMap<String,Object> storeInfo=response.statusCode(201)
                .extract()
                .body()
                .jsonPath().get();
        System.out.println(storeInfo);
        assertThat(storeInfo,hasValue(name));
        System.out.println(name);
    }

    @When("^I delete stores using storesID$")
    public void iDeleteStoresUsingStoresID() {
        response=storesSteps.deleteStoresData(storesId);
    }

    @Then("^I verify that the stores with storesID is deleted and get status code 404$")
    public void iVerifyThatTheStoresWithStoresIDIsDeletedAndGetStatusCode() {
        response.statusCode(200);
        storesSteps.getSingleStoresData(storesId).statusCode(404);
    }
}
