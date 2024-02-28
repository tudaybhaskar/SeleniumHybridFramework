package com.app.selenium.pages.components;

import com.app.selenium.basePage.BasePage;
import com.app.selenium.pages.CartPage;
import com.app.selenium.pages.StorePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductThumbnail extends BasePage {

    private final By viewCartLink =By.cssSelector("a[title='View cart']");
    public ProductThumbnail(WebDriver driver) {
        super(driver);
    }

    private By getAddToCartElement(String productName){
        return By.cssSelector("a[aria-label='Add “"+ productName+ "” to your cart']");
    }

    public ProductThumbnail clickAddToCart(String productName){
        // driver.findElement(addToCart).click();
        By addToCart = getAddToCartElement(productName);
        driver.findElement(addToCart).click();
        return this;
    }

    public CartPage clickViewCart(){
        waitFor5Seconds.until(ExpectedConditions.visibilityOfElementLocated(viewCartLink)).click();
        // driver.findElement(viewCartLink).click();
        return new CartPage(driver);
    }
}
