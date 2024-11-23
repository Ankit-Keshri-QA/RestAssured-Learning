package org.example.practisePackage;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class Test2 {


    // RequestSpecification Interface
    // RequestSpecBuilder class
    // build() method

    private final RequestSpecification res = new RequestSpecBuilder()
            .setBaseUri("http://api.zippopotam.us")
            .build();

    private final ResponseSpecification resp = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .build();

    @Test
    public void verifyResponse() {
        given().spec(res)
                .when()
                .get("/us/90210")
                .then().assertThat().spec(resp)
                .body("country", equalTo("United States"));
    }

    @Test
    public void verifyPlacesDetails() {

        given().spec(res)
                .when()
                .get("/us/90210")
                .then().assertThat().spec(resp)
                .body("places[0].'place name'", equalTo("Beverly Hills"))
                .body("places[0].state", equalTo("California"))
                .body("places[0].'state abbreviation'", equalTo("CA"));
    }
}
