package org.example.practisePackage;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

public class Test4_DataDrivenTest {

    @BeforeTest
    public void setup() {
        RestAssured.baseURI = "http://api.zippopotam.us";
    }

    @Test(dataProvider = "getTestData")
    public void getResponse(String zipCode, String state) {
        when()
                .get("/" + zipCode)
                .then()
                .assertThat()
                .statusCode(200)
                .body("places[0].state", equalTo(state));
    }

    @DataProvider
    public Object[][] getTestData() {
        Object data[][] = {{"us/90210", "California"},
                {"us/12345", "New York"},
                {"de/24848", "Schleswig-Holstein"},
                {"ca/Y1A", "Yukon"}};

        return data;
    }

}
