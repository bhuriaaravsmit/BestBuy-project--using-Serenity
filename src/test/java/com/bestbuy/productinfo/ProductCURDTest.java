package com.bestbuy.productinfo;

import com.bestbuy.constants.EndPoints1;
import com.bestbuy.model.ProductPojo;
import com.bestbuy.testbase.TestBase1;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.serenitybdd.rest.RestRequests;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Title;
import org.junit.Test;

import static io.restassured.RestAssured.given;

//@RunWith(SerenityRunner.class)
public class ProductCURDTest extends TestBase1 {


    static String name = "ball";
    static String type = "Hard Good";
    static String upc = "12345676";
    static double price = 99.99;
    static String description = "This is a placeholder request for creating a new product.";

    static String model = "NP12345";

    static int id = 9999680;
    


    @Title("This will create a new product")
    @Test
    public void createProduct() {
        ProductPojo productPojo = new ProductPojo();
        productPojo.setName(name);
        productPojo.setType(type);
        productPojo.setUpc(upc);
        productPojo.setPrice(price);
        productPojo.setDescription(description);
        productPojo.setModel(model);

        SerenityRest.given()
                .contentType(ContentType.JSON)
                .header("Accept", "application/json")
                .when()
                .body(productPojo)
                .post()
                .then().log().body().statusCode(201);
    }


    @Title("This will get  product name and description")
    @Test
    public void getProductNameAndDescription() {

        Response response = given().queryParam("$select[]", "name").queryParam("$select[]","description")
                .basePath("/products")
                .header("Content-Type", "application/json")
                .when()
                .get();
        response.then().statusCode(200);
        response.prettyPrint();

    }
    @Title("This will get  price will give you maximum price")
    @Test
    public void getMaximumPrice() {

        Response response = given().queryParam("$sort[price]", "-1")
                .basePath("/products")
                .header("Content-Type", "application/json")
                .when()
                .get();
        response.then().statusCode(200);
        response.prettyPrint();

    }


    @Title("Update the user Information and verify the updated information")
    @Test
    public void updateProduct() {

        ProductPojo productPojo = new ProductPojo();
        productPojo.setName(name);
        productPojo.setType(type);
        productPojo.setUpc(upc);
        productPojo.setPrice(price);
        productPojo.setDescription(description);
        productPojo.setModel(model);

        SerenityRest.given()
                .header("Content-Type", "application/json")
                .pathParams("id", id)
                .body(productPojo)
                .when()
                .patch(EndPoints1.UPDATE_Product_BY_id)
                .then().log().all().statusCode(200);

    }

    @Test
    public void deleteProduct() {
        RestRequests.given()
                .pathParam("id", id)
                .when()
                .delete(EndPoints1.DELETE_Product_BY_ID)
                .then()
                .statusCode(200);

        RestRequests.given()
                .pathParam("id", id)
                .when()
                .get(EndPoints1.DELETE_Product_BY_ID)
                .then().statusCode(404);


    }
}