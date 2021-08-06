package com.bestbuyapi.cucumber.steps;

import com.bestbuyapi.bestbuyapiInfo.ServicesSteps;
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

public class ServiceStepdefs {

    @Before
    public void setUp(){

        RestAssured.basePath= Path.SERVICES;
    }

    static String name="API service"+ TestUtils.getRandomValue();
    static int servicesID;
    static ValidatableResponse response = null;
    @Steps
    ServicesSteps servicesSteps;
    @When("^I create new services by providing a name$")
    public void iCreateNewServicesByProvidingAName() {
        response=servicesSteps.createServices(name);
    }

    @Then("^I verify that the services is created with name$")
    public void iVerifyThatTheServicesIsCreatedWithName() {
        HashMap<String,Object> serviceInfo=response.statusCode(201)
                .extract()
                .body()
                .jsonPath().get();
        System.out.println(serviceInfo);
        assertThat(serviceInfo,hasValue(name));
        servicesID=response.extract().path("id");
        System.out.println(name);
    }

    @When("^I update a existing services using servicesID$")
    public void iUpdateAExistingServicesUsingServicesID() {
        response=servicesSteps.updateServices(servicesID,name);
    }

    @Then("^I verify that the services name is updated$")
    public void iVerifyThatTheServicesNameIsUpdated() {

        HashMap<String,Object> serviceInfo=response.statusCode(201)
                .extract()
                .body()
                .jsonPath().get();
        System.out.println(serviceInfo);
        assertThat(serviceInfo,hasValue(name));
        System.out.println(name);
    }

    @When("^I delete services using servicesID$")
    public void iDeleteServicesUsingServicesID() {
        response=servicesSteps.deleteServices(servicesID);
    }

    @Then("^I verify that the services with servicesID is deleted$")
    public void iVerifyThatTheServicesWithServicesIDIsDeleted() {
        response.statusCode(200);
        servicesSteps.getSingleServicesWithId(servicesID).statusCode(404);
    }
}
