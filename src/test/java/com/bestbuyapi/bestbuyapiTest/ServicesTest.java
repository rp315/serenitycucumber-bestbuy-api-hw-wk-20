package com.bestbuyapi.bestbuyapiTest;
import com.bestbuyapi.bestbuyapiInfo.ServicesSteps;
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
public class ServicesTest extends TestBase {


    @Before
    public void setUp(){

        RestAssured.basePath= Path.SERVICES;
    }

    static String name="API service"+ TestUtils.getRandomValue();
    static int servicesID;

    //Get Request
    @Steps
    ServicesSteps servicesSteps;
    @Title("This will get all services")
    @Test
    public void test006() {

        servicesSteps.allServices()
                .log().all()
                .statusCode(200);
    }

    @Title("This will get single services with id")
    @Test
    public void test007() {

        servicesSteps.getSingleServicesWithId(6).log().all()
                .statusCode(200);

    }

    @Test
    @Title("This will get services with parameters")
    public void test008() {

        servicesSteps.getServicesWithParameters(4,2).log().all()
                .statusCode(200);
    }

    //Create Request
    @Title("This will create new services")
    @Test
    public void test004(){


        servicesSteps.createServices(name)
                .log().all()
                .statusCode(201);

    }

    @Title("Create and Verify the services was added to the application")
    @Test
    public void test001() {

        HashMap<String,Object> servicesValue=
                servicesSteps.createServices(name).statusCode(201)
                        .extract().body().jsonPath().get();
        assertThat(servicesValue,hasValue(name));
        System.out.println(servicesValue);
        servicesID= (int) servicesValue.get("id");
    }

    //Put Request
    @Title("Update services information and verify the updated information")
    @Test
    public void test002(){

        name=name+"_Updated";

        HashMap<String,Object> servicesValue= servicesSteps.updateServices(servicesID,name)
                .statusCode(200).extract().body().jsonPath().get();
        assertThat(servicesValue,hasValue(name));
        System.out.println("Updated name : "+name);
        System.out.println(servicesValue);

    }

    //Delete Request
    @Title("Delete services information with id and verify it")
    @Test
    public void test003(){
        servicesSteps.deleteServices(servicesID)
                .log().all()
                .statusCode(200);
        servicesSteps.getSingleServicesWithId(servicesID)
                .statusCode(404);

    }
}
