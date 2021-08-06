package com.bestbuyapi.bestbuyapiTest;

import com.bestbuyapi.bestbuyapiInfo.CategoriesSteps;
import com.bestbuyapi.consants.Path;
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
public class CategoriesTest extends TestBase {


    @Before
    public void setUp() {

        RestAssured.basePath = Path.CATEGORIES;
    }

    static String name = "Learning Ideas" + TestUtils.getRandomValue();
    static String categoriesId = "abcat008002"+TestUtils.getRandomNumber();

    //Get Request
    @Steps
    CategoriesSteps categoriesSteps;

    @Title("This will get all categories")
    @Test
    public void test006() {

        categoriesSteps.allCategories()
                .log().all()
                .statusCode(200);
    }

    @Title("This will get single categories with id")
    @Test
    public void test007() {

        categoriesSteps.getSingleCategoriesWithId("abcat0020002").log().all()
                .statusCode(200);

    }

    @Test
    @Title("This will get categories with parameters")
    public void test008() {

        categoriesSteps.getCategoriesWithParameters(3, 1).log().all()
                .statusCode(200);
    }

    //Create Request
    @Title("This will create new categories")
    @Test
    public void test005() {

        categoriesSteps.createCategories(categoriesId, name)
                .log().all()
                .statusCode(201);

    }

    @Title("Create and Verify if the categories was added to the application")
    @Test
    public void test001() {


        HashMap<String, Object> categoriesValue =
                categoriesSteps.createCategories(categoriesId, name).statusCode(201)
                        .extract()
                        .body().jsonPath().get();
        System.out.println(categoriesValue);
        assertThat(categoriesValue, hasValue(name));
        categoriesId = (String) categoriesValue.get("id");
    }

    //Put Request
    @Title("Update categories information and verify the updated information")
    @Test
    public void test002() {

        name = name + "_Updated";
        HashMap<String, Object> categoriesValue = categoriesSteps.updateCategories(categoriesId, name)
                .statusCode(200)
                .extract()
                .body().jsonPath().get();
        assertThat(categoriesValue, hasValue(name));
        System.out.println(categoriesValue);

    }

    //Patch Request
    @Title("Update categories information with patch request and verify the updated information")
    @Test
    public void test003() {

        name = name + "_UpdatedPatch";

        HashMap<String, Object> categoriesValue = categoriesSteps.updateCategoriesWithPatch(categoriesId, name)
                .statusCode(200)
                .extract()
                .body().jsonPath().get();
        assertThat(categoriesValue, hasValue(name));
        System.out.println(categoriesValue);

    }


    //Delete Request
    @Title("Delete categories information with id and verify it")
    @Test
    public void test004() {

        categoriesSteps.deleteCategories(categoriesId)
                .log().all()
                .statusCode(200);

        categoriesSteps.getSingleCategoriesWithId(categoriesId)
                .statusCode(404);

    }
}
