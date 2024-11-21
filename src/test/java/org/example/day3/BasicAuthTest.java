package org.example.day3;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class BasicAuthTest {

    @Test
    public void noAuth() {
        given().baseUri("https://postman-echo.com")
                .when()
                .get("/basic-auth")
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void basicAuthAdded() {
        Response response = given().baseUri("https://postman-echo.com")
                .auth().basic("postman", "password")
                .when()
                .get("/basic-auth");

        String responseBody = response.getBody().asString();

        // Print the response body
        System.out.println("Response Body: ");
        System.out.println(responseBody);

    }
}
