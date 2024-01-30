package com.tests.selenium.tests.apiTests;

import com.app.selenium.api.actions.SignUpAPI;
import com.app.selenium.dataObjects.User;
import com.app.selenium.pages.AccountPage;
import com.app.selenium.pages.HomePage;
import com.app.selenium.utils.ConfigLoader;
import com.app.selenium.utils.CookieUtils;
import com.app.selenium.utils.FakerUtils;
import com.tests.selenium.base.BaseTest;
import io.restassured.http.Cookies;
import io.restassured.response.Response;
import org.openqa.selenium.Cookie;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class ApiTrailTests extends BaseTest {

    @Test
    public void verifyAPITest(){
        SignUpAPI signUpAPI = new SignUpAPI();
        Response response = signUpAPI.getAccount();
        System.out.println(response.body().prettyPrint());
        System.out.println("cookies: " + signUpAPI.getCookies());
    }

    @Test
    public void verifyRegisterUser_UsingAPI(){
        FakerUtils fakerUtils = new FakerUtils();
        String userName = "testSelenium" + fakerUtils.generateRandomNumber();
        String password = "testSelenium";
        String email = userName + "@testmail.com";
        User user = new User();
        user.setUserName(userName).setPassword(password).setEmail(email);

        SignUpAPI signUpAPI = new SignUpAPI();
        Response response = signUpAPI.registerUser(user);
        System.out.println("Detailed Cookies : " + signUpAPI.getCookies());
    }

    @Test
    public void verifyLoginUser_UsingAPI(){
        User user = new User();
        String userName = "testSelenium419242";
        String password = "testSelenium";
        user.setUserName(userName).setPassword(password);

        SignUpAPI signUpAPI = new SignUpAPI();
        Response response = signUpAPI.loginUser(user);
        System.out.println("Detailed Cookies : " + signUpAPI.getCookies());
        System.out.println("Status Code : " + response.getStatusCode());
    }

    @Test
    public void verifyLoginUserWhenCookiesAreSetViaAPI(){
        User user = new User();
        String userName = "testSelenium419242";
        String password = "testSelenium";
        user.setUserName(userName).setPassword(password);

        SignUpAPI signUpAPI = new SignUpAPI();
        Response response = signUpAPI.loginUser(user);
        Cookies cookies = signUpAPI.getCookies();

        CookieUtils cookieUtils = new CookieUtils();
        List<Cookie> seleniumCookies = cookieUtils.convertRestAssuredCookiesToSeleniumCookies(cookies);

        getDriver().get(ConfigLoader.getInstance().openBaseUrl());
        for(Cookie cookie : seleniumCookies){
            getDriver().manage().addCookie(cookie);
        }
        getDriver().navigate().refresh();
        HomePage homePage = new HomePage(getDriver());

        AccountPage accountPage = homePage.getMyHeader().navigateToAccountUsingMenu();
        Assert.assertTrue(accountPage.validateLoginUser(user),"Not a API login User:" + user.getUserName());

    }

}
