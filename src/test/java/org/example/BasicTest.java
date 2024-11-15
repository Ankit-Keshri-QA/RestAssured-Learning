package org.example;

import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class BasicTest {

    @Test
    public void testME() {
        Assert.assertEquals(1, 1);
    }

    @Test
    public void verifyPostalCode() {
        given().baseUri("http://api.zippopotam.us").
                when()
                .get("/us/90210")
                .then().assertThat().statusCode(200);
    }

}
