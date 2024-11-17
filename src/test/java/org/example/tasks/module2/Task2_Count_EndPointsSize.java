package org.example.tasks;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Task2_Count_EndPointsSize {

    @Test
    public void verifyPostsCount() {
        given().baseUri("https://jsonplaceholder.typicode.com")
                .when()
                .get("/posts")
                .then()
                .assertThat()
                .body("size()", equalTo(100));
    }

    @Test
    public void verifyCommentsCount() {
        given().baseUri("https://jsonplaceholder.typicode.com")
                .when()
                .get("/comments")
                .then()
                .assertThat()
                .body("size()", equalTo(500));
    }

    @Test
    public void verifyAlbumCount() {
        given().baseUri("https://jsonplaceholder.typicode.com")
                .when()
                .get("/albums")
                .then()
                .assertThat()
                .body("size()", equalTo(100));
    }

    @Test
    public void verifyPhotosCount() {
        given().baseUri("https://jsonplaceholder.typicode.com")
                .when()
                .get("/photos")
                .then()
                .assertThat()
                .body("size()", equalTo(5000));
    }

    @Test
    public void verifyTodosCount() {
        given().baseUri("https://jsonplaceholder.typicode.com")
                .when()
                .get("/todos")
                .then()
                .assertThat()
                .body("size()", equalTo(200));
    }

    @Test
    public void verifyUserCount() {
        given().baseUri("https://jsonplaceholder.typicode.com")
                .when()
                .get("/users")
                .then()
                .assertThat()
                .body("size()", equalTo(10));
    }

}
