package com.bestbuyapi.bestbuyapiInfo;

import com.bestbuyapi.consants.EndPoints;
import com.bestbuyapi.model.ProductsPojo;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;


public class ProductsSteps {



    @Step("Get all products")
    public ValidatableResponse allProducts() {

        return SerenityRest.rest()
                .given()
                .when()
                .get()
                .then();
    }

    @Step("Get single product with id : {0}")
    public ValidatableResponse getSingleProductsWithId(int productsId){

        return SerenityRest.rest()
                .given()
                .pathParam("productsId",productsId)
                .when()
                .get(EndPoints.GET_SINGLE_PRODUCTS_BY_ID)
                .then();
    }

    @Step("Get Products with parameter limit : {0},skip : {1}")
    public ValidatableResponse getProductsWithParameters(int limit,int skip){

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

    @Step("Create new product with  name : {0} , type : {1} , upc : {2} , price : {3} , description : {4} , model : {5}, url :{6},image :{7}")
    public ValidatableResponse createProduct(String name,String type,double price,String upc,
                                             int shipping,String description,String manufacturer,
                                             String model,String url,String image){

        ProductsPojo productsPojo=new ProductsPojo();
        productsPojo.setName(name);
        productsPojo.setType(type);
        productsPojo.setPrice(price);
        productsPojo.setUpc(upc);
        productsPojo.setShipping(shipping);
        productsPojo.setDescription(description);
        productsPojo.setManufacturer(manufacturer);
        productsPojo.setModel(model);
        productsPojo.setUrl(url);
        productsPojo.setImage(image);

        return SerenityRest.rest()
                .given()
                .header("Content-Type","application/json")
                .body(productsPojo)
                .when()
                .post()
                .then();
    }

    @Step("Update products with  productId : {0}, name : {1} , type : {2} , upc : {3} , price : {4} , description : {5} , model : {6}, url :{7},image :{8}")
    public ValidatableResponse updateProducts(int productId,String name,String type,double price,String upc,
                                              int shipping,String description,String manufacturer,
                                              String model,String url,String image){

        ProductsPojo productsPojo=new ProductsPojo();
        productsPojo.setName(name);
        productsPojo.setType(type);
        productsPojo.setPrice(price);
        productsPojo.setUpc(upc);
        productsPojo.setShipping(shipping);
        productsPojo.setDescription(description);
        productsPojo.setManufacturer(manufacturer);
        productsPojo.setModel(model);
        productsPojo.setUrl(url);
        productsPojo.setImage(image);

        return SerenityRest.rest()
                .given()
                .header("Content-Type","application/json")
                .pathParam("productsId",productId)
                .body(productsPojo)
                .when()
                .put(EndPoints.UPDATE_PRODUCTS_BY_ID)
                .then();

    }

    @Step("Update products information with productId : {0} , name : {1}, price :{2}")
    public ValidatableResponse updateProductsWithPatch(int productId,String name,double price){

        ProductsPojo productsPojo=new ProductsPojo();
        productsPojo.setName(name);
        productsPojo.setPrice(price);

        return SerenityRest.rest()
                .given()
                .header("Content-Type","application/json")
                .pathParam("productsId",productId)
                .body(productsPojo)
                .when()
                .patch(EndPoints.UPDATE_PRODUCTS_BY_ID)
                .then();

    }

    @Step("Delete the products information")
    public ValidatableResponse deleteProducts(int productsId){

        return SerenityRest.rest()
                .given()
                .pathParam("productsId",productsId)
                .when()
                .delete(EndPoints.DELETE_PRODUCTS_BY_ID)
                .then();
    }

    @Step("Getting product information with name :{0}")
    public HashMap<String,Object> getVideoGameInfoByName(String name){
        String p1="data.findAll{it.name=='";
        String p2="'}.get(0)";

        return SerenityRest.rest()
                .given().log().all()
                .header("Accept","application/json")
                .get()
                .then()
                .statusCode(200)
                .extract()
                .path(p1+name+p2);

    }
}
