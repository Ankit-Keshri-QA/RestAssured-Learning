package org.example.day6;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TwitterTest {

    @Test
    public void twitterOauth1Test(){
        Response response = given()
                .auth()
                .oauth("your-consumer-key", "your-consumer-secret",
                        "your-access-token", "your-access-token-secret")
                .when()
                .post("https://api.twitter.com/1.1/statuses/update.json?status=This is my tweet from API");

        System.out.println(response.getStatusCode());
        System.out.println(response.getBody().prettyPrint());

    }
}
