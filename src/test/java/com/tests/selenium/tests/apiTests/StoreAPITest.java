package com.tests.selenium.tests.apiTests;

import com.app.selenium.api.actions.StoreApi;
import com.tests.selenium.base.BaseTest;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class StoreAPITest extends BaseTest {

    @Test
    public void verifyUserInStorePage_UsingAPI(){
        StoreApi storeApi = new StoreApi();
        Response response = storeApi.navigateToStore_API();
        System.out.println(response.body().prettyPrint());
    }
}
