package com.app.selenium.pages;

import com.app.selenium.basePage.BasePage;
import com.app.selenium.dataObjects.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class CartPage extends BasePage {

    private final By productName = By.cssSelector("td.product-name a");
    private final By proceedToCheckoutButton = By.cssSelector(".checkout-button");

    JavascriptExecutor js = (JavascriptExecutor) driver;

    public CartPage(WebDriver driver){
        super(driver);
    }

    public String getProductName(){
        return driver.findElement(productName).getText();
    }

    public CheckoutPage clickProceedToCheckout(){
        driver.findElement(proceedToCheckoutButton).click();
        return new CheckoutPage(driver);
    }

    public void assertFeaturedProductInCart(Product product){
        Assert.assertEquals(this.getProductName(), product.getName());
    }

}
