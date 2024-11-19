package org.example.day2;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class DataDrivenTests {

    private final RequestSpecification res = new RequestSpecBuilder()
            .setBaseUri("http://api.zippopotam.us")
            .setContentType("application/json")
            .setAccept("*/*").build();

    private final ResponseSpecification resp = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectContentType("application/json")
            .expectResponseTime(lessThan(2000L))
            .build();

    @Test(dataProvider = "getZipCodeData")
    public void verifyStates(String country, String zipCode, String state) {
        given().
                spec(res).
                when()
                .get("/" + country + "/" + zipCode)
                .then().assertThat().spec(resp)
                .body("places[0].state", equalTo(state));
    }

    @DataProvider
    public Object[][] getZipCodeData() {
        return new Object[][]
                {{"us", "90210", "California"},
                        {"us", "12345", "New York"}
                , {"de", "24848", "Schleswig-Holstein"},
                        {"ca", "Y1A", "Yukon"}};
    }

}
