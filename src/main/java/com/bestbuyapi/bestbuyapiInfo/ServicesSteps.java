package com.bestbuyapi.bestbuyapiInfo;

import com.bestbuyapi.consants.EndPoints;
import com.bestbuyapi.model.ServicesPojo;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

public class ServicesSteps {

    ServicesPojo servicesPojo=new ServicesPojo();

    @Step("Get all Services")
    public ValidatableResponse allServices() {

        return SerenityRest.rest()
                .given()
                .when()
                .get()
                .then();
    }

    @Step("Get single services with id : {0}")
    public ValidatableResponse getSingleServicesWithId(int servicesId){

        return SerenityRest.rest()
                .given()
                .pathParam("servicesId",servicesId)
                .when()
                .get(EndPoints.GET_SINGLE_SERVICES_BY_ID)
                .then();
    }

    @Step("Get Services with parameter limit : {0},skip : {1}")
    public ValidatableResponse getServicesWithParameters(int limit,int skip){

        HashMap<String,Integer> qParams=new HashMap<>();
        qParams.put("$limit",limit);
        qParams.put("$skip",skip);
        return SerenityRest.rest()
                .given()
                .queryParams(qParams)
                .when()
                .get()
                .then();
    }

    @Step("Create new Services with  name : {0}")
    public ValidatableResponse createServices(String name){


        servicesPojo.setName(name);
        return SerenityRest.rest()
                .given()
                .header("Content-Type","application/json")
                .body(servicesPojo)
                .when()
                .post()
                .then();
    }

    @Step("Update services with servicesId : {0} name : {1}")
    public ValidatableResponse updateServices(int servicesId,String name){
        servicesPojo.setName(name);
        return SerenityRest.rest()
                .given()
                .header("Content-Type","application/json")
                .pathParam("servicesId",servicesId)
                .body(servicesPojo)
                .when()
                .put(EndPoints.UPDATE_SERVICES_BY_ID)
                .then();

    }


    @Step("Delete the services information  with ServicesId : {0}")
    public ValidatableResponse deleteServices(int servicesId){

        return SerenityRest.rest()
                .given()
                .pathParam("servicesId",servicesId)
                .when()
                .delete(EndPoints.DELETE_SERVICES_BY_ID)
                .then();
    }
}
