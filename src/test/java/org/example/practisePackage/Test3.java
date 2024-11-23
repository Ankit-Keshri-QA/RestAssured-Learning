package org.example.practisePackage;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class Test3 {

    // Leverage BeforeClass TestNG method in RestAssured
    // Since we know our baseUri and basePath, why dont we have a BeforeClass method define
    // So that all our TestNG test cases use the same baseUri
    //This BeforeClass method will execute first and then all test cases will use the same code written inside

    // We have define baseUri in this method
    @BeforeClass
    public void setUp() {
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBaseUri("http://api.zippopotam.us")
                .setAccept("*/*").build();

        RestAssured.responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();

        // Or we could have written
        // RestAssured.baseUri = "uri";
        // RestAssured.basePath ="/cdef/"

    }

    // No need of given() as it will be handled in above testNG
    @Test
    public void verifyResponse() {
        get("/us/90210")
                .then().assertThat()
                .body("country", equalTo("United States"));
    }

    @Test
    public void validateZipCodeDetailsUsingBody() {

        get("/us/90210")
                .then()
                .body("'post code'", equalTo("90210"))// Validate status code
                .body("country", equalTo("United States")) // Validate country
                .body("places[0].'place name'", equalTo("Beverly Hills")) // Validate place name
                .body("'country abbreviation'", equalTo("US")); // Validate state
    }
}
