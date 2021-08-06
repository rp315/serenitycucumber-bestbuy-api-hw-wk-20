package com.bestbuyapi.bestbuyapiTest;

import com.bestbuyapi.bestbuyapiInfo.StoresSteps;
import com.bestbuyapi.consants.Path;
import com.bestbuyapi.model.StoresPojo;
import com.bestbuyapi.testBase.TestBase;
import com.bestbuyapi.utils.TestUtils;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
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
public class StoresTest extends TestBase {

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
    static int storesId = 7;

    @Steps
    StoresSteps storesSteps;

    //Get request
    @Title("This will get all stores information")
    @Test
    public void test006() {

        storesSteps.getAllStoresData()
                .log().all()
                .statusCode(200);
    }

    @Title("This will get single stores information")
    @Test
    public void test007() {

        storesSteps.getSingleStoresData(11)
                .log().all()
                .statusCode(200);
    }

    @Title("This will get stores information with parameters limit and skip")
    @Test
    public void test008() {

        storesSteps.getStoresDataWithParameter()
                .log().all()
                .statusCode(200);
    }

    //Post request
    @Title("Create new store")
    @Test
    public void test005() {

        storesSteps.createStoresData(name, type, address, address2, city, state, zip, lat, lng, hours)
                .log().all()
                .statusCode(201);
    }


    @Title("Create and Verify the stores was added to the application")
    @Test
    public void test001() {

        HashMap<String, Object> storesValue =
                storesSteps.createStoresData(name, type, address, address2, city, state, zip, lat, lng, hours)
                        .log().all()
                        .statusCode(201)
                        .extract()
                        .body().jsonPath().get();
        System.out.println(storesValue);
        assertThat(storesValue, hasValue(name));
        storesId = (int) storesValue.get("id");
    }

    //Put Request
    @Title("Update stores information and verify updated information")
    @Test
    public void test002() {

        name = name + "_Update";
        HashMap<String, Object> storesValue = storesSteps.updateStoresData(storesId, name, type, address, address2, city, state, zip, lat, lng, hours)
                .statusCode(200)
                .extract()
                .body().jsonPath().get();
        assertThat(storesValue, hasValue(name));
        System.out.println(storesValue);

    }

    //Patch request
    @Title("Update stores information with patch request and verify information")
    @Test
    public void test003() {

        name = name + "_UpdatePatch";

        HashMap<String, Object> storesValue = storesSteps.updateStoreRecordWithPatch(storesId, name).statusCode(200)
                        .extract()
                        .body().jsonPath().get();
        assertThat(storesValue, hasValue(name));
        System.out.println(storesValue);

    }

    //Delete Request
    @Title("This will delete the stores information")
    @Test
    public void test004() {

        storesSteps.deleteStoresData(storesId).statusCode(200);
        storesSteps.getSingleStoresData(storesId).statusCode(404);
    }
}
