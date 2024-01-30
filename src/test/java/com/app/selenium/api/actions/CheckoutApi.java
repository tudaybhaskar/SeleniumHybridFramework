package com.app.selenium.api.actions;

import io.restassured.RestAssured;
import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

import java.util.HashMap;

public class CheckoutApi {

    private Cookies cookies;

    public Cookies getCookies(){
        return cookies;
    }

    public CheckoutApi(){

    }

    public CheckoutApi(Cookies cookies){
        this.cookies = cookies;
    }

    public Response proceedToCheckout(){
        Header header = new Header("content-type","application/x-www-form-urlencoded");
        Headers headers = new Headers(header);

        if( cookies == null){
            cookies = new Cookies();
        }

        Response response = RestAssured.given()
                .cookies(cookies)
                .log().all()
                .when()
                .get("/checkout")
                .then()
                .log().all()
                .extract().response();

        if( response.getStatusCode() != 200){
            throw new RuntimeException(" Unable to proceed to Checkout via API " + response.getStatusCode());
        }
        cookies = response.getDetailedCookies();
        return response;

    }
}
