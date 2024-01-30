package com.tests.selenium.tests.appTests;

import com.app.selenium.api.actions.CartApi;
import com.app.selenium.api.actions.CheckoutApi;
import com.app.selenium.constants.ProductType;
import com.app.selenium.dataObjects.BillingAddress;
import com.app.selenium.dataObjects.Product;
import com.app.selenium.pages.CartPage;
import com.app.selenium.pages.CheckoutPage;
import com.app.selenium.pages.FeaturedProductPage;
import com.app.selenium.pages.HomePage;
import com.app.selenium.utils.ConfigLoader;
import com.app.selenium.utils.CookieUtils;
import com.app.selenium.utils.FakerUtils;
import com.app.selenium.utils.JacksonUtils;
import com.tests.selenium.base.BaseTest;
import io.qameta.allure.Description;
import io.restassured.http.Cookies;
import io.restassured.response.Response;
import org.openqa.selenium.Cookie;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class CheckoutTest extends BaseTest {

    @Description("Allure Description: Guest checkout using Direct Bank transfer" )
    @Test(description = "Testng Description: Guest Checkout using Direct Bank Transfer")
    public void guestCheckoutUsingDirectBankTransfer() throws IOException {

        /*
        Guest Details :
         */
        BillingAddress billingAddress = JacksonUtils.deserializeJson("myBillingAddress.json", BillingAddress.class);
        FakerUtils fakerUtils = new FakerUtils();
        Map<String, String> guestUser = fakerUtils.generateGuestUser();
        billingAddress.setFirstName(guestUser.get("firstName"));
        billingAddress.setLastName(guestUser.get("lastName"));
        billingAddress.setEmail(guestUser.get("email"));

        HomePage homePage = new HomePage(getDriver());
        homePage.load("/");
        Product product = new Product(1196);
        String productName = product.getName();
        FeaturedProductPage featuredProductPage = homePage.clickOnProduct(ProductType.BLUETSHIRT.getProductLink());
        Assert.assertTrue(featuredProductPage.isURLLoaded(ProductType.BLUETSHIRT.getProductLink()));

        featuredProductPage.addToCart();
        CartPage cartPage = featuredProductPage.viewCart();
        Assert.assertEquals(cartPage.getProductName(), productName);

        CheckoutPage checkoutPage = cartPage.clickProceedToCheckout();
        checkoutPage.setBillingAddressUsingBuilderPatter(billingAddress);

        checkoutPage.clickPlaceOrder();
        checkoutPage.getOrderNotice();
        checkoutPage.assertOrderIsPlaced();

    }

    @Description("Allure Description: Guest checkout using Direct Bank transfer - API data Setup" )
    @Test(description = "Testng Description: Guest Checkout using Direct Bank Transfer - API data Setup")
    public void guestCheckoutUsingDirectBankTransfer_API() throws IOException {
        BillingAddress billingAddress = JacksonUtils.deserializeJson("myBillingAddress.json", BillingAddress.class);
        FakerUtils fakerUtils = new FakerUtils();
        Map<String, String> guestUser = fakerUtils.generateGuestUser();
        billingAddress.setFirstName(guestUser.get("firstName"));
        billingAddress.setLastName(guestUser.get("lastName"));
        billingAddress.setEmail(guestUser.get("email"));

        Product product = new Product(1196);
        CartApi cartApi = new CartApi();
        Response response = cartApi.addToCart( product.getId(), 1);

        CheckoutApi checkoutApi = new CheckoutApi(cartApi.getCookies());
        Cookies cookies = checkoutApi.getCookies();

        CookieUtils cookieUtils = new CookieUtils();
        List<Cookie> seleniumCookies = cookieUtils.convertRestAssuredCookiesToSeleniumCookies(cookies);

        getDriver().get(ConfigLoader.getInstance().openBaseUrl());
        for ( Cookie cookie : seleniumCookies ){
            getDriver().manage().addCookie(cookie);
        }

        getDriver().navigate().refresh();
        CheckoutPage checkoutPage = new CheckoutPage(getDriver());
        checkoutPage.load();
        checkoutPage.setBillingAddressUsingBuilderPatter(billingAddress)
                .clickPlaceOrder()
                .getOrderNotice();

        checkoutPage.assertOrderIsPlaced();

    }


}
