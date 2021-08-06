package com.bestbuyapi.bestbuyapiTest;

import com.bestbuyapi.bestbuyapiInfo.ProductsSteps;
import com.bestbuyapi.consants.Path;
import com.bestbuyapi.model.ProductsPojo;
import com.bestbuyapi.testBase.TestBase;
import com.bestbuyapi.utils.TestUtils;
import io.restassured.RestAssured;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;
import static org.hamcrest.Matchers.hasValue;
import static org.junit.Assert.assertThat;

@RunWith(SerenityRunner.class)
public class ProductsTest extends TestBase {

    static String name="Choco Lava biscuits"+ TestUtils.getRandomValue();
    static String type="Cookies"+TestUtils.getRandomValue();
    static double price=5.99;
    static String upc="0413334"+TestUtils.getRandomValue();
    static int shipping=1+TestUtils.getRandomNumber();
    static String description="Chocolaty and Testy ; 5-pack"+TestUtils.getRandomValue();
    static String manufacturer="CadBury"+TestUtils.getRandomValue();
    static String model=" ";
    static String url="http://www.bestbuy.com/site/macvities biscuit-4-pack/43900.p?id=1059384074145&skuId=43900&cmp=RMXCC";
    static String image="http://img.bbystatic.com/BestBuy_US/images/products/4390/43900_sa.jpg";
    static int productsId;

    @Before
    public void setUp(){

        RestAssured.basePath= Path.PRODUCTS;
    }

    //Get Request
    @Steps
    ProductsSteps productsSteps;
    @Title("This will get all products")
    @Test
    public void test006() {

        productsSteps.allProducts()
                .log().all()
                 .statusCode(200);
    }

    @Title("This will get single product with id")
    @Test
    public void test007() {

        productsSteps.getSingleProductsWithId(48530).log().all()
                .statusCode(200);

    }

    @Test
    @Title("This will get products with parameters")
    public void test008() {

        productsSteps.getProductsWithParameters(3,2).log().all()
                .statusCode(200);
    }

    //Create Request
    @Title("This will create new product")
    @Test
    public void test001(){

        productsSteps.createProduct(name,type,price,upc,shipping,description,manufacturer,model,url,image)
                .log().all()
                .statusCode(201);

    }

    @Title("Create and Verify if the products was added to the application")
    @Test
    public void test002() {

        String p1="data.findAll{it.name=='";
        String p2="'}.get(0)";

        HashMap<String,Object> PData=
               productsSteps.allProducts().statusCode(200)
                        .extract()
                        .path(p1+name+p2);
        assertThat(PData,hasValue(name));
        System.out.println(PData);
        productsId= (int)PData.get("id");
    }

     //Put Request
    @Title("Update products information and verify the updated information")
    @Test
    public void test005(){

        name=name+"_Updated";
        HashMap<String,Object> pValue= productsSteps.updateProducts(productsId,name,type,price,upc,shipping,description,manufacturer,model,url,image)
                .statusCode(200).extract().response().body().jsonPath().get();

        assertThat(pValue,hasValue(name));
        System.out.println(pValue);

    }

    //Patch Request
    @Title("Update product information with patch and verify it")
    @Test
    public void test003(){


        name=name+"_patch";
        price=price+1;

        HashMap<String,Object> pValue= productsSteps.updateProductsWithPatch(productsId,name,price)
                .statusCode(200).extract().response().body().jsonPath().get();

        assertThat(pValue,hasValue(name));
        System.out.println(pValue);

    }

    //Delete Request
    @Title("Delete products information with id and verify it")
    @Test
    public void test004(){
        productsSteps.deleteProducts(productsId)
                .log().all()
                .statusCode(200);

        productsSteps.getSingleProductsWithId(productsId)
                .statusCode(404);

    }
}
