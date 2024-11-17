package org.example.tasks;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class Task2_ResponseCodeValidation {

    private static final String BASE_URI = "https://jsonplaceholder.typicode.com";

    @Test(dataProvider = "endpointsProvider")
    public void verifyStatusCodeForEachEndPoint(String endpoint) {
        RestAssured.baseURI = BASE_URI;

        Response response = RestAssured
                .given()
                .when()
                .get(endpoint);

        Assert.assertEquals(response.statusCode(), 200, "Status code should be 200");

    }

    @DataProvider(name = "endpointsProvider")
    public String[] endpointsProvider() {
        String data[] = {"/posts", "/comments", "/albums", "/photos", "/todos", "/users"};

        return data;

    }

}
