package org.example.day1;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;

public class SampleTest {

    @Test
    public void testGetEmployeeDetails() {
        RestAssured.baseURI = "https://dummy.restapiexample.com/api/v1";

        given()
                .header("Accept", ContentType.JSON)
                .param("id", 123)
                .when()
                .get("/employee/{id}",1)
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("data.id", equalTo(123))
                .body("data.employee_name", equalTo("John Doe"));
    }

    @Test
    public void verifyJsonRepsonse() {
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
