package org.example.day5;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class Football_API_Auth_Tests {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://api.football-data.org";
        RestAssured.basePath = "/v4";

        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setContentType("application/json")
                .addHeader("X-Auth-Token", "dbd13a5afb194441841c979a7190b52d")
                // My Token received as API key from the team on mail
                .build();
    }

    @Test
    public void getTeamLists() {
        get("/teams")
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void validateTeamName() {
        get("/teams/66")
                .then()
                .assertThat()
                .statusCode(200)
                .body("name", equalTo("Manchester United FC"));
    }

    @Test
    public void responsePathValidation() {
        Response response = get("/teams/66");

        Assert.assertEquals(response.path("name"), "Manchester United FC");
        Assert.assertEquals(response.statusCode(), 200);

    }

    @Test
    public void testCase4() {
        Response response1 = get("/teams/66");
        JsonPath jsonPath = response1.jsonPath();
        Assert.assertEquals(jsonPath.get("name"), "Manchester United FC");
    }

    @Test
    public void testCase5() {
        String responseAsString = get("/teams/66").asString();
        String name = JsonPath.from(responseAsString).get("name");
        Assert.assertEquals(name, "Manchester United FC");
    }

    @Test
    public void oneLinerAssertion() {
        Assert.assertEquals(get("/teams/66").path("name"), "Manchester United FC");
    }



}
