package com.app.selenium.api.actions;

import com.app.selenium.dataObjects.User;
import com.app.selenium.utils.ConfigLoader;
import io.restassured.RestAssured;
import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class SignUpAPI {
    private Cookies cookies;

    public Cookies getCookies(){
        return  cookies;
    }

    public String fetchRegisterNonceValueUsingGroovy(){
        Response response = getAccount();
        String registerNonceValue = response.htmlPath()
                .getString("**.findAll { it.name == 'woocommerce-register-nonce' }.@value");
        return registerNonceValue;
    }

    public String fetchRegisterNonceValueUsingJsoup(){
        Response response = getAccount();
        Document document = Jsoup.parse(response.body().prettyPrint());
        Element element = document.selectFirst("#woocommerce-register-nonce");
        return element.attr("value");
    }

    public String fetchLoginNonceValueUsingJsoup(){
        Response response = getAccount();
        Document document = Jsoup.parse(response.body().prettyPrint());
        Element element = document.selectFirst("#woocommerce-login-nonce");
        return element.attr("value");
    }


    public Response getAccount(){
        Cookies cookies = new Cookies();
        Response response = given()
                .baseUri(ConfigLoader.getInstance().openBaseUrl())
                .cookies(cookies)
                .log().all().
                when()
                .get("/account")
                .then()
                .log().all()
                .extract().response();
        this.cookies = response.getDetailedCookies();
        return response;

    }

    public Response registerUser(User user){
        Cookies cookies = new Cookies();
        Header header = new Header("content-type","application/x-www-form-urlencoded");
        Headers headers = new Headers(header);
        Map<String, String> formParams = new HashMap<>();
        formParams.put("username", user.getUserName());
        formParams.put("password", user.getPassword());
        formParams.put("email", user.getEmail());
        formParams.put("woocommerce-register-nonce", fetchRegisterNonceValueUsingJsoup());
        formParams.put("register", "Register");

        Response response = RestAssured.given().baseUri(ConfigLoader.getInstance().openBaseUrl())
                .headers(headers)
                .formParams(formParams)
                .cookies(cookies)
                .log().all()
                .when()
                .post("/account")
                .then().log().all()
                .extract().response();

        if(response.getStatusCode() != 302){
            throw new RuntimeException("Failed to Register the Account, HTTP Status code : " + response.getStatusCode());
        }
        this.cookies = response.getDetailedCookies();
        return response;
    }

    public Response loginUser(User user){
        Cookies cookies = new Cookies();
        Header header = new Header("content-type","application/x-www-form-urlencoded");
        Headers headers = new Headers(header);
        Map<String, String> formParams = new HashMap<>();
        formParams.put("username", user.getUserName());
        formParams.put("password", user.getPassword());
        formParams.put("woocommerce-login-nonce", fetchLoginNonceValueUsingJsoup());
        formParams.put("login", "Log in");

        Response response =  RestAssured.given()
                .baseUri(ConfigLoader.getInstance().openBaseUrl())
                .headers(headers)
                .formParams(formParams)
                .cookies(cookies)
                .log().all()
                .when()
                .post("/account")
                .then()
                .log().all()
                .extract().response();

        if(response.getStatusCode()!= 302){
            throw new RuntimeException("User Not found:"+ response.getStatusCode());
        }
        this.cookies = response.getDetailedCookies();
        return response;
    }
}
