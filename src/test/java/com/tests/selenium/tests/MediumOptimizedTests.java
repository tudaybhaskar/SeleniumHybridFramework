package com.tests.selenium.tests;

import com.app.selenium.pages.HomePage;
import com.app.selenium.pages.StorePage;
import com.app.selenium.utils.ConfigLoader;
import com.tests.selenium.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MediumOptimizedTests extends BaseTest {

    @Test
    public void navigateToStoreUsingMenu(){
        getDriver().get(ConfigLoader.getInstance().openBaseUrl());
        HomePage homePage = new HomePage(getDriver());
        homePage.load();
        StorePage storePage = homePage.getMyHeader().navigateToStoreUsingMenu();
        /* To verify Whether StorePage is loaded using URL contains. This is important to verify on page loading to avoid
         * issues that may cause in parallel execution or running in cloud.
         * Also, to make sure page is loaded and this gives confidence in proceeding to next actions to perform on that
         * page
         */
        Assert.assertTrue(storePage.isURLLoaded());
    }

    @Test
    public void verifyNavigateToStoreUsingMenu_ElementIsPresent(){
        getDriver().get(ConfigLoader.getInstance().openBaseUrl());
        HomePage homePage = new HomePage(getDriver());
        homePage.load();
        StorePage storePage = homePage.getMyHeader().navigateToStoreUsingMenu();
        Assert.assertTrue(storePage.isPageLoaded_UsingElementPresent());
    }

}
