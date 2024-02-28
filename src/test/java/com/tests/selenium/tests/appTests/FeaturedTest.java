package com.tests.selenium.tests.appTests;

import com.app.selenium.dataObjects.Product;
import com.app.selenium.dataProvider.MyDataProvider;
import com.app.selenium.pages.CartPage;
import com.app.selenium.pages.HomePage;
import com.app.selenium.pages.components.ProductThumbnail;
import com.tests.selenium.base.BaseTest;
import org.testng.annotations.Test;

public class FeaturedTest extends BaseTest {

    @Test(dataProvider = "getFeaturedProducts", dataProviderClass = MyDataProvider.class)
    public void verifyFeaturedProductsAddedToCart(Product product){
        HomePage homePage = new HomePage(getDriver());
        homePage.load("/");

        ProductThumbnail productThumbnail = homePage.getProductThumbnail();

        CartPage cartPage = productThumbnail.clickAddToCart(product.getName())
                .clickViewCart();

        cartPage.assertFeaturedProductInCart(product);
    }
}
