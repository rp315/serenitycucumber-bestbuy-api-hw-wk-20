package com.bestbuyapi.bestbuyapiInfo;

import com.bestbuyapi.consants.EndPoints;
import com.bestbuyapi.model.StoresPojo;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

public class StoresSteps {

    //Get Request
    @Step("Get all stores")
    public ValidatableResponse getAllStoresData() {

        return SerenityRest.rest()
                .given()
                .when()
                .get()
                .then();

    }

    @Step("Get single stores with id : {0}")
    public ValidatableResponse getSingleStoresData(int storesId) {

        return SerenityRest.rest()
                .given()
                .pathParam("storesId", storesId)
                .when()
                .get(EndPoints.GET_SINGLE_STORES_BY_ID)
                .then();

    }

    @Step("Get stores information with parameter limit: {0},skip: {1}")
    public ValidatableResponse getStoresDataWithParameter() {

        HashMap<String,Object> qParams=new HashMap<>();
        qParams.put("$limit",3);
        qParams.put("$skip",1);

      return   SerenityRest.rest()
                .given()
                .queryParams(qParams)
                .when().get()
                .then();

    }

    //Post Request
    @Step("Create new stores with name: {0},type :{1},address: {2},address2: {3},city :{4}," +
            "state : {5},zip :{6},lat :{7},lng :{8},hours:{9}")
    public ValidatableResponse createStoresData(String name,String type,String address,String address2,
                                                String city,String state,String zip,double lat,double lng,String hours ) {
        StoresPojo storesPojo = new StoresPojo();
        storesPojo.setName(name);
        storesPojo.setType(type);
        storesPojo.setAddress(address);
        storesPojo.setAddress2(address2);
        storesPojo.setCity(city);
        storesPojo.setState(state);
        storesPojo.setZip(zip);
        storesPojo.setLat(lat);
        storesPojo.setLng(lng);
        storesPojo.setHours(hours);

        return SerenityRest.rest()
                .given()
                .header("Content-Type","application/json")
                .body(storesPojo)
                .when()
                .post()
                .then();

    }

    //Put request
    @Step("Update new stores with name: {0},type :{1},address: {2},address2: {3},city :{4}," +
            "state : {5},zip :{6},lat :{7},lng :{8},hours:{9}")
    public ValidatableResponse updateStoresData(int storesId,String name,String type,String address,String address2,
                                                String city,String state,String zip,double lat,double lng,String hours ) {
        StoresPojo storesPojo = new StoresPojo();
        storesPojo.setName(name);
        storesPojo.setType(type);
        storesPojo.setAddress(address);
        storesPojo.setAddress2(address2);
        storesPojo.setCity(city);
        storesPojo.setState(state);
        storesPojo.setZip(zip);
        storesPojo.setLat(lat);
        storesPojo.setLng(lng);
        storesPojo.setHours(hours);

        return SerenityRest.rest()
                .given()
                .pathParam("storesId",storesId)
                .header("Content-Type","application/json")
                .body(storesPojo)
                .when()
                .put(EndPoints.UPDATE_STORES_BY_ID)
                .then();

    }


    //Patch Request
    @Step("Update stores information with patch request with name : {0}")
    public ValidatableResponse updateStoreRecordWithPatch(int storesId,String name) {

        StoresPojo storesPojo = new StoresPojo();
        storesPojo.setName(name);

        return SerenityRest.rest()
                .given()
                .pathParam("storesId",storesId)
                .header("Content-Type","application/json")
                .body(storesPojo)
                .when()
                .patch(EndPoints.UPDATE_STORES_BY_ID)
                .then();


    }

    //Delete Request
    @Step("Delete stores information with storesId : {0}")
    public ValidatableResponse deleteStoresData(int storesId) {

        return SerenityRest.rest()
                .given()
                .pathParam("storesId",storesId)
                .when()
                .delete(EndPoints.DELETE_STORES_BY_ID)
                .then();
    }
}
