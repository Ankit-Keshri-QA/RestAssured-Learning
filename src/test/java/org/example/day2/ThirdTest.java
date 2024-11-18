package org.example.day2;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class ThirdTest {

    private final RequestSpecification res = new RequestSpecBuilder()
            .setBaseUri("http://api.zippopotam.us").build();

    @Test
    public void verifyResponse() {
        given().spec(res)
                .when()
                .get("/us/90210")
                .then().assertThat().statusCode(200)
                .body("country", equalTo("United States"));
    }

    @Test
    public void validateZipCodeDetailsUsingBody() {
        // Base URI
        RestAssured.baseURI = "http://api.zippopotam.us";

        // Send GET request and validate response fields
        given()
                .when()
                .get("/us/90210")
                .then()
                .statusCode(200)
                .body("'post code'", equalTo("90210"))// Validate status code
                .body("country", equalTo("United States")) // Validate country
                .body("places[0].'place name'", equalTo("Beverly Hills")) // Validate place name
                .body("places[0].state", equalTo("California"))
                .body("'country abbreviation'", equalTo("US")); // Validate state
    }

    @Test
    public void verifyPlacesDetails() {

        given().spec(res)
                .when()
                .get("/us/90210")
                .then().assertThat().statusCode(200)
                .body("places[0].'place name'", equalTo("Beverly Hills"))
                .body("places[0].'longitude'", equalTo("-118.4065"))
                .body("places[0].state", equalTo("California"))
                .body("places[0].'state abbreviation'", equalTo("CA"))
                .body("places[0].latitude", equalTo("34.0901"));


    }

    @Test
    public void verifyNegativeAreas() {
        given().spec(res)
                .when()
                .get("/us/99999")
                .then().assertThat().statusCode(404)
                .statusLine(containsString("Not Found"));


    }

    @Test
    public void verifyContentType() {
        given().spec(res)
                .when()
                .get("/us/90210")
                .then().assertThat().statusCode(200)
                .contentType("application/json");
    }


}
