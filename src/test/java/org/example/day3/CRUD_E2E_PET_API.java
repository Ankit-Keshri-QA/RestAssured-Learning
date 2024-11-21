package org.example.day3;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.equalTo;

public class CRUD_E2E_PET_API {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://petstore.swagger.io";
        RestAssured.basePath = "/v2";
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setContentType("application/json").build();
    }

    public Map<String, Object> getPetData() {
        Map<String, Object> category = new HashMap<>();
        category.put("id", 4424);
        category.put("name", "Giddadd");

        Map<String, Object> pet = new HashMap<>();
        pet.put("name", "New-Dogter");
        pet.put("status", "pending");
        pet.put("category", category);

        return pet;
    }

    @Test
    public void xyz_CRUD_E2E() {

        Map<String, Object> petMap = getPetData();

        // creating a new PET and storing ID in a new variable
        String newPetID = given().body(petMap).when().post("/pet").path("id").toString();

        // GET the created PET
        get("/pet/" + newPetID).then().assertThat().statusCode(200)
                .and()
                .body("status", equalTo("pending"));

        // Update the PET status
        petMap.put("status", "available");
        petMap.put("id", newPetID);
        given().body(petMap).when().put("/pet").then().statusCode(200);

        // GET the PET status after update
        get("/pet/" + newPetID).then().statusCode(200)
                .and()
                .body("status", equalTo("available"));

        // Delete the PET
        delete("/pet/" + newPetID).then().statusCode(200);

        // Get the deleted PET and verifying PET is deleted
        get("/pet/" + newPetID).then().statusCode(404);
    }
}
