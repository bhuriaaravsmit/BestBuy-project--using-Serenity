package com.bestbuy.storeinfo;

import com.bestbuy.testbase.TestBase;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import org.junit.Test;

public class FirstSerenityTest extends TestBase {

    @Test
    public void getAllStoreInfo() {
        Response response = SerenityRest.given()
                .when()
                .get();
        response.then().statusCode(200);
        response.prettyPrint();
    }


}
