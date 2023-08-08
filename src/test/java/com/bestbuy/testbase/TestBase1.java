package com.bestbuy.testbase;

import com.bestbuy.constants.Path1;
import com.bestbuy.utils.PropertyReader;
import io.restassured.RestAssured;
import org.junit.BeforeClass;

public class TestBase1 {
    @BeforeClass
    public static void init1() {
        PropertyReader propertyReader = PropertyReader.getInstance();
        RestAssured.baseURI = propertyReader.getProperty("baseUrl");
        RestAssured.port = Integer.parseInt(propertyReader.getProperty("port"));
        RestAssured.basePath = Path1.PRODUCT;

    } 
    
    
}
