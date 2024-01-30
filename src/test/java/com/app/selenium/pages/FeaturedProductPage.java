package com.app.selenium.pages;

import com.app.selenium.basePage.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class FeaturedProductPage extends BasePage {

    private final By productTitle = By.cssSelector(".product_title");
    public FeaturedProductPage(WebDriver driver) {
        super(driver);
    }

    public boolean isURLLoaded(String product){
        if(product.contains("Blue Shoes")){
            product = "blue-shoes";
        }
        return waitFor5Seconds.until(ExpectedConditions.urlContains("askomdch.com/product/"+product));
    }

    /*

    public FeaturedProductPage load(){
        load("/product/");
        return this;
    }
     */

    public String getProductTitle(){
        return driver.findElement(productTitle).getText();
    }
}
