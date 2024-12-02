package org.example.day6;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class CookieTest_JIRA {

    @Test
    public void generateCookieSessionID() {
        Map<String, String> data = new HashMap<>();
        data.put("username", "pm1");
        data.put("password", "project123");

        Response post = given().
                header("Content-Type", "application/json").
                body(data).
                post("https://jira-rmsis.optimizory.com/rest/auth/1/session");

        System.out.println(post.getStatusCode());
        System.out.println(post.getBody().prettyPrint());

        String jsessionid = post.getCookie("JSESSIONID");
        System.out.println(jsessionid);

        Response response = given().
                contentType("application/json").
                cookie("JSESSIONID", jsessionid).
                body("{\"fields\":{\"project\":{\"key\":\"TP\"},\"summary\":\"Sample Issue\",\"description\":\"Creating issue via API\",\"issuetype\":{\"name\":\"Bug\"}}}").
                post("https://jira-rmsis.optimizory.com/rest/api/2/issue/");

        System.out.println("new response " + response.getStatusCode());
        System.out.println(response.getBody().prettyPrint());
    }

}
