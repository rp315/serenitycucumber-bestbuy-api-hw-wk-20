package com.bestbuyapi.bestbuyapiInfo;

import com.bestbuyapi.consants.EndPoints;
import com.bestbuyapi.model.CategoriesPojo;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

public class CategoriesSteps {

    CategoriesPojo categoriesPojo=new CategoriesPojo();

    @Step("Get all Categories")
    public ValidatableResponse allCategories() {

        return SerenityRest.rest()
                .given()
                .when()
                .get()
                .then();
    }

    @Step("Get single categories with id : {0}")
    public ValidatableResponse getSingleCategoriesWithId(String categoriesId){

        return SerenityRest.rest()
                .given()
                .pathParam("categoriesId",categoriesId)
                .when()
                .get(EndPoints.GET_SINGLE_CATEGORIES_BY_ID)
                .then();
    }

    @Step("Get Categories with parameter limit : {0},skip : {1}")
    public ValidatableResponse getCategoriesWithParameters(int limit,int skip){

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

    @Step("Create new Categories  with name : {0} , id : {1}")
    public ValidatableResponse createCategories(String id,String name){

        categoriesPojo.setId(id);
        categoriesPojo.setName(name);
        return SerenityRest.rest()
                .given()
                .header("Content-Type","application/json")
                .body(categoriesPojo)
                .when()
                .post()
                .then();
    }

    @Step("Update Categories with name : {0} , id : {1}")
    public ValidatableResponse updateCategories(String id, String name){
        categoriesPojo.setId(id);
        categoriesPojo.setName(name);
        return SerenityRest.rest()
                .given()
                .header("Content-Type","application/json")
                .pathParam("categoriesId",id)
                .body(categoriesPojo)
                .when()
                .put(EndPoints.UPDATE_CATEGORIES_BY_ID)
                .then();

    }

    @Step("Update Categories with name : {0} , id : {1}")
    public ValidatableResponse updateCategoriesWithPatch(String id, String name){

        categoriesPojo.setName(name);
        return SerenityRest.rest()
                .given()
                .header("Content-Type","application/json")
                .pathParam("categoriesId",id)
                .body(categoriesPojo)
                .when()
                .patch(EndPoints.UPDATE_CATEGORIES_BY_ID)
                .then();

    }

    @Step("Delete the categories information with categoriesId : {0}")
    public ValidatableResponse deleteCategories(String categoriesId){

        return SerenityRest.rest()
                .given()
                .pathParam("categoriesId",categoriesId)
                .when()
                .delete(EndPoints.DELETE_CATEGORIES_BY_ID)
                .then();
    }
}
