package com.tests.selenium.tests.appTests;

import com.app.selenium.api.actions.CartApi;
import com.app.selenium.api.actions.SignUpAPI;
import com.app.selenium.dataObjects.BillingAddress;
import com.app.selenium.dataObjects.Product;
import com.app.selenium.dataObjects.User;
import com.app.selenium.pages.CheckoutPage;
import com.app.selenium.utils.ConfigLoader;
import com.app.selenium.utils.FakerUtils;
import com.app.selenium.utils.JacksonUtils;
import com.tests.selenium.base.BaseTest;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Map;

@Epic("Add Product to Cart")
@Feature("Add to Cart feature")
public class FirstTestCase extends BaseTest {

    @Story("Create user and Add product to cart")
    @Link("https://example.org")
    @Link(name="allure", type="mylink")
    @Issue("12345")
    @TmsLink("98987")
    @Description("Description from Allure: Add Blue Shoes to cart")
    @Test(description = "Testng description : Verify AddBlueShoes to Cart")
    public void verifyAddBlueShoesToCart() throws InterruptedException {
        getDriver().get(ConfigLoader.getInstance().openBaseUrl());
        getDriver().findElement(By.cssSelector("a[aria-label='Add “Blue Shoes” to your cart']")).click();
        Thread.sleep(2000);
        getDriver().findElement(By.cssSelector("a[title='View cart']")).isDisplayed();
        getDriver().findElement(By.cssSelector("a[title='View cart']")).click();
        /* td.product-name a" - reason for using this xpath is we need to get the text value of element irrespective
            product that get displayed there. So we should never use directly product name to find the element and get text of it.
            e.g: to write xpath like "//td/a[text()='Blue Shoes']" . Here we are specifying the product name .So , this is not a good approach.
        */
        Assert.assertEquals(getDriver().findElement(By.cssSelector("td.product-name a")).getText(), "Blue Shoes");

    }

    /*@Test
    public void addBlueShoesFromStoreToCart(){
        driver.get("https://askomdch.com/");
        HomePage homePage = new HomePage(driver);
        StorePage storePage = homePage.navigateToStoreUsingMenu();

        storePage.enterTextInSearchField("Blue");
        storePage.
                clickOnSearchButton()
                .clickOnSearchButton();
        Assert.assertEquals(storePage.getSearchResultsTitle(), "Search results: “Blue”");

        //storePage.clickAddToCart();
    }

    @Test
    public void addBlueShoesFromStoreToCart_UsingFunctionalPageObject(){
        driver.get("https://askomdch.com/");
        HomePage homePage = new HomePage(driver);
        StorePage storePage = homePage.navigateToStoreUsingMenu();

        storePage.search("Blue"); // Here we are using the search function created in StorePAge which
        //performs entering the searchText followed by clicking on Search Button. This approach can also be used
        //based on test cases we want to design. This might not be much helpful when designing the atomic negative test cases
        Assert.assertEquals(storePage.getSearchResultsTitle(), "Search results: “Blue”");

        storePage.clickAddToCart("Blue Shoes");
    }

    @Test
    public void verifyProductCheckout() throws InterruptedException {
        driver.get("https://askomdch.com/");
        HomePage homePage = new HomePage(driver);
        StorePage storePage = homePage.navigateToStoreUsingMenu();

        storePage.enterTextInSearchField("Blue");
        storePage.
                clickOnSearchButton()
                .clickOnSearchButton();
        Assert.assertEquals(storePage.getSearchResultsTitle(), "Search results: “Blue”");

        storePage
                .clickAddToCart("Blue Shoes");
        Thread.sleep(2000);

        CartPage cartPage = storePage.clickViewCart();

        Assert.assertEquals(cartPage.getProductName(),"Blue Shoes");
        Thread.sleep(2000);
        cartPage.clickProceedToCheckout();

    }
    */
    @Severity(SeverityLevel.BLOCKER)
    @Description("Description from Allure : Register User via API and Place order")
    @Test(description = "Register User(API) and Place Order")
    public void verifyProductCheckout_PlaceOrder() throws InterruptedException, IOException {
        BillingAddress billingAddress = JacksonUtils.deserializeJson("myBillingAddress.json", BillingAddress.class);
        //User user = JacksonUtils.deserializeJson("UserData.json", User.class);
        User user = new User();
        Map<String, String> map_FakeUser = new FakerUtils().generateUser();
        user.setUserName(map_FakeUser.get("username"))
                .setPassword(map_FakeUser.get("password"))
                        .setEmail(map_FakeUser.get("email"));
        billingAddress.setEmail(user.getEmail());//Setting email from faker API
        Product product = JacksonUtils.deserializeJson("products.json", Product.class);
        int productId = product.getId();
        int quantity = 1;
        SignUpAPI signUpAPI = new SignUpAPI();
        Response registerResponse = signUpAPI.registerUser(user);
        CartApi cartApi = new CartApi();
        cartApi.addToCart(productId, quantity);

        CheckoutPage checkoutPage = new CheckoutPage(getDriver());
        checkoutPage.load();

        injectCookiesToBrowser(cartApi.getCookies());//Injecting cookies to browser

        checkoutPage.load();
        checkoutPage.clickHereToLogin()
                .loginAtCheckoutPage(user);

        Assert.assertEquals(user.getEmail(), checkoutPage.getBillingEmailAddress());

        /* CartPage cartPage = storePage.clickViewCart();

        Assert.assertEquals(cartPage.getProductName(),"Blue Shoes");
        Thread.sleep(2000);
        CheckoutPage checkoutPage = cartPage.clickProceedToCheckout();

        checkoutPage.enterFirstName("test2")
                .enterLastName("last2")
                .enterAddressLine1("San Francisco")
                .enterCity("San Francisco")
                .enterZipCode("94188")
                .enterEmail("testemail@gmail.com");

        checkoutPage.setBillingAddress(billingAddress);
        Thread.sleep(2000);
        checkoutPage.clickPlaceOrder();

        Thread.sleep(5000);
        Assert.assertEquals(checkoutPage.getOrderNotice(), "Thank you. Your order has been received."); */
    }

    /*

    @Test
    public void verifyWaitStrategies(){
        HomePage homePage = new HomePage(driver);
        homePage.load();
        Assert.assertTrue(homePage.isLoaded(), "HomePage title error");
    }

    @Test
    public void verifyString(){
        System.out.println(100 + 100 + "Simplilearn");
        System.out.println("E-Learning Company" + 100 + 100);
    }*/

}
