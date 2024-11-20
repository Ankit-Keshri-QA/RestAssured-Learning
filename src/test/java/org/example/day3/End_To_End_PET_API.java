package org.example.day3;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class End_To_End_PET_API {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://petstore.swagger.io";
        RestAssured.basePath = "/v2";
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setContentType("application/json").build();
    }

    @Test()
    public void getPetByID() {
        when()
                .get("/pet/9223372036854297711")
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test(dependsOnMethods = "getPetByID")
    public void deletePet() {
        when()
                .delete("/pet/9223372036854297711")
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void createPet() {

    String requestBody = "{\n"+"  \n"+"  \"category\": {\n"+"    \"id\": 10,\n"+"    \"name\": \"HUSKY\"\n"+"  },\n"+"  \"name\": \"Candy-Dog\",\n"+"  \"photoUrls\": [\n"+"    \"random\"\n"+"  ],\n"+"  \"tags\": [\n"+"    {\n"+"      \"id\": 131,\n"+"      \"name\": \"string\"\n"+"    }\n"+"  ],\n"+"  \"status\": \"pending\"\n"+"}";

        Response response = given()
                .body(requestBody).when()
                .post("/pet");

        long pet_id = response.path("id");
        System.out.println("Pet created with ID : " + pet_id);

    }

}
