package org.example.practisePackage;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.*;

public class Test1 {

    // Req-Res Website
    // Website URL on Swagger :- https://reqres.in/api-docs/


    // One way to write the code - Most Basic
    @Test(priority = 3)
    public void getUsersList(){
        given().baseUri("https://reqres.in")
                .basePath("/api")
                .contentType("application/json")
                .accept("*/*").
        when().get("/users").
        then().assertThat()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .header("Content-Type","application/json; charset=utf-8")
                .body("per_page",equalTo(6))
                .body("page",equalTo(1))
                .body("data",hasSize(6))
                .body("total",equalTo(12))
                .body("data[1].first_name",equalTo("Janet"))
                .body("data.last_name",hasItem("Ramos"));

    }

    // Other Way - We can skip given() as baseUri is already setup above
    @Test(priority = 2)
    public void getLimitedUserssonPage2(){
        RestAssured.baseURI = "https://reqres.in";
        RestAssured.basePath = "/api";

        given().queryParam("page",2)
                        .queryParam("per_page",4).
        when()
                .get("/users").
        then().assertThat()
                .statusCode(200)
                .body("page",equalTo(2))
                .body("per_page",equalTo(4))
                .body("data",hasSize(4));
    }

    // Skip given() completely , add complete web url inside get()
    @Test(priority = 4)
    public void skipGivenAndAddAllWithWhen(){
        when()
                .get("https://reqres.in/api/users")
                .then()
                .statusCode(200);
    }


    // Suppose the key inside response body has white space
    // Then use '' along with "" in body to make the code understand
    @Test(priority = 1)
    public void whiteSpaceInKeyOfResponseBody() {
        // Base URI
        RestAssured.baseURI = "http://api.zippopotam.us";

        // Send GET request and validate response fields
        when()
                .get("/us/90210")
                .then()
                .statusCode(200)
                .body("'post code'", equalTo("90210"))
                .body("places[0].'place name'", equalTo("Beverly Hills"))
                .body("'country abbreviation'", equalTo("US"));
    }

    // Monkey Testing
    @Test
    public void verifyNegativeTestCase() {
        given().baseUri("http://api.zippopotam.us")
                .when()
                .get("/us/99999")
                .then().assertThat().statusCode(404)
                .statusLine(containsString("Not Found"));


    }

}
