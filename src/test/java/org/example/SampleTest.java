package org.example;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;

public class SampleTest {


    public static void main(String[] args) {
        given()
                .baseUri("https://jsonplaceholder.typicode.com")
                .when()
                .get("/users")
                .then()
                .statusCode(200) // Assert status code
                .contentType("application/json") // Assert content type
                .body("size()", greaterThan(3)) // Assert the response contains more than 3 users
                .body("[0].name", equalTo("Leanne Graham")); // Assert the first user's name
    }

}
