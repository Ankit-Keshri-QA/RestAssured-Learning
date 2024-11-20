package org.example.day1;

import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

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

    @Test
    public void verifyPostalCode2() {
        // We can skip given() method and just use when and then methods to create the template
        when()
                .get("http://api.zippopotam.us/us/30301")
                .then()
                .assertThat().statusCode(200);
    }

    @Test
    public void verifyStatusCodeWithCoreJava() throws IOException {
        URL restEndpoint = new URL("http://api.zippopotam.us/us/96162");
        HttpURLConnection httpURLConnect = (HttpURLConnection) restEndpoint.openConnection();
        httpURLConnect.setRequestMethod("GET");

        int responseCode = httpURLConnect.getResponseCode();
        Assert.assertEquals(responseCode, 200);
    }



}
