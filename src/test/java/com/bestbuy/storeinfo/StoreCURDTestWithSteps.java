package com.bestbuy.storeinfo;

import com.bestbuy.testbase.TestBase;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class StoreCURDTestWithSteps extends TestBase {
    static String name = "New Store";
    static String type = "BigBox";
    static String address = "123 Fake St";
    static String address2 = "";
    static String city = "Springfield";
    static String state = "MN";
    static String zip = "55123";
    static double lat = 44.969658;
    static double lng = -93.449539;
    static String hours = "Mon: 10-9; Tue: 10-9; Wed: 10-9; Thurs: 10-9; Fri: 10-9; Sat: 10-9; Sun: 10-8";
    // static int id=8931;
    @Steps
    StoreSteps storeSteps;

    @Title("This will create a new store")
    @Test
    public void test001() {

        storeSteps.createStore(name, type, address, address2, city, state, zip, lat, lng, hours).statusCode(201);
    }

    @Title("This will getting a store data")
    @Test
    public void test002() {

        storeSteps.getAllStoreInfo();
    }

    @Title("Update store")
    @Test
    public void test003() {

        storeSteps.updateStore(name, type, address, address2, city, state, zip, lat, lng, hours).statusCode(200);

    }

    @Title("Delete Store")
    @Test
    public void test004() {

storeSteps.deleteId();
    }

}