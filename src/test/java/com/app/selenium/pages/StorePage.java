package com.app.selenium.pages;

import com.app.selenium.basePage.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class StorePage extends BasePage {

    private final By searchField = By.cssSelector("#woocommerce-product-search-field-0");
    private final By searchButton = By.cssSelector("button[value='Search']");
    private final By searchResultsTitle = By.cssSelector(".woocommerce-products-header__title.page-title");
    private final By viewCartLink =By.cssSelector("a[title='View cart']");
    private final By addToCart = By.cssSelector("a[aria-label='Add “Blue Shoes” to your cart']");

    private final By pageTitle = By.cssSelector(".woocommerce-products-header__title.page-title");
    //In the above cssSelector , we are mentioning the Blue Shoes as product name. Which means we are not making it dynamic
    // and flexible enough to use it for another product. So, we have make this dynamic.
    public StorePage(WebDriver driver) {
        super(driver);
    }

    public void load(){
        driver.get("/store");
    }

    public boolean isURLLoaded(){
        return waitFor5Seconds.until(ExpectedConditions.urlContains("askomdch.com/store/"));
    }

    public boolean isPageLoaded_UsingElementPresent(){
        return waitFor5Seconds.until(ExpectedConditions.textToBe(pageTitle, "Store"));
    }

    public StorePage enterTextInSearchField(String searchText){
        driver.findElement(searchField).sendKeys(searchText);
        return this;
    }

    public StorePage clickOnSearchButton(){
        driver.findElement(searchButton).click();
        return this;
    }

    public String getSearchResultsTitle(){
        return driver.findElement(searchResultsTitle).getText();
    }

    //making the addToCart button dynamic to use it for any other product as well.
    private By getAddToCartElement(String productName){
        return By.cssSelector("a[aria-label='Add “"+ productName+ "” to your cart']");
    }

    public StorePage clickAddToCart(String productName){
       // driver.findElement(addToCart).click();
        By addToCart = getAddToCartElement(productName);
        driver.findElement(addToCart).click();
        return this;
    }

    public CartPage clickViewCart(){
        driver.findElement(viewCartLink).click();
        return new CartPage(driver);
    }

    public StorePage search(String searchText){
        driver.findElement(searchField).sendKeys(searchText);
        driver.findElement(searchButton).click();
        return this;
    }


}
