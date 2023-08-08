package com.bestbuy.storeinfo;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.model.StorePojo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import static net.serenitybdd.rest.RestRequests.given;

public class StoreSteps {

    @Step("Creating student with name : {0},type : {1}, address : {2}, address2 : {3} city : {4}, state : {5}, zip : {7},lat :{8},lng : {9},hours :{10}")
    public ValidatableResponse createStore(String name, String type ,String address, String address2 ,String city,String state ,String zip,double lat,double lng,String hours){
        StorePojo storepojo= new StorePojo();
        storepojo.setName(name);
        storepojo.setType(type);
        storepojo.setAddress(address);
        storepojo.setAddress2(address2);
        storepojo.setCity(city);
        storepojo.setState(state);
        storepojo.setZip(zip);
        storepojo.setLat(lat);
        storepojo.setLng(lng);
        storepojo.setHours(hours);

        return SerenityRest.given()
                .contentType(ContentType.JSON)
                .when()
                .body(storepojo)
                .post()
                .then();
    }

   @Step("Getting all store data")
   public void getAllStoreInfo() {
       Response response = SerenityRest.given()
               .when()
               .get();
       response.then().statusCode(200);
       response.prettyPrint();
   }

    @Step("updating store with name : {0},type : {1}, address : {2}, address2 : {3} city : {4}, state : {5}, zip : {7},lat :{8},lng : {9},hours :{10}")
    public ValidatableResponse updateStore(String name, String type ,String address, String address2 ,String city,String state ,String zip,double lat,double lng,String hours){
        int id=8939;
        StorePojo storepojo= new StorePojo();
        storepojo.setName(name);
        storepojo.setType(type);
        storepojo.setAddress(address);
        storepojo.setAddress2(address2);
        storepojo.setCity(city);
        storepojo.setState(state);
        storepojo.setZip(zip);
        storepojo.setLat(lat);
        storepojo.setLng(lng);
        storepojo.setHours(hours);

        return SerenityRest.given()
                .header("Content-Type", "application/json")
                .pathParam("id",id)
                .body(storepojo)
                .patch(EndPoints.UPDATE_STORE_BY_id)
                .then().log().all().statusCode(200);
    }
    public void deleteId(){
        int id = 8939;
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





