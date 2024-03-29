package com.app.selenium.pages;

import com.app.selenium.basePage.BasePage;
import com.app.selenium.pages.components.MyHeader;
import com.app.selenium.pages.components.ProductThumbnail;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {

    private MyHeader myHeader;
    private ProductThumbnail productThumbnail;
    public HomePage(WebDriver driver){
        super(driver);
        myHeader = new MyHeader(driver);
        productThumbnail = new ProductThumbnail(driver);
    }

    public HomePage load(){
        load("/");
        return this;
    }

    public boolean isLoaded(){
        return waitFor5Seconds.until(ExpectedConditions.urlContains("askomdch.com"));
    }

    public MyHeader getMyHeader() {
        return myHeader;
    }

    public ProductThumbnail getProductThumbnail() {
        return productThumbnail;
    }


}
