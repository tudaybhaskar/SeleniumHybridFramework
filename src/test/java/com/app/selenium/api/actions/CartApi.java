package com.app.selenium.api.actions;

import com.app.selenium.utils.ConfigLoader;
import io.restassured.RestAssured;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class CartApi {

    private Cookies cookies;
    /*
    This default constructor is used when the user is not logged in
     */
    public CartApi(){

    }
    /* This constructor is used when the user is logged in .
     * Either we use login cookies or register cookies
     */
    public CartApi(Cookies cookies){
        this.cookies = cookies;
    }

    public Cookies getCookies(){
        return cookies;
    }

    public Response addToCart(int productId, int quantity){
        Header header = new Header("content-type","application/x-www-form-urlencoded");
        Headers headers = new Headers(header);

        HashMap<String, Object> formParams = new HashMap<>();
        formParams.put("product_sku", "");
        formParams.put("product_id", productId);
        formParams.put("quantity", quantity);
        /*
            We are sending form params as it is form-based authentication.No need to send in body.
            But again this is as per implementation in API regarding the Request Payload.
         */

        if(cookies == null){
            cookies = new Cookies();
        }

        Response response = RestAssured.given()
                .baseUri(ConfigLoader.getInstance().openBaseUrl())
                .headers(headers)
                .cookies(cookies)
                .formParams(formParams)// be cautious in using formParams .formParams has parameter to pass is Map.
                .log().all()
                .when()
                .post("/?wc-ajax=add_to_cart")
                .then()
                .log().all()
                .extract().response();

        if( response.getStatusCode() != 200){
            throw new RuntimeException("Unable to Add Product to Cart via API:"+response.getStatusCode());
        }
        cookies = response.getDetailedCookies();
        return response;
    }
}
