package com.bestbuy.productinfo;

import com.bestbuy.testbase.TestBase1;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import org.junit.Test;

public class FirstSerenityTest extends TestBase1 {

    @Test
    public void getAllProductInfo() {
        Response response = SerenityRest.given()
                .when()
                .get();
        response.then().statusCode(200);
        response.prettyPrint();
    }
}
