package org.example.day1.tasks.module2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Task2_Count_DataDriven {

    // Base URL for all tests
    private static final String BASE_URI = "https://jsonplaceholder.typicode.com";

    @Test(dataProvider = "endpointsProvider")
    public void validateResourceCount(String endpoint, int expectedSize) {
        // Set the Base URI
        RestAssured.baseURI = BASE_URI;

        // Send GET request and get the response
        Response response =
                given()
                        .when()
                        .get(endpoint);

        // Verify the status code
        Assert.assertEquals(response.statusCode(), 200, "Status code should be 200");

        // Parse the response and get the array size
        int arraySize = response.jsonPath().getList("").size();

        // Print the endpoint and array size
        System.out.println("Endpoint: " + endpoint + " | Array Size: " + arraySize);

        // Assert the size of the array
        Assert.assertEquals(arraySize, expectedSize, "Array size mismatch for endpoint: " + endpoint);
    }

    @DataProvider(name = "endpointsProvider")
    public Object[][] endpointsProvider() {
        return new Object[][]{
                {"/posts", 100},    // Expected size for /posts
                {"/comments", 500}, // Expected size for /comments
                {"/albums", 100},   // Expected size for /albums
                {"/photos", 5000},  // Expected size for /photos
                {"/todos", 200},    // Expected size for /todos
                {"/users", 10}      // Expected size for /users
        };
    }

}
