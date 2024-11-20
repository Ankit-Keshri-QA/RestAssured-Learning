package org.example.day3;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import model.Category;
import model.Pet;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

public class Serialization_Deserialization_Test {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://petstore.swagger.io";
        RestAssured.basePath = "/v2";
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setContentType("application/json").build();
    }

    @Test
    public void createPet1() {

        String requestBody = "{\n" + "  \n" + "  \"category\": {\n" + "    \"id\": 10,\n" + "    \"name\": \"HUSKY\"\n" + "  },\n" + "  \"name\": \"Candy-Dog\",\n" + "  \"photoUrls\": [\n" + "    \"random\"\n" + "  ],\n" + "  \"tags\": [\n" + "    {\n" + "      \"id\": 131,\n" + "      \"name\": \"string\"\n" + "    }\n" + "  ],\n" + "  \"status\": \"pending\"\n" + "}";

        Response response = given()
                .body(requestBody).when()
                .post("/pet");

        long pet_id = response.path("id");
        System.out.println("Pet created with ID : " + pet_id);

    }

    @Test
    public void createPet2() {

        Category cat = new Category(123, "BullDog");
        Pet pet = new Pet(356331, "Tester-Bull", "pending", cat);

        Response response = given()
                .body(pet).when()
                .post("/pet");

        int pet_id = response.path("id");
        System.out.println("Pet created with ID : " + pet_id);

    }

    @Test
    public void getPetAfterCreate() {
        Pet pet = get("/pet/567876567").as(Pet.class);
        System.out.println(pet.getName());
        System.out.println(pet.getStatus());
    }

}
