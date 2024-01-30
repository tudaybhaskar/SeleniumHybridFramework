package com.tests.selenium.optimizedTests;

import com.app.selenium.dataObjects.Product;
import com.app.selenium.dataObjects.Products;
import com.app.selenium.pages.FeaturedProductPage;
import com.app.selenium.pages.HomePage;
import com.app.selenium.pages.StorePage;
import com.app.selenium.utils.JacksonUtils;
import com.tests.selenium.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class NavigationTest extends BaseTest {

    @Test()
    public void NavigationFromHomeToStoreUSingMainMenu(){
        HomePage homePage = new HomePage(getDriver());
        StorePage storePage = homePage.load().getMyHeader().navigateToStoreUsingMenu();
        Assert.assertTrue(storePage.isPageLoaded_UsingElementPresent());
    }

    @Test
    public void NavigateFromStoreToProductPage() throws IOException {
        Product product = new Product(1215);
        String productName = product.getName();
        StorePage storePage = new HomePage(getDriver())
                .load()
                .getMyHeader().navigateToStoreUsingMenu();

        FeaturedProductPage featuredProductPage = storePage.clickOnProduct(productName);
        featuredProductPage.isURLLoaded(productName);
        Assert.assertEquals(featuredProductPage.getProductTitle(), productName);
    }

    @Test
    public void NavigateFromHomeToFeaturedProductPage() throws IOException {
        Product product = new Product(1215);
        HomePage homePage = new HomePage(getDriver());
        String FeatureProductTitle = homePage.load().getMyHeader().clickOnProduct(product.getName()).getProductTitle();
        Assert.assertEquals(FeatureProductTitle,product.getName());
    }
}
