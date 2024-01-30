package com.app.selenium.api.actions;

import com.app.selenium.utils.ConfigLoader;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class StoreApi {

    public Response navigateToStore_API(){
        Response  response = RestAssured.given()
                .baseUri(ConfigLoader.getInstance().openBaseUrl())
                .log().all()
                .when()
                .get("/cart")
                .then()
                .log().all()
                .extract().response();

        return  response;
    }
}
