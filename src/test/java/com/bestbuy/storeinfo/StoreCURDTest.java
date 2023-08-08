package com.bestbuy.storeinfo;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.model.StorePojo;
import com.bestbuy.testbase.TestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import static net.serenitybdd.rest.RestRequests.given;
@RunWith(SerenityRunner.class)
public class StoreCURDTest extends TestBase {
    static String name="New Store";
    static String type="BigBox";
    static String address="123 Fake St";
    static String address2="";
    static String city="Springfield";
    static String state="MN";
    static String zip="55123";
    static double lat=44.969658;
    static  double lng=-93.449539;
    static String hours="Mon: 10-9; Tue: 10-9; Wed: 10-9; Thurs: 10-9; Fri: 10-9; Sat: 10-9; Sun: 10-8";
    static int id=8931;

 static String serviceName="Apple Shop";
    @Test
    public void getAllStoreInfo() {
        Response response = SerenityRest.given()
                .when()
                .get();
        response.then().statusCode(200);
        response.prettyPrint();
    }

@Title("This will create a new store")
    @Test
    public void createStore(){
    StorePojo storePojo = new StorePojo();
    storePojo.setName(name);
    storePojo.setType(type);
    storePojo.setAddress(address);
    storePojo.setAddress2(address2);
    storePojo.setCity(city);
    storePojo.setState(state);
    storePojo.setZip(zip);
    storePojo.setLat(lat);
    storePojo.setLng(lng);
    storePojo.setHours(hours);

    SerenityRest. given()
            .contentType(ContentType.JSON)
            .header("Accept", "application/json")
            .when()
            .body(storePojo)
            .post()
            .then().log().body().statusCode(201);
    }
    @Test
    public void getId(){
        int sId=given()
                .pathParams("id",id)
                .when()
                .get(EndPoints.GET_SINGLE_STORE_BY_name)
                .then().statusCode(200)
                .extract()
                .path("id");
        System.out.println("value of ID"+sId);
        Assert.assertEquals(sId,id);
    }


    @Test
    public void findStores(){
        int sId=given()
                .queryParam("state",state)
                .when()
                .get()
                .then().statusCode(200)
                .extract()
               .path("total");
      //  System.out.println("value of ID"+sId);
        Assert.assertEquals(sId,54);
    }

    @Test
    public void findStroresWithServiceName(){
        int sId=given()
                .queryParam("service.name",serviceName)
                .when()
                .get()
                .then().statusCode(200)
                .extract()
                .path("total");
        System.out.println("value of ID"+sId);
        Assert.assertEquals(sId,756);
    }
    @Title("Update the user Information and verify the updated information")
    @Test
    public void updateID(){
        StorePojo storePojo=new StorePojo();
        storePojo.setName("New Store");
        storePojo.setType(type);
        storePojo.setAddress(address);
        storePojo.setAddress2(address2);
        storePojo.setCity(city);
        storePojo.setState(state);
        storePojo.setZip(zip);
        storePojo.setLat(lat);
        storePojo.setLng(lng);
        storePojo.setHours("Mon: 10-9; Tue: 10-9; Wed: 10-9; Thurs: 10-9; Fri: 10-9; Sat: 10-9; Sun: 10-8");
        SerenityRest.given()
                .header("Content-Type", "application/json")
                .pathParams("id", id)
                .body(storePojo)
                .when()
                .patch(EndPoints.UPDATE_STORE_BY_id)
                .then().log().all().statusCode(200);

    }
    @Test
    public void deleteId(){
        given()
                .pathParam("id", id)
                .when()
                .delete(EndPoints.DELETE_STORE_BY_ID)
                .then()
                .statusCode(200);

        given()
                .pathParam("id", id)
                .when()
                .get(EndPoints.DELETE_STORE_BY_ID)
                .then().statusCode(404);
    }
}


