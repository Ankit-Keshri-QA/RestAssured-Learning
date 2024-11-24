package org.example.day5;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PetSchemaTests {

    @Test
    public void validatePetJsonSchema() {
        given().baseUri("https://petstore.swagger.io")
                .basePath("/v2")
                .when()
                .get("/pet/9223372036854575000")
                .then()
                .assertThat()
                .statusCode(200);
               // .body(matchesJsonSchemaInClasspath("PetSchema.json"));
    }

}
