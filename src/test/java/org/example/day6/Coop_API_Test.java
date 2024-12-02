package org.example.day6;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Coop_API_Test {

    @Test
    public void requestWithoutOAuth() {
        given()
                .when()
                .post("https://coops.apps.symfonycasts.com/api/878/chickens-feed")
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void requestWithoutOAuth2() {
        Response response = given()
                .when()
                .post("https://coops.apps.symfonycasts.com/api/878/chickens-feed");

        System.out.println(response.getBody().prettyPrint());

    }

    @Test
    public void requestWithOAuth() {
        given()
                .when()
                .auth()
                .oauth2("ksdubksbufkbqaek1232kjbrwfksdbvk")
                .post("https://coops.apps.symfonycasts.com/api/878/chickens-feed")
                .then()
                .statusCode(200);
    }

    @Test
    public void outOfScopeEndpointTest() {
        Response response = given()
                .when()
                .auth()
                .oauth2("ksdubksbufkbqaek1232kjbrwfksdbvk")
                .post("https://coops.apps.symfonycasts.com/api/878/egg-collect");

        System.out.println(response.getBody().asString());

    }

    @Test
    public void generateAccessToken() {
        // Step 1: Fetch the access token
        Response tokenResponse = given()
                .formParam("client_id", "api-testing-ep")
                .formParam("client_secret", "YOUR_CLIENT_SECRET")
                .formParam("grant_type", "client_credentials")
                .post("http://coop.apps.symfonycasts.com/token");

        // Step 2: Check if token request was successful
        if (tokenResponse.getStatusCode() != 200) {
            System.out.println("Failed to fetch token. Response: " + tokenResponse.getBody().prettyPrint());
            return;
        }

        // Step 3: Extract the access token
        String accessToken = tokenResponse.path("access_token");
        if (accessToken == null) {
            System.out.println("Access token not retrieved. Check your token request.");
            return;
        }

        // Step 4: Use the token to make an API request
        Response response = given()
                .auth()
                .oauth2(accessToken)
                .post("http://coop.apps.symfonycasts.com/api/878/chickens-feed");

        // Step 5: Check if the API request was successful
        if (response.getStatusCode() != 200) {
            System.out.println("API call failed. Response: " + response.getBody().prettyPrint());
            return;
        }

        // Step 6: Print the response
        System.out.println("API Response:");
        System.out.println(response.getBody().prettyPrint());
    }

    @Test
    public void coopTestDynamic() {
        Response tokenResponse = given()
                .formParam("client_id", "api-testing-epam")
                .formParam("client_secret", "63e8e509849c984c4806f58f451adb97")
                .formParam("grant_type", "client_credentials")
                .post("http://coop.apps.symfonycasts.com/token");

        System.out.println("Token response is: " + tokenResponse.getBody().prettyPrint());

        String accessToken = tokenResponse.path("access_token");

        Response response = given()
                .auth()
                .oauth2(accessToken)
                .post("http://coop.apps.symfonycasts.com/api/878/chickens-feed");

        System.out.println("Actual OAuth2 response is: " + response.getBody().prettyPrint());

        // Assert.assertEquals(response.getStatusCode(), 200, "API call failed!");
    }


}


