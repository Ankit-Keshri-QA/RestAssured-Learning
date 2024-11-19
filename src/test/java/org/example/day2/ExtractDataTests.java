package org.example.day2;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ExtractDataTests {

    private final RequestSpecification res = new RequestSpecBuilder()
            .setBaseUri("http://api.zippopotam.us")
            .setContentType("application/json")
            .setAccept("*/*").build();
    private final ResponseSpecification resp = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectContentType("application/json")
            .expectResponseTime(lessThan(2000L))
            .build();

    @Test
    public void extractResponseBodyTest() {
        Response resp = given().
                spec(res).
                when()
                .get("/us/90210")
                .then().extract().response();

        int statusCode = resp.getStatusCode();
        String country = resp.path("country");
        String state = resp.path("places[0].state");

        System.out.println("Status Code is " + statusCode);
        System.out.println(country + " - " + state);

    }
}
