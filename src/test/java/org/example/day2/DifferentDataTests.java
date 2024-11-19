package org.example.day2;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class DifferentDataTests {

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
    public void verifyState1() {
        given().
                spec(res).
                when()
                .get("/us/90210")
                .then().assertThat().spec(resp)
                .body("places[0].state", equalTo("California"));
    }

    @Test
    public void verifyState2() {
        given().
                spec(res).
                when()
                .get("/us/12345")
                .then().assertThat().spec(resp)
                .body("places[0].state", equalTo("New York"));
    }

    @Test
    public void verifyState3() {
        given().
                spec(res).
                when()
                .get("/de/24848")
                .then().assertThat().spec(resp)
                .body("places[0].state", equalTo("Schleswig-Holstein"));
    }

    @Test
    public void verifyState4() {
        given().
                spec(res).
                when()
                .get("/ca/Y1A")
                .then().assertThat().spec(resp)
                .body("places[0].state", equalTo("Yukon"));
    }

}
