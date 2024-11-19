package org.example.day2;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class RequestResponseSpecification_New {

    @BeforeClass
    public void setUp() {
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBaseUri("http://api.zippopotam.us")
                .setContentType("application/json")
                .setAccept("*/*").build();

        RestAssured.responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType("application/json")
                .expectResponseTime(lessThan(2000L))
                .build();
    }

    @Test
    public void verifyResponse() {
        get("/us/90210")
                .then().assertThat()
                .body("country", equalTo("United States"));
    }

    @Test
    public void validateZipCodeDetailsUsingBody() {

        get("/us/90210")
                .then()
                .body("'post code'", equalTo("90210"))// Validate status code
                .body("country", equalTo("United States")) // Validate country
                .body("places[0].'place name'", equalTo("Beverly Hills")) // Validate place name
                .body("places[0].state", equalTo("California"))
                .body("'country abbreviation'", equalTo("US")); // Validate state
    }

    @Test
    public void verifyPlacesDetails() {

        get("/us/90210")
                .then().assertThat()
                .body("places[0].'place name'", equalTo("Beverly Hills"))
                .body("places[0].'longitude'", equalTo("-118.4065"))
                .body("places[0].state", equalTo("California"))
                .body("places[0].'state abbreviation'", equalTo("CA"))
                .body("places[0].latitude", equalTo("34.0901"));
    }

    @Test
    public void verifyContentType() {
        get("/us/90210")
                .then().assertThat()
                .contentType("application/json");
    }
}
